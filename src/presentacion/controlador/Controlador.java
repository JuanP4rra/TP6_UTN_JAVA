package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entidad.Persona;
import negocio.PersonaNegocio;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.VistaAgregarPersona;
import presentacion.vista.VistaEliminarPersona;
import presentacion.vista.VistaListarPersona;
import presentacion.vista.VistaModificarPersona;

public class Controlador implements ActionListener {

	private VentanaPrincipal ventana_principal;
	private VistaAgregarPersona vista_agregar_persona;
	private PersonaNegocio pNeg;
	private VistaModificarPersona vista_modificar_persona;
	private VistaListarPersona vista_listar_persona;
	private VistaEliminarPersona vista_eliminar_persona;

	// Constructor
	public Controlador(VentanaPrincipal vista, PersonaNegocio pNeg) {
		// Guardo todas las instancias que recibo en el constructor
		this.ventana_principal = vista;
		this.pNeg = pNeg;

		// Instancio los paneles
		this.vista_agregar_persona = new VistaAgregarPersona();

		this.vista_modificar_persona = new VistaModificarPersona();

		this.vista_listar_persona = new VistaListarPersona();

		this.vista_eliminar_persona = new VistaEliminarPersona();

		// Enlazo todos los eventos

		// Eventos menu del Frame principal llamado Ventana
		this.ventana_principal.getMntmAgregar().addActionListener(a -> EventoClickMenu_AbrirVista_AgregarPersona(a));

		this.ventana_principal.getMntmModificar()
				.addActionListener(a -> EventoClickMenu_AbrirVista_ModificarPersona(a));

		this.ventana_principal.getMntmListar().addActionListener(a -> EventoClickMenu_AbrirVista_ListarPersona(a));

		this.ventana_principal.getMntmEliminar().addActionListener(a -> EventoClickMenu_AbrirVista_EliminarPersona(a));

		// Eventos VentanaAgregarPersonas
		this.vista_agregar_persona.getBtnAceptar()
				.addActionListener(a -> EventoClickBoton_AgregarPesona_VistaAgregarPersonas(a));

		// Eventos VentanaEliminarPersonas
		this.vista_eliminar_persona.getBtnEliminar()
				.addActionListener(a -> EventoClickBoton_EliminarPersona_VistaEliminarPersona(a));

		// Eventos VentanaModificarPersonas
		this.vista_modificar_persona.getBtnModificar()
				.addActionListener(a -> EventoClickBoton_ModificarPersona_VistaModificarPersona(a));
		this.vista_modificar_persona.getListPersonas().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Persona personaSeleccionada = vista_modificar_persona.getListPersonas().getSelectedValue();
					manejarSeleccionPersona(personaSeleccionada);
				}
			}
		});

	}

	private void EventoClickMenu_AbrirVista_ListarPersona(ActionEvent a) {
		ventana_principal.getContentPane().removeAll();
		ventana_principal.getContentPane().add(vista_listar_persona);
		ventana_principal.getContentPane().repaint();
		ventana_principal.getContentPane().revalidate();

		// Obtener la lista de personas desde la capa de negocio
		List<Persona> listaPersonas = pNeg.leerTodaPersona();

		// Convertir la lista a un array de objetos para llenar el JTable
		Object[][] data = new Object[listaPersonas.size()][3];
		for (int i = 0; i < listaPersonas.size(); i++) {
			data[i][0] = listaPersonas.get(i).getDni();
			data[i][1] = listaPersonas.get(i).getNombre();
			data[i][2] = listaPersonas.get(i).getApellido();
		}

		// Actualizar la tabla con los datos
		vista_listar_persona.actualizarTabla(data);
	}

	private void manejarSeleccionPersona(Persona personaSeleccionada) {
		if (personaSeleccionada != null) {
			vista_modificar_persona.getTxtNombre().setText(personaSeleccionada.getNombre());
			vista_modificar_persona.getTxtApellido().setText(personaSeleccionada.getApellido());
			vista_modificar_persona.getTxtDni().setText(personaSeleccionada.getDni());
		}
	}

	private void EventoClickMenu_AbrirVista_ModificarPersona(ActionEvent a) {
		ventana_principal.getContentPane().removeAll();
		ventana_principal.getContentPane().add(vista_modificar_persona);
		ventana_principal.getContentPane().repaint();
		ventana_principal.getContentPane().revalidate();

		if (pNeg != null) {
			DefaultListModel<Persona> modeloLista = new DefaultListModel<>();
			for (Persona persona : pNeg.leerTodaPersona()) {
				modeloLista.addElement(persona);
			}
			vista_modificar_persona.getListPersonas().setModel(modeloLista);
		}
	}

	private void EventoClickBoton_ModificarPersona_VistaModificarPersona(ActionEvent a) {
		// Obtener la persona seleccionada
		Persona personaSeleccionada = vista_modificar_persona.getListPersonas().getSelectedValue();

		if (personaSeleccionada != null) {
			// Obtener los nuevos valores
			String nuevoNombre = vista_modificar_persona.getTxtNombre().getText();
			String nuevoApellido = vista_modificar_persona.getTxtApellido().getText();

			// Crear una nueva persona con los datos modificados
			Persona personaModificada = new Persona(personaSeleccionada.getDni(), nuevoNombre, nuevoApellido);

			// Llamar al método en pNeg para actualizar la persona en la base de datos
			boolean estado = pNeg.modificarPersona(personaModificada);
			if (estado) {
				// Limpiar los JTextField
				vista_modificar_persona.getTxtNombre().setText("");
				vista_modificar_persona.getTxtApellido().setText("");
				vista_modificar_persona.getTxtDni().setText("");

				actualizarListaPersonas();
			}
		}
	}

	private void actualizarListaPersonas() {
		DefaultListModel<Persona> modeloLista = new DefaultListModel<>();
		for (Persona persona : pNeg.leerTodaPersona()) {
			modeloLista.addElement(persona);
		}
		vista_modificar_persona.getListPersonas().setModel(modeloLista);
	}

	public void EventoClickMenu_AbrirVista_AgregarPersona(ActionEvent a) {
		ventana_principal.getContentPane().removeAll();
		ventana_principal.getContentPane().add(vista_agregar_persona.getContentPane());
		ventana_principal.getContentPane().repaint();
		ventana_principal.getContentPane().revalidate();
	}

	public void EventoClickBoton_AgregarPesona_VistaAgregarPersonas(ActionEvent a) {
		String nombre = this.vista_agregar_persona.getTxtNombre().getText();
		String apellido = this.vista_agregar_persona.getTxtApellido().getText();
		String dni = this.vista_agregar_persona.getTxtDNI().getText();
		Persona nuevaPersona = new Persona(dni, nombre, apellido);

		boolean estado = pNeg.agregarPersona(nuevaPersona);
		String mensaje;
		if (estado == true) {
			mensaje = "Persona agregada con exito";
			this.vista_agregar_persona.getTxtNombre().setText("");
			this.vista_agregar_persona.getTxtApellido().setText("");
			this.vista_agregar_persona.getTxtDNI().setText("");
		} else
			mensaje = "Persona no agregada, complete todos los campos";

		this.vista_agregar_persona.mostrarMensaje(mensaje);
	}

	public void EventoClickBoton_EliminarPersona_VistaEliminarPersona(ActionEvent a) {

		if (vista_eliminar_persona.getList().getSelectedIndex() != -1) {

			boolean success = false;
			success = pNeg.borrarPersona(vista_eliminar_persona.getList().getSelectedValue());

			if (success == true) {
				JOptionPane.showMessageDialog(null, "Se elimino esa persona");
			}

			actualizarListaBorrar();

		}

	}

	private void EventoClickMenu_AbrirVista_EliminarPersona(ActionEvent a) {
		ventana_principal.getContentPane().removeAll();
		ventana_principal.getContentPane().add(vista_eliminar_persona);
		ventana_principal.getContentPane().repaint();
		ventana_principal.getContentPane().revalidate();

		List<Persona> listaPersonas = pNeg.leerTodaPersona();

		Persona[] data = new Persona[listaPersonas.size()];
		for (int i = 0; i < listaPersonas.size(); i++) {
			data[i] = listaPersonas.get(i);
		}

		vista_eliminar_persona.actualizarLista(data);

	}

	public void inicializar() {
		this.ventana_principal.setVisible(true);
		;
	}

	private void actualizarListaBorrar() {
		List<Persona> listaPersonas = pNeg.leerTodaPersona();

		Persona[] data = new Persona[listaPersonas.size()];
		for (int i = 0; i < listaPersonas.size(); i++) {
			data[i] = listaPersonas.get(i);
		}

		vista_eliminar_persona.actualizarLista(data);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
