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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import bll.interfaces.IBLLFacade;

public class TokenizePage {

	private IBLLFacade facadeBLL;

	private JPanel crudOperationPanel;

	public static int poem_id;

	public static DefaultTableModel idVersesModel;

	public TokenizePage(IBLLFacade obj) {

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

		crudOperationPanel.add(new JLabel("Tokens-------------------------------"));
		crudOperationPanel.add(buttonRead);

		frame.add(crudOperationPanel);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(650, 400));
		crudOperationPanel.add(scrollPane);

		JButton rootAutoButton = new JButton("Auto Roots");

		crudOperationPanel.add(rootAutoButton);

		buttonRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				listPanel.removeAll();
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Token");
				model.addColumn("POS");

				for (int i = 0; i < idVersesModel.getRowCount(); i++) {

					List<Map<String, Object>> tokens = facadeBLL.getAllTokens((int) idVersesModel.getValueAt(i, 0));
					if (tokens.isEmpty()) {
						System.err.println("no tokens found------------");

					} else {

						for (Map<String, Object> token : tokens) {

							model.addRow(new Object[] { token.get("token"), token.get("pos") });

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

		rootAutoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(() -> new RootsAutoPage(facadeBLL).idVersesModel = idVersesModel);

			}
		});

		frame.setVisible(true);

	}

}
