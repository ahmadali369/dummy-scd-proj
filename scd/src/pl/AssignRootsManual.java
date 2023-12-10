package pl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import bll.interfaces.IBLLFacade;
import opennlp.tools.stemmer.PorterStemmer;

public class AssignRootsManual extends JFrame {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;


	private PorterStemmer stemmer;

	private JComboBox<String> verseComboBox;
	private JList<String> rootsList;
	
	private JTextField selectedRootField;
	private List<String> selectedRootsList;

	int poem_idd;

	private IBLLFacade facadeBLL;

	public AssignRootsManual(IBLLFacade obj, int pid) {

        poem_idd = pid;
        this.facadeBLL = obj;

        stemmer = new PorterStemmer();

        verseComboBox = new JComboBox<>();
        loadVerses();

        rootsList = new JList<>();
        rootsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        selectedRootsList = new ArrayList<>();

        JButton suggestRootButton = new JButton("Suggest Root");
        suggestRootButton.addActionListener(e -> suggestRoot((String) verseComboBox.getSelectedItem()));

        JButton clearButton = new JButton("Clear Suggested Roots");
        clearButton.addActionListener(e -> clearSuggestedRoots());

        JButton assignRootButton = new JButton("Assign Root");
        assignRootButton.addActionListener(e -> assignRoot());

        JButton clearRootsButton = new JButton("Clear Roots");
        clearRootsButton.addActionListener(e -> clearRoots());


        JPanel versePanel = new JPanel(new FlowLayout());
        versePanel.add(new JLabel("Verse: "));
        versePanel.add(verseComboBox);


        JPanel suggestionPanel = new JPanel(new FlowLayout());
        suggestionPanel.add(suggestRootButton);
        suggestionPanel.add(clearButton);


        JPanel assignmentPanel = new JPanel(new FlowLayout());
        assignmentPanel.add(new JLabel("Roots: "));
        assignmentPanel.add(rootsList);
        assignmentPanel.add(assignRootButton);
        assignmentPanel.add(clearRootsButton);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(versePanel, BorderLayout.NORTH);
        mainPanel.add(suggestionPanel, BorderLayout.CENTER);
        mainPanel.add(assignmentPanel, BorderLayout.SOUTH);


        JPanel selectedRootPanel = new JPanel(new FlowLayout());
        selectedRootPanel.add(new JLabel("Selected Roots: "));
        selectedRootField = new JTextField(50);
        selectedRootField.setEditable(false);
        selectedRootPanel.add(selectedRootField);


        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.NORTH);
        add(selectedRootPanel, BorderLayout.SOUTH);

        setTitle("Root Assignment Manually");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        
        setSize(700, 500);

        setLocationRelativeTo(null);
        setVisible(true);
        
        
    }

	private void loadVerses() {

		List<Map<String, Object>> verses = facadeBLL.getAllVersesAndGenerateTokensAndRootsList(poem_idd);

		for (Map<String, Object> verse : verses) {
			String verseText = verse.get("misra1") + " " + verse.get("misra2");
			verseComboBox.addItem(verseText);
		}

	}

	private List<String> getAllVerses() {
		List<String> combinedVerses = new ArrayList<>();

		List<Map<String, Object>> verses = facadeBLL.getAllVersesAndGenerateTokensAndRootsList(poem_idd);
		for (Map<String, Object> verse : verses) {
			combinedVerses.add(verse.get("misra1") + " " + verse.get("misra2"));
		}

		return combinedVerses;
	}

	private void suggestRoot(String selectedVerse) {
		List<String> verses = getAllVerses();

		List<String> suggestedRoots = new ArrayList<>();
		for (String verse : verses) {
			if (verse.equals(selectedVerse)) {
				String[] words = verse.split("\\s+");
				for (String word : words) {
					suggestedRoots.add(stemmer.stem(word));
				}
			}
		}

		displaySuggestedRoots(suggestedRoots);
	}

	private void displaySuggestedRoots(List<String> suggestedRoots) {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String root : suggestedRoots) {
			listModel.addElement(root);
		}
		rootsList.setModel(listModel);
	}

	private void clearSuggestedRoots() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		rootsList.setModel(listModel);
	}

	private void assignRoot() {
		List<String> selectedRoots = rootsList.getSelectedValuesList();

		for (String selectedRoot : selectedRoots) {
			if (!selectedRootsList.contains(selectedRoot)) {
				selectedRootsList.add(selectedRoot);

				facadeBLL.updateRootStatus(selectedRoot, (String) verseComboBox.getSelectedItem());
			}
		}

		displaySelectedRoots();
	}

	private void displaySelectedRoots() {
		StringBuilder rootsText = new StringBuilder();
		for (String root : selectedRootsList) {
			rootsText.append(root).append(", ");
		}
		selectedRootField.setText(rootsText.toString());
	}

	private void clearRoots() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		rootsList.setModel(listModel);
	}

}
