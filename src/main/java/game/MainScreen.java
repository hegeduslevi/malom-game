package game;

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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/***
 * A főképernyő grafikus felületi elemeit, az eseménykezelőket, és az azokhoz
 * tartozó {@code private} metódusokat tartalmazó osztály.
 */
public class MainScreen {
	/**
	 * A játék rendszerét felölelő osztály.
	 */
	public static Malom malom;
	/***
	 * A loggoláshoz szükséges {@code Logger} objektum.
	 */
	private static Logger logger = LoggerFactory.getLogger(MainScreen.class);

	/***
	 * A {@code JFrame} objektum amin a grafikus felület elemei találhatóak.
	 */
	private JFrame frmMalom;

	/***
	 * A felületen található köveket tartalmazó {@code StoneType} típusú
	 * elemeket tartalmazó lista.
	 */
	public static final List<StoneType> stones = Algoritmusok.getStones();

	/**
	 * A lépések játék körökre osztását elősegítő változó, segítségével ellenőrizhető, hogy lépett-e már az adott játékos.
	 */
	public static boolean hasPlayerTakenTheStep = false;
	
	/**
	 * Megadja egy több lépéses művelet esetén hogy a kiinduló követ kiválaszották-e már.
	 */
	public static boolean haveSelected = false;
	
	/**
	 * többlépéses művelet esetén, a kiválaszott kő adatait tartalmazó objektum.
	 */
	public static StoneType selected;

	/**
	 * Elindítja az alkalmazást.
	 * 
	 * @param args
	 *            az intáshoz felhasználható paraméterek
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
		malom = new Malom();

		initialize();
	}

	/***
	 * Elkészíti a keret tartalmát a megjelenítésre.
	 */
	private void initialize() {
		frmMalom = new JFrame();
		frmMalom.setResizable(false);
		frmMalom.setTitle("Malom " + malom.playerOne.getName() + " - "
				+ malom.playerTwo.getName());
		frmMalom.setBounds(100, 100, 550, 340);
		frmMalom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImagePanel imgPanel = new ImagePanel(new ImageIcon(this.getClass()
				.getResource("/background.png")).getImage());
		imgPanel.setBorder(null);

		frmMalom.getContentPane().add(imgPanel);

		final JLabel lblJtkos = new JLabel("1. játékos: "
				+ malom.playerOne.getName());
		lblJtkos.setForeground(Color.RED);
		lblJtkos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos.setBounds(305, 11, 179, 21);
		imgPanel.add(lblJtkos);

		final JLabel lblJtkos_step = new JLabel("1. játékos: "
				+ malom.playerOne.getName() + " - lép!");
		lblJtkos_step.setForeground(Color.RED);
		lblJtkos_step.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos_step.setBounds(305, 11, 179, 21);
		lblJtkos_step.setVisible(false);
		imgPanel.add(lblJtkos_step);

		final JLabel lblJtkos_1 = new JLabel("2. játékos: "
				+ malom.playerTwo.getName());
		lblJtkos_1.setForeground(Color.BLUE);
		lblJtkos_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos_1.setBounds(305, 117, 179, 14);
		imgPanel.add(lblJtkos_1);

		final JLabel lblJtkos_step_1 = new JLabel("2. játékos: "
				+ malom.playerTwo.getName() + " - lép!");
		lblJtkos_step_1.setForeground(Color.BLUE);
		lblJtkos_step_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos_step_1.setBounds(305, 117, 179, 14);
		lblJtkos_step_1.setVisible(false);
		imgPanel.add(lblJtkos_step_1);

		JButton btnNextButton = new JButton("Következő");
		btnNextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				logger.info("next round");
				malom.roundCounter++;
				hasPlayerTakenTheStep = false;
				haveSelected = false;
				if (malom.roundCounter % 2 == 1) {
					lblJtkos.setVisible(false);
					lblJtkos_step.setVisible(true);
					lblJtkos_1.setVisible(true);
					lblJtkos_step_1.setVisible(false);
					frmMalom.repaint();
				} else {
					lblJtkos.setVisible(true);
					lblJtkos_step.setVisible(false);
					lblJtkos_1.setVisible(false);
					lblJtkos_step_1.setVisible(true);
					frmMalom.repaint();
				}
			}
		});
		btnNextButton.setBounds(305, 217, 129, 23);
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
		btnNewButton_1.setBounds(305, 243, 129, 23);
		imgPanel.add(btnNewButton_1);

		final JLabel lblKvekSzma_1 = new JLabel("Kövek száma: "
				+ Malom.playerOne.getStones());
		lblKvekSzma_1.setBounds(305, 32, 112, 14);
		imgPanel.add(lblKvekSzma_1);

		final JLabel lblKvekATablan_1 = new JLabel("Kövek a táblán: "
				+ Malom.playerOne.getOnBoardStones());
		lblKvekATablan_1.setBounds(305, 57, 112, 14);
		imgPanel.add(lblKvekATablan_1);

		final JLabel lblKvekSzma_2 = new JLabel("Kövek száma: "
				+ Malom.playerTwo.getStones());
		lblKvekSzma_2.setBounds(305, 142, 101, 14);
		imgPanel.add(lblKvekSzma_2);

		final JLabel lblKvekATablan_2 = new JLabel("Kövek a táblán: "
				+ Malom.playerTwo.getOnBoardStones());
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
				logger.info(Malom.roundCounter % 2 == 1 ? "playerOne given up"
						: "playerTwo given up");
				Algoritmusok.felad();
				frmMalom.dispose();
			}
		});
		btnFelad.setBounds(444, 217, 89, 23);
		imgPanel.add(btnFelad);

		final JLabel lblLevehetszKovet_1 = new JLabel("Levehetsz egy követ.");
		lblLevehetszKovet_1.setBounds(409, 32, 124, 14);
		lblLevehetszKovet_1.setVisible(false);
		imgPanel.add(lblLevehetszKovet_1);

		final JLabel lblLevehetszKovet_2 = new JLabel("Levehetsz egy követ.");
		lblLevehetszKovet_2.setBounds(409, 142, 124, 14);
		lblLevehetszKovet_2.setVisible(false);
		imgPanel.add(lblLevehetszKovet_2);

		for (StoneType s : stones) {
			imgPanel.add(s.getLabel());
		}

		// Algoritmusok.updateTable();

		frmMalom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					if (!hasPlayerTakenTheStep) {
						if (new MalomOperator(malom.t).canRemove(
								malom.roundCounter, malom.playerOne,
								malom.playerTwo, malom)) {
							hasPlayerTakenTheStep = Algoritmusok
									.removeStone(arg0);

							logger.info("remove");

							{
								malom.previousMalmok = malom.malmok;
								malom.malmok = new MalomOperator(malom.t)
										.getMalmok();
								logger.info("malom positions updated");
							}

							lblKvekSzma_1.setText("Kövek száma: "
									+ malom.playerOne.getStones());
							lblKvekATablan_1.setText("Kövek a táblán: "
									+ malom.playerOne.getOnBoardStones());
							lblKvekSzma_2.setText("Kövek száma: "
									+ malom.playerTwo.getStones());
							lblKvekATablan_2.setText("Kövek a táblán: "
									+ malom.playerTwo.getOnBoardStones());

							if (malom.roundCounter % 2 == 1)
								lblLevehetszKovet_1.setVisible(false);
							else
								lblLevehetszKovet_2.setVisible(false);

							frmMalom.repaint();
						} else {
							if (new MalomOperator(malom.t).canJump(
									malom.roundCounter, malom.playerOne,
									malom.playerTwo)) {
								logger.info("jump " + haveSelected);
								hasPlayerTakenTheStep = Algoritmusok
										.jumpStone(arg0);
								frmMalom.repaint();

								

								if (hasPlayerTakenTheStep) {
									{
										malom.previousMalmok = malom.malmok;
										malom.malmok = new MalomOperator(malom.t)
												.getMalmok();
										logger.info("malom positions updated");
									}
									
									if (new MalomOperator(malom.t).canRemove(
											malom.roundCounter,
											malom.playerOne, malom.playerTwo,
											malom)) {
										if (malom.roundCounter % 2 == 1)
											lblLevehetszKovet_1
													.setVisible(true);
										else
											lblLevehetszKovet_2
													.setVisible(true);
										frmMalom.repaint();

										hasPlayerTakenTheStep = false;
									}
								}
							}

							if (new MalomOperator(malom.t).canMove(
									malom.roundCounter, malom.playerOne,
									malom.playerTwo)) {
								logger.info("move " + haveSelected);
								hasPlayerTakenTheStep = Algoritmusok
										.moveStone(arg0);
								frmMalom.repaint();

								

								if (hasPlayerTakenTheStep) {
									{
										malom.previousMalmok = malom.malmok;
										malom.malmok = new MalomOperator(malom.t)
												.getMalmok();
										logger.info("malom positions updated");
									}

									
									if (new MalomOperator(malom.t).canRemove(
											malom.roundCounter,
											malom.playerOne, malom.playerTwo,
											malom)) {

										if (malom.roundCounter % 2 == 1)
											lblLevehetszKovet_1
													.setVisible(true);
										else
											lblLevehetszKovet_2
													.setVisible(true);
										frmMalom.repaint();

										hasPlayerTakenTheStep = false;
									}
								}
							} else {
								JOptionPane
										.showMessageDialog(frmMalom,
												"Még nem raktál le minden követ - Jobb gomb");
							}
						}
					} else {
						JOptionPane.showMessageDialog(frmMalom,
								"Már léptél, nyomd meg a 'következő' gombot!");
					}

				}

				if (arg0.getButton() == MouseEvent.BUTTON3) {
					if (!hasPlayerTakenTheStep) {
						if (new MalomOperator(malom.t).canPut(
								malom.roundCounter, malom.playerOne,
								malom.playerTwo)) {
							Algoritmusok.putStone(arg0);
							
							{
								malom.previousMalmok = malom.malmok;
								malom.malmok = new MalomOperator(malom.t)
										.getMalmok();
								logger.info("malom positions updated");
							}
							
							hasPlayerTakenTheStep = true;
							
							if (new MalomOperator(malom.t).canRemove(
									malom.roundCounter, malom.playerOne,
									malom.playerTwo, malom)) {
								if (malom.roundCounter % 2 == 1)
									lblLevehetszKovet_1.setVisible(true);
								else
									lblLevehetszKovet_2.setVisible(true);
								frmMalom.repaint();

								hasPlayerTakenTheStep = false;
							}
						} else {
							JOptionPane.showMessageDialog(frmMalom,
									"Már minden követ lehejeztél");
						}
					} else {
						logger.info("" + malom.roundCounter + " "
								+ hasPlayerTakenTheStep);
						JOptionPane.showMessageDialog(frmMalom,
								"Már léptél, nyomd meg a 'következő' gombot!");
					}

					lblKvekSzma_1.setText("Kövek száma: "
							+ malom.playerOne.getStones());
					lblKvekATablan_1.setText("Kövek a táblán: "
							+ malom.playerOne.getOnBoardStones());
					lblKvekSzma_2.setText("Kövek száma: "
							+ malom.playerTwo.getStones());
					lblKvekATablan_2.setText("Kövek a táblán: "
							+ malom.playerTwo.getOnBoardStones());
					frmMalom.repaint();
				}
			}
		});

		frmMalom.setVisible(true);
	}

}
