/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.List;

/**
 *	A játékban való mozgást lehetővé tevő metódusok és azok segédmetódusainak gyűjteménye.
 */
public class MalomOperator {
	/**
	 * Az X koordináta amelyről elmodzdulunk.
	 */
	int fromX;
	
	/**
	 * Az Y koordináta amelyről elmozdulunk.
	 */
	int fromY;
	
	/**
	 * Az X koordináta amelyre érkezünk.
	 */
	int toX;
	
	/**
	 * Az Y koordináta amelyre érkezünk.
	 */
	int toY;
	
	/**
	 * A játék állapottere, a tábla.
	 */
	TableType table;

	/**
	 * Letétel és elvétel esetén használható egyszerűsített konstruktor.
	 * 
	 * @param t az állapotteret tartalmazó objektum
	 */
	public MalomOperator(TableType t) {
		this.table = t;
	}
	
	/**
	 * A kő asztalra tételéhez elegendő adatot szolgáltató konstruktor.
	 * 
	 * @param x a cél oszlop szám
	 * @param y a cál sor szám
	 * @param t az állapotteret tartalmazó objektum
	 */
	public MalomOperator(int x, int y, TableType t) {
		this.toX = y;
		this.toY = x;
		this.table = t;
	}
	

	/**
	 * A telljes konstruktor.
	 * 
	 * @param fx az X koordináta amelyről elmozulunk
	 * @param fy az Y koordináta amelyről elmozdulunk
	 * @param tx az X koordináta amelyre érkezünk
	 * @param ty az Y koordináta amelyre érkezünk
	 * @param t  az állapotteret tartalmazó objektum
	 */
	public MalomOperator(int fx, int fy, int tx, int ty, TableType t) {
		this.fromX = fy;
		this.fromY = fx;
		this.toX = ty;
		this.toY = tx;
		this.table = t;
	}

	/**
	 * Eldönti hogy az adott helyzetben a kitűzött helyre léphetünk-e.
	 * 
	 * @param toX a cél X koordináta
	 * @param toY a cél Y koordináta
	 * @param t az állapotteret reprezentáló objektum
	 * @param roundCounter a játékos meghatározásához kellő számláló
	 * @return igaz, ha léphet, hamis egyébként
	 */
	public boolean isValidStep(int toX, int toY, TableType t,
			Integer roundCounter) {
		if (t.getTable()[toX][toY] == 0)
			return true;
		if (t.getTable()[toX][toY] == roundCounter % 2 + 1)
			return false;
		return false;
	}
	
	/***
	 * Meghatározza, hogy az adott sor és oszlop megtalálható-e az adott esetek között.
	 * 
	 * @param targetx a feldolgozandó x koordináta
	 * @param targety a feldolgozandó y koordináta
	 * @param r1 az első kő sor száma
	 * @param c1 az első kő oszlop száma
	 * @param r2 a második kő sor száma
	 * @param c2 a második kő oszlop száma
	 * @param r3 a harmadik kő sor száma
	 * @param c3 a harmadik kő oszlop száma
	 * @return igaz, ha megtalálható, hamis egyébként
	 */
	private boolean isUnderTest(int targetx, int targety, int r1, int c1,
			int r2, int c2, int r3, int c3) {
		if (targetx == r1 || targetx == r2 || targetx == r3)
			if (targety == c1 || targety == c2 || targety == c3)
				return true;

		return false;
	}

	/***
	 * Megmondja egy kőről, hogy az malomban van-e.
	 * 
	 * @param row
	 *            a kő sor száma
	 * @param col
	 *            a kő oszlop száma
	 * @param t
	 *            a táblázat amelyben az adatok vannak
	 * @return malomban van-e
	 */
	public boolean isStoneInMalom(int row, int col, TableType t) {

		for (int i = 0; i < 8; i++) {
			if (row == i && (col == 0 || col == 1 || col == 2))
				if (isMalom(i, 0, i, 1, i, 2, t.getTable())) {
					return true;
				}
		}

		if (isUnderTest(row, col, 0, 0, 3, 0, 7, 0))
			if (isMalom(0, 0, 3, 0, 7, 0, t.getTable()))
				return true; // 1. oszlop

		if (isUnderTest(row, col, 1, 0, 3, 1, 6, 0))
			if (isMalom(1, 0, 3, 1, 6, 0, t.getTable()))
				return true; // 2. oszlop

		if (isUnderTest(row, col, 2, 0, 3, 2, 5, 0))
			if (isMalom(2, 0, 3, 2, 5, 0, t.getTable()))
				return true; // 3. oszlop

		if (isUnderTest(row, col, 0, 1, 1, 1, 2, 1))
			if (isMalom(0, 1, 1, 1, 2, 1, t.getTable()))
				return true; // 4. oszlop felső

		if (isUnderTest(row, col, 5, 1, 6, 1, 7, 0))
			if (isMalom(5, 1, 6, 1, 7, 0, t.getTable()))
				return true; // 4. oszlop alsó

		if (isUnderTest(row, col, 2, 2, 4, 0, 5, 2))
			if (isMalom(2, 2, 4, 0, 5, 2, t.getTable()))
				return true; // 5. oszlop

		if (isUnderTest(row, col, 1, 2, 4, 1, 6, 2))
			if (isMalom(1, 2, 4, 1, 6, 2, t.getTable()))
				return true; // 6. oszlop

		if (isUnderTest(row, col, 0, 2, 4, 2, 7, 2))
			if (isMalom(0, 2, 4, 2, 7, 2, t.getTable()))
				return true; // 7. oszlop

		return false;
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
	public boolean isMalom(int r1, int c1, int r2, int c2, int r3, int c3,
			Integer[][] table) {
		if (table[r1][c1] != 0) {
			if (table[r1][c1] == table[r2][c2])
				if (table[r2][c2] == table[r3][c3])
					return true;
		}
		return false;
	}

	/**
	 * A táblára egy kő letevését teszi lehetővé.
	 * 
	 * @param roundCounter az aktuális jétékos meghatározásához kell
	 */
	public void operatorPut(Integer roundCounter, PlayerType playerOne, PlayerType playerTwo) {
		if (isValidStep(toX, toY, table, roundCounter)) {
			table.setTable(toX, toY, (roundCounter-1) % 2 + 1);
			if (roundCounter % 2 == 1) { 
				playerOne.setOnBoardStones(playerOne.getOnBoardStones() + 1);
			}
			else {
				playerTwo.setOnBoardStones(playerTwo.getOnBoardStones() + 1);
			}
		}
	}

	/**
	 * A táblán egy kővel alrébb lépést tesz lehetővé.
	 * 
	 * @param roundCounter az aktuális játékos meghatározásához kell
	 */
	public boolean operatorMove(Integer roundCounter) {
		if (isValidStep(toX, toY, table, roundCounter)) {
			if (fromX == 0 && fromY == 0) {
				if (toX == 0 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 3 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 0 && fromY == 1) {
				if (toX == 0 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 1 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 0 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 0 && fromY == 2) {
				if (toX == 0 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 1 && fromY == 0) {
				if (toX == 1 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 3 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 1 && fromY == 1) {
				if (toX == 0 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 1 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 1 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 2 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 1 && fromY == 2) {
				if (toX == 1 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 2 && fromY == 0) {
				if (toX == 2 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 3 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 2 && fromY == 1) {
				if (toX == 1 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 2 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 2 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}

			if (fromX == 2 && fromY == 2) {
				if (toX == 2 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 3 && fromY == 0) {
				if (toX == 0 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 7 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 3 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 3 && fromY == 1) {
				if (toX == 3 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 1 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 3 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 3 && fromY == 2) {
				if (toX == 3 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 2 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 5 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 4 && fromY == 0) {
				if (toX == 2 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 5 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 4 && fromY == 1) {
				if (toX == 4 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 1 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 4 && fromY == 2) {
				if (toX == 4 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 0 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 7 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 5 && fromY == 0) {
				if (toX == 3 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 5 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 5 && fromY == 1) {
				if (toX == 5 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 5 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 5 && fromY == 2) {
				if (toX == 5 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 6 && fromY == 0) {
				if (toX == 3 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 6 && fromY == 1) {
				if (toX == 6 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 5 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 7 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 6 && fromY == 2) {
				if (toX == 6 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 7 && fromY == 0) {
				if (toX == 3 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 7 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 7 && fromY == 1) {
				if (toX == 7 && toY == 0) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 6 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 7 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
			
			if (fromX == 7 && fromY == 2) {
				if (toX == 7 && toY == 1) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
				if (toX == 4 && toY == 2) {
					table.setTable(toX, toY, roundCounter % 2 + 1);
					table.setTable(fromX, fromY, 0);
					return true;
				}
			}
		}
		return false;
	}

	public boolean allInMalom(Integer roundCounter) {
		boolean allInMalom = true;
		
		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 3; c++) {
				if (table.getTable()[r][c] == roundCounter % 2 +1)
					if (!isStoneInMalom(r, c, table)) {
						allInMalom = false;
						break;
					} else {
						continue;
					}
			}
			if (!allInMalom) 
				break;
			else
				continue;
		}
		
		return allInMalom;
	}
	
	/**
	 * A tábláról egy kő elvételét teszi lehetővé.
	 * 
	 * @param X a kiszemelt kő sorszáma
	 * @param Y a kiszemelt kő oszlopszáma
	 * @param roundCounter az játékos meghatározásához szükséges
	 * @param playerOne az első játékost reprezentáló objektum
	 * @param playerTwo a második játékost reprezentáló objektum
	 */
	public boolean operatorRemove(int X, int Y, Integer roundCounter, PlayerType playerOne, PlayerType playerTwo) {
		if (allInMalom(roundCounter)) {
			table.setTable(X, Y, 0);
			if (roundCounter % 2 + 1 == 1) { 
				playerOne.setStones(playerOne.getStones() - 1);
				playerOne.setOnBoardStones(playerOne.getOnBoardStones() - 1);
				return true;
			}
			else {
				playerTwo.setStones(playerTwo.getStones() - 1);
				playerTwo.setOnBoardStones(playerTwo.getOnBoardStones() - 1);
				return true;
			}
		} else {
			if (!isStoneInMalom(X, Y, table)) {
				table.setTable(X, Y, 0);
				if (roundCounter % 2 + 1 == 1) { 
					playerOne.setStones(playerOne.getStones() - 1);
					playerOne.setOnBoardStones(playerOne.getOnBoardStones() - 1);
					return true;
				}
				else {
					playerTwo.setStones(playerTwo.getStones() - 1);
					playerTwo.setOnBoardStones(playerTwo.getOnBoardStones() - 1);
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * A táblán egy korongnak az ugrását megvalósító metódus.
	 * 
	 * @param roundCounter
	 */
	public boolean operatorJump(Integer roundCounter) {
		if (isValidStep(toX,toY, table, roundCounter)) {
			table.setTable(toX, toY, roundCounter % 2 + 1);
			table.setTable(fromX, fromY, 0);
			return true;
		}
		return false;
	}

	/**
	 * A paraméterül kapott számokat egy {@code Integer} típusú tömbbe helyezi.
	 * 
	 * @param i1
	 *            az első tömbösítendő szám
	 * @param i2
	 *            a második tömbösítendő szám
	 * @param i3
	 *            a harmadik tömbösítendő szám
	 * @param i4
	 *            a negyedik tömbösítendő szám
	 * @param i5
	 *            az ötödik tömbösítendő szám
	 * @param i6
	 *            a hatodik tömbösítendő szám
	 * @return {@cod Integer} tömb benne a hat számmal
	 */
	private Integer[] createRow(int i1, int i2, int i3, int i4, int i5, int i6) {
		Integer[] res = new Integer[6];
		res[0] = i1;
		res[1] = i2;
		res[2] = i3;
		res[3] = i4;
		res[4] = i5;
		res[5] = i6;

		return res;
	}

	/***
	 * Megadja a táblán előforduló malom állapotok listját.
	 * 
	 * @param t
	 *            a tábla állapotát tároló objektum
	 * @return a malom helyzetek számokkal reprezentált listája.
	 */
	public List<Integer[]> getMalmok() {
		List<Integer[]> res = new ArrayList<Integer[]>();

		for (int i = 0; i < 8; i++) {
			if (isMalom(i, 0, i, 1, i, 2, table.getTable())) {
				res.add(createRow(i, 0, i, 1, i, 2));
			}
		}

		if (isMalom(0, 0, 3, 0, 7, 0, table.getTable()))
			res.add(createRow(0, 0, 3, 0, 7, 0)); // 1. oszlop

		if (isMalom(1, 0, 3, 1, 6, 0, table.getTable()))
			res.add(createRow(1, 0, 3, 1, 6, 0)); // 2. oszlop

		if (isMalom(2, 0, 3, 2, 5, 0, table.getTable()))
			res.add(createRow(2, 0, 3, 2, 5, 0)); // 3. oszlop

		if (isMalom(0, 1, 1, 1, 2, 1, table.getTable()))
			res.add(createRow(0, 1, 1, 1, 2, 1)); // 4. oszlop felső

		if (isMalom(5, 1, 6, 1, 7, 0, table.getTable()))
			res.add(createRow(5, 1, 6, 1, 7, 0)); // 4. oszlop alsó

		if (isMalom(2, 2, 4, 0, 5, 2, table.getTable()))
			res.add(createRow(2, 2, 4, 0, 5, 2)); // 5. oszlop

		if (isMalom(1, 2, 4, 1, 6, 2, table.getTable()))
			res.add(createRow(1, 2, 4, 1, 6, 2)); // 6. oszlop

		if (isMalom(0, 2, 4, 2, 7, 2, table.getTable()))
			res.add(createRow(0, 2, 4, 2, 7, 2)); // 7. oszlop

		return res;
	}

	/***
	 * Eldöndi két malmokat tartalmazó állapotról hogy jött-e létre időközben új
	 * malomhelyzet.
	 * 
	 * @param malmok
	 *            a jelenlegi malmok listája
	 * @param previousMalmok
	 *            az előző lépéskori malmok listája
	 * @return igaz ha volt új malom, hamis egyébként
	 */
	public boolean hasNewMalom(List<Integer[]> malmok,
			List<Integer[]> previousMalmok) {
		Boolean[] hasPair = new Boolean[malmok.size()];
		for (int m = 0; m < malmok.size(); m++) {
			boolean has = false;
			for (Integer[] intarray : previousMalmok) {
				int i = 0;
				for (i = 0; i < 6; i++) {
					if (malmok.get(m)[i] == intarray[i])
						continue;
					else {
						break;
					}
				}
				if (i == 6)
					has = true;
			}
			hasPair[m] = has;
		}

		for (boolean b : hasPair) {
			if (!b)
				return true;
		}

		return false;
	}

	/***
	 * Eldönti az adott helyzetben hogy a sorrakerült játékos lehelyezhet-e követ
	 * a táblára.
	 * 
	 * @param roundCounter
	 *            a jelenlegi játékos meghatározásához szükséges számláló
	 * @param playerOne
	 *            az első játékos objektuma
	 * @param playerTwo
	 *            a második játékos objektuma
	 * @return igaz, ha tehet le, hamis egyébként
	 */
	public boolean canPut(Integer roundCounter, PlayerType playerOne,
			PlayerType playerTwo) {
		if (roundCounter % 2 == 1) {
			return playerOne.canPut();
		} else {
			return playerTwo.canPut();
		}
	}

	/***
	 * Eldönti az adott helyzetben hogy a sorrakerült játékos léphet-e az adott
	 * kővel.
	 * 
	 * @param roundCounter
	 *            a sorrakerülő játékos meghatározásához szükséges számláló
	 * @param playerOne
	 *            az első játékos objektuma
	 * @param playerTwo
	 *            a második játékos objektuma
	 * @return igaz, ha léphet, hamis egyébként
	 */
	public boolean canMove(Integer roundCounter, PlayerType playerOne,
			PlayerType playerTwo) {
		if (roundCounter % 2 == 1) {
			return playerOne.canMove();
		} else {
			return playerTwo.canMove();
		}
	}

	/*** 
	 * Meghatározza, hogy az adott játékos ugorhat-e a táblán.
	 * 
	 * @param roundCounter a játékos meghatározásához szökséges számláló
	 * @param playerOne az első játékos objektuma
	 * @param playerTwo a második játékos objektuma
	 * @return igaz, ha ugorhat, hamis egyébként
	 */
	public boolean canJump(Integer roundCounter, PlayerType playerOne,
			PlayerType playerTwo) {
		if (roundCounter % 2 == 1) {
			return playerOne.canJump();
		} else
			return playerTwo.canJump();
	}
	
	/***
	 * Eldönti hogy az épp soron lévő játékos vehet-e le követ a tábláról.
	 * @param roundCounter
	 * @param playerOne
	 * @param playerTwo
	 * @return
	 */
	public boolean canRemove(Integer roundCounter, PlayerType playerOne, PlayerType playerTwo, Malom malom) {
		if (hasNewMalom(malom.malmok, malom.previousMalmok)) {
			return true;
		} else 
			return false;
	}
}
