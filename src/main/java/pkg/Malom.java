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
	 * A loggoláshoz szükséges {@code Logger} objektum.
	 */
	private static Logger logger = LoggerFactory.getLogger(Malom.class);
	
	/***
	 * A tábla állapotát tartalmazza.
	 */
	public static TableType t = new TableType();
	
	/***
	 * A játék körökre osztását szolgálja és az aktuális játékos eldöltését segíti elő.
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
	 * A program fő osztálya, amely indítja a grafikus felületet és tartalmazza a játékmenethez szükséges objektumokat.
	 * 
	 * @param args parancssori argumentumok
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		//System.out.println("Elindítod a grafikus felületet? (Yes / No ) ");
		//String in = sc.nextLine().toLowerCase();
		sc.close();
		
		//t.setTable(1, 1, 1);
		
		if (/*in.startsWith("y")*/true/*false*/) {
			MainScreen.start(args);
		} else {
			System.out.println("Demó mód életbe lép.");
			
			
		}
		
		System.out.println("viszlát.");		
	}
}
