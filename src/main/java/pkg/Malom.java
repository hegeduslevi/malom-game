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

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pkg.*;

/***
 * 
 * A program fő osztálya ez indítja a grafikus felületet, vagy a parancssori
 * alkalmazást.
 *
 */
public class Malom {
	/***
	 * A loggoláshoz szükséges {@code Logger} objektum.
	 */
	private static Logger logger = LoggerFactory.getLogger(Malom.class);

	/***
	 * A tábla állapotát tartalmazza.
	 */
	public static TableType t = new TableType();

	/***
	 * A játék körökre osztását szolgálja és az aktuális játékos eldöltését
	 * segíti elő.
	 */
	public static Integer roundCounter = 1;

	/***
	 * Az egyes játékos.
	 */
	public static PlayerType playerOne = new PlayerType();

	/***
	 * A kettes játékos.
	 */
	public static PlayerType playerTwo = new PlayerType();

	/***
	 * A program fő osztálya, amely indítja a grafikus felületet és tartalmazza
	 * a játékmenethez szükséges objektumokat.
	 * 
	 * @param args
	 *            parancssori argumentumok
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		System.out.println("Elindítod a grafikus felületet? (Yes / No ) ");
		String in = sc.nextLine().toLowerCase();
		
		if (in.startsWith("y")/*true*//*false*/) {
			System.out.println("adja meg az első játékos nevét: ");
			playerOne.setName(sc.nextLine());
			
			System.out.println("adja mega második játékos nevét: ");
			playerTwo.setName(sc.nextLine());
			
			MainScreen.start(args);
		} else {
			System.out.println("Demó mód életbe lép.");
			
			playerOne.setName("luke");
			playerTwo.setName("feri");
			
			t.setTable(0,1,1);
			t.setTable(2, 1, 2);
			t.setTable(3, 1, 1);
			t.setTable(2, 2, 2);
			t.setTable(0,2, 1);
			t.setTable(2, 0, 2);
			
			List<Integer> positions = Algoritmusok.getMalmok(t.getTable());
			
			for (int i : positions) {
				System.out.println(i);
			}
			
			Algoritmusok.felad();
			
			System.out.println("Ranglista megnyílt.");
		}
		
		sc.close();
		
		System.out.println("viszlát.");		
	}
}
