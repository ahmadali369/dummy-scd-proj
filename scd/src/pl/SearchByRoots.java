//package pl;
//
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//import java.util.Map;
//
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.SwingUtilities;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.table.DefaultTableModel;
//
//import bll.interfaces.IBLLFacade;
//import transferObjects.VerseTO;
//
//public class SearchByRoots extends JFrame {
//
//	private IBLLFacade facadeBLL;
//	private static final long serialVersionUID = 1L;
//
//	private JPanel crudOperationPanel;
////	private JPanel inputFieldsPanelForEditverse;
//
//	private int tableRow;
////	private String title;
//	public static int poem_id;
//	private int verse_id;
//	private String misra1, misra2;
//
//	DefaultTableModel idmodel;
//
//	public SearchByRoots(IBLLFacade obj) {
//
//		this.facadeBLL = obj;
//
//		JFrame frame = new JFrame();
//
//		idmodel = new DefaultTableModel();
//
//		frame.setSize(700, 500);
//		frame.setTitle("Encyclopedia Of Arabic Poems");
//		frame.setDefaultCloseOperation(VersesPage.HIDE_ON_CLOSE);
//
//		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
//		int x = (screenDimension.width - frame.getWidth()) / 2;
//		int y = (screenDimension.height - frame.getHeight()) / 2;
//		frame.setLocation(x, y);
//
//		crudOperationPanel = new JPanel();
//
//		JButton buttonRead = new JButton("Search");
//		JTextField rootTextField = new JTextField(10); 
//		
//
//		crudOperationPanel.add(new JLabel("Search Poem by Roots -------------------------------"));
//		
//		crudOperationPanel.add(rootTextField);
//		crudOperationPanel.add(buttonRead);
//
//		frame.add(crudOperationPanel);
//
//
//		
//		
//
//		JPanel listPanel = new JPanel();
//		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
//		JScrollPane scrollPane = new JScrollPane(listPanel);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setPreferredSize(new Dimension(650, 400));
//		crudOperationPanel.add(scrollPane);
//
//		buttonRead.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				List<Map<String, Object>> verses = facadeBLL.getAllVersesByRoots(rootTextField.getText());
//
//				if (verses.isEmpty()) {
//					System.err.println("no verses found------------");
//
//				} else {
//					listPanel.removeAll();
//					DefaultTableModel model = new DefaultTableModel();
////					DefaultTableModel idmodel = new DefaultTableModel();
//
//					model.addColumn("Misra 1");
//					model.addColumn("Misra 2");
//					idmodel.addColumn("id");
//
//					for (Map<String, Object> verse : verses) {
//						model.addRow(new Object[] { verse.get("misra1"), verse.get("misra2") });
//						idmodel.addRow(new Object[] { verse.get("verseId"), });
//
//					}
//
//					JTable table = new JTable(model);
//					JTable idtable = new JTable(idmodel);
//					JScrollPane scrollPane = new JScrollPane(table);
//
//					table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//						@Override
//						public void valueChanged(ListSelectionEvent e) {
//							if (!e.getValueIsAdjusting()) {
//								int selectedRow = table.getSelectedRow();
//
//								if (selectedRow != -1) {
//									tableRow = selectedRow;
//
//									verse_id = (int) idtable.getValueAt(tableRow, 0);
//									misra1 = (String) table.getValueAt(tableRow, 0);
//									misra2 = (String) table.getValueAt(tableRow, 1);
//
//								}
//							}
//						}
//
//					});
//
//					listPanel.add(scrollPane);
//					listPanel.revalidate();
//					listPanel.repaint();
//
//				}
//
//			}
//
//		});
//
//		JButton buttonOpenPoem = new JButton("Open Poem");
//
//		crudOperationPanel.add(buttonOpenPoem);
//
//		buttonOpenPoem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
////				SwingUtilities.invokeLater(() -> new AssignRootsManual(facadeBLL, poem_id));
//
//			}
//		});
//
//		frame.setVisible(true);
//
//	}
//
//}


package pl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bll.interfaces.IBLLFacade;

import javax.swing.JTextArea;

public class SearchByRoots extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/booksdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private JTextField rootTextField;
    private JTextArea verseTextArea;
    private IBLLFacade facadeBLL; 
    
//	private static final Logger logger = LogManager.getLogger(BooksBLO.class);


    public SearchByRoots(IBLLFacade obj) {
        super("View Root Verses");
    	this.facadeBLL = obj; 

        // Create components
        JLabel rootLabel = new JLabel("Enter Root:");
        rootTextField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton goToPoemButton = new JButton("Go to Poem");
        verseTextArea = new JTextArea(10, 40);
        verseTextArea.setEditable(false);

        // Set up the layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(rootLabel);
        inputPanel.add(rootTextField);
        inputPanel.add(searchButton);
        inputPanel.add(goToPoemButton);

        // Add action listeners
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAndDisplayVerses();
//            	facadeBLL.searchAndDisplayVerses(rootTextField.getText().trim()); 
            	
            }
        });

        goToPoemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPoem();
//            	facadeBLL.goToPoem(rootTextField.getText().trim()); 
            }
        });

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(verseTextArea, BorderLayout.CENTER);

        // Set frame properties
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void searchAndDisplayVerses() {
        String root = rootTextField.getText().trim();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Check if the root exists in the verse table
            if (rootExistsInVerse(connection, root)) {
                String query = "SELECT verse_number, misra1, misra2 " +
                               "FROM verses " +
                               "WHERE verse_id IN (SELECT verse_id FROM tokens WHERE tokens.token = ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, root.toLowerCase());

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        StringBuilder result = new StringBuilder();
                        while (resultSet.next()) {
                            int verseNumber = resultSet.getInt("verse_number");
                            String misra1 = resultSet.getString("misra1");
                            String misra2 = resultSet.getString("misra2");
                            result.append("Verse ").append(verseNumber).append(": ").append(misra1).append(" ").append(misra2).append("\n");
                        }

                        if (result.length() == 0) {
                            verseTextArea.setText("No verses found for the given root.");
                        } else {
                            verseTextArea.setText(result.toString());
                        }
                    }
                }
            } else {
                verseTextArea.setText("Root does not exist in the verse table.");
            }
        } catch (Exception e) {
//        	logger.debug("insertBook func triggerd an exception");
            e.printStackTrace();
            verseTextArea.setText("Error occurred. Check console for details.");
        }
        System.out.println("Search completed for root: " + root);
    }

    private boolean rootExistsInVerse(Connection connection, String root) {
        String query = "SELECT COUNT(*) AS count FROM verses WHERE verse_id IN (SELECT verse_id FROM tokens WHERE tokens.token = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, root.toLowerCase());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (Exception e) {
//        	logger.debug("insertBook func triggerd an exception");
            e.printStackTrace();
        }
        return false;
    }

    private void goToPoem() {
        String root = rootTextField.getText().trim();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Check if the root exists in the verse table
            if (rootExistsInVerse(connection, root)) {
                String query = "SELECT poems.title, verses.verse_number, verses.misra1, verses.misra2 " +
                               "FROM poems " +
                               "JOIN verses ON poems.id = verses.poem_id " +
                               "WHERE poems.id IN (SELECT DISTINCT poems.id " +
                                                 "FROM poems " +
                                                 "JOIN verses ON poems.id = verses.poem_id " +
                                                 "JOIN tokens ON verses.verse_id = tokens.verse_id " +
                                                 "WHERE tokens.token = ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, root.toLowerCase());

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        StringBuilder result = new StringBuilder();
                        String currentPoemTitle = "";
                        while (resultSet.next()) {
                            String poemTitle = resultSet.getString("title");
                            int verseNumber = resultSet.getInt("verse_number");
                            String misra1 = resultSet.getString("misra1");
                            String misra2 = resultSet.getString("misra2");

                            // Check if the poem title has changed
                            if (!currentPoemTitle.equals(poemTitle)) {
                                // If it has, append the new poem title
                                currentPoemTitle = poemTitle;
                                result.append("Poem: ").append(poemTitle).append("\n");
                            }

                            // Append the current verse information
                            result.append(misra1).append("     ").append(misra2).append("\n");
                        }

                        if (result.length() == 0) {
                            verseTextArea.setText("No verses found for the given root.");
                        } else {
                            verseTextArea.setText(result.toString());
                        }
                    }
                }
            } else {
                verseTextArea.setText("Root does not exist in the verse table.");
            }
        } catch (Exception e) {
//        	logger.debug("insertBook func triggerd an exception");
            e.printStackTrace();
            verseTextArea.setText("Error occurred. Check console for details.");
        }
        System.out.println("Go to poem completed for root: " + root);
    }

//    public static void main(String[] args) {
//        // Set up the database driver
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
////            logger.debug("insertBook func triggerd an exception");
//            return;
//        }
//
//        new SearchByRoots();
//    }
}


