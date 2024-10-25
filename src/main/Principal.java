package main;

import negocio.PersonaNegocio;
import presentacion.controlador.Controlador;
import presentacion.vista.VentanaPrincipal;
import negocioImpl.PersonaNegocioImpl;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaPrincipal vista = new VentanaPrincipal();
		PersonaNegocio negocio = new PersonaNegocioImpl();
		Controlador controlador = new Controlador(vista,negocio);
		controlador.inicializar();
	}

}
