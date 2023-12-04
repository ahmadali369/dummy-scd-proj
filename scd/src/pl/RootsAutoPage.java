package pl;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bll.interfaces.IBLLFacade;

public class RootsAutoPage {

	private IBLLFacade facadeBLL;
	private JPanel crudOperationPanel;

	public static int poem_id;

	public static DefaultTableModel idVersesModel;

	public RootsAutoPage(IBLLFacade obj) {

		this.facadeBLL = obj;

		JFrame frame = new JFrame();

		frame.setSize(700, 500);
		frame.setTitle("Encyclopedia Of Arabic Poems");
		frame.setDefaultCloseOperation(VersesPage.HIDE_ON_CLOSE);

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenDimension.width - frame.getWidth()) / 2;
		int y = (screenDimension.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);

		crudOperationPanel = new JPanel();

		JButton buttonRead = new JButton("Reload");

		crudOperationPanel.add(new JLabel("Auto Roots-------------------------------"));
		crudOperationPanel.add(buttonRead);

		frame.add(crudOperationPanel);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(650, 400));
		crudOperationPanel.add(scrollPane);

		JButton verifyButton = new JButton("Verify Roots");
		crudOperationPanel.add(verifyButton);

		buttonRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				listPanel.removeAll();
				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("Token");
				model.addColumn("Root");
				model.addColumn("Status");

				for (int i = 0; i < idVersesModel.getRowCount(); i++) {

					List<Map<String, Object>> tokens = facadeBLL.getAllTokens((int) idVersesModel.getValueAt(i, 0));
					List<Map<String, Object>> roots = facadeBLL.getAllRoots((int) idVersesModel.getValueAt(i, 0));

					if (roots.isEmpty()) {
						System.err.println("no tokens found------------");

					} else {

						int size = Math.min(tokens.size(), roots.size());

						for (int j = 0; j < size; j++) {

							Map<String, Object> root = roots.get(j);
							Map<String, Object> token = tokens.get(j);

							model.addRow(new Object[] { token.get("token"), root.get("root"), root.get("status") });
						}

					}

				}

				JTable table = new JTable(model);
				JScrollPane scrollPane = new JScrollPane(table);
				listPanel.add(scrollPane);
				listPanel.revalidate();
				listPanel.repaint();

			}

		});

		verifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < idVersesModel.getRowCount(); i++) {
					facadeBLL.updateStatus((int) idVersesModel.getValueAt(i, 0));

				}

			}
		});

		frame.setVisible(true);
	}
}
