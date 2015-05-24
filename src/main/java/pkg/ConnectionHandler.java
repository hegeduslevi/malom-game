package pkg;

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
