package pkg;

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
