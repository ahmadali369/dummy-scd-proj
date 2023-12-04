package pl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IBLLFacade facadeBLL;

	private static final Logger logger = LogManager.getLogger(VersesAdd.class);

	private JFrame frame;

	private JTextField misra1Field;
	private JTextField misra2Field;
	public static int poem_id;

	public VersesAdd(IBLLFacade obj) {
		this.facadeBLL = obj;

		frame = new JFrame("Arabic Poem Database");
		frame.setDefaultCloseOperation(PoemAdd.HIDE_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setLayout(new GridLayout(6, 3));

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenDimension.width - frame.getWidth()) / 2;
		int y = (screenDimension.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);

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

		frame.add(misra1Label);
		frame.add(misra1Field);
		frame.add(misra2Label);
		frame.add(misra2Field);
		frame.add(insertButton);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//		buttonPanel.add(chooseBookButton);

		frame.add(buttonPanel, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	private void insertVerse() {
//		String title = titleField.getText();
		String misra1 = misra1Field.getText();
		String misra2 = misra2Field.getText();

		VerseTO verse = new VerseTO();

		verse.setMisra1(misra1);
		verse.setMisra2(misra2);
		verse.setPoem_id(poem_id);

		try {

			facadeBLL.saveVerse(verse);

			JOptionPane.showMessageDialog(frame, "Verse inserted successfully.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			clearFields();
		} catch (SQLException ex) {
			logger.debug("insertVerse func triggerd an exception");
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error occurred while inserting poem.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void clearFields() {
		misra1Field.setText("");
		misra2Field.setText("");
	}
}
