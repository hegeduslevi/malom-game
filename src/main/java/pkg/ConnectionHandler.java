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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/***
 * A kapcsolat felépítéséhez szükséges osztály.
 */
public class ConnectionHandler {
	
	/***
	 * Az osztály osztályváltozója amely az adatbázis kapcsolatot tartalmazza.
	 */
	static Connection conn;
	
	/***
	 * Az adatbázissal a kapcsolatot felépítő osztály.
	 * 
	 * @return a kapcsolat
	 */
	public static Connection getConnection() {
		try {
		if (conn == null || conn.isClosed()) {
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g", "h_f0lhc6", "berkogep");
			
		}
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return conn;
	}
}
