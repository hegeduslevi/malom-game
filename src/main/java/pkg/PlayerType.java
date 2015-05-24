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


/***
 * Játékost reprezentáló osztály.
 */
public class PlayerType {
	/***
	 * A játékos köveinek száma.
	 */
	private int stones;
	
	/***
	 * A játékos táblán lévő köveine száma.
	 */
	private int onBoard;
	
	/***
	 * A játékos osztály konstruktora.
	 */
	public PlayerType() {
		this.stones = 9;
		this.onBoard = 0;
	}
	
	/***
	 * Ellenzőrzi hogy ogorhat-e már a táblán a játékos.
	 * 
	 * @return ugorhat-e
	 */
	public boolean canJump() {
		if (stones <= 3) {
			return true;
		}
		return false;
	}
	
	/***
	 * Beállítja a játékos köveinek a számát.
	 * 
	 * @param count kövek száma
	 */
	public void setStones(int count) {
		stones = count;
	}
	
	/***
	 * Megadja hány kővel rendelkezik a játékos.
	 * 
	 * @return kövek száma
	 */
	public int getStones() {
		return stones;
	}
	
	/***
	 * Megadja a táblán taláható kövek számát.
	 *
	 * @return kövek száma
	 */
	public int getOnBoardStones() {
		return onBoard;
	}
	
	/***
	 * Beállítja a táblán taláható kövek számát.
	 *
	 * @param count kövek száma
	 */
	public void setOnBoardStones(int count) {
		this.onBoard = count;
	}
}
