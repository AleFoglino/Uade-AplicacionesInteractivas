/**
 * 
 */
package ar.com.api.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ar.com.api.controler.ControlerUsuario;
import ar.com.api.excepciones.ControlersException;
import ar.com.api.pojo.UsuarioPojo;

/**
 * @author Alejandro Foglino
 *
 */
public class BuscarUsuarioView extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextField txtUsuario;
	ControlerUsuario controlerUsuario;
	private JButton btnSalir;
	private JTable table;
	private JButton btnCancelar;
	private DefaultTableModel dtm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarUsuarioView frame = new BuscarUsuarioView();
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
	public BuscarUsuarioView() {
		controlerUsuario = ControlerUsuario.getInstanceControler();
		setTitle("B\u00FAsqueda de Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(42, 11, 62, 14);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(141, 8, 156, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		btnSalir = new JButton("Limpiar");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUsuario.setText("");
				scrollPane.setVisible(false);
				btnSalir.setVisible(false);
			}
		});
		btnSalir.setBounds(191, 237, 89, 23);
		btnSalir.setVisible(false);
		contentPane.add(btnSalir);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					cargarTabla();
					btnSalir.setVisible(true);
				} catch (ControlersException e1) {
					JOptionPane.showMessageDialog(null, "Error al conectarse con la base de datos!!!", "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(329, 7, 89, 23);
		contentPane.add(btnBuscar);
		btnCancelar = new JButton("Cerrar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(428, 7, 89, 23);
		contentPane.add(btnCancelar);
	}

	private void cargarTabla() throws ControlersException {
		table = new JTable();
		dtm = new DefaultTableModel(0, 0);
		String[] columnNames = { "Usuario", "Nombre", "Mail", "Fecha Creacion", "Es Admin", "Actualizar" ,"Cancelar"};
		dtm.setColumnIdentifiers(columnNames);
		table.setModel(dtm);
		List<UsuarioPojo> listUser = null;
		if (txtUsuario.getText().equals("")) {
			listUser = controlerUsuario.obtenerTodosLosUsuarios();
		} else {
			listUser = new ArrayList<UsuarioPojo>();
			listUser.add(controlerUsuario.obtenerUsuarioPorNombre(txtUsuario.getText()));
		}
		for (UsuarioPojo usuarioPojo : listUser) {
			Vector<Object> data = new Vector<Object>();
			data.add(usuarioPojo.getNombreUsuario());
			data.add(usuarioPojo.getNombre());
			data.add(usuarioPojo.getMail());
			data.add(usuarioPojo.getFechaCreacionUsuario());
			data.add(usuarioPojo.isEsAdmin());
			data.add("Modificar");
			data.add("Eliminar");
			dtm.addRow(data);
		}
		table.getColumn("Actualizar").setCellRenderer(new ButtonRenderer());
		ButtonEditor be =  new ButtonEditor(new JCheckBox());
		be.setUsuarioView(this);
		table.getColumn("Actualizar").setCellEditor(be);
		table.getColumn("Cancelar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Cancelar").setCellEditor(new ButtonEditor(new JCheckBox()));
		scrollPane = new JScrollPane(table);
		// Add the scroll pane to this panel.
		scrollPane.setBounds(57, 84, 524, 142);
		contentPane.add(scrollPane);
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {

		protected JButton button;
		private String label;
		private boolean isPushed;
		private int row, col;
		private JTable table;
		private BuscarUsuarioView usuarioView;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			this.table = table;
			this.row = row;
			this.col = column;
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			label = (value == null) ? "" : value.toString();
			button.setText(label);
			button.setName(label);
			if(label.equals("Modificar")) {				
				ImageIcon delete = new ImageIcon("resources/close.png");
				button.setIcon(delete);
			}
			isPushed = true;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			String user = (String) table.getValueAt(row, 0);
			if (button.getName().equals("Modificar")) {
					JOptionPane.showMessageDialog(button, "Usuario seleccionado: " + user );
					UsuarioView usuario = new UsuarioView(View.MODIFCAR, user);
					usuario.setBuscarUsuarioView(getUsuarioView());
					usuario.setVisible(true);
			}else if(button.getName().equals("Eliminar") ) {
				if (JOptionPane.showConfirmDialog(button," ¿Esta seguro que desa eliminar el usario " + user +"?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						controlerUsuario.eliminarUsuario(user);
						JOptionPane.showMessageDialog(null, "El usuario se elimino correctamente.", "",
								JOptionPane.INFORMATION_MESSAGE);
						recargarTabla();
						//cargarTabla();
					} catch (ControlersException e) {
						JOptionPane.showMessageDialog(null, "Error al borran el usuario", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
				    // no option
				}
			}
			isPushed = false;
			return label;
		}

		@Override
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}

		public BuscarUsuarioView getUsuarioView() {
			return usuarioView;
		}

		public void setUsuarioView(BuscarUsuarioView usuarioView) {
			this.usuarioView = usuarioView;
		}
	}

	public void recargarTabla() throws ControlersException {
		int rowCount = dtm.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    dtm.removeRow(i);
		}
		List<UsuarioPojo> listUser = null;
		if (txtUsuario.getText().equals("")) {
			listUser = controlerUsuario.obtenerTodosLosUsuarios();
		} else {
			listUser = new ArrayList<UsuarioPojo>();
			listUser.add(controlerUsuario.obtenerUsuarioPorNombre(txtUsuario.getText()));
		}
		for (UsuarioPojo usuarioPojo : listUser) {
			Vector<Object> data = new Vector<Object>();
			data.add(usuarioPojo.getNombreUsuario());
			data.add(usuarioPojo.getNombre());
			data.add(usuarioPojo.getMail());
			data.add(usuarioPojo.getFechaCreacionUsuario());
			data.add(usuarioPojo.isEsAdmin());
			data.add("Modificar");
			data.add("Eliminar");
			dtm.addRow(data);
		}
		table.getColumn("Actualizar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Actualizar").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Cancelar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Cancelar").setCellEditor(new ButtonEditor(new JCheckBox()));
	}
}
