/**
 * 
 */
package pkg;

import javax.swing.JLabel;

/***
 * A táblán található kövek reprezentálásához és a velük való művelet végzéshez szükséges osztály.
 */
public class StoneType {
	/***
	 * A kő megjelenítésére szolgáló {@code JLabel} objektum.
	 */
	private JLabel label;
	
	/***
	 * A kő állapota.
	 */
	private String state;
	
	/***
	 * A kő színe.
	 */
	private String color;
	
	/*** 
	 * A követ reprezentáló {@code JLabel} objektum láthatósága.
	 */
	private Boolean visible;
	
	/***
	 * A kő sor száma.
	 */
	private Integer row;
	
	/***
	 * A kő oszlop száma.
	 */
	private Integer col;

	/***
	 * Visszaadja a követ reprezentáló {@code JLabel} típusú objektumot.
	 * 
	 * @return a reprezentáló objektum
	 */
	public JLabel getLabel() {
		return label;
	}

	/***
	 * Visszaadja a kő állapotát.
	 * 
	 * @return a kő állapota
	 */
	public String getState() {
		return state;
	}

	/***
	 * Beállítja a kő állapotát.
	 * 
	 * @param state a kő állapota
	 */
	public void setState(String state) {
		this.state = state;
	}

	/***
	 * Megadja a kő színét - a játékost.
	 * 
	 * @return a kő színe
	 */
	public String getColor() {
		return color;
	}

	/***
	 * Beállítja a kő színét - a játékost.
	 * 
	 * @param color a kő színe
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/***
	 * A grafikus felületen beállított kő láthatóságát adja vissza.
	 * 
	 * @return a kő láthatósága
	 */
	public Boolean getVisible() {
		return visible;
	}

	/***
	 * Beállítája a kő láthatóságát.
	 * 
	 * @param visible a kő láthatósága
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
		this.label.setVisible(visible);
	}

	/***
	 * Vissza adja a kő táblán található helyének sor számát.
	 * 
	 * @return a kő sor száma
	 */
	public Integer getRow() {
		return row;
	}
	
	/***
	 * Vissza adja a kő táblán található helyének oszlop számát.
	 * 
	 * @return a kő oszlop száma
	 */
	public Integer getCol() {
		return col;
	}

	/***
	 * A {@code StoneType} osztály konstruktora.
	 * 
	 * @param state a kő állapota
	 * @param color a kő színe
	 * @param visible a kő láthatósága
	 * @param row a kő sor száma
	 * @param col a kő oszlop száma
	 */
	public StoneType(String state, String color, Boolean visible,
			Integer row, Integer col) {
		super();
		this.state = state;
		this.color = color;
		this.visible = visible;
		this.row = row;
		this.col = col;
		this.label = Algoritmusok.createStone(row, col, color, state);
		this.label.setVisible(visible);
	}

}
