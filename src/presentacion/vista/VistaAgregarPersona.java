package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

public class VistaAgregarPersona extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	
	private JButton btnAceptar;

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
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

	public JTextField getTxtDNI() {
		return txtDNI;
	}

	public void setTxtDNI(JTextField txtDNI) {
		this.txtDNI = txtDNI;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public VistaAgregarPersona() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(94, 37, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(94, 81, 46, 14);
		contentPane.add(lblApellido);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(94, 116, 46, 14);
		contentPane.add(lblDni);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(117, 159, 89, 23);
		contentPane.add(btnAceptar);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(163, 34, 86, 20);
		contentPane.add(txtNombre);
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
		txtApellido.setBounds(163, 78, 86, 20);
		contentPane.add(txtApellido);
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
		
		txtDNI = new JTextField();
		txtDNI.setBounds(163, 113, 86, 20);
		contentPane.add(txtDNI);
		txtDNI.setColumns(10);
		
		// Validacion solo n�meros
	    txtDNI.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
	                e.consume();  // Ignorar la entrada si no es n�mero
	            }
	        }
	    });
	}
	
	//Metodo para mostrar mensaje
	public void mostrarMensaje(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public void show()
	{
		this.setVisible(true);
	}
}
