package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.IConnectable;

public class Conexion implements IConnectable {

	public static Conexion instance;
	private Connection connection;

	public Conexion() {
		try {
			String dbUrl = DB_HOST + DB_NAME;
			this.connection = DriverManager.getConnection(dbUrl, DB_USERNAME, DB_PASSWORD);
			this.connection.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Conexion getInstance() {
		if (instance == null) {
	        instance = new Conexion();
	    }
	    return instance;
	}

	public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Si la conexi�n es nula o est� cerrada, crea una nueva
        	String dbUrl = DB_HOST + DB_NAME;
            connection = DriverManager.getConnection(dbUrl,  DB_USERNAME, DB_PASSWORD);            
            connection.setAutoCommit(false); // Establecer modo de transacci�n, si es necesario
        }
        return connection;
    }

	public void closeConnection() {
		try (Connection conn = this.connection) {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance = null;
	}
}
