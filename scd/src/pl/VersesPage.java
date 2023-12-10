package pl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.jcp.xml.dsig.internal.MacOutputStream;

import bll.interfaces.IBLLFacade;
import transferObjects.VerseTO;

public class VersesPage extends JFrame {

	private IBLLFacade facadeBLL;
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(VersesPage.class);

	private JPanel crudOperationPanel;
	private JPanel inputFieldsPanelForEditverse;

	private int tableRow;

	public static int poem_id;

	private String misra1, misra2;

	DefaultTableModel idmodel;

	public VersesPage(IBLLFacade obj) {

		this.facadeBLL = obj;

		JFrame frame = new JFrame();

		idmodel = new DefaultTableModel();

		frame.setSize(700, 500);
		frame.setTitle("Encyclopedia Of Arabic Poems");
		frame.setDefaultCloseOperation(VersesPage.HIDE_ON_CLOSE);

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenDimension.width - frame.getWidth()) / 2;
		int y = (screenDimension.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);

		crudOperationPanel = new JPanel();

		inputFieldsPanelForEditverse = new JPanel();

		JButton buttonManageRoots = new JButton("Manage Roots");
		JButton buttonRead = new JButton("Reload");
		JButton buttonToken = new JButton("Tokens");

		/// ======================

		JButton buttonEditverse = new JButton("Edit");

		crudOperationPanel.add(new JLabel("Verses-------------------------------"));
		crudOperationPanel.add(buttonRead);
		crudOperationPanel.add(buttonToken);
		crudOperationPanel.add(buttonManageRoots);

		frame.add(crudOperationPanel);

		JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		backButtonPanel.add(new JLabel("Create verse"));

		JTextField misra1TextFieldEditTemp = new JTextField(20);
		JTextField misra2TextFieldEditTemp = new JTextField(20);

		JTextField misra1NameTextFieldEdit = new JTextField(20);
		JTextField misra2NameTextFieldEdit = new JTextField(20);

		JButton buttonBackedit = new JButton("<-");

		JPanel backButtonPanelEdit = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonPanelEdit.add(buttonBackedit);
		backButtonPanelEdit.add(new JLabel("Edit Verse"));

		JPanel inputFieldsPanelHorizontalEdit = new JPanel(new GridLayout(0, 1));
		inputFieldsPanelHorizontalEdit.add(new JLabel("Update Misra 1: "));
		inputFieldsPanelHorizontalEdit.add(misra1NameTextFieldEdit);
		inputFieldsPanelHorizontalEdit.add(new JLabel("Update Misra 2: "));
		inputFieldsPanelHorizontalEdit.add(misra2NameTextFieldEdit);

		inputFieldsPanelForEditverse.setLayout(new BoxLayout(inputFieldsPanelForEditverse, BoxLayout.Y_AXIS));
		inputFieldsPanelForEditverse.setAlignmentY(TOP_ALIGNMENT);
		inputFieldsPanelForEditverse.add(backButtonPanelEdit);
		inputFieldsPanelForEditverse.add(inputFieldsPanelHorizontalEdit);
		inputFieldsPanelForEditverse.add(buttonEditverse);

		RootsPO rootsPO = new RootsPO(facadeBLL);
		buttonManageRoots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				rootsPO.setVisible(true);
				Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
				int x = (screenDimension.width - rootsPO.getWidth()) / 2;
				int y = (screenDimension.height - rootsPO.getHeight()) / 2;
				rootsPO.setLocation(x, y);

			}
		});

		buttonBackedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				crudOperationPanel.setVisible(true);
				inputFieldsPanelForEditverse.setVisible(false);

			}
		});

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(650, 400));
		crudOperationPanel.add(scrollPane);

		JButton deleteButton = new JButton("Delete Verse");
//		JButton ReadPoemsButton = new JButton("Open Poem");
		JButton editButton = new JButton("Edit Verse");
		JButton addversesButton = new JButton("Add Verse");

//		crudOperationPanel.add(ReadversesButton);
		crudOperationPanel.add(addversesButton);
		crudOperationPanel.add(editButton);
		crudOperationPanel.add(deleteButton);

		buttonRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Map<String, Object>> verses = facadeBLL.getAllVersesAndGenerateTokensAndRootsList(poem_id);

				if (verses.isEmpty()) {
					System.err.println("no verses found------------");

				} else {
					listPanel.removeAll();
					DefaultTableModel model = new DefaultTableModel();
//					DefaultTableModel idmodel = new DefaultTableModel();

					model.addColumn("Misra 1");
					model.addColumn("Misra 2");
					idmodel.addColumn("id");

					for (Map<String, Object> verse : verses) {
						model.addRow(new Object[] { verse.get("misra1"), verse.get("misra2") });
						idmodel.addRow(new Object[] { verse.get("verseId"), });

					}

					JTable table = new JTable(model);
					JTable idtable = new JTable(idmodel);
					JScrollPane scrollPane = new JScrollPane(table);

					table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							if (!e.getValueIsAdjusting()) {
								int selectedRow = table.getSelectedRow();

								if (selectedRow != -1) {
									tableRow = selectedRow;

//									verse_id = (int) idtable.getValueAt(tableRow, 0);
									misra1 = (String) table.getValueAt(tableRow, 0);
									misra2 = (String) table.getValueAt(tableRow, 1);

								}
							}
						}

					});

					listPanel.add(scrollPane);
					listPanel.revalidate();
					listPanel.repaint();

				}

			}

		});

		JButton buttonAssignRoots = new JButton("Manual Assign Roots");
		crudOperationPanel.add(buttonAssignRoots);

		buttonAssignRoots.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(() -> new AssignRootsManual(facadeBLL, poem_id));
			}
		});

		buttonToken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				facadeBLL.tokenRootProcessing(poem_id); 
				
				SwingUtilities.invokeLater(() -> new TokenizePage(facadeBLL).idVersesModel = idmodel);

			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				facadeBLL.deleteBook(title, author);

				JFrame frame = new JFrame("TextField Validation");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new GridLayout(3, 2));

				try {

					facadeBLL.deleteVerse(misra1, misra2);
					JOptionPane.showMessageDialog(frame, "Verse Deleted Sccessfully.. Please reload", "Success",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception e1) {
					logger.debug("deleteButton func triggerd an exception");
					JOptionPane.showMessageDialog(frame, "Something went wrong..", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}
		});

		// Create "Edit" button

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				crudOperationPanel.setVisible(false);
				inputFieldsPanelForEditverse.setVisible(true);

				misra1TextFieldEditTemp.setText(misra1);
				misra2TextFieldEditTemp.setText(misra2);

				frame.add(inputFieldsPanelForEditverse);

			}
		});
		// Create "readP" button

		addversesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(() -> new VersesAdd(facadeBLL).poem_id = poem_id);

			}
		});

		buttonEditverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputFieldsPanelForEditverse.isVisible()) {

					JFrame frame = new JFrame("TextField Validation");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLayout(new GridLayout(3, 2));

					if (misra1NameTextFieldEdit.getText().isEmpty() || misra2NameTextFieldEdit.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {

							VerseTO verse = new VerseTO();

							verse.setMisra1(misra1NameTextFieldEdit.getText());
							verse.setMisra2(misra1NameTextFieldEdit.getText());

							facadeBLL.updateVerse(misra1, misra2, verse);

							inputFieldsPanelForEditverse.setVisible(false);
							crudOperationPanel.setVisible(true);

							JOptionPane.showMessageDialog(frame, "Verse Edited Sccessfully... Please reload", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (NumberFormatException e1) {

							logger.debug("buttonEditverse triggerd and exception");
							JOptionPane.showMessageDialog(frame, "Please fill in Currect fields", "Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}

					}

				}
			}
		});

		frame.setVisible(true);

	}

}
