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


/***
 * JTable sorainak elkészítéséhez szükséges osztály. Egy játékos adatait tartalmaza.
 */
public class TableRowType {
	/***
	 * A játékos neve.
	 */
	public String name;
	
	/***
	 * A játékos győzelmeinek a száma.
	 */
	public int wins;
	
	/***
	 * A játékos vereségeinek a száma.
	 */
	public int loses;
	
	/***
	 * Az osztály konstruktora.
	 * 
	 * @param s a játékos neve
	 * @param w győzelmek száma
	 * @param l vereségek száma
	 */
	public TableRowType(String s, int w, int l) {
		this.name = s;
		this.wins = w;
		this.loses = l;
	}
}
