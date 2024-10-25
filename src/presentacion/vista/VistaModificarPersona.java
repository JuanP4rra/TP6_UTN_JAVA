package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import entidad.Persona;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class VistaModificarPersona extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JButton btnModificar;
	private JList<Persona> listPersonas;
	private DefaultListModel<Persona> dlmPersonas;
	
	public VistaModificarPersona() {
		setLayout(null);
		
		listPersonas = new JList<Persona>();
		listPersonas.setBounds(53, 59, 399, 169);
		dlmPersonas= new  DefaultListModel<Persona>();
		listPersonas.setModel(dlmPersonas);
		add(listPersonas);
		
		JLabel lblSeleccionarPersona = new JLabel("Seleccionar la persona que desea modificar");
		lblSeleccionarPersona.setFont(new Font("Arial", Font.BOLD, 13));
		lblSeleccionarPersona.setBounds(53, 44, 331, 14);
		add(lblSeleccionarPersona);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(63, 239, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		// Validacion solo letras
	    txtNombre.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE) {
	                e.consume();  // Ignorar la entrada si no es letra
	            }
	        }
	    });
		
		txtApellido = new JTextField();
		txtApellido.setBounds(169, 239, 86, 20);
		add(txtApellido);
		txtApellido.setColumns(10);
		
		// Validacion solo letras
	    txtApellido.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE) {
	                e.consume();  // Ignorar la entrada si no es letra
	            }
	        }
	    });
		
		
		txtDni = new JTextField();
		txtDni.setBounds(272, 239, 86, 20);
		add(txtDni);
		txtDni.setColumns(10);
		
		// Bloquear todas las entradas
		txtDni.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        e.consume();  // Ignorar absolutamente todas las teclas
		    }
		    public void keyPressed(KeyEvent e) {
		        e.consume();  // Ignorar absolutamente todas las teclas en keyPressed
		    }

		    public void keyReleased(KeyEvent e) {
		        e.consume();  // Ignorar absolutamente todas las teclas en keyReleased
		    }
		});
		
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Arial", Font.BOLD, 12));
		btnModificar.setBounds(368, 239, 94, 30);
		add(btnModificar);
	}
	public JList<Persona> getListPersonas() {
		return listPersonas;
	}
	public void setListPersonas(JList<Persona> listPersonas) {
		this.listPersonas = listPersonas;
	}
	public JButton getBtnModificar() {
		return btnModificar;
	}
	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}
	public JTextField getTxtApellido() {
		return txtApellido;
	}
	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}
	public JTextField getTxtDni() {
		return txtDni;
	}
	public void setTxtDni(JTextField txtDni) {
		this.txtDni = txtDni;
	}
}
