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

import org.junit.Test;

import pkg.Algoritmusok;
import pkg.TableType;

public class MalomTest {

	@Test
	public void testEmptyTable() {
		TableType t = new TableType();
		List<Integer> li= Algoritmusok.getMalmok(t.getTable());
		assertEquals(0, li.size());
	}
	
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
	
	
	

}
