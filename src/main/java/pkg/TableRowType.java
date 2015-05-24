package pkg;

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
