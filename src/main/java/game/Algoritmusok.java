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

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/***
 * Az alkalmazás grafikus felületének működéséhez szükséges algoritmusok gyűjtő
 * osztálya.
 *
 */
public class Algoritmusok {

	/***
	 * Létrehozza a kövek megjelenítéséhez szükséges {@code JLabel} típusú
	 * objektumot.
	 * 
	 * @param row
	 *            a kő sorának száma a táblán
	 * @param col
	 *            a kő oszlopának száma a táblán
	 * @param c
	 *            a kő szine
	 * @param a
	 *            a kő állapota
	 * @return {@code Jlabel} típusú objektum
	 */
	public static JLabel createStone(int row, int col, String c, String a) {
		Integer[] data = getCorrectDataForStones(row, col);

		JLabel temp = new JLabel();
		if ("s".equals(a)) {
			if ("r".equals(c)) {
				temp = new JLabel(new ImageIcon(
						Malom.class.getResource("/redstoneSelected.png")));
				temp.setBounds(45 * data[1], 45 * data[0], 30, 30);
				temp.setVisible(false);
			} else {
				temp = new JLabel(new ImageIcon(
						Malom.class.getResource("/bluestoneSelected.png")));
				temp.setBounds(45 * data[1], 45 * data[0], 30, 30);
				temp.setVisible(false);
			}
		}
		if ("n".equals(a)) {
			if ("r".equals(c)) {
				temp = new JLabel(new ImageIcon(
						Malom.class.getResource("/redstone.png")));
				temp.setBounds(45 * data[1], 45 * data[0], 30, 30);
				temp.setVisible(false);
			} else {
				temp = new JLabel(new ImageIcon(
						Malom.class.getResource("/bluestone.png")));
				temp.setBounds(45 * data[1], 45 * data[0], 30, 30);
				temp.setVisible(false);
			}
		}
		if ("p".equals(a)) {
			temp = new JLabel(new ImageIcon(
					Malom.class.getResource("/possible.png")));
			temp.setBounds(45 * data[1], 45 * data[0], 30, 30);
			temp.setVisible(false);
		}
		return temp;
	}

	/***
	 * Az állapottér reprezentáció és a megjelenített tábla között sor oszlop
	 * eltolódások korigálására szolgál.
	 * 
	 * @param r
	 *            a reprezentációban található sorszám
	 * @param c
	 *            a reprezentációban található oszlop szám
	 * @return {@code Integer} tömbben a helyes sor és oszlop szám
	 */
	public static Integer[] getCorrectDataForStones(int r, int c) {
		Integer[] res = new Integer[2];
		int col = -1;
		switch (r) {
		case 0:
		case 7:
			switch (c) {
			case 0:
				col = 0;
				break;
			case 1:
				col = 3;
				break;
			case 2:
				col = 6;
				break;
			}
			break;

		case 1:
		case 6: {
			switch (c) {
			case 0:
				col = 1;
				break;
			case 1:
				col = 3;
				break;
			case 2:
				col = 5;
				break;
			}
			break;
		}

		case 2:
		case 5: {
			switch (c) {
			case 0:
				col = 2;
				break;
			case 1:
				col = 3;
				break;
			case 2:
				col = 4;
				break;
			}
			break;
		}

		case 3: {
			switch (c) {
			case 0:
				col = 0;
				break;
			case 1:
				col = 1;
				break;
			case 2:
				col = 2;
				break;
			}
			break;
		}

		case 4: {
			switch (c) {
			case 0:
				col = 4;
				break;
			case 1:
				col = 5;
				break;
			case 2:
				col = 6;
				break;
			}
			break;
		}
		}

		res[0] = r < 4 ? r : r - 1;
		res[1] = col;

		return res;
	}

	/***
	 * Létrehozza a grafikus felületre felkerülő összes követ.
	 * 
	 * @return {@code StoneType} típusú listában a kövek.
	 */
	public static List<StoneType> getStones() {
		List<StoneType> res = new ArrayList<StoneType>();

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 3; c++) {
				res.add(new StoneType("n", "r", false, r, c));
				res.add(new StoneType("n", "b", false, r, c));
				res.add(new StoneType("s", "r", false, r, c));
				res.add(new StoneType("s", "b", false, r, c));
				res.add(new StoneType("n", "p", false, r, c));
			}
		}

		return res;
	}

	/***
	 * Eldönti egy kőről, hogy azt a követ keressük-e.
	 * 
	 * @param st
	 *            a kő amit vizsgálunk
	 * @param r
	 *            a táblán a sor száma a kőnek
	 * @param c
	 *            a táblán az oszlop száma a kőnek
	 * @param t
	 *            a táblát tartalmazó szerkezet
	 * @return a keresett kő volt-e vagy sem
	 */
	private static boolean isWanted(StoneType st, int r, int c, TableType t) {
		String color = t.getTable()[r][c] == 1 ? "r" : "b";
		if (st.getRow() == r && st.getCol() == c) {
			if (st.getColor().equals(color)) {
				if (st.getState().equals("n"))
					return true;
			}
		}

		return false;
	}

	/***
	 * Megadja hogy az adott pozíción történő kattintással követ választottak-e
	 * ki.
	 * 
	 * @param st
	 *            a követ szimbolizáló kő típus
	 * @param me
	 *            az egér-esemény
	 * @return visszaadja a találat helyességét
	 */
	public static boolean isClicked(StoneType st, MouseEvent me) {
		Integer[] data = Algoritmusok.getCorrectDataForStones(st.getRow(),
				st.getCol());
		int laMidX = (int) ((data[1]) * 45 + 15);
		int laMidY = (int) ((data[0]) * 45 + 15 + 20);

		if (Math.abs(laMidX - me.getX()) < 15) {
			if (Math.abs(laMidY - me.getY()) < 15) {
				return true;
			}
		}
		return false;
	}

	/***
	 * A grafikus felületen egy kővel veló alrébb ugrás operátorát felhasználó metódus.
	 * 
	 * @param me az egér esemény 
	 * @return igaz sa sikerült ugrani, hamis egyébként
	 */
	public static boolean jumpStone(MouseEvent me) {
		if (MainScreen.haveSelected) {
			for (StoneType st : MainScreen.stones) {
				if (MainScreen.malom.roundCounter % 2 == 1) {
					if (st.getState().equals("n"))
						if (st.getColor().equals("r"))
							if (isClicked(st, me)) {
								MalomOperator mo = new MalomOperator(
										MainScreen.selected.getCol(),
										MainScreen.selected.getRow(),
										st.getCol(), st.getRow(),
										MainScreen.malom.t);
								if (mo.operatorJump(MainScreen.malom.roundCounter)) {
									st.setVisible(true);
									for (StoneType sta : MainScreen.stones) {
										if (sta.equals(MainScreen.selected)) {
											sta.setVisible(false);
										}
									}
									return true;
								} else {
									JOptionPane.showMessageDialog(null,
											"Nem léphetsz oda");
									return false;
								}
							}
				} else {
					if (st.getState().equals("n"))
						if (st.getColor().equals("b"))
							if (isClicked(st, me)) {
								MalomOperator mo = new MalomOperator(
										MainScreen.selected.getCol(),
										MainScreen.selected.getRow(),
										st.getCol(), st.getRow(),
										MainScreen.malom.t);
								if (mo.operatorJump(MainScreen.malom.roundCounter)) {
									st.setVisible(true);
									for (StoneType sta : MainScreen.stones) {
										if (sta.equals(MainScreen.selected)) {
											sta.setVisible(false);
										}
									}
									return true;
								} else {
									JOptionPane.showMessageDialog(null,
											"Nem léphetsz oda");
									return false;
								}
							}
				}
			}
		} else {
			for (StoneType st : MainScreen.stones) {
				if (MainScreen.malom.roundCounter % 2 == 1) {
					if (st.getState().equals("s"))
						if (st.getColor().equals("r"))
							if (isClicked(st, me)) {
								MainScreen.haveSelected = true;
								MainScreen.selected = st;
								st.setVisible(true);
								for (StoneType sta : MainScreen.stones) {
									if (sta.getState().equals("n")) {
										if (sta.getColor().equals("r")) {
											if (isClicked(sta, me)) {
												sta.setVisible(false);
											}
										}
									}
								}
								return false;
							}
				} else {
					if (st.getState().equals("s"))
						if (st.getColor().equals("b"))
							if (isClicked(st, me)) {
								MainScreen.haveSelected = true;
								MainScreen.selected = st;
								st.setVisible(true);
								for (StoneType sta : MainScreen.stones) {
									if (sta.getState().equals("n")) {
										if (sta.getColor().equals("b")) {
											if (isClicked(sta, me)) {
												sta.setVisible(false);
											}
										}
									}
								}
								return false;
							}
				}
			}
		}
		return false;
	}

	/***
	 * Az egy kővel alrébb lépés operátorát felhasználó metódus.
	 * 
	 * @param me egér esemény
	 * @return	igaz, ha sikerült a lépés, hamis egyébként
	 */
	public static boolean moveStone(MouseEvent me) {
		if (MainScreen.haveSelected) {
			for (StoneType st : MainScreen.stones) {
				if (MainScreen.malom.roundCounter % 2 == 1) {
					if (st.getState().equals("n"))
						if (st.getColor().equals("r"))
							if (isClicked(st, me)) {
								MalomOperator mo = new MalomOperator(
										MainScreen.selected.getCol(),
										MainScreen.selected.getRow(),
										st.getCol(), st.getRow(),
										MainScreen.malom.t);
								if (mo.operatorMove(MainScreen.malom.roundCounter)) {
									st.setVisible(true);
									for (StoneType sta : MainScreen.stones) {
										if (sta.equals(MainScreen.selected)) {
											sta.setVisible(false);
										}
									}
									return true;
								} else {
									JOptionPane.showMessageDialog(null,
											"Nem léphetsz oda");
									return false;
								}
							}
				} else {
					if (st.getState().equals("n"))
						if (st.getColor().equals("b"))
							if (isClicked(st, me)) {
								MalomOperator mo = new MalomOperator(
										MainScreen.selected.getCol(),
										MainScreen.selected.getRow(),
										st.getCol(), st.getRow(),
										MainScreen.malom.t);
								if (mo.operatorMove(MainScreen.malom.roundCounter)) {
									st.setVisible(true);
									for (StoneType sta : MainScreen.stones) {
										if (sta.equals(MainScreen.selected)) {
											sta.setVisible(false);
										}
									}
									return true;
								} else {
									JOptionPane.showMessageDialog(null,
											"Nem léphetsz oda");
									return false;
								}
							}
				}
			}
		} else {
			for (StoneType st : MainScreen.stones) {
				if (MainScreen.malom.roundCounter % 2 == 1) {
					if (st.getState().equals("s"))
						if (st.getColor().equals("r"))
							if (isClicked(st, me)) {
								MainScreen.haveSelected = true;
								MainScreen.selected = st;
								st.setVisible(true);
								for (StoneType sta : MainScreen.stones) {
									if (sta.getState().equals("n")) {
										if (sta.getColor().equals("r")) {
											if (isClicked(sta, me)) {
												sta.setVisible(false);
											}
										}
									}
								}
								return false;
							}
				} else {
					if (st.getState().equals("s"))
						if (st.getColor().equals("b"))
							if (isClicked(st, me)) {
								MainScreen.haveSelected = true;
								MainScreen.selected = st;
								st.setVisible(true);
								for (StoneType sta : MainScreen.stones) {
									if (sta.getState().equals("n")) {
										if (sta.getColor().equals("b")) {
											if (isClicked(sta, me)) {
												sta.setVisible(false);
											}
										}
									}
								}
								return false;
							}
				}
			}
		}
		return false;
	}

	/***
	 * A kő letételét szolgáló operátorral dolgozó metódus.
	 * 
	 * @param me egér esemény
	 */
	public static void putStone(MouseEvent me) {
		for (StoneType st : MainScreen.stones) {
			if (MainScreen.malom.roundCounter % 2 == 1) {
				if (st.getState().equals("n"))
					if (st.getColor().equals("r"))
						if (isClicked(st, me)) {
							MalomOperator mo = new MalomOperator(st.getCol(),
									st.getRow(), MainScreen.malom.t);
							st.setVisible(true);
							mo.operatorPut(MainScreen.malom.roundCounter,
									MainScreen.malom.playerOne,
									MainScreen.malom.playerTwo);
						}

			} else {
				if (st.getState().equals("n"))
					if (st.getColor().equals("b"))
						if (isClicked(st, me)) {
							MalomOperator mo = new MalomOperator(st.getCol(),
									st.getRow(), MainScreen.malom.t);
							st.setVisible(true);
							mo.operatorPut(MainScreen.malom.roundCounter,
									MainScreen.malom.playerOne,
									MainScreen.malom.playerTwo);
						}
			}
		}
	}

	/***
	 * A kő elvételét implementáló operátort felhasználó metódus.
	 * 
	 * @param me egér esemény
	 * @return igaz, ha sikerült elvenni, hamis egyébként.
	 */
	public static boolean removeStone(MouseEvent me) {
		for (StoneType st : MainScreen.stones) {
			if (MainScreen.malom.roundCounter % 2 == 1) {
				if (st.getState().equals("n"))
					if (st.getColor().equals("b"))
						if (isClicked(st, me)) {
							MalomOperator mo = new MalomOperator(
									MainScreen.malom.t);
							if (!mo.operatorRemove(st.getRow(), st.getCol(),
									MainScreen.malom.roundCounter,
									MainScreen.malom.playerOne,
									MainScreen.malom.playerTwo)) {
								JOptionPane.showMessageDialog(null,
										"Nem veheted le azt a követ.");
								return false;
							} else {
								st.setVisible(false);
								return true;
							}
						}
			} else {
				if (st.getState().equals("n"))
					if (st.getColor().equals("r"))
						if (isClicked(st, me)) {
							MalomOperator mo = new MalomOperator(
									MainScreen.malom.t);
							if (!mo.operatorRemove(st.getRow(), st.getCol(),
									MainScreen.malom.roundCounter,
									MainScreen.malom.playerOne,
									MainScreen.malom.playerTwo)) {
								JOptionPane.showMessageDialog(null,
										"Nem veheted le azt a követ.");
								return false;
							} else {
								st.setVisible(false);
								return true;
							}
						}
			}
		}
		return false;
	}

	/***
	 * A játék feladásakor frissíti az adatbázist.
	 */
	public static void felad() {
		if (Malom.roundCounter % 2 == 1) {
			try {
				Connection conn = ConnectionHandler.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from MALOM_DATABASE");

				List<TableRowType> data = new LinkedList<TableRowType>();
				while (rs.next()) {
					data.add(new TableRowType(rs.getString(2), rs.getInt(3), rs
							.getInt(4)));
				}

				Boolean winnerBeenSet = false;
				Boolean loserBeenSet = false;
				for (TableRowType trt : data) {
					if (trt.name.equals(Malom.playerOne.getName())) {
						st.executeUpdate("update MALOM_DATABASE set WINS="
								+ (trt.wins + 1) + " where NAME='" + trt.name
								+ "'");
						winnerBeenSet = true;
					}

					if (trt.name.equals(Malom.playerTwo.getName())) {
						st.executeUpdate("update MALOM_DATABASE set LOSES="
								+ (trt.loses + 1) + " where NAME='" + trt.name
								+ "'");
						loserBeenSet = true;
					}
				}
				if (!winnerBeenSet)
					st.execute("insert INTO MALOM_DATABASE (ID, NAME, WINS, LOSES) VALUES (seq_player.nextval, '"
							+ Malom.playerOne.getName() + "',1,0)");
				if (!loserBeenSet)
					st.execute("insert INTO MALOM_DATABASE (ID, NAME, WINS, LOSES) VALUES (seq_player.nextval, '"
							+ Malom.playerTwo.getName() + "',0,1)");

				st.executeQuery("commit");
			} catch (SQLException e) {
				e.getMessage();
			}
		} else {
			try {
				Connection conn = ConnectionHandler.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from MALOM_DATABASE");

				List<TableRowType> data = new LinkedList<TableRowType>();
				while (rs.next()) {
					data.add(new TableRowType(rs.getString(2), rs.getInt(3), rs
							.getInt(4)));
				}

				Boolean winnerBeenSet = false;
				Boolean loserBeenSet = false;
				for (TableRowType trt : data) {
					if (trt.name.equals(Malom.playerTwo.getName())) {
						st.executeUpdate("update MALOM_DATABASE set WINS="
								+ (trt.wins + 1) + " where NAME='" + trt.name
								+ "'");
						winnerBeenSet = true;
					}

					if (trt.name.equals(Malom.playerOne.getName())) {
						st.executeUpdate("update MALOM_DATABASE set LOSES="
								+ (trt.loses + 1) + " where NAME='" + trt.name
								+ "'");
						loserBeenSet = true;
					}
				}
				if (!winnerBeenSet)
					st.execute("insert INTO MALOM_DATABASE (ID, NAME, WINS, LOSES) VALUES (seq_player.nextval, '"
							+ Malom.playerTwo.getName() + "',1,0)");
				if (!loserBeenSet)
					st.execute("insert INTO MALOM_DATABASE (ID, NAME, WINS, LOSES) VALUES (seq_player.nextval, '"
							+ Malom.playerOne.getName() + "',0,1)");

				st.executeQuery("commit");
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		JFrame topList = new TopList();
		topList.setVisible(true);
	}
	
	/***
	 * A játék feladásakor frissíti az adatbázist.
	 */
	public static void felad(String c) {
		if (Malom.roundCounter % 2 == 1) {
			try {
				Connection conn = ConnectionHandler.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from MALOM_DATABASE");

				List<TableRowType> data = new LinkedList<TableRowType>();
				while (rs.next()) {
					data.add(new TableRowType(rs.getString(2), rs.getInt(3), rs
							.getInt(4)));
				}

				Boolean winnerBeenSet = false;
				Boolean loserBeenSet = false;
				for (TableRowType trt : data) {
					if (trt.name.equals(Malom.playerOne.getName())) {
						st.executeUpdate("update MALOM_DATABASE set WINS="
								+ (trt.wins + 1) + " where NAME='" + trt.name
								+ "'");
						winnerBeenSet = true;
					}

					if (trt.name.equals(Malom.playerTwo.getName())) {
						st.executeUpdate("update MALOM_DATABASE set LOSES="
								+ (trt.loses + 1) + " where NAME='" + trt.name
								+ "'");
						loserBeenSet = true;
					}
				}
				if (!winnerBeenSet)
					st.execute("insert INTO MALOM_DATABASE (ID, NAME, WINS, LOSES) VALUES (seq_player.nextval, '"
							+ Malom.playerOne.getName() + "',1,0)");
				if (!loserBeenSet)
					st.execute("insert INTO MALOM_DATABASE (ID, NAME, WINS, LOSES) VALUES (seq_player.nextval, '"
							+ Malom.playerTwo.getName() + "',0,1)");

				st.executeQuery("commit");
				
				for (TableRowType trt : data) {
					System.out.println(trt.name + " " + trt.wins + " " + trt.loses);
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		} else {
			try {
				Connection conn = ConnectionHandler.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from MALOM_DATABASE");

				List<TableRowType> data = new LinkedList<TableRowType>();
				while (rs.next()) {
					data.add(new TableRowType(rs.getString(2), rs.getInt(3), rs
							.getInt(4)));
				}

				Boolean winnerBeenSet = false;
				Boolean loserBeenSet = false;
				for (TableRowType trt : data) {
					if (trt.name.equals(Malom.playerTwo.getName())) {
						st.executeUpdate("update MALOM_DATABASE set WINS="
								+ (trt.wins + 1) + " where NAME='" + trt.name
								+ "'");
						winnerBeenSet = true;
					}

					if (trt.name.equals(Malom.playerOne.getName())) {
						st.executeUpdate("update MALOM_DATABASE set LOSES="
								+ (trt.loses + 1) + " where NAME='" + trt.name
								+ "'");
						loserBeenSet = true;
					}
				}
				if (!winnerBeenSet)
					st.execute("insert INTO MALOM_DATABASE (ID, NAME, WINS, LOSES) VALUES (seq_player.nextval, '"
							+ Malom.playerTwo.getName() + "',1,0)");
				if (!loserBeenSet)
					st.execute("insert INTO MALOM_DATABASE (ID, NAME, WINS, LOSES) VALUES (seq_player.nextval, '"
							+ Malom.playerOne.getName() + "',0,1)");

				st.executeQuery("commit");
				
				for (TableRowType trt : data) {
					System.out.println(trt.name + " " + trt.wins + " " + trt.loses);
				}
				
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
	}

}
