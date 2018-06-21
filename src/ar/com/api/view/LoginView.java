package ar.com.api.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import ar.com.api.controler.ControlerUsuario;
import ar.com.api.excepciones.ControlersException;
import ar.com.api.pojo.UsuarioPojo;

public class LoginView extends JFrame{

	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	private ControlerUsuario controlerUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(LookAndFeelUI.WindowsLookAndFeel.getDescripcion());
					LoginView window = new LoginView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
		controlerUsuario = ControlerUsuario.getInstanceControler();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setTitle("Listas de Regalo - Login");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					
					if(validaCamposContraseña()) 
						JOptionPane.showMessageDialog(null, "Debe ingresar contraseña!!!", "", JOptionPane.ERROR_MESSAGE);						 
					else if(validaCamposUsuaurio())
						JOptionPane.showMessageDialog(null, "Debe ingresar usuario!!!", "", JOptionPane.ERROR_MESSAGE);						 						
					else {						
						UsuarioPojo user = controlerUsuario.validarPasswordeUsuario(txtUsuario.getText().trim(), txtContrasena.getText().trim());
						if(user.getPasword().equals(txtContrasena.getText())) {
							JFrame Menu = new MenuView(user);
							setVisible(false);
							Menu.setVisible(true);						 
						}else{
							JOptionPane.showMessageDialog(null, "Usuario o la contraseña es invalida!!!", "", JOptionPane.ERROR_MESSAGE);						 
						}
					}
				} catch (ControlersException e) {
					JOptionPane.showMessageDialog(null, "Error al conectarse con la base de datos!!!", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptar.setBounds(116, 176, 89, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(232, 176, 89, 23);
		getContentPane().add(btnCancelar);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setBounds(235, 54, 86, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(235, 106, 86, 20);
		getContentPane().add(txtContrasena);
		
		JLabel lblusuario = new JLabel("Usuario:");
		lblusuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblusuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblusuario.setBounds(88, 57, 46, 14);
		getContentPane().add(lblusuario);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContrasena.setBounds(88, 108, 69, 14);
		getContentPane().add(lblContrasena);
		
		JLabel lblIngreseSusDatos = new JLabel("Ingrese sus datos");
		lblIngreseSusDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseSusDatos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIngreseSusDatos.setBounds(128, 20, 168, 23);
		getContentPane().add(lblIngreseSusDatos);
	}

	private boolean validaCamposContraseña() {
		return (this.txtContrasena.getText().equals("")); 
	}
	
	private boolean validaCamposUsuaurio() {
		return (this.txtUsuario.getText().equals("") ); 
	}
}
