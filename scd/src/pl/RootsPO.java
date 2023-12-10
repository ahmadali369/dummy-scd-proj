package pl;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import Presentation_Layer.Root_Home;
import bll.interfaces.IBLLFacade;
import transferObjects.RootTO;

public class RootsPO extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableData;
	private DefaultTableModel defaultmodel;
	private JTextField textFieldID;
	private JTextField textFieldword;
	JRadioButton insertbtn;
	JRadioButton updatebtn;
	JRadioButton deletebtn;
	JRadioButton searchbtn;
	JRadioButton clearbtn;
	private static final Logger logger = LogManager.getLogger(RootsPO.class);

	List words = new ArrayList();

	boolean T = false;

	private IBLLFacade facadeBLL;

	public RootsPO(IBLLFacade obj) {
		this.facadeBLL = obj;

		setResizable(false);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 838, 621);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane js = new JScrollPane();
		js.setVisible(true);
		getContentPane().add(js);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setBounds(29, 33, 548, 480);
		contentPane.add(scrollPane);

		tableData = new JTable();
		tableData.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane.setViewportView(tableData);

//		tableData.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, { null, null },
//				{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
//				{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
//				{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
//				{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
//				{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
//				{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
//				{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
//				{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, },
//				new String[] { "Count", "Root" }));
//
//		String[] colNames = { "Count", "Root"};
//		defaultmodel = new DefaultTableModel(colNames, 0);
		
		tableData.setModel(new DefaultTableModel(new Object[][] {
	        {null, null, null},
	        {null, null, null},
	        // Add more rows as needed
	    },
	    new String[]{"Count", "Root", "Status"}));

	String[] colNames = {"Count", "Root", "Status"};
	DefaultTableModel defaultmodel = new DefaultTableModel(colNames, 0);


		JButton btnNewButton = new JButton("View Table");
		btnNewButton.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 18));

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) tableData.getModel();
				dtm.getDataVector().removeAllElements();
				dtm.fireTableDataChanged();
				words.clear();
				try {
					words = facadeBLL.getAllRoots();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				int count = 1;
				for (int i = 0; i < words.size(); i = i + 3) {

					Object[] B_Dat = new Object[3];
					B_Dat[0] = count++;
					B_Dat[1] = words.get(i + 1);
					B_Dat[2] = words.get(i + 2);

					defaultmodel.addRow(B_Dat);
				}
				tableData.setModel(defaultmodel);

			}

		});

		btnNewButton.setBounds(206, 533, 178, 41);
		contentPane.add(btnNewButton);

		textFieldID = new JTextField();
		textFieldID.setBackground(new Color(255, 255, 255));
		textFieldID.setEditable(false);
		textFieldID.setBounds(671, 249, 132, 32);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);

		textFieldword = new JTextField();
		textFieldword.setBackground(new Color(255, 255, 255));
		textFieldword.setEditable(false);
		textFieldword.setColumns(10);
		textFieldword.setBounds(671, 188, 132, 32);
		contentPane.add(textFieldword);

		JLabel lblNewID = new JLabel("Word");
		lblNewID.setForeground(SystemColor.textHighlightText);
		lblNewID.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblNewID.setBounds(590, 185, 81, 32);
		contentPane.add(lblNewID);

		JLabel lblWord = new JLabel("New word");
		lblWord.setForeground(SystemColor.textHighlightText);
		lblWord.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblWord.setBounds(587, 246, 84, 32);
		contentPane.add(lblWord);

		JButton btnNewButton_insert = new JButton("insert");
		btnNewButton_insert.setBackground(SystemColor.activeCaptionBorder);

		btnNewButton_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (insertbtn.isSelected()) {
					RootTO rootTO = new RootTO(1, textFieldword.getText(), "Manual"); 
					facadeBLL.insertRoot(rootTO);
					table();

				} else {

					JOptionPane.showMessageDialog(null, "Please First Select Something");
				}

			}
		});

		btnNewButton_insert.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnNewButton_insert.setBounds(586, 400, 109, 32);
//		contentPane.add(btnNewButton_insert);     //////     ==============

		JButton btnNewButton_update = new JButton("Update");
		btnNewButton_update.setBackground(SystemColor.activeCaptionBorder);

		btnNewButton_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (updatebtn.isSelected()) {
					String id = textFieldword.getText();

					facadeBLL.updateWord(textFieldID.getText(), id);

					table();

				} else {

					JOptionPane.showMessageDialog(null, "Please First Select Something");

				}

			}
		});
		btnNewButton_update.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnNewButton_update.setBounds(718, 400, 96, 32);
		contentPane.add(btnNewButton_update);

		JButton btnNewButton_1_1_1 = new JButton("Delete");
		btnNewButton_1_1_1.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (deletebtn.isSelected()) {
					String id = textFieldword.getText();

					facadeBLL.deleteroot(id);
					table();
				} else {

					JOptionPane.showMessageDialog(null, "Please First Select Something");

				}

			}
		});

		btnNewButton_1_1_1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnNewButton_1_1_1.setBounds(586, 463, 109, 32);
		contentPane.add(btnNewButton_1_1_1);

		JButton btnNewButton_clear = new JButton("Clear");
		btnNewButton_clear.setBackground(SystemColor.inactiveCaption);
		btnNewButton_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel dm = (DefaultTableModel) tableData.getModel();
				dm.getDataVector().removeAllElements();
				dm.fireTableDataChanged();
				textFieldword.setText("");

				textFieldID.setText("");

			}
		});

		btnNewButton_clear.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnNewButton_clear.setBounds(590, 518, 224, 32);
		contentPane.add(btnNewButton_clear);

		JTable table = new JTable(defaultmodel);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		JButton btnNewButton_search = new JButton("Search");
		btnNewButton_search.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (searchbtn.isSelected()) {
					DefaultTableModel dm = (DefaultTableModel) tableData.getModel();
					dm.getDataVector().removeAllElements();
					dm.fireTableDataChanged();
					String id = textFieldID.getText();
					String wordField = textFieldword.getText();

					try {
						if (id != null) {
							for (int i = 0; i < words.size(); i = i + 3) {
								if (id.equals(words.get(i))) {

									Object[] B_Data = new Object[3];
									B_Data[0] = 1;
									B_Data[1] = words.get(i + 1);
									B_Data[2] = words.get(i + 2);

									defaultmodel.addRow(B_Data);
									break;
								}
							}
							tableData.setModel(defaultmodel);
						}

						if (wordField != null) {

							for (int i = 0; i < words.size(); i = i + 3) {
								if (wordField.equals(words.get(i + 1))) {

									Object[] B_Data = new Object[3];
									B_Data[0] = 1;
									B_Data[1] = words.get(i + 1);
									B_Data[2] = words.get(i + 2);

									defaultmodel.addRow(B_Data);
									break;
								}
							}
							tableData.setModel(defaultmodel);
						}

					} catch (NumberFormatException e1) {

						logger.debug("btnNewButton_search func triggerd an exception");
					}

				}

				else {

					JOptionPane.showMessageDialog(null, "Please First Select Something");
				}
			}
		});

		btnNewButton_search.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnNewButton_search.setBounds(718, 463, 96, 32);
		contentPane.add(btnNewButton_search);

		insertbtn = new JRadioButton("");
		insertbtn.setBackground(SystemColor.desktop);
		insertbtn.setBounds(583, 380, 28, 21);
		contentPane.add(insertbtn);

		updatebtn = new JRadioButton("");
		updatebtn.setBackground(SystemColor.desktop);
		updatebtn.setBounds(718, 380, 28, 21);
		contentPane.add(updatebtn);

		deletebtn = new JRadioButton("");
		deletebtn.setBackground(SystemColor.activeCaptionText);
		deletebtn.setBounds(589, 444, 28, 21);
		contentPane.add(deletebtn);

		clearbtn = new JRadioButton("");
		clearbtn.setBackground(Color.BLACK);
		clearbtn.setBounds(589, 496, 28, 21);
		contentPane.add(clearbtn);

		searchbtn = new JRadioButton("");
		searchbtn.setBackground(SystemColor.desktop);
		searchbtn.setBounds(718, 446, 28, 21);
		contentPane.add(searchbtn);
		ButtonGroup btngroup = new ButtonGroup();
		btngroup.add(insertbtn);
		btngroup.add(updatebtn);
		btngroup.add(searchbtn);
		btngroup.add(deletebtn);
		btngroup.add(searchbtn);
		btngroup.add(clearbtn);

		getContentPane().add(insertbtn);
		getContentPane().add(updatebtn);
		getContentPane().add(deletebtn);
		getContentPane().add(searchbtn);
		getContentPane().add(clearbtn);

//		JButton btnNewButton_2 = new JButton(">");
//		btnNewButton_2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
////			Root_Home root_Home = new Root_Home();
////			root_Home.setVisible(true);
////			viewupdate.dispose();
//
//			}
//		});
//		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
//		btnNewButton_2.setBounds(740, 28, 63, 32);
//		contentPane.add(btnNewButton_2);

		insertbtn.addAncestorListener(null);

		updatebtn.addAncestorListener(null);

		textFieldword.setText("///");
		textFieldID.setText("///");

		insertbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				textFieldword.setEditable(true);
				textFieldID.setEditable(false);

				textFieldID.setText("///");
				textFieldword.setText("");

			}

		});
		updatebtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				textFieldword.setEditable(true);
				textFieldID.setEditable(true);

				textFieldID.setText("");
				textFieldword.setText("");

			}

		});
		deletebtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				textFieldword.setEditable(false);
				textFieldID.setEditable(true);

				textFieldID.setText("///");
				textFieldword.setText("");

			}

		});
		searchbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				textFieldword.setEditable(true);
				textFieldID.setEditable(true);

				textFieldID.setText("///");
				textFieldword.setText("");

			}

		});

		clearbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				textFieldword.setEditable(false);
				textFieldID.setEditable(false);

				textFieldID.setText("///");
				textFieldword.setText("///");

			}

		});

		tableData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				try {
					if (updatebtn.isSelected() || deletebtn.isSelected() || searchbtn.isSelected()) {
						if (e.getClickCount() == 1) {
							final JTable le = (JTable) e.getSource();
							final int r = le.getSelectedRow();
							final int c = le.getSelectedColumn();
							final String val = (String) le.getValueAt(r, c);
							textFieldword.setText(val);
						}
					}
				} catch (Exception e1) {
					logger.debug("tableData func triggerd an exception");

				}
			}
		});
	}

	public void table() {

		DefaultTableModel dtm = (DefaultTableModel) tableData.getModel();
		dtm.getDataVector().removeAllElements();
		dtm.fireTableDataChanged();
		words.clear();
		try {
			words = facadeBLL.getAllRoots();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int count = 1;
		for (int i = 0; i < words.size(); i = i + 3) {

			Object[] B_Dat = new Object[3];
			B_Dat[0] = count++;
			B_Dat[1] = words.get(i + 1);
			B_Dat[3] = words.get(i + 2);

			defaultmodel.addRow(B_Dat);
		}
		tableData.setModel(defaultmodel);

	}

}