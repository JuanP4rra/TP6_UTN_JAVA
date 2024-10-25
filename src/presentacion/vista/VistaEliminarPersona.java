package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import entidad.Persona;

public class VistaEliminarPersona extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<Persona> list;
	private DefaultListModel<Persona> dlmodel;
	private JButton btnEliminar;
	
	public JList<Persona> getList() {
		return list;
	}

	public void setList(JList<Persona> list) {
		this.list = list;
	}

	public DefaultListModel<Persona> getDlmodel() {
		return dlmodel;
	}

	public void setDlmodel(DefaultListModel<Persona> dlmodel) {
		this.dlmodel = dlmodel;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public VistaEliminarPersona() {
		setLayout(null);
		
		JLabel lblEliminarPersona = new JLabel("Eliminar Persona");
		lblEliminarPersona.setBounds(178, 32, 102, 16);
		add(lblEliminarPersona);
		
		list = new JList<Persona>();
		list.setBounds(164, 65, 116, 153);
		dlmodel = new DefaultListModel<Persona>();
		list.setModel(dlmodel);
		
	    add(list);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(169, 245, 97, 25);
		add(btnEliminar);
		
		

	}
	
	public void actualizarLista(Persona[] listaPersonas) {
		
		list.setListData(listaPersonas);
		
	}
	
}
