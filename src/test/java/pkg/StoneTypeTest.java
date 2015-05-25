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
 * A köveket tartalmazó osztály tesztje.
 */
public class StoneTypeTest {
	/***
	 * A kövek állapotának modosítását végző metódus tesztje.
	 */
	@Test
	public void testState() {
		StoneType st = new StoneType("n", "r", true, 2, 2);
		st.setState("s");
		assertEquals("s", st.getState());
	}
	
	/***
	 * A kövek színének modosítását végző metódus tesztje.
	 */
	@Test
	public void testColor() {
		StoneType st = new StoneType("n", "r", true, 2, 2);
		st.setColor("b");
		assertEquals("b", st.getColor());
	}
	
	/***
	 * A kövek láthatóségának modosítását végző metódus tesztje.
	 */
	@Test
	public void testVisible() {
		StoneType st = new StoneType("n", "r", true, 2, 2);
		st.setVisible(false);
		assertEquals(false, st.getVisible());
	}
	
}
