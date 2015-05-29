/**
 * 
 */
package pkg;

import static org.junit.Assert.*;

import java.util.List;

import game.Malom;
import game.MalomOperator;
import game.TableType;

import org.junit.Test;

/**
 * @author Levente
 *
 */
public class MalomOperatorTest {

	/**
	 * Teszt metódus: {@link game.MalomOperator#MalomOperator(game.TableType)}.
	 */
	//@Test
	public void testMalomOperatorTableType() {
		Malom m = new Malom();
		
		MalomOperator a = new MalomOperator(m.t);
		MalomOperator b = new MalomOperator(m.t);
		
		assertEquals(true, a.canJump(m.roundCounter, m.playerOne, m.playerTwo) == b.canJump(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#MalomOperator(int, int, game.TableType)}.
	 */
	//@Test
	public void testMalomOperatorIntIntTableType() {
		Malom m = new Malom();
		
		MalomOperator a = new MalomOperator(1,1,m.t);
		MalomOperator b = new MalomOperator(1,1,m.t);
		
		assertEquals(true, a.canJump(m.roundCounter, m.playerOne, m.playerTwo) == b.canJump(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#MalomOperator(int, int, int, int, game.TableType)}.
	 */
	//@Test
	public void testMalomOperatorIntIntIntIntTableType() {
		Malom m = new Malom();
		
		MalomOperator a = new MalomOperator(1,1,2,2,m.t);
		MalomOperator b = new MalomOperator(1,1,2,2,m.t);
		
		assertEquals(true, a.canJump(m.roundCounter, m.playerOne, m.playerTwo) == b.canJump(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#isValidStep(int, int, game.TableType, java.lang.Integer)}.
	 */
	//@Test
	public void testIsValidStep() {
		fail("Not yet implemented");
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#isMalom(int, int, int, int, int, int, java.lang.Integer[][])}.
	 */
	//@Test
	public void testIsMalom() {
		Malom m = new Malom();
		m.t.setTable(0, 2, 1);
		m.t.setTable(4, 2, 1);
		m.t.setTable(7, 2, 1);
		
		assertEquals(true, new MalomOperator(m.t).isMalom(0, 2, 4, 2, 7, 2, m.t.getTable()));
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#operatorPut(java.lang.Integer, game.PlayerType, game.PlayerType)}.
	 */
	//@Test
	public void testOperatorPut() {
		fail("Not yet implemented");
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#operatorMove(java.lang.Integer)}.
	 */
	//@Test
	public void testOperatorMove() {
		fail("Not yet implemented");
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#operatorRemove(int, int, java.lang.Integer, game.PlayerType, game.PlayerType)}.
	 */
	//@Test
	public void testOperatorRemove() {
		Malom m = new Malom();
		m.t.setTable(3, 0, 2);
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		
		assertEquals(false, new MalomOperator(m.t).operatorRemove(3, 0, m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#operatorJump(java.lang.Integer)}.
	 */
	//@Test
	public void testOperatorJump() {
		fail("Not yet implemented");
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#getMalmok()}.
	 */
	//@Test
	public void testGetMalmok() {
		Malom m = new Malom();
		m.t.setTable(0, 2, 1);
		m.t.setTable(4, 2, 1);
		m.t.setTable(7, 2, 1);
		
		List<Integer[]> res = new MalomOperator(m.t).getMalmok();
		
		assertEquals(1, res.size());
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#hasNewMalom(java.util.List, java.util.List)}.
	 */
	//@Test
	public void testHasNewMalom() {
		Malom m = new Malom();
		m.t.setTable(0, 2, 1);
		m.t.setTable(4, 2, 1);
		m.t.setTable(7, 2, 1);
		m.t.setTable(6, 0, 2);
		m.t.setTable(6, 1, 2);	
		m.t.setTable(6, 2, 2);
		m.t.setTable(4, 0, 1);
		m.t.setTable(2, 2, 2);
		m.t.setTable(4, 0, 2);
		m.t.setTable(5, 2, 2);
		
		MalomOperator mo = new MalomOperator(m.t);
		
		m.malmok = mo.getMalmok();
		m.previousMalmok = mo.getMalmok();
		
		m.t.setTable(6, 0, 0);
		m.t.setTable(3, 0, 1);
		m.t.setTable(3, 1, 1);
		m.t.setTable(3, 2, 1);
		
		m.previousMalmok = m.malmok;
		m.malmok = mo.getMalmok();
		
		assertEquals(true, mo.hasNewMalom(m.malmok, m.previousMalmok));
	}
	
	/**
	 * Teszt metódus: {@link game.MalomOperator#hasNewMalom(java.util.List, java.util.List)}.
	 */
	//@Test
	public void testHasNewMalomNoModification() {
		Malom m = new Malom();
		m.t.setTable(0, 2, 1);
		m.t.setTable(4, 2, 1);
		m.t.setTable(7, 2, 1);
		m.t.setTable(6, 0, 2);
		m.t.setTable(6, 1, 2);	
		m.t.setTable(6, 2, 2);
		m.t.setTable(4, 0, 1);
		m.t.setTable(2, 2, 2);
		m.t.setTable(4, 0, 2);
		m.t.setTable(5, 2, 2);
		
		MalomOperator mo = new MalomOperator(m.t);
		
		m.malmok = mo.getMalmok();
		m.previousMalmok = mo.getMalmok();
		
		m.previousMalmok = m.malmok;
		m.malmok = mo.getMalmok();
		
		assertEquals(false, mo.hasNewMalom(m.malmok, m.previousMalmok));
	}
		
	/**
	 * Teszt metódus: {@link game.MalomOperator#canPut(java.lang.Integer, game.PlayerType, game.PlayerType)}.
	 */
	//@Test
	public void testCanPut() {
		Malom m = new Malom();
		assertEquals(true, new MalomOperator(m.t).canPut(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#canMove(java.lang.Integer, game.PlayerType, game.PlayerType)}.
	 */
	//@Test
	public void testCanMove() {
		Malom m = new Malom();
		assertEquals(false, new MalomOperator(m.t).canMove(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#canJump(java.lang.Integer, game.PlayerType, game.PlayerType)}.
	 */
	//@Test
	public void testCanJump() {
		Malom m = new Malom();
		assertEquals(false, new MalomOperator(m.t).canJump(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: {@link game.MalomOperator#canRemove(java.lang.Integer, game.PlayerType, game.PlayerType)}.
	 */
	//@Test
	public void testCanRemove() {
		Malom m = new Malom();
		assertEquals(false, new MalomOperator(m.t).canRemove(m.roundCounter, m.playerOne, m.playerTwo, m));
		
	}
	
	//@Test
	public void testIsStoneInMalomIN() {
		Malom m = new Malom();
		m.t.setTable(0, 2, 1);
		m.t.setTable(4, 2, 1);
		m.t.setTable(7, 2, 1);
		m.t.setTable(4, 1, 1);
		
		assertEquals(true, new MalomOperator(m.t).isStoneInMalom(4, 2, m.t));
	}

	//@Test
	public void testIsStoneInMalomNot() {
		Malom m = new Malom();
		m.t.setTable(0, 2, 1);
		m.t.setTable(4, 2, 1);
		m.t.setTable(7, 2, 1);
		m.t.setTable(4, 1, 1);
		
		assertEquals(false, new MalomOperator(m.t).isStoneInMalom(4, 1, m.t));
	}
	
	@Test
	public void testAllInMalomAll() {
		Malom m = new Malom();
		m.roundCounter++;
		m.t.setTable(2, 0, 1);
		m.t.setTable(2, 1, 1);
		m.t.setTable(2, 2, 1);
		//m.t.setTable(2, 1, 2);
		
		assertEquals(true, new MalomOperator(m.t).allInMalom(m.roundCounter));
	}
	
	@Test
	public void testAllInMalomNot() {
		Malom m = new Malom();
		m.roundCounter++;
		m.t.setTable(2, 0, 1);
		m.t.setTable(2, 1, 1);
		m.t.setTable(2, 2, 1);
		m.t.setTable(1, 1, 1);
		
		assertEquals(false, new MalomOperator(m.t).allInMalom(m.roundCounter));
	}
	
}
