/**
 * 
 */
package pkg;

/**
 *	A játék állapotterét reprezentáló tábla.
 */
public class TableType {
	/***
	 * A táblát tároló {@code Integer} tömb.
	 */
	private Integer[][] table = new Integer[8][3];
	
	/***
	 * A táblatípus konstruktora amely inicializálja is a táblát.
	 */
	public TableType() {
		for (Integer[] i : table)
			for (int k = 0; k < i.length; k++) {
				i[k] = 0;
			}
	}
	
	/***
	 * Beállíja a tábla egy pontjának az állapotát.
	 * 
	 * @param rowNum a reprezentációban található sor száma
	 * @param colNum a reprezentációban található oszlop száma
	 * @param value a tábla adott pontjának az állapota
	 */
	public void setTable(int rowNum, int colNum, int value) {
		table[rowNum][colNum] = value;
	}
	
	
	/***
	 * Visszaadja a tábla reprezentációját egy egyészeket tartalmazó tömbben.
	 * 
	 * @return az egészeket tartalmazó tömb
	 */
	public Integer[][] getTable() {
		return table;
	}
}
