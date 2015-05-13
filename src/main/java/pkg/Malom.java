package pkg;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/***
 * 
 * A program fő osztálya ez indítja a grafikus felületet, vagy a parancssori alkalmazást.
 *
 */
public class Malom {
	/*** 
	 * A tábla állapotát tartalmazza.
	 */
	
	private static Logger logger = LoggerFactory.getLogger(Malom.class);
	
	public static Integer[][] tabla = new Integer[8][3];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (Integer[] i : tabla)
			for (int k = 0; k < i.length; k++) {
				i[k] = 0;
			}

		//System.out.println("Elindítod a grafikus felületet? (Yes / No ) ");
		//String in = sc.nextLine().toLowerCase();
		sc.close();
		
		tabla[0][0] = 1;
		tabla[2][1] = 1;
		tabla[2][0] = 2;
		tabla[3][2] = 1;
		
		tabla[4][1] = 2;
		tabla[5][1] = 2;
		tabla[6][1] = 2;
		
		logger.info("a szükséges adatok betöltődtek");
		
		if (/*in.startsWith("y")*/true) {
			Foablak.start(args);
		} else {
			for (Integer[] i : tabla) {
				for (int k = 0; k < i.length; k++) {
					System.out.println(i[k]);
				}
			}
		}
	}
}
