package GUI;

/*
 * #%L
 * Malom-Game
 * %%
 * Copyright (C) 2015 Berkó-gép
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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

import service.ConnectionHandler;

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
	 */
	public static void main() {
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			
			rs.close();

		} catch (SQLException e) {
			e.getMessage();
		}

		Object[][] data = new Object[datar.size()][3];

		int i = 0;
		for (TableRowType tr : datar) {
			data[i][0] = tr.name;
			data[i][1] = tr.wins;
			data[i][2] = tr.loses;
			i++;
		}

		table = new JTable(data, header);
		scrollPane.setViewportView(table);
	}

}
