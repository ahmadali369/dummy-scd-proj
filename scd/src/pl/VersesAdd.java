
package pl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bll.interfaces.IBLLFacade;
import transferObjects.VerseTO;

public class VersesAdd extends JFrame {
	private static final long serialVersionUID = 1L;
	private IBLLFacade facadeBLL;
	private static final Logger logger = LogManager.getLogger(VersesAdd.class);
	private JFrame frame;
	private List<JTextField> dynamicMisra1TextFields = new ArrayList<>();
	private List<JTextField> dynamicMisra2TextFields = new ArrayList<>();
	private JTextField misra1Field;
	private JTextField misra2Field;
	public static int poem_id;
	public int size = 200;

	public VersesAdd(IBLLFacade obj) {
		this.facadeBLL = obj;

		frame = new JFrame("Arabic Poem Database");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(400, size);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenDimension.width - frame.getWidth()) / 2;
		int y = (screenDimension.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);

		JButton plusButton = new JButton("+");
		plusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPoemTextField();
			}
		});

		JLabel misra1Label = new JLabel("Misra 1:");
		misra1Field = new JTextField();
		JLabel misra2Label = new JLabel("Misra 2:");
		misra2Field = new JTextField();

		JButton insertButton = new JButton("Insert Verse");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertVerse();
			}
		});

		frame.add(createMisraPanel(misra1Label, misra1Field));
		frame.add(createMisraPanel(misra2Label, misra2Field));

		dynamicMisra1TextFields.add(misra1Field);
		dynamicMisra2TextFields.add(misra2Field);

		frame.add(Box.createVerticalStrut(10));
		frame.add(plusButton);
		frame.add(insertButton);

		frame.setVisible(true);
	}

	private JPanel createMisraPanel(JLabel label, JTextField textField) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(label);
		panel.add(textField);
		textField.setPreferredSize(new Dimension(300, textField.getPreferredSize().height));
		return panel;
	}

	private void addPoemTextField() {
		size += 100;
		JLabel misra1Label = new JLabel("Misra 1:");
		JLabel misra2Label = new JLabel("Misra 2:");
		JTextField newMisra1TextField = new JTextField();
		JTextField newMisra2TextField = new JTextField();

		JPanel misra1Panel = createMisraPanel(misra1Label, newMisra1TextField);
		JPanel misra2Panel = createMisraPanel(misra2Label, newMisra2TextField);

		JPanel verseJPanel = new JPanel();
		verseJPanel.setLayout(new BoxLayout(verseJPanel, BoxLayout.Y_AXIS));

		verseJPanel.add(misra1Panel);
		verseJPanel.add(misra2Panel);

		dynamicMisra1TextFields.add(newMisra1TextField);
		dynamicMisra2TextFields.add(newMisra2TextField);

		int plusButtonIndex = frame.getContentPane().getComponentCount() - 3;
		frame.getContentPane().add(verseJPanel, plusButtonIndex);

		frame.setSize(400, size);
		frame.revalidate();
		frame.repaint();
	}

	private void insertVerse() {

		List<String> misra1DataList = new ArrayList<>();
		List<String> misra2DataList = new ArrayList<>();

		for (JTextField textField : dynamicMisra1TextFields) {
			misra1DataList.add(textField.getText());

		}
		for (JTextField textField : dynamicMisra2TextFields) {
			misra2DataList.add(textField.getText());
		}

		for (int i = 0; i < misra1DataList.size(); i++) {
			String misra1 = misra1DataList.get(i).toString();
			String misra2 = misra2DataList.get(i).toString();

			VerseTO verse = new VerseTO();
			verse.setMisra1(misra1);
			verse.setMisra2(misra2);
			verse.setPoem_id(poem_id);

			try {
				facadeBLL.saveVerse(verse);
				JOptionPane.showMessageDialog(frame, "Verse inserted successfully.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException ex) {
				logger.debug("insertVerse func triggered an exception");
				ex.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Error occurred while inserting poem.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

}
