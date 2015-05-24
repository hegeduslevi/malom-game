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

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/***
 * Az alkalmazások működéséhez szükséges algoritmusok gyűjtő osztálya.
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
	 * Eldönti 3 kőről, hogy malom állásban vannak-e.
	 * 
	 * @param r1
	 *            a reprezentáló szerekezetben az első kő sorának száma
	 * @param c1
	 *            a reprezentáló szerekezetben az első kő oszlopának száma
	 * @param r2
	 *            a reprezentáló szerekezetben az második kő sorának száma
	 * @param c2
	 *            a reprezentáló szerekezetben az második kő oszlopának száma
	 * @param r3
	 *            a reprezentáló szerekezetben az harmadik kő sorának száma
	 * @param c3
	 *            a reprezentáló szerekezetben az harmadik kő oszlopának száma
	 * @param table
	 *            a reprezentáló szerkezet
	 * 
	 * @return malom állásban áll-e a három kő.
	 */
	public static boolean isMalom(int r1, int c1, int r2, int c2, int r3,
			int c3, Integer[][] table) {
		if (table[r1][c1] != 0) {
			if (table[r1][c1] == table[r2][c2])
				if (table[r2][c2] == table[r3][c3])
					return true;
		}
		return false;
	}

	/***
	 * Megmondja egy kőről, hogy az malomban van-e.
	 * 
	 * @param row
	 *            a kő sor száma
	 * @param col
	 *            a kő oszlop száma
	 * @param color
	 *            a kő színe
	 * @param t
	 *            a táblázat amelyben az adatok vannak
	 * @return malomban van-e
	 */
	public static boolean isStoneInMalom(int row, int col, String color,
			TableType t) {
		if ((row >= 1 && row <= 6) && (col >= 1 && col <= 6)) {
			if (isMalom(row - 1, col, row, col, row + 1, col, t.getTable()))
				return true;
			if (isMalom(row, col - 1, row, col, row, col + 1, t.getTable()))
				return true;
		}
		if (row == 0) {
			if (isMalom(row, col, row + 1, col, row + 2, col, t.getTable()))
				return true;
			if (isMalom(row, col - 1, row, col, row, col + 1, t.getTable()))
				return true;
		}

		if (col == 0) {
			if (isMalom(row - 1, col, row, col, row + 1, col, t.getTable()))
				return true;
			if (isMalom(row, col, row, col + 1, row, col + 2, t.getTable()))
				return true;
		}

		if (row == 7) {
			if (isMalom(row - 2, col, row - 1, col, row, col, t.getTable()))
				if (isMalom(row, col, row, col + 1, row, col + 2, t.getTable()))
					return true;
		}

		if (col == 2) {
			if (isMalom(row - 1, col, row, col, row + 1, col, t.getTable()))
				return true;
			if (isMalom(row, col - 2, row, col - 1, row, col, t.getTable()))
				return true;
		}
		return false;

	}

	/***
	 * Megadja a táblán előforduló malom állapotok listját.
	 * 
	 * @param t
	 *            a tábla állapotát tároló objektum
	 * @return a malom helyzetek számokkal reprezentált listája.
	 */
	public static List<Integer> getMalmok(Integer[][] t) {
		List<Integer> res = new ArrayList<Integer>();

		for (int i = 0; i < 8; i++) {
			if (isMalom(i, 0, i, 1, i, 2, t)) {
				res.add(i);
			}
		}

		if (isMalom(0, 0, 3, 0, 7, 0, t))
			res.add(8); // 1. oszlop

		if (isMalom(1, 0, 3, 1, 6, 0, t))
			res.add(9); // 2. oszlop

		if (isMalom(2, 0, 3, 2, 5, 0, t))
			res.add(10); // 3. oszlop

		if (isMalom(0, 1, 1, 1, 2, 1, t))
			res.add(11); // 4. oszlop felső

		if (isMalom(5, 1, 6, 1, 7, 0, t))
			res.add(12); // 4. oszlop alsó

		if (isMalom(2, 2, 4, 0, 5, 2, t))
			res.add(13); // 5. oszlop

		if (isMalom(1, 2, 4, 1, 6, 2, t))
			res.add(14); // 6. oszlop

		if (isMalom(0, 2, 4, 2, 7, 2, t))
			res.add(15); // 7. oszlop

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
	 * @return a keresett kő volt-e vagy sem
	 */
	private static boolean isWanted(StoneType st, int r, int c) {
		String color = Malom.t.getTable()[r][c] == 1 ? "r" : "b";
		if (st.getRow() == r && st.getCol() == c) {
			if (st.getColor().equals(color)) {
				if (st.getState().equals("n"))
					return true;
			}
		}

		return false;
	}

	/***
	 * A tábla állapota alapján frissíti a grafikus felületet.
	 */
	public static void updateTable() {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 3; c++) {
				if (Malom.t.getTable()[r][c] != 0) {
					for (StoneType st : MainScreen.stones) {
						if (isWanted(st, r, c)) {
							st.setVisible(true);
						}
					}
				}
			}
		}
	}

	/***
	 * Megadja, hogy egy lépés szabályos-e.
	 * 
	 * @param color
	 *            a kő színe
	 * @param row
	 *            a lépés céljának sora
	 * @param col
	 *            a lépés céljának oszlopa
	 * @return szabályos-e
	 */
	public boolean isValidStep(String color, int row, int col) {
		if ("b".equals(color)) {
			if (Malom.t.getTable()[row][col] == 0) {
				if (!isStoneInMalom(row, col, "c", Malom.t)) {
					return true;
				}
			}
		} else {
			if (Malom.t.getTable()[row][col] == 0) {
				if (!isStoneInMalom(row, col, "b", Malom.t)) {
					return true;
				}
			}
		}
		return false;
	}

	/***
	 * Megmutatja mely pozíciókra léphet, vagy tehet követ a játékos.
	 */
	public static void showAvailableSpots() {
		if (Malom.roundCounter % 2 == 1) {
			if ((Malom.playerOne.getOnBoardStones() != Malom.playerOne
					.getStones()) || Malom.playerOne.canJump()) {
				for (int r = 0; r < 8; r++)
					for (int c = 0; c < 3; c++)
						if (Malom.t.getTable()[r][c] == 0)
							for (StoneType st : MainScreen.stones)
								if (st.getRow() == r && st.getCol() == c)
									if (st.getState() == "p")
										st.setVisible(true);
				/*
				 * } else {
				 * 
				 * }
				 */
			}
		} else {
			if ((Malom.playerTwo.getOnBoardStones() != Malom.playerTwo
					.getStones()) || Malom.playerTwo.canJump()) {
				for (int r = 0; r < 8; r++)
					for (int c = 0; c < 3; c++)
						if (Malom.t.getTable()[r][c] == 0)
							for (StoneType st : MainScreen.stones)
								if (st.getRow() == r && st.getCol() == c)
									if (st.getState() == "p")
										st.setVisible(true);
			}
		}
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
	 * A táblára tesz egy követ.
	 * 
	 * @param me
	 *            az egér esemény
	 */
	public static void putStone(MouseEvent me) {
		if (Malom.roundCounter % 2 == 1) {
			for (StoneType st : MainScreen.stones) {
				if (!st.getVisible())
					if (isClicked(st, me))
						if ("r".equals(st.getColor()))
							if ("n".equals(st.getState())) {
								st.setVisible(true);
								Malom.playerOne
										.setOnBoardStones(Malom.playerOne
												.getOnBoardStones() + 1);
							}
			}
		} else {
			for (StoneType st : MainScreen.stones) {
				if (!st.getVisible())
					if (isClicked(st, me))
						if ("b".equals(st.getColor()))
							if ("n".equals(st.getState())) {
								st.setVisible(true);
								Malom.playerTwo
										.setOnBoardStones(Malom.playerTwo
												.getOnBoardStones() + 1);
							}
			}
		}
	}

}
