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

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.junit.Test;


/***
 * Az algoritmus osztály metódusainak teszt osztálya.
 */
public class AlgoritmusokTest {

	/***
	 * Üres tábla esetén malmok számát teszteli.
	 */
	@Test
	public void testEmptyTable() {
		TableType t = new TableType();
		List<Integer> li= Algoritmusok.getMalmok(t.getTable());
		assertEquals(0, li.size());
	}
	
	/***
	 * Egy sorban található malom helyzetek tesztje
	 */
	@Test
	public void testRowMil() {
		TableType t = new TableType();
		t.setTable(2, 0, 1);
		t.setTable(2, 1, 1);
		t.setTable(2, 2, 1);
		
		List<Integer> li = Algoritmusok.getMalmok(t.getTable());
		for (int i : li) {
			assertEquals(2, i);
		}
	}
	
	/***
	 * Egy oszlopban található malom helyzetek tesztje.
	 */
	@Test
	public void testColMil() {
		TableType t = new TableType();
		t.setTable(5, 1, 1);
		t.setTable(6, 1, 1);
		t.setTable(7, 1, 1);
		
		List<Integer> li = Algoritmusok.getMalmok(t.getTable());
		for (int i : li) {
			assertEquals(12, i);
		}
	}
	
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
	 * A malom helyzetek felismerését tesztelő metódus. 
	 */
	@Test
	public void testIsMalom() {
		TableType t = new TableType();
		t.setTable(0, 0, 1);
		t.setTable(0, 1, 1);
		t.setTable(0, 2, 1);
		assertEquals(true, Algoritmusok.isMalom(0,0, 0,1, 0,2, t.getTable()));
	}
	
	/***
	 * Egy kőről a malom helyzetet eldöntő algoritmus tesztje.
	 */
	@Test
	public void testIsStoneInMalom() {
		TableType t = new TableType();
		t.setTable(0, 1, 1);
		t.setTable(1, 1, 1);
		t.setTable(2, 1, 1);
		assertEquals(true, Algoritmusok.isStoneInMalom(1,1, "r", t));
	}
	
	/***
	 * A malom helyzeteket visszaadó metódus tesztje.
	 */
	@Test
	public void testGetMalmok() {
		TableType t = new TableType();
		assertEquals(0, Algoritmusok.getMalmok(t.getTable()).size());
	}
}
