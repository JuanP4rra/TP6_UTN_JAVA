package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.IPersonaDao;
import entidad.Persona;

public class PersonaDao implements IPersonaDao {

	public boolean agregarPersona(Persona persona) {

		PreparedStatement statement;
		Connection conexion = null;
		boolean success = false;

		try {
			conexion = Conexion.getInstance().getConnection(); // Obtener la conexiÃ³n aquÃ­
			if (conexion != null) { // Verificar si la conexiÃ³n es vÃ¡lida
				statement = conexion.prepareStatement(INSERT_QUERY);
				statement.setString(1, persona.getDni());
				statement.setString(2, persona.getNombre());
				statement.setString(3, persona.getApellido());

				if (statement.executeUpdate() > 0) {
					conexion.commit();
					success = true;
				}
			} else {
				System.err.println("Error: No se pudo obtener una conexiÃ³n a la base de datos 1.");
			}

		} catch (SQLException e) {
			e.printStackTrace();

			try {
				if (conexion != null) {
					conexion.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			try {
				if (conexion != null) {
					conexion.close(); // Cerrar la conexiÃ³n
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return success;
	}

	public boolean borrarPersona(Persona persona) {

		boolean success = false;

		try (Connection conexion = Conexion.getInstance().getConnection();
				PreparedStatement pstmt = conexion.prepareStatement(DELETE_QUERY)) {

			pstmt.setString(1, persona.getDni());
			if (pstmt.executeUpdate() > 0) {
				conexion.commit();
				success = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	public List<Persona> leerTodaPersona() {
		List<Persona> personas = new ArrayList<>();

		// Usar try-with-resources para asegurar que los recursos se cierren
		// automáticamente
		try (Connection conexion = new Conexion().getConnection(); // Nueva conexión
				Statement statement = (Statement) conexion.createStatement();
				ResultSet resultSet = statement.executeQuery(READ_ALL_QUERY)) {

			// Recorrer el ResultSet y añadir cada persona a la lista
			while (resultSet.next()) {
				personas.add(getPersonaResultSet(resultSet)); // Método para convertir ResultSet a Persona
			}

		} catch (SQLException e) {
			System.err.println("Error durante la ejecución de la consulta: " + e.getMessage());
			e.printStackTrace(); // Manejo de errores
		}

		return personas; // Retornar la lista de personas
	}

	public boolean modificarPersona(Persona persona) {

		try (Connection conexion = Conexion.getInstance().getConnection();
				PreparedStatement pstmt = conexion.prepareStatement(UPDATE_QUERY)) {

			pstmt.setString(1, persona.getNombre());
			pstmt.setString(2, persona.getApellido());
			pstmt.setString(3, persona.getDni());
			int filasActualizadas = pstmt.executeUpdate();

			conexion.commit();
			return filasActualizadas > 0; // Retorna true si se actualizó al menos una fila
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Retorna false en caso de error
		}

	}

	private Persona getPersonaResultSet(ResultSet set) throws SQLException {
		String Dni = set.getString("Dni");
		String Nombre = set.getString("Nombre");
		String Apellido = set.getString("Apellido");
		return new Persona(Dni, Nombre, Apellido);
	}
}
