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


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pkg.*;

/**
 * XML fálj feldolgozáshoz szükséges osztály.
 */
public class XMLParser {
	/***
	 * A loggoláshoz szükséges {@code Logger} objektum.
	 */
	private static Logger logger = LoggerFactory.getLogger(XMLParser.class);

	/***
	 * A beolvasott tartalmat tároló objektuma.
	 */
	private static Document doc;

	/***
	 * Az osztály konstruktora.
	 */
	public XMLParser() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser;
		try {
			parser = dbf.newDocumentBuilder();

			doc = parser.parse(Malom.class
					.getResourceAsStream("/databaseInit.xml"));
		} catch (IOException e) {
			e.getMessage();
		} catch (ParserConfigurationException e) {
			e.getMessage();
		} catch (SAXException e) {
			e.getMessage();
		}
	}

	/***
	 * Az adatbázist módosítja a felolvasott fálj tartalma szerint.
	 * 
	 * @throws SQLException
	 *             amennyiben nem sikerült a kapcsoaltot felépíteni
	 */
	public void updateDatabase() throws SQLException {
		Connection conn = ConnectionHandler.getConnection();
		Statement st = conn.createStatement();

		NodeList players = doc.getElementsByTagName("player");

		String name = new String();
		int wins = 0, loses = 0;
		logger.info("" + players.getLength());
		for (int i = 0; i < players.getLength(); i++) {
			Element playerElement = (Element) players.item(i);
			name = playerElement.getElementsByTagName("name").item(0)
					.getTextContent();
			wins = Integer.parseInt(playerElement.getElementsByTagName("wins")
					.item(0).getTextContent());
			loses = Integer.parseInt(playerElement
					.getElementsByTagName("loses").item(0).getTextContent());

			logger.info(name + " " + wins + " " + loses);
			String sql = "insert INTO MALOM_DATABASE (ID, NAME, WINS, LOSES) VALUES (seq_player.nextval, '"
					+ name + "'," + wins + "," + loses + ")";
			st.executeUpdate(sql);

		}
		st.executeQuery("commit");
		conn.close();
		logger.info("adatok betöltve az adatbázisba");
	}
}
