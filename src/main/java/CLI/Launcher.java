/**
 * 
 */
package CLI;

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
			GUI.MainScreen.start(args);
		} else {
			//TODO
		}
		

	}

}
