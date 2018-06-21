/**
 * 
 */
package ar.com.api.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import ar.com.api.controler.ControlerListaCumpleanios;
import ar.com.api.excepciones.ControlersException;
import ar.com.api.pojo.ListaCumpleaniosPojo;
import ar.com.api.pojo.UsuarioPojo;
import ar.com.api.util.DateLabelFormatter;

/**
 * @author Alejandro Foglino
 *
 */
public class ListaCumpleaniosView extends JFrame {

	private JPanel contentPane;
	private JTextField txtAgasajado;
	private JTextField txtNombre;
	private ControlerListaCumpleanios controlerListaCumpleanios;
	private UsuarioPojo usuarioPojo;
	private String nombreLista;
	private ListaCumpleaniosPojo listaCumpleaniosPojo;
	private UtilDateModel modelDate;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaCumpleaniosView frame = new ListaCumpleaniosView(View.ALTA);
					UsuarioPojo pojo = new UsuarioPojo(9);
					pojo.setNombreUsuario("Admin");
					frame.setUsuarioPojo(pojo);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListaCumpleaniosView(View tipo) {
		iniciarView(tipo);
	}

	/**
	 * @param modifcar
	 * @param nombreLista
	 */
	public ListaCumpleaniosView(View modifcar, String nombreLista) {
		this.nombreLista = nombreLista;
		iniciarView(modifcar);

	}

	private void iniciarView(View tipo) {

		setTitle(tipo.getDescripcion() + "Lista de Cumpleaños");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(58, 28, 85, 14);
		contentPane.add(lblNombre);

		JLabel lblEmail = new JLabel("Agasajado");
		lblEmail.setBounds(58, 62, 85, 14);
		contentPane.add(lblEmail);

		JLabel lblUsuario = new JLabel("Fecha Vigencia");
		lblUsuario.setBounds(58, 105, 85, 14);
		contentPane.add(lblUsuario);

		modelDate = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(modelDate, p);
		// Don't know about the formatter, but there it is...
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(177, 102, 135, 23);
		contentPane.add(datePicker);

		txtAgasajado = new JTextField();
		txtAgasajado.setBounds(177, 59, 135, 20);
		contentPane.add(txtAgasajado);
		txtAgasajado.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(177, 25, 135, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		controlerListaCumpleanios = ControlerListaCumpleanios.getInstances();
		// VER ESTO QUE NO FUNCIONA
		if (tipo == View.ALTA) {

			JButton btnAceptar = new JButton("Generar");
			btnAceptar.setBounds(54, 198, 89, 23);
			contentPane.add(btnAceptar);
			btnAceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						Date fehaVigencia = (Date) datePicker.getModel().getValue();
						if (validaCamposNombre())
							JOptionPane.showMessageDialog(null, "Debe ingresar nombre de la lista!!!", "",
									JOptionPane.ERROR_MESSAGE);
						else if (validaAgasjado())
							JOptionPane.showMessageDialog(null, "Debe ingresar nombre del asagajado!!!", "",
									JOptionPane.ERROR_MESSAGE);
						else if (validaFecha(fehaVigencia)) {
							JOptionPane.showMessageDialog(null, "Debe ingresar fecha valida!!!", "",
									JOptionPane.ERROR_MESSAGE);
						} else {
							controlerListaCumpleanios.crearLista(usuarioPojo.getNombreUsuario(), txtNombre.getText(),
									txtAgasajado.getText(), fehaVigencia);
							JOptionPane.showMessageDialog(null, "La lista  se creo con exito!!!", "",
									JOptionPane.INFORMATION_MESSAGE);
							limpiar();
						}
					} catch (ControlersException e) {
						JOptionPane.showMessageDialog(null, "Error al conectarse con la base de datos!!!", "",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			});

			// JButton btnActualizar = new JButton("Actualizar");
			// btnActualizar.setBounds(68, 227, 89, 23);
			// contentPane.add(btnActualizar);
			// que campos se deberian poder editar y cuales no
		} else if (tipo == View.CANCELADO) {

			JButton btnAceptar = new JButton("Eliminar");
			btnAceptar.setBounds(54, 198, 89, 23);

			// contentPane.add(btnAceptar);
			//
			// JButton btnBorrar = new JButton("Borrar");
			// btnBorrar.setBounds(78, 266, 89, 23);
			// contentPane.add(btnBorrar);
			// que campos se deberian poder editar y cuales no
		} else if (tipo == View.MODIFCAR) {

			JButton btnAceptar = new JButton("Modificar");
			btnAceptar.setBounds(54, 198, 89, 23);

			try {
				listaCumpleaniosPojo  = controlerListaCumpleanios.obtenerListaDeCumpleaniosPorNombre(this.nombreLista);
				txtAgasajado.setText(listaCumpleaniosPojo.getAgasajado());
				txtNombre.setText(listaCumpleaniosPojo.getNombreLista());
				modelDate = new UtilDateModel();
				modelDate.setDate(listaCumpleaniosPojo.getFechaVigencia().getYear(), listaCumpleaniosPojo.getFechaVigencia().getMonth(), listaCumpleaniosPojo.getFechaVigencia().getDay());
			} catch (ControlersException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btnAceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						Date fechaVigencia = (Date) datePicker.getModel().getValue();
						if (validaCamposNombre())
							JOptionPane.showMessageDialog(null, "Debe ingresar nombre de la lista!!!", "",
									JOptionPane.ERROR_MESSAGE);
						else if (validaAgasjado())
							JOptionPane.showMessageDialog(null, "Debe ingresar nombre del asagajado!!!", "",
									JOptionPane.ERROR_MESSAGE);
						else if (validaFecha(fechaVigencia)) {
							JOptionPane.showMessageDialog(null, "Debe ingresar fecha valida!!!", "",
									JOptionPane.ERROR_MESSAGE);
						} else {
							listaCumpleaniosPojo.setAgasajado(txtAgasajado.getText());
							listaCumpleaniosPojo.setNombreLista(txtNombre.getText());
							listaCumpleaniosPojo.setFechaVigencia(fechaVigencia);
							controlerListaCumpleanios.actualizarLista(listaCumpleaniosPojo);
							JOptionPane.showMessageDialog(null, "La lista  se actualizo con exito!!!", "",
									JOptionPane.INFORMATION_MESSAGE);
							limpiar();
						}
						
					} catch (ControlersException e) {
						JOptionPane.showMessageDialog(null, "Error al conectarse con la base de datos!!!", "",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			JButton btnGenerar = new JButton("Generar");
			btnGenerar.setBounds(72, 247, 89, 23);
			contentPane.add(btnGenerar);
			// que campos se deberian poder editar y cuales no
		}

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(211, 198, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});

		// JButton btnAceptar = new JButton("Aceptar");
		// btnAceptar.setBounds(58, 214, 89, 23);
		// contentPane.add(btnAceptar);
	}

	private void limpiar() {
		txtAgasajado.setText("");
		txtNombre.setText("");
		

	}

	private boolean validaAgasjado() {
		return (this.txtAgasajado.getText().equals(""));
	}

	private boolean validaCamposNombre() {
		return (this.txtNombre.getText().equals(""));
	}

	private boolean validaFecha(Date date) {
		Date hoy = new Date();
		boolean valida = false;
		if (hoy.compareTo(date) > 0 && hoy.compareTo(date) != 0) {
			valida = true;
		}
		return valida;
	}

	public UsuarioPojo getUsuarioPojo() {
		return usuarioPojo;
	}

	public void setUsuarioPojo(UsuarioPojo usuarioPojo) {
		this.usuarioPojo = usuarioPojo;
	}

}
