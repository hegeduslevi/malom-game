package GUI;

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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/***
 * A {@code JPanel} kiterjesztése, hogy képet tudjon megjeleníteni a háttereként.
 */
public class ImagePanel extends JPanel {
	/*** 
	 * A kép amit megjelentít.
	 */
	private Image img;

	/***
	 * Az osztály konstuktora amely egy képet fáljnév alapján háttérként betesz.
	 * 
	 * @param imgIn a kép fáljneve
	 */
	private ImagePanel(String imgIn) {
		this(new ImageIcon(imgIn).getImage());
	}
	
	/***
	 * Az osztály konstruktora amely egy {@code Image} típusú képet tartalmazó objektumot tesz be háttérnek.
	 * 
	 * @param img {@code Image} típusú képet tartalmazó objektum
	 */
	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}
	
	/***
	 * A kép tartamának kiírása egy {@code Graphics} objektumba.
	 * 
	 * @param g {@code Graphics} típusú objektum 
	 */
	public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }
}
