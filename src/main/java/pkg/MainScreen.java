package pkg;

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


import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import pkg.*;

/***
 * A főképernyő grafikus felületi elemeit, az eseménykezelőket, és az azokhoz tartozó {@code private} metódusokat tartalmazó osztály.
 */
public class MainScreen {

	/***
	 * A loggoláshoz szükséges {@code Logger} objektum.
	 */
	private static Logger logger = LoggerFactory.getLogger(MainScreen.class);

	/***
	 * A {@code JFrame} objektum amin a grafikus felület elemei találhatóak.
	 */
	private JFrame frmMalom;
	
	/***
	 * A felületen található köveket tartalmazó {@code StoneType} típusú elemeket tartalmazó lista.
	 */
	public static final List<StoneType> stones = Algoritmusok.getStones();

	/**
	 * Elindítja az alkalmazást.
	 * 
	 * @param args az intáshoz felhasználható paraméterek
	 */
	public static void start(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frmMalom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Elkészíti az alkalmazást.
	 */
	public MainScreen() {
		initialize();
	}

	

	/***
	 * Elkészíti a keret tartalmát a megjelenítésre.
	 */
	private void initialize() {
		frmMalom = new JFrame();
		frmMalom.setResizable(false);
		frmMalom.setTitle("Malom " + Malom.playerOne.getName() + " - " + Malom.playerTwo.getName());
		frmMalom.setBounds(100, 100, 500, 340);
		frmMalom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImagePanel imgPanel = new ImagePanel(new ImageIcon(this.getClass()
				.getResource("/background.png")).getImage());
		imgPanel.setBorder(null);

		frmMalom.getContentPane().add(imgPanel);

		JLabel lblJtkos = new JLabel("1. játékos: " + Malom.playerOne.getName());
		lblJtkos.setForeground(Color.RED);
		lblJtkos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos.setBounds(305, 11, 179, 21);
		imgPanel.add(lblJtkos);

		JLabel lblJtkos_1 = new JLabel("2. játékos: " + Malom.playerTwo.getName());
		lblJtkos_1.setForeground(Color.BLUE);
		lblJtkos_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos_1.setBounds(305, 117, 179, 14);
		imgPanel.add(lblJtkos_1);

		JButton btnNextButton = new JButton("Következő");
		btnNextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				logger.info("next round");
				Malom.roundCounter++;
			}
		});
		btnNextButton.setBounds(305, 217, 119, 23);
		imgPanel.add(btnNextButton);

		JButton btnNewButton_1 = new JButton("Toplista");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFrame topList = new TopList();
				topList.setVisible(true);
				logger.info("TopList windows opened.");
			}
		});
		btnNewButton_1.setBounds(305, 243, 89, 23);
		imgPanel.add(btnNewButton_1);
		
		final JLabel lblKvekSzma_1 = new JLabel("Kövek száma: " + Malom.playerOne.getStones());
		lblKvekSzma_1.setBounds(305, 32, 112, 14);
		imgPanel.add(lblKvekSzma_1);
		
		final JLabel lblKvekATablan_1 = new JLabel("Kövek a táblán: " + Malom.playerOne.getOnBoardStones());
		lblKvekATablan_1.setBounds(305, 57, 112, 14);
		imgPanel.add(lblKvekATablan_1);
		
		final JLabel lblKvekSzma_2 = new JLabel("Kövek száma: " + Malom.playerTwo.getStones());
		lblKvekSzma_2.setBounds(305, 142, 101, 14);
		imgPanel.add(lblKvekSzma_2);
		
		final JLabel lblKvekATablan_2 = new JLabel("Kövek a táblán: " + Malom.playerTwo.getOnBoardStones());
		lblKvekATablan_2.setBounds(305, 167, 112, 14);
		imgPanel.add(lblKvekATablan_2);
		
		JLabel lblMostUgorhatsz_1 = new JLabel("Most ugorhatsz!");
		lblMostUgorhatsz_1.setBounds(305, 82, 112, 14);
		if (Malom.playerOne.canJump())
			lblMostUgorhatsz_1.setVisible(true);
		else
			lblMostUgorhatsz_1.setVisible(false);
		imgPanel.add(lblMostUgorhatsz_1);
		
		JLabel lblMostUgorhatsz_2 = new JLabel("Most ugorhatsz!");
		lblMostUgorhatsz_2.setBounds(305, 192, 101, 14);
		if (Malom.playerTwo.canJump())
			lblMostUgorhatsz_2.setVisible(true);
		else
			lblMostUgorhatsz_2.setVisible(false);
		imgPanel.add(lblMostUgorhatsz_2);
		
		JButton btnNewButton = new JButton("Adatb-t inicializal");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				logger.info("Database under data upload.");
				try {
					new XMLParser().updateDatabase();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		});
		btnNewButton.setBounds(305, 277, 129, 23);
		imgPanel.add(btnNewButton);
		
		JButton btnFelad = new JButton("Felad");
		btnFelad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logger.info(Malom.roundCounter % 2 == 1 ? "playerOne given up" : "playerTwo given up");
				Algoritmusok.felad();
				frmMalom.dispose();
			}
		});
		btnFelad.setBounds(412, 243, 72, 23);
		imgPanel.add(btnFelad);

		for (StoneType s : stones) {
			imgPanel.add(s.getLabel());
		}
		
		Algoritmusok.updateTable();
		Algoritmusok.showAvailableSpots();
		
		frmMalom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					for (StoneType st : stones) {
						if (st.getVisible())
							if (Algoritmusok.isClicked(st, arg0)) {
								Algoritmusok.updateTable();
								frmMalom.repaint();
							}
					}
				}

				if (arg0.getButton() == MouseEvent.BUTTON3) {
					Algoritmusok.putStone(arg0); 
					frmMalom.repaint();
					lblKvekSzma_1.setText("Kövek száma: " + Malom.playerOne.getStones());
					lblKvekATablan_1.setText("Kövek a táblán: " + Malom.playerOne.getOnBoardStones());
					lblKvekSzma_2.setText("Kövek száma: " + Malom.playerTwo.getStones());
					lblKvekATablan_2.setText("Kövek a táblán: " + Malom.playerTwo.getOnBoardStones());
				}
			}
		});

		frmMalom.setVisible(true);
	}
}
