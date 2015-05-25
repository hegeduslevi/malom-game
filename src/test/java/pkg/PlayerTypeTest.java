/**
 * 
 */
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


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *	A játékos típus osztály egységtesztje.
 */
public class PlayerTypeTest {
	/***
	 * A konstruktor tesztje.
	 */
	@Test 
	public void testPlayerType() {
		PlayerType p = new PlayerType();
		p.setName("feri");
		
		PlayerType p1 = new PlayerType();
		p1.setName("feri");
		assertEquals(p.getName(), p1.getName());
	}
	
	/***
	 * A ugrás lehetőségét vizsgáló metódus tesztje.
	 */
	@Test
	public void testCanJump() {
		PlayerType p = new PlayerType();
		p.setStones(3);
		assertEquals(true, p.canJump());
	}
	
	/***
	 * Az ugrás lehetőségét vizsgáló metódus tesztje.
	 */
	@Test
	public void testCanJump1() {
		PlayerType p = new PlayerType();
		assertEquals(false, p.canJump());
	}
	
	/***
	 * A táblán található kövek számának tesztje.
	 */
	@Test
	public void testSetOnBoardStones() {
		PlayerType p = new PlayerType();
		p.setOnBoardStones(1);
		
		assertEquals(1, p.getOnBoardStones());
	}
	
	/***
	 * A játékos kövei számának a tesztje.
	 */
	@Test
	public void testSetStones() {
		PlayerType p = new PlayerType();
		p.setStones(1);
		
		assertEquals(1, p.getStones());
	}
}
