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


import static org.junit.Assert.*;

import java.util.List;

import game.Malom;
import game.MalomOperator;
import game.TableType;

import org.junit.Test;

/**
 * Teszt osztály a MalomOperátor metódusainak tesztelésére.
 */
public class MalomOperatorTest {

	/**
	 * Teszt metódus: MalomOperator(game.TableType) konstruktorhoz.
	 */
	@Test
	public void testMalomOperatorTableType() {
		Malom m = new Malom();
		
		MalomOperator a = new MalomOperator(m.t);
		MalomOperator b = new MalomOperator(m.t);
		
		assertEquals(true, a.canJump(m.roundCounter, m.playerOne, m.playerTwo) == b.canJump(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: MalomOperator(int, int, game.TableType) konstruktorhoz.
	 */
	@Test
	public void testMalomOperatorIntIntTableType() {
		Malom m = new Malom();
		
		MalomOperator a = new MalomOperator(1,1,m.t);
		MalomOperator b = new MalomOperator(1,1,m.t);
		
		assertEquals(true, a.canJump(m.roundCounter, m.playerOne, m.playerTwo) == b.canJump(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: MalomOperator(int, int, int, int, game.TableType) konstruktorhoz.
	 */
	@Test
	public void testMalomOperatorIntIntIntIntTableType() {
		Malom m = new Malom();
		
		MalomOperator a = new MalomOperator(1,1,2,2,m.t);
		MalomOperator b = new MalomOperator(1,1,2,2,m.t);
		
		assertEquals(true, a.canJump(m.roundCounter, m.playerOne, m.playerTwo) == b.canJump(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: isValidStep(int, int, game.TableType, java.lang.Integer).
	 */
	@Test
	public void testIsValidStep() {
		Malom m = new Malom();
		
		m.t.setTable(2, 1, 2);
		
		assertEquals(false, new MalomOperator(2,2,2,1,m.t).isValidStep(2, 1, m.t, m.roundCounter));
	}

	/**
	 * Teszt metódus: isMalom(int, int, int, int, int, int, java.lang.Integer[][]).
	 */
	@Test
	public void testIsMalom() {
		Malom m = new Malom();
		m.t.setTable(0, 2, 1);
		m.t.setTable(4, 2, 1);
		m.t.setTable(7, 2, 1);
		
		assertEquals(true, new MalomOperator(m.t).isMalom(0, 2, 4, 2, 7, 2, m.t.getTable()));
	}

	/**
	 * Teszt metódus: operatorPut(java.lang.Integer, game.PlayerType, game.PlayerType).
	 */
	@Test
	public void testOperatorPut() {
		Malom m = new Malom();
		new MalomOperator(1,1, m.t).operatorPut(m.roundCounter, m.playerOne, m.playerTwo);
		
		assertEquals(true, m.t.getTable()[1][1] == 1);
	}

	/**
	 * Teszt metódus: operatorMove(java.lang.Integer).
	 */
	@Test
	public void testOperatorMove() {
		Malom m = new Malom();
		m.t.setTable(3, 0, 2);
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		
		assertEquals(true, new MalomOperator(1, 2, 2, 2, m.t).operatorMove(m.roundCounter));
	}

	/**
	 * Teszt metódus: operatorRemove(int, int, java.lang.Integer, game.PlayerType, game.PlayerType).
	 */
	@Test
	public void testOperatorRemove() {
		Malom m = new Malom();
		m.t.setTable(3, 0, 2);
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		
		assertEquals(false, new MalomOperator(m.t).operatorRemove(3, 0, m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: operatorJump(java.lang.Integer).
	 */
	@Test
	public void testOperatorJump() {
		Malom m = new Malom();
		m.t.setTable(3, 0, 2);
		m.t.setTable(3, 1, 2);
		m.t.setTable(3, 2, 2);
		m.t.setTable(2, 1, 2);
		
		assertEquals(true, new MalomOperator(1, 2, 2, 2, m.t).operatorJump(m.roundCounter));
	}

	/**
	 * Teszt metódus: getMalmok().
	 */
	@Test
	public void testGetMalmok() {
		Malom m = new Malom();
		m.t.setTable(0, 2, 1);
		m.t.setTable(4, 2, 1);
		m.t.setTable(7, 2, 1);
		
		List<Integer[]> res = new MalomOperator(m.t).getMalmok();
		
		assertEquals(1, res.size());
	}

	/**
	 * Teszt metódus: hasNewMalom(java.util.List, java.util.List).
	 */
	@Test
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
	 * Teszt metódus: hasNewMalom(java.util.List, java.util.List).
	 */
	@Test
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
	 * Teszt metódus: canPut(java.lang.Integer, game.PlayerType, game.PlayerType).
	 */
	@Test
	public void testCanPut() {
		Malom m = new Malom();
		assertEquals(true, new MalomOperator(m.t).canPut(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: canMove(java.lang.Integer, game.PlayerType, game.PlayerType).
	 */
	@Test
	public void testCanMove() {
		Malom m = new Malom();
		assertEquals(false, new MalomOperator(m.t).canMove(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: canJump(java.lang.Integer, game.PlayerType, game.PlayerType).
	 */
	@Test
	public void testCanJump() {
		Malom m = new Malom();
		assertEquals(false, new MalomOperator(m.t).canJump(m.roundCounter, m.playerOne, m.playerTwo));
	}

	/**
	 * Teszt metódus: canRemove(java.lang.Integer, game.PlayerType, game.PlayerType).
	 */
	@Test
	public void testCanRemove() {
		Malom m = new Malom();
		assertEquals(false, new MalomOperator(m.t).canRemove(m.roundCounter, m.playerOne, m.playerTwo, m));
		
	}
	
	/**
	 * Teszt metódus: isStoneInMalom() abban az esetben mikor mind benne van.
	 */
	@Test
	public void testIsStoneInMalomIN() {
		Malom m = new Malom();
		m.t.setTable(0, 2, 1);
		m.t.setTable(4, 2, 1);
		m.t.setTable(7, 2, 1);
		m.t.setTable(4, 1, 1);
		
		assertEquals(true, new MalomOperator(m.t).isStoneInMalom(4, 2, m.t));
	}

	/**
	 * Teszt metódus: isStoneInMalom() amikor valamelyik nincs.
	 */
	@Test
	public void testIsStoneInMalomNot() {
		Malom m = new Malom();
		m.t.setTable(0, 2, 1);
		m.t.setTable(4, 2, 1);
		m.t.setTable(7, 2, 1);
		m.t.setTable(4, 1, 1);
		
		assertEquals(false, new MalomOperator(m.t).isStoneInMalom(4, 1, m.t));
	}
	
	/**
	 * Teszt metódus: allInMalom() mindegyikre igaz.
	 */
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
	
	/**
	 * Teszt metódus: allInMalom() nem igaz.
	 */
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
