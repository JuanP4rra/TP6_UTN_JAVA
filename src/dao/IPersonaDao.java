package dao;

import java.util.List;

import entidad.Persona;

public interface IPersonaDao {

	public boolean agregarPersona(Persona persona);

	public boolean borrarPersona(Persona persona);

	public List<Persona> leerTodaPersona();

	public boolean modificarPersona(Persona persona);

	String INSERT_QUERY = "INSERT INTO Personas(Dni, Nombre, Apellido) VALUES(?, ?, ?)";
	String DELETE_QUERY = "DELETE FROM Personas WHERE Dni = ?";
	String READ_ALL_QUERY = "SELECT * FROM personas";
	String UPDATE_QUERY = "UPDATE personas SET nombre = ?, apellido = ? WHERE dni = ?";
}
