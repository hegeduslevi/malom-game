/**
 * 
 */
package pkg;

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
}
