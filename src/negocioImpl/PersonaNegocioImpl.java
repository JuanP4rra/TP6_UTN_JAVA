package negocioImpl;

import java.util.List;

import dao.IPersonaDao;
import daoImpl.PersonaDao;
import entidad.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio {
    IPersonaDao ipdao = new PersonaDao();

    @Override
    public boolean agregarPersona(Persona persona) {
        boolean estado = false;

        // Validación de nombre y apellido no vacíos
        if (persona.getNombre().trim().length() > 0 && persona.getApellido().trim().length() > 0) {
            
            // Validación del DNI: debe estar lleno y contener solo números
            String dni = persona.getDni().trim();
            if (dni.length() > 0 && dni.matches("\\d+")) {
                // Si las validaciones se cumplen, se agrega la persona
                estado = ipdao.agregarPersona(persona);
            }
        }
        return estado;
    }

	@Override
	public boolean borrarPersona(Persona persona) {
		// TODO Auto-generated method stub
		boolean estado = false;
		if(persona.getDni().trim().length()>0)
		{
			estado= ipdao.borrarPersona(persona);
		}
		return estado;
	}

	@Override
	 public List<Persona> leerTodaPersona() {
        // Llamada al método del DAO que obtiene todas las personas
        List<Persona> personas = ipdao.leerTodaPersona();
        return personas;
    }
	
	public boolean modificarPersona(Persona persona) {
		boolean estado= ipdao.modificarPersona(persona);
		return estado;
	}
}
