/**
 * 
 */
package ar.com.api.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.com.api.controler.ControlerUsuario;
import ar.com.api.excepciones.ControlersException;
import ar.com.api.pojo.UsuarioPojo;

/**
 * @author Alejandro Foglino
 *
 */
public class UsuarioView extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtContrasena;
	private JTextField txtUsuario;
	private JTextField txtMail;
	private JTextField txtNombre;
	private ControlerUsuario controlerUsuario;
	private String usuario;
	UsuarioPojo user = null;
	private BuscarUsuarioView buscarUsuarioView;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioView frame = new UsuarioView(View.ALTA);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UsuarioView(View tipo, String usuario) {
		this.usuario = usuario;
		iniciarView(tipo);
	}

	private void iniciarView(View tipo) {
		
		setTitle(tipo.getDescripcion() + "Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(58, 28, 85, 14);
		contentPane.add(lblNombre);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(58, 62, 85, 14);
		contentPane.add(lblEmail);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(58, 105, 85, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(58, 152, 85, 14);
		contentPane.add(lblContrasea);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(213, 149, 86, 20);
		contentPane.add(txtContrasena);
		
		txtUsuario = new JTextField();
		txtUsuario.setText("");
		txtUsuario.setBounds(213, 102, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtMail = new JTextField();
		txtMail.setBounds(213, 59, 86, 20);
		contentPane.add(txtMail);
		txtMail.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(213, 25, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		controlerUsuario = ControlerUsuario.getInstanceControler();
		//VER ESTO QUE NO FUNCIONA
		if(tipo == View.ALTA)
		{
			ImageIcon i = new ImageIcon("resources/close.png");
			JButton btnAceptar = new JButton("Generar");
			btnAceptar.setIcon(i);
			btnAceptar.setBounds(58, 214, 89, 23);
			contentPane.add(btnAceptar);
			btnAceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						
						if(validaCamposContraseña()) 
							JOptionPane.showMessageDialog(null, "Debe ingresar contraseña!!!", "", JOptionPane.ERROR_MESSAGE);						 
						else if(validaCamposUsuaurio())
							JOptionPane.showMessageDialog(null, "Debe ingresar usuario!!!", "", JOptionPane.ERROR_MESSAGE);						 						
						else if(validaCamposMail())
							JOptionPane.showMessageDialog(null, "Debe ingersar mail!!!", "", JOptionPane.ERROR_MESSAGE);						 						
						else if(validaCamposNombre())
							JOptionPane.showMessageDialog(null, "Debe ingresar nombre!!!", "", JOptionPane.ERROR_MESSAGE);						 							
						else {						
							controlerUsuario.crearUsuario(txtNombre.getText(), txtUsuario.getText(), txtContrasena.getText(), txtMail.getText());
							JOptionPane.showMessageDialog(null, "El usuario de creo con exito nombre usuario!!!", "", JOptionPane.INFORMATION_MESSAGE);
							limpiar();
						}
					} catch (ControlersException e) {
						JOptionPane.showMessageDialog(null, "Error al conectarse con la base de datos!!!", "", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}else if(tipo ==View.CANCELADO)
		{

			JButton btnAceptar = new JButton("Eliminar");
			btnAceptar.setBounds(58, 214, 89, 23);
			contentPane.add(btnAceptar);
		}else if(tipo ==View.MODIFCAR)
		{	
			
			try {
				user =  controlerUsuario.obtenerUsuarioPorNombre(usuario);
				txtContrasena.setText(user.getPasword());
				txtMail.setText(user.getMail());
				txtNombre.setText(user.getNombre());
				txtUsuario.setText(user.getNombreUsuario());
			} catch (ControlersException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JButton btnAceptar = new JButton("Modificar");
			btnAceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						
						if(validaCamposContraseña()) 
							JOptionPane.showMessageDialog(null, "Debe ingresar contraseña!!!", "", JOptionPane.ERROR_MESSAGE);						 
						else if(validaCamposUsuaurio())
							JOptionPane.showMessageDialog(null, "Debe ingresar usuario!!!", "", JOptionPane.ERROR_MESSAGE);						 						
						else if(validaCamposMail())
							JOptionPane.showMessageDialog(null, "Debe ingersar mail!!!", "", JOptionPane.ERROR_MESSAGE);						 						
						else if(validaCamposNombre())
							JOptionPane.showMessageDialog(null, "Debe ingresar nombre!!!", "", JOptionPane.ERROR_MESSAGE);						 							
						else {						
							user.setMail(txtMail.getText());
							user.setNombre(txtNombre.getText());
							user.setNombreUsuario(txtUsuario.getText());
							user.setPasword(txtContrasena.getText());
							controlerUsuario.actualizarUsuario(user);
							JOptionPane.showMessageDialog(null, "El usuario se actualizo con exito nombre usuario!!!", "", JOptionPane.INFORMATION_MESSAGE);
							getBuscarUsuarioView().recargarTabla();
							dispose();
						}
					} catch (ControlersException e) {
						JOptionPane.showMessageDialog(null, "Error al conectarse con la base de datos!!!", "", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnAceptar.setBounds(58, 214, 89, 23);
			contentPane.add(btnAceptar);
		}
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(213, 214, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
	}
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public UsuarioView(View tipo) {
		iniciarView(tipo);
	}
	
	
	private boolean validaCamposContraseña() {
		return (this.txtContrasena.getText().equals("")); 
	}
	
	private boolean validaCamposUsuaurio() {
		return (this.txtUsuario.getText().equals("") ); 
	}
	
	private boolean validaCamposMail() {
		return (this.txtMail.getText().equals("") ); 
	}
	
	private boolean validaCamposNombre() {
		return (this.txtNombre.getText().equals("") ); 
	}
	
	private void limpiar() {
		this.txtContrasena.setText("");
		this.txtMail.setText("");
		this.txtNombre.setText("");
		this.txtUsuario.setText("");
	}

	public BuscarUsuarioView getBuscarUsuarioView() {
		return buscarUsuarioView;
	}

	public void setBuscarUsuarioView(BuscarUsuarioView buscarUsuarioView) {
		this.buscarUsuarioView = buscarUsuarioView;
	}
}
