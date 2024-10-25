package negocio;

import java.util.List;

import entidad.Persona;

public interface PersonaNegocio {
	public boolean agregarPersona(Persona persona);
	public boolean borrarPersona(Persona persona);
	public List<Persona> leerTodaPersona();
	public boolean modificarPersona(Persona persona);
}
