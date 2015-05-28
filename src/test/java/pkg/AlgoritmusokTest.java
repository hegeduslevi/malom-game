package pkg;

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


import static org.junit.Assert.*;
import game.Malom;
import game.TableType;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.junit.Test;

import GUI.Algoritmusok;
import GUI.MainScreen;
import GUI.StoneType;
import service.*;

/***
 * Az algoritmus osztály metódusainak teszt osztálya.
 */
public class AlgoritmusokTest {

	
	
	
	/*** 
	 * A követ reprezentáló {@code JLabel} típusú objektum készítésének a tesztje.
	 */
	@Test
	public void testCreateStone() {
		JLabel temp = new JLabel(new ImageIcon(Malom.class.getResource("/redstone.png")));
		temp.setBounds(45 * 3, 45 * 1, 30, 30);
		temp.setVisible(false);
		assertEquals(temp.getBounds(), Algoritmusok.createStone(1, 1, "r", "n").getBounds() );
	}

	/***
	 * A reprezentáció és megjelenítésbeli oszlop eltolódások áthidalására szolgáló algoritmus tesztje.
	 */
	@Test
	public void testGetCorrectDataForStones() {
		Integer[] data = {1,3};
		assertEquals(data, Algoritmusok.getCorrectDataForStones(1, 1));
	}
	
	/***
	 * A grafikus felhasználói felületen található labelek elkészítését teszteli.
	 */
	@Test
	public void testGetStones() {
		assertEquals(120, Algoritmusok.getStones().size());
	}

	
	/***
	 * A kattintás helyességét ellenőrző metódus tesztje.
	 */
	@Test
	public void testIsClicked() {
		StoneType st = new StoneType("n", "r", true, 2, 2);
		long a = 1;
		MouseEvent me = new MouseEvent(st.getLabel() ,1,a,1,195,125,1,false);
		assertEquals(true, Algoritmusok.isClicked(st, me));
	}
	
	/***
	 * A tábla alapján a grafikus felületet frissító metódus tesztje.
	 */
	@Test
	public void testUpdateTable() {
		Algoritmusok.updateTable();
		boolean hasVisible = false;
		
		for (StoneType st : MainScreen.stones) {
			if (st.getVisible() == true) 
				hasVisible = true;
		}
			
		assertEquals(false, hasVisible);
		
	}
}
