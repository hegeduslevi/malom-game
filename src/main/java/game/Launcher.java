/**
 * 
 */
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


import java.util.Scanner;

/**
 * Egy választó program amelyben eldönthető hogy grafikusan vagy parancssorosan játszik az ember.
 */
public class Launcher {

	/**
	 * Az indító program main metódusa.
	 * 
	 * @param args a parancssori argumentumok.
	 */
	public static void main(String[] args) {
		System.out.println("Elindítod a grafikus felületet: (igen/nem)");
		Scanner sc = new Scanner(System.in);
		
		String answ = sc.nextLine();
		
		if (answ.equals("igen")) {
			String[] names = new String[2];
			
			System.out.println("1. játékos neve:"); 
			names[0] = sc.nextLine();
			
			System.out.println("2. játékos neve:"); 
			names[1] = sc.nextLine();
			
			game.MainScreen.start(names);
			
		} else {
			System.out.println("Demó mód életbe lép.");
			
			Malom m = new Malom();
			
			m.playerOne.setName("Sanya");
			m.playerTwo.setName("Brandon");
			
			m.t.setTable(1, 2, 1);
			m.roundCounter++;
			
			m.t.setTable(1, 1, 2);
			m.roundCounter++;
			
			System.out.println("Sanya feladja a játékot. \n\nAdatok feldolgozása folyamatban");
			Algoritmusok.felad("cmd");
		}
		

	}

}
