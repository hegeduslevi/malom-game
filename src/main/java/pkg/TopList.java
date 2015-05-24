package pkg;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/***
 * Az eredmény táblázat megjelenítésére szolgáló felület.
 */
public class TopList extends JFrame {
	/***
	 * A tartalmat összefogó konténer.
	 */
	private JPanel contentPane;

	/***
	 * Az eredmények jól átlátható megjelenítésére szolgáló táblázat.
	 */
	private JTable table;

	/**
	 * Megnyitja az ablakot.
	 * 
	 * @param args
	 *            indítási paraméterek
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TopList frame = new TopList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Elkészíti a keret tartalmát.
	 */
	public TopList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		String[] header = { "Név", "Győzelmek", "Vereségek" };

		List<TableRowType> datar = new LinkedList<TableRowType>();

		try {
			Connection conn = ConnectionHandler.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from MALOM_DATABASE");

			while (rs.next()) {
				datar.add(new TableRowType(rs.getString(2), rs.getInt(3), rs
						.getInt(4)));
			}

		} catch (SQLException e) {
			e.getMessage();
		}

		Object[][] data = new Object[datar.size()][3];

		int i = 0;
		for (TableRowType tr : datar) {
			data[i][0] = tr.name;
			data[i][1] = tr.wins;
			data[i][2] = tr.loses;
		}

		table = new JTable(data, header);
		scrollPane.setViewportView(table);
	}

}
