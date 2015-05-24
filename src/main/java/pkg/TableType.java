/**
 * 
 */
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
