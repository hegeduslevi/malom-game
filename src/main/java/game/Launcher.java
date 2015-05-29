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
			game.MainScreen.start(args);
		} else {
			//TODO
		}
		

	}

}
