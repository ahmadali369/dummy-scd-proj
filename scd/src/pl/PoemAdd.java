package pl;
//

//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
////import java.awt.List;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.sql.SQLException;
//
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.filechooser.FileNameExtensionFilter;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import bll.interfaces.IBLLFacade;
//import transferObjects.PoemTO;
//
//public class PoemAdd extends JFrame {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private IBLLFacade facadeBLL;
//
//	private JFrame frame;
//	private JTextField titleField;
//	private static final Logger logger = LogManager.getLogger(PoemAdd.class);
//
//	public static int idBook;
//
//	public PoemAdd(IBLLFacade obj) {
//		this.facadeBLL = obj;
//
//		frame = new JFrame("Arabic Poem Database");
//		frame.setDefaultCloseOperation(PoemAdd.HIDE_ON_CLOSE);
//		frame.setSize(400, 200);
//		frame.setLayout(new GridLayout(6, 3));
//
//		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
//		int x = (screenDimension.width - frame.getWidth()) / 2;
//		int y = (screenDimension.height - frame.getHeight()) / 2;
//		frame.setLocation(x, y);
//
//		JLabel titleLabel = new JLabel("Title:");
//		titleField = new JTextField();
//
//		JButton insertButton = new JButton("Insert Poem");
//		insertButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				insertPoem();
//			}
//		});
//
//		JButton chooseBookButton = new JButton("Import from .txt");
//		chooseBookButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				chooseBook();
//			}
//
//		});
//
//		frame.add(titleLabel);
//		frame.add(titleField);
//
//		frame.add(insertButton);
//
//		JPanel buttonPanel = new JPanel();
//		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//		buttonPanel.add(chooseBookButton);
//
//		frame.add(buttonPanel, BorderLayout.CENTER);
//
//		frame.setVisible(true);
//	}
//
//	private void insertPoem() {
//		String title = titleField.getText();
//
//		PoemTO poem = new PoemTO();
//		poem.setTitle(title);
//
//		poem.setBookId(idBook);
//
//		try {
//			facadeBLL.savePoem(poem);
//			JOptionPane.showMessageDialog(frame, "Poem inserted successfully.", "Success",
//					JOptionPane.INFORMATION_MESSAGE);
//			clearFields();
//		} catch (SQLException ex) {
//
//			logger.debug("insertPoem func triggerd an exception");
//			ex.printStackTrace();
//			JOptionPane.showMessageDialog(frame, "Error occurred while inserting poem.", "Error",
//					JOptionPane.ERROR_MESSAGE);
//		}
//	}
//
//	private void chooseBook() {
////		facadeBLL.chooseBookAndRead(frame, idBook);
//		
//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setDialogTitle("Choose a Book File");
//		fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
//
//		int userSelection = fileChooser.showOpenDialog(frame);
//
//		if (userSelection == JFileChooser.APPROVE_OPTION) {
//			File selectedFile = fileChooser.getSelectedFile();
//
//			int option = JOptionPane.showConfirmDialog(frame, "Is the chosen book in Arabic?", "Language Confirmation",
//					JOptionPane.YES_NO_OPTION);
//
//			try {
//				if (option == JOptionPane.YES_OPTION) {
////					importPoem(idBook, selectedFile);
//					facadeBLL.importPoem(idBook, selectedFile);
//				} else {
//					JOptionPane.showMessageDialog(frame, "Warning: Only Arabic books are supported for now.", "Warning",
//							JOptionPane.WARNING_MESSAGE);
//				}
//			} catch (SQLException e) {
//				logger.debug("chooseBookAndRead triggered and exception");
//				e.printStackTrace();
//			}
//		}
//		
//		
//
//	}
//
//	private void clearFields() {
//		titleField.setText("");
//
//	}
//
//}
//
////==============================================================================================

//import java.awt.BorderLayout;
//import java.awt.Button;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.filechooser.FileNameExtensionFilter;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.validator.PublicClassValidator;
//
//import bll.interfaces.IBLLFacade;
//
//public class PoemAdd extends JFrame {
//
//    private static final long serialVersionUID = 1L;
//
//    private IBLLFacade facadeBLL;
//    private JFrame frame;
//    private JTextField titleField;
//    private List<JTextField> dynamicPoemTextFields = new ArrayList<>();
//    private static final Logger logger = LogManager.getLogger(PoemAdd.class);
//    public static int idBook;
//    private int size = 200;  
//
//    public PoemAdd(IBLLFacade obj) {
//        this.facadeBLL = obj;
//
//        frame = new JFrame("Arabic Poem Database");
//        frame.setDefaultCloseOperation(PoemAdd.HIDE_ON_CLOSE);
//        frame.setSize(400, size);
//        frame.setLayout(new GridLayout(0, 1));
//
//        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
//        int x = (screenDimension.width - frame.getWidth()) / 2;
//        int y = (screenDimension.height - frame.getHeight()) / 2;
//        frame.setLocation(x, y);
//
//        JLabel titleLabel = new JLabel("Title:");
//        titleField = new JTextField();
//
//        JButton plusButton = new JButton("+");
//        JButton insertPoemsButton = new JButton("Insert Poems");
//
//        plusButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addPoemTextField();
//            }
//        });
//
//        insertPoemsButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                insertPoems();
//            }
//        });
//
//        JButton chooseBookButton = new JButton("Import from .txt");
//        chooseBookButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                chooseBook();
//            }
//        });
//
//        frame.add(titleLabel);
//        frame.add(titleField);
//        frame.add(plusButton);
//        frame.add(insertPoemsButton);
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        buttonPanel.add(chooseBookButton);
//
//        frame.add(buttonPanel, BorderLayout.CENTER);
//
//        frame.setVisible(true);
//    }
//
//    private void addPoemTextField() {
//    	size += 50; 
//        JTextField newPoemTextField = new JTextField();
//        
//        
//        dynamicPoemTextFields.add(newPoemTextField);
//        
//        
//        frame.add(newPoemTextField);
//        frame.setSize(400, size);
//        frame.revalidate();
//        frame.repaint();
//    }
//
//    private void insertPoems() {
//        List<String> poemDataList = new ArrayList<>();
//        for (JTextField textField : dynamicPoemTextFields) {
//            poemDataList.add(textField.getText());
//        }
//
//        for (String poemData : poemDataList) {
//            System.out.println("Poem Data: " + poemData);
//        }
//
//        // Optionally, clear the text fields
//        for (JTextField textField : dynamicPoemTextFields) {
//            frame.remove(textField);
//        }
//        dynamicPoemTextFields.clear();
//        frame.revalidate();
//        frame.repaint();
//    }
//
//    private void chooseBook() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Choose a Book File");
//        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
//
//        int userSelection = fileChooser.showOpenDialog(frame);
//
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//
//            int option = JOptionPane.showConfirmDialog(frame, "Is the chosen book in Arabic?", "Language Confirmation",
//                    JOptionPane.YES_NO_OPTION);
//
//            try {
//                if (option == JOptionPane.YES_OPTION) {
//                    facadeBLL.importPoem(idBook, selectedFile);
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Warning: Only Arabic books are supported for now.", "Warning",
//                            JOptionPane.WARNING_MESSAGE);
//                }
//            } catch (SQLException e) {
//                logger.debug("chooseBookAndRead triggered an exception");
//                e.printStackTrace();
//            }
//        }
//        
//        
//        
//        
//    }
//        
//    
//    
//    public static void main(String[]args) {
//    	
//    	PoemAdd pdAdd = new PoemAdd(null); 
//    	pdAdd.setVisible(true);
//    }
//    
//}

//==================================================================================================

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bll.interfaces.IBLLFacade;
import transferObjects.PoemTO;

public class PoemAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private IBLLFacade facadeBLL;
	private JFrame frame;
	private JTextField titleField;
	private List<JTextField> dynamicPoemTextFields = new ArrayList<>();
	private static final Logger logger = LogManager.getLogger(PoemAdd.class);
	public static int idBook;
	private int size = 200;

	public PoemAdd(IBLLFacade obj) {
		this.facadeBLL = obj;

		frame = new JFrame("Arabic Poem Database");
		frame.setDefaultCloseOperation(PoemAdd.HIDE_ON_CLOSE);
		frame.setSize(400, size);
		frame.setLayout(new GridLayout(0, 1));

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenDimension.width - frame.getWidth()) / 2;
		int y = (screenDimension.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);

		JLabel titleLabel = new JLabel("Title:");
		titleField = new JTextField();

		JButton plusButton = new JButton("+");
		JButton insertPoemsButton = new JButton("Insert Poems");

		plusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPoemTextField();
			}
		});

		insertPoemsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertPoems();
			}
		});

		JButton chooseBookButton = new JButton("Import from .txt");
		chooseBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseBook();
			}
		});

		frame.add(titleLabel);
		frame.add(titleField);
		dynamicPoemTextFields.add(titleField);
		frame.add(plusButton);
		frame.add(insertPoemsButton);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(chooseBookButton);

		frame.add(buttonPanel, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	private void addPoemTextField() {
		size += 50;
		JTextField newPoemTextField = new JTextField();

		dynamicPoemTextFields.add(newPoemTextField);

		int plusButtonIndex = frame.getContentPane().getComponentCount() - 3;
		frame.getContentPane().add(newPoemTextField, plusButtonIndex);

		frame.setSize(400, size);
		frame.revalidate();
		frame.repaint();
	}

	private void insertPoems() {
		List<String> poemDataList = new ArrayList<>();
		for (JTextField textField : dynamicPoemTextFields) {
			poemDataList.add(textField.getText());
		}

		for (String poemData : poemDataList) {

			PoemTO poem = new PoemTO();
			poem.setTitle(poemData);

			poem.setBookId(idBook);

			try {
				facadeBLL.savePoem(poem);
				JOptionPane.showMessageDialog(frame, "Poem inserted successfully.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
//            			clearFields();
			} catch (SQLException ex) {

				logger.debug("insertPoem func triggerd an exception");
				ex.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Error occurred while inserting poem.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}

		frame.revalidate();
		frame.repaint();
	}

	private void chooseBook() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose a Book File");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));

		int userSelection = fileChooser.showOpenDialog(frame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			int option = JOptionPane.showConfirmDialog(frame, "Is the chosen book in Arabic?", "Language Confirmation",
					JOptionPane.YES_NO_OPTION);

			try {
				if (option == JOptionPane.YES_OPTION) {
					facadeBLL.importPoem(idBook, selectedFile);
					JOptionPane.showMessageDialog(frame, "Poems Imported successfully.", "Success",
							JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(frame, "Warning: Only Arabic books are supported for now.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			} catch (SQLException e) {
				logger.debug("chooseBookAndRead triggered an exception");
				e.printStackTrace();
			}
		}
	}

}
