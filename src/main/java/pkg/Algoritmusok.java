package pkg;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/***
 * 
 * Az alkalmazások működéséhez szükséges algoritmusok gyűjtő osztálya
 *
 */
public class Algoritmusok {

	/***
	 * Elkészít egy a táblán található köveket reprezentáló objektumot.
	 * 
	 * @param row a kő grafikus felületen elhelyezéséhez szükséges X koordinája meghatározásához szükséges egység
	 * @param col a kő grafikus felületen elhelyezéséhez szükséges Y koordinája meghatározásához szükséges egység
	 * @param c   a kő színe
	 * @return a tényleges objektum
	 */
	private JLabel createStone(int row, int col, String c) {
		if ("r".equals(c)) {
			JLabel temp = new JLabel(new ImageIcon(this.getClass().getResource(
					"/redstone.png")));
			temp.setBounds(45 * col, 45 * row, 30, 30);
			return temp;
		} else {
			JLabel temp = new JLabel(new ImageIcon(this.getClass().getResource(
					"/bluestone.png")));
			temp.setBounds(45 * col, 45 * row, 30, 30);
			return temp;
		}
	}

	/***
	 * A tábla állapota alapján létrehozza a megjelenítéshez szükséges címkéket.
	 * 
	 * @return {@code JLabel} típusban visszaadja a címkéket amelyek a megfelelő helyen vannak.
	 */
	public List<JLabel> getStones() {
		List<JLabel> res = new ArrayList<JLabel>();

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 3; c++) {
				if (Malom.tabla[r][c] != 0) { // ha van k�
					int col = -1;
					switch (r) {
					case 0:
					case 7:
						switch (c) {
						case 0:
							col = 0;
							break;
						case 1:
							col = 3;
							break;
						case 2:
							col = 6;
							break;
						}
						break;

					case 1:
					case 6: {
						switch (c) {
						case 0:
							col = 1;
							break;
						case 1:
							col = 3;
							break;
						case 2:
							col = 5;
							break;
						}
						break;
					}

					case 2:
					case 5: {
						switch (c) {
						case 0:
							col = 2;
							break;
						case 1:
							col = 3;
							break;
						case 2:
							col = 4;
							break;
						}
						break;
					}

					case 3: {
						switch (c) {
						case 0:
							col = 0;
							break;
						case 1:
							col = 1;
							break;
						case 2:
							col = 2;
							break;
						}
						break;
					}

					case 4: {
						switch (c) {
						case 0:
							col = 4;
							break;
						case 1:
							col = 5;
							break;
						case 2:
							col = 6;
							break;
						}
						break;
					}
					}

					if (Malom.tabla[r][c] == 1) { // piros kő
						res.add(createStone(r < 4 ? r : r - 1, col, "r"));
					} else { // kék kő
						res.add(createStone(r < 4 ? r : r - 1, col, "b"));
					}
				}
			}
		}

		return res;
	}
	
	public void setStone(MouseEvent me) {
		System.out.println(me.getX() /7); 
		System.out.println(me.getY() / 7);
		
	}
	
}
