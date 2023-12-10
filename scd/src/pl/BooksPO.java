
package pl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
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
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import bll.interfaces.IBLLFacade;
import transferObjects.BookTO;

public class BooksPO extends JFrame {

	private IBLLFacade facadeBLL;

	private static final long serialVersionUID = 1L;
	private JPanel initialPanel;
	private JPanel crudOperationPanel;

	private JPanel inputFieldsPanelForInsertBook;
	private JPanel inputFieldsPanelForEditBook;

	private int tableRow;
	private String title;
	private String author;
	private int id;
	private static final Logger logger = LogManager.getLogger(BooksPO.class);

	public BooksPO(IBLLFacade obj) {

		this.facadeBLL = obj;

		setSize(700, 500);
		setTitle("Encyclopedia Of Arabic Poems");
		setDefaultCloseOperation(BooksPO.EXIT_ON_CLOSE);

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenDimension.width - getWidth()) / 2;
		int y = (screenDimension.height - getHeight()) / 2;
		setLocation(x, y);

		initialPanel = new JPanel();
		crudOperationPanel = new JPanel();

		inputFieldsPanelForInsertBook = new JPanel();
		inputFieldsPanelForEditBook = new JPanel();

		JButton buttonManageBooks = new JButton("Manage Books");
//		JButton searchByRootButton = new JButton("Search by Root");

		initialPanel.add(buttonManageBooks);
//		initialPanel.add(searchByRootButton);
		add(initialPanel);

		JButton buttonCreate = new JButton("Insert Book");
		JButton buttonRead = new JButton("Reload");

		JButton buttonInsertBook = new JButton("Insert");
		JButton buttonEditBook = new JButton("Edit");

		crudOperationPanel.add(new JLabel("Books-------------------------------"));
		crudOperationPanel.add(buttonCreate);
		crudOperationPanel.add(buttonRead);

		JTextField bookNameTextFieldInsert = new JTextField(20);
		JTextField authorNameTextFieldInsert = new JTextField(20);

		JButton buttonBackinsert = new JButton("<-");

		JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		backButtonPanel.add(buttonBackinsert);
		backButtonPanel.add(new JLabel("Create Book"));

		////////
		UtilDateModel modelDob = new UtilDateModel();
		UtilDateModel modelDod = new UtilDateModel();

		Properties p = new Properties();
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		JDatePanelImpl datePanelDob = new JDatePanelImpl(modelDob, p);
		JDatePanelImpl datePanelDod = new JDatePanelImpl(modelDod, p);

		JDatePickerImpl datePickerDob = new JDatePickerImpl(datePanelDob, new DateLabelFormatter());
		JDatePickerImpl datePickerDod = new JDatePickerImpl(datePanelDod, new DateLabelFormatter());

		JDatePanelImpl datePanelDobEdit = new JDatePanelImpl(modelDob, p);
		JDatePanelImpl datePanelDodEdit = new JDatePanelImpl(modelDod, p);

		JDatePickerImpl datePickerDobEdit = new JDatePickerImpl(datePanelDobEdit, new DateLabelFormatter());
		JDatePickerImpl datePickerDodEdit = new JDatePickerImpl(datePanelDodEdit, new DateLabelFormatter());

		JPanel inputFieldsPanelHorizontal = new JPanel(new GridLayout(0, 1));
		inputFieldsPanelHorizontal.add(new JLabel("Book Title: "));
		inputFieldsPanelHorizontal.add(bookNameTextFieldInsert);
		inputFieldsPanelHorizontal.add(new JLabel("Author: "));
		inputFieldsPanelHorizontal.add(authorNameTextFieldInsert);

		inputFieldsPanelHorizontal.add(new JLabel("Date of Birth (Author): "));
		inputFieldsPanelHorizontal.add(datePickerDob);

		inputFieldsPanelHorizontal.add(new JLabel("Date of Death (Author): "));
		inputFieldsPanelHorizontal.add(datePickerDod);

		inputFieldsPanelForInsertBook.setLayout(new BoxLayout(inputFieldsPanelForInsertBook, BoxLayout.Y_AXIS));
		inputFieldsPanelForInsertBook.setAlignmentY(TOP_ALIGNMENT);
		inputFieldsPanelForInsertBook.add(backButtonPanel);
		inputFieldsPanelForInsertBook.add(inputFieldsPanelHorizontal);
		inputFieldsPanelForInsertBook.add(buttonInsertBook);

//---------------------

		JTextField bookNameTextFieldEditTemp = new JTextField(20);
		JTextField authorNameTextFieldEditTemp = new JTextField(20);

		JTextField bookNameTextFieldEdit = new JTextField(20);
		JTextField authorNameTextFieldEdit = new JTextField(20);

		JTextField dateOfBirthAuthorTextFieldEdit = new JTextField(10);
		JTextField dateOfDeathAuthorTextFieldEdit = new JTextField(10);
		JButton buttonBackedit = new JButton("<-");

		JPanel backButtonPanelEdit = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonPanelEdit.add(buttonBackedit);
		backButtonPanelEdit.add(new JLabel("Edit Book"));

		JPanel inputFieldsPanelHorizontalEdit = new JPanel(new GridLayout(0, 1));
		inputFieldsPanelHorizontalEdit.add(new JLabel("Update Book Title: "));
		inputFieldsPanelHorizontalEdit.add(bookNameTextFieldEdit);
		inputFieldsPanelHorizontalEdit.add(new JLabel("Update Author: "));
		inputFieldsPanelHorizontalEdit.add(authorNameTextFieldEdit);

		inputFieldsPanelHorizontalEdit.add(new JLabel("Update Date of Birth (Author): "));
		inputFieldsPanelHorizontalEdit.add(datePickerDobEdit);

		inputFieldsPanelHorizontalEdit.add(new JLabel("Update Date of Death (Author): "));
		inputFieldsPanelHorizontalEdit.add(datePickerDodEdit);

		inputFieldsPanelForEditBook.setLayout(new BoxLayout(inputFieldsPanelForEditBook, BoxLayout.Y_AXIS));
		inputFieldsPanelForEditBook.setAlignmentY(TOP_ALIGNMENT);
		inputFieldsPanelForEditBook.add(backButtonPanelEdit);
		inputFieldsPanelForEditBook.add(inputFieldsPanelHorizontalEdit);
		inputFieldsPanelForEditBook.add(buttonEditBook);

//--------------------

//		searchByRootButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				SwingUtilities.invokeLater(() -> new SearchByRoots(facadeBLL));
//
//			}
//		});

//---------------------

		buttonManageBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				initialPanel.setVisible(false);
				crudOperationPanel.setVisible(true);
				add(crudOperationPanel);
			}
		});

		buttonBackinsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				crudOperationPanel.setVisible(true);
				inputFieldsPanelForInsertBook.setVisible(false);

			}
		});
		buttonBackedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				crudOperationPanel.setVisible(true);
				inputFieldsPanelForEditBook.setVisible(false);

			}
		});

		// -------------------------

		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				crudOperationPanel.setVisible(false);
				inputFieldsPanelForInsertBook.setVisible(true);
				add(inputFieldsPanelForInsertBook);

			}
		});

//-------------------

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(650, 400));
		crudOperationPanel.add(scrollPane);

		JButton deleteButton = new JButton("Delete Book");
		JButton editButton = new JButton("Edit Book");
		JButton openBookButton = new JButton("Open Book");

		crudOperationPanel.add(openBookButton);
		crudOperationPanel.add(editButton);
		crudOperationPanel.add(deleteButton);

		buttonRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Map<String, Object>> books = facadeBLL.getAllBooks();

				if (books.isEmpty()) {

				} else {
					listPanel.removeAll();
					DefaultTableModel model = new DefaultTableModel();
					DefaultTableModel idmodel = new DefaultTableModel();

					model.addColumn("Title");
					model.addColumn("Author");
					model.addColumn("Date of Birth");
					model.addColumn("Date of Death");
					model.addColumn("Total Poems");
					idmodel.addColumn("id");

					for (Map<String, Object> book : books) {
						model.addRow(
								new Object[] { book.get("title"), book.get("authorName"), book.get("authorDateOfBirth"),
										book.get("authorDateOfDeath"), book.get("totalPoems"), });
						idmodel.addRow(new Object[] { book.get("bookId"), });

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

									id = (int) idtable.getValueAt(tableRow, 0);
									title = (String) table.getValueAt(tableRow, 0);
									author = (String) table.getValueAt(tableRow, 1);
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

				facadeBLL.deleteBook(title, author);

				JFrame frame = new JFrame("TextField Validation");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new GridLayout(3, 2));

				try {

					facadeBLL.deleteBook(title, author);
					JOptionPane.showMessageDialog(frame, "Book Deleted Sccessfully.. Please reload", "Success",
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
				inputFieldsPanelForEditBook.setVisible(true);

				bookNameTextFieldEditTemp.setText(title);
				authorNameTextFieldEditTemp.setText(author);

				add(inputFieldsPanelForEditBook);

			}
		});

		openBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(() -> new PoemPage(facadeBLL).book_id = id);

			}
		});

		buttonInsertBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputFieldsPanelForInsertBook.isVisible()) {

					JFrame frame = new JFrame("TextField Validation");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLayout(new GridLayout(3, 2));

					Date dob = (Date) datePickerDob.getModel().getValue();
					Date dod = (Date) datePickerDod.getModel().getValue();

					if (bookNameTextFieldInsert.getText().isEmpty() || authorNameTextFieldInsert.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {

							BookTO book = new BookTO();
							book.setTitle(bookNameTextFieldInsert.getText());
							book.setAuthorName(authorNameTextFieldInsert.getText());
							book.setAuthorDateOfBirth(dob.toString());
							book.setAuthorDateOfDeath(dod.toString());

							book.setTotalPoems(0);
							facadeBLL.insertBook(book);

							inputFieldsPanelForInsertBook.setVisible(false);
							crudOperationPanel.setVisible(true);
							JOptionPane.showMessageDialog(frame, "Book Created Sccessfully.. Please reload.", "Success",
									JOptionPane.INFORMATION_MESSAGE);

						} catch (NumberFormatException e1) {

							JOptionPane.showMessageDialog(frame, "Please fill in Currect fields", "Error",
									JOptionPane.ERROR_MESSAGE);
							// TODO Auto-generated catch block
							logger.debug("buttonInsertBook func triggerd an exception");
							e1.printStackTrace();
						}

					}

				}
			}
		});

		buttonEditBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputFieldsPanelForEditBook.isVisible()) {

					JFrame frame = new JFrame("TextField Validation");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLayout(new GridLayout(3, 2));

					Date dob = (Date) datePickerDobEdit.getModel().getValue();
					Date dod = (Date) datePickerDodEdit.getModel().getValue();

					if (bookNameTextFieldEdit.getText().isEmpty() || authorNameTextFieldEdit.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {

							BookTO book = new BookTO();

							book.setTitle(bookNameTextFieldEdit.getText());
							book.setAuthorName(authorNameTextFieldEdit.getText());
							book.setAuthorDateOfBirth(dob.toString());
							book.setAuthorDateOfDeath(dod.toString());
							book.setTotalPoems(0);
							facadeBLL.updateBook(bookNameTextFieldEditTemp.getText(),
									authorNameTextFieldEditTemp.getText(), book);

							inputFieldsPanelForEditBook.setVisible(false);
							crudOperationPanel.setVisible(true);

							JOptionPane.showMessageDialog(frame, "Book Edited Sccessfully... Please reload", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (NumberFormatException e1) {

							logger.debug("buttoneditbook triggerd and exception");
							JOptionPane.showMessageDialog(frame, "Please fill in Currect fields", "Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}

					}

				}
			}
		});

	}

}

class DateLabelFormatter extends AbstractFormatter {

	private String datePattern = "yyyy-MM-dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}

}
