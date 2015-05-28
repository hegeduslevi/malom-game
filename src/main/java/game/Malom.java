package game;

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

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import GUI.MainScreen;
import service.*;

/***
 * 
 * A program fő osztálya ez indítja a grafikus felületet, vagy a parancssori
 * alkalmazást.
 *
 */
public class Malom {
	/***
	 * A loggoláshoz szükséges {@code Logger} objektum.
	 */
	private static Logger logger;

	/***
	 * A tábla állapotát tartalmazza.
	 */
	public static TableType t;

	/***
	 * A játék körökre osztását szolgálja és az aktuális játékos eldöltését
	 * segíti elő.
	 */
	public static Integer roundCounter;

	/***
	 * Az egyes játékos.
	 */
	public static PlayerType playerOne = new PlayerType();

	/***
	 * A kettes játékos.
	 */
	public static PlayerType playerTwo = new PlayerType();

	/**
	 * Az aktuális malom helyezetek listája.
	 */
	public static List<Integer[]> malmok;
	
	/**
	 * Az előző lépéskor fennálló malom helyzetek listája.
	 */
	public static List<Integer[]> previousMalmok;
	
	/**
	 * Az összefogó osztály konstruktora.
	 */
	public Malom() {
		logger  = LoggerFactory.getLogger(Malom.class);
		t = new TableType();
		roundCounter = 1;
	}
	
}
