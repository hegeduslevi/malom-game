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


import static org.junit.Assert.assertEquals;
import game.ConnectionHandler;
import game.XMLParser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 *	XML feldolgozó osztyál tesztje.
 */
public class XMLParserTest {
	@Test
	public void testXMLParser() throws SQLException {
		XMLParser xpr = new XMLParser();
		
		Connection conn = new ConnectionHandler().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select count(*) from MALOM_DATABASE");
		
		rs.next();
		int count = rs.getInt(1);
		
		xpr.updateDatabase();
		
		conn = new ConnectionHandler().getConnection();
		st = conn.createStatement();
		rs = st.executeQuery("select count(*) from MALOM_DATABASE");
		rs.next();
		assertEquals(count + 4 , rs.getInt(1));
		
	}
}
