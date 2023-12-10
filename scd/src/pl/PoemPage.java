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

import bll.interfaces.IBLLFacade;
import transferObjects.PoemTO;

public class PoemPage extends JFrame {

	private IBLLFacade facadeBLL;

	
	private static final Logger logger = LogManager.getLogger(PoemPage.class);

	private static final long serialVersionUID = 1L;

	private JPanel crudOperationPanel;

	private JPanel inputFieldsPanelForEditpoem;

	private int tableRow;
	public static int book_id;

	private String title;

	private int poem_id;

	public PoemPage(IBLLFacade obj) {

		this.facadeBLL = obj;

		JFrame frame = new JFrame();

		frame.setSize(700, 500);
		frame.setTitle("Encyclopedia Of Arabic Poems");
		frame.setDefaultCloseOperation(PoemPage.HIDE_ON_CLOSE);

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenDimension.width - frame.getWidth()) / 2;
		int y = (screenDimension.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);

		crudOperationPanel = new JPanel();

		inputFieldsPanelForEditpoem = new JPanel();

		JButton buttonRead = new JButton("Reload");
		
		JButton buttonRootSearch = new JButton("Search"); 
		JTextField rootSearchField = new JTextField(20); 
		
		
		
		
		
		JButton buttonEditpoem = new JButton("Edit");

		crudOperationPanel.add(new JLabel("Poems----------------"));
		
		crudOperationPanel.add(rootSearchField);
		crudOperationPanel.add(buttonRootSearch);
		crudOperationPanel.add(buttonRead);

		frame.add(crudOperationPanel);

		JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		backButtonPanel.add(new JLabel("Create poem"));

		JTextField poemNameTextFieldEditTemp = new JTextField(20);

		JTextField poemNameTextFieldEdit = new JTextField(20);
		JButton buttonBackedit = new JButton("<-");

		JPanel backButtonPanelEdit = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonPanelEdit.add(buttonBackedit);
		backButtonPanelEdit.add(new JLabel("Edit Poem"));

		JPanel inputFieldsPanelHorizontalEdit = new JPanel(new GridLayout(0, 1));
		inputFieldsPanelHorizontalEdit.add(new JLabel("Update Poem Title: "));
		inputFieldsPanelHorizontalEdit.add(poemNameTextFieldEdit);

		inputFieldsPanelForEditpoem.setLayout(new BoxLayout(inputFieldsPanelForEditpoem, BoxLayout.Y_AXIS));
		inputFieldsPanelForEditpoem.setAlignmentY(TOP_ALIGNMENT);
		inputFieldsPanelForEditpoem.add(backButtonPanelEdit);
		inputFieldsPanelForEditpoem.add(inputFieldsPanelHorizontalEdit);
		inputFieldsPanelForEditpoem.add(buttonEditpoem);

		buttonBackedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				crudOperationPanel.setVisible(true);
				inputFieldsPanelForEditpoem.setVisible(false);

			}
		});

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(650, 400));
		crudOperationPanel.add(scrollPane);

		JButton deleteButton = new JButton("Delete Poem");
		JButton ReadPoemsButton = new JButton("Open Poem");
		JButton editButton = new JButton("Edit Poem");
		JButton addPoemsButton = new JButton("Add Poems");

		crudOperationPanel.add(ReadPoemsButton);
		crudOperationPanel.add(addPoemsButton);
		crudOperationPanel.add(editButton);
		crudOperationPanel.add(deleteButton);
		
		
		
		
		
		
		
		

		buttonRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Map<String, Object>> poems = facadeBLL.getAllPoems(book_id);

				if (poems.isEmpty()) {

				} else {
					listPanel.removeAll();
					DefaultTableModel model = new DefaultTableModel();
					DefaultTableModel idmodel = new DefaultTableModel();

					model.addColumn("Poem Title");
					model.addColumn("Total Verses");
					idmodel.addColumn("id");

					for (Map<String, Object> poem : poems) {
						model.addRow(new Object[] { poem.get("title"), poem.get("total_verses") });
						idmodel.addRow(new Object[] { poem.get("poemid"), });

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

									poem_id = (int) idtable.getValueAt(tableRow, 0);
									title = (String) table.getValueAt(tableRow, 0);
									System.out.println("selected row" + tableRow);
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
		
		
		buttonRootSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Map<String, Object>> poems = facadeBLL.getAllPoems(book_id);

				if (poems.isEmpty()) {

				} else {
					listPanel.removeAll();
					DefaultTableModel model = new DefaultTableModel();
					DefaultTableModel idmodel = new DefaultTableModel();

					model.addColumn("Poem Title");
					model.addColumn("Total Verses");
					idmodel.addColumn("id");

					for (Map<String, Object> poem : poems) {
						model.addRow(new Object[] { poem.get("title"), poem.get("total_verses") });
						idmodel.addRow(new Object[] { poem.get("poemid"), });

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

									poem_id = (int) idtable.getValueAt(tableRow, 0);
									title = (String) table.getValueAt(tableRow, 0);
									System.out.println("selected row" + tableRow);
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

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				facadeBLL.deleteBook(title, author);

				JFrame frame = new JFrame("TextField Validation");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new GridLayout(3, 2));

				try {

					facadeBLL.deletePoem(title);
					JOptionPane.showMessageDialog(frame, "Poem Deleted Sccessfully.. Please reload", "Success",
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
				inputFieldsPanelForEditpoem.setVisible(true);

				poemNameTextFieldEditTemp.setText(title);
//				authorNameTextFieldEditTemp.setText(author);

				frame.add(inputFieldsPanelForEditpoem);

			}
		});
		// Create "readP" button

		ReadPoemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(() -> new VersesPage(facadeBLL).poem_id = poem_id);

			}
		});

		addPoemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new PoemAdd(facadeBLL);
				SwingUtilities.invokeLater(() -> PoemAdd.idBook = book_id);

			}
		});

		buttonEditpoem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputFieldsPanelForEditpoem.isVisible()) {

					JFrame frame = new JFrame("TextField Validation");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLayout(new GridLayout(3, 2));

					if (poemNameTextFieldEdit.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {

							PoemTO poem = new PoemTO();

							poem.setTitle(poemNameTextFieldEdit.getText());

							facadeBLL.updatePoem(poemNameTextFieldEditTemp.getText(), poem);

							inputFieldsPanelForEditpoem.setVisible(false);
							crudOperationPanel.setVisible(true);

							JOptionPane.showMessageDialog(frame, "Poem Edited Sccessfully... Please reload", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (NumberFormatException e1) {

							logger.debug("buttonEditpoem triggerd and exception");
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

	
	public static void main(String[]args) {
		
		PoemPage poemPage = new PoemPage(null); 
		poemPage.setVisible(true);
	}
}
