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

import ar.com.api.controler.ControlerListaCumpleanios;
import ar.com.api.controler.ControlerUsuario;
import ar.com.api.excepciones.ControlersException;
import ar.com.api.pojo.ListaCumpleaniosPojo;
import ar.com.api.pojo.UsuarioPojo;
import ar.com.api.view.BuscarUsuarioView.ButtonEditor;
import ar.com.api.view.BuscarUsuarioView.ButtonRenderer;

/**
 * @author Alejandro Foglino
 *
 */
public class BuscaListaDeCumpleaniosView extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextField txtLista;
	ControlerUsuario controlerUsuario;
	private JButton btnSalir;
	private JTable table;
	private JButton btnCancelar;
	private DefaultTableModel dtm;
	private ControlerListaCumpleanios controlerListaCumpleanios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscaListaDeCumpleaniosView frame = new BuscaListaDeCumpleaniosView();
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
	public BuscaListaDeCumpleaniosView() {
		controlerListaCumpleanios = ControlerListaCumpleanios.getInstances();
		setTitle("B\u00FAsqueda de Lista");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLista = new JLabel("Nombre de Lista:");
		lblLista.setBounds(10, 11, 112, 14);
		contentPane.add(lblLista);

		txtLista = new JTextField();
		txtLista.setBounds(132, 8, 156, 20);
		contentPane.add(txtLista);
		txtLista.setColumns(10);
		btnSalir = new JButton("Limpiar");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtLista.setText("");
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
				btnSalir.setVisible(true);
			}

			
		});
		btnBuscar.setBounds(321, 7, 89, 23);
		contentPane.add(btnBuscar);

		btnCancelar = new JButton("Cerrar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(446, 7, 89, 23);
		contentPane.add(btnCancelar);

	}

	private void cargarTabla() throws ControlersException {
		table = new JTable();
		dtm = new DefaultTableModel(0, 0);
		String[] columnNames = { "Nombre Lista", "Agasajado",  "Fecha Vigencia", "AgregarMiembro","Actualizar" ,"Cancelar"};
		dtm.setColumnIdentifiers(columnNames);
		table.setModel(dtm);
		List<ListaCumpleaniosPojo> list = null;
		if (txtLista.getText().equals("")) {
			list = controlerListaCumpleanios.obtenerTodasLasListas();
		} else {
			list = new ArrayList<ListaCumpleaniosPojo>();
			list.add(controlerListaCumpleanios.obtenerListaDeCumpleaniosPorNombre(txtLista.getText()));
		}
		for (ListaCumpleaniosPojo listaCumpleaniosPojo : list) {
			Vector<Object> data = new Vector<Object>();
			data.add(listaCumpleaniosPojo.getNombreLista());
			data.add(listaCumpleaniosPojo.getAgasajado());
			data.add(listaCumpleaniosPojo.getFechaVigencia());
			data.add("Agregar");
			data.add("Modificar");
			data.add("Eliminar");
			dtm.addRow(data);
		}
		table.getColumn("AgregarMiembro").setCellRenderer(new ButtonRenderer());
		table.getColumn("AgregarMiembro").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Actualizar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Actualizar").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Cancelar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Cancelar").setCellEditor(new ButtonEditor(new JCheckBox()));
		scrollPane = new JScrollPane(table);
		// Add the scroll pane to this panel.
		scrollPane.setBounds(47, 84, 524, 142);
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
			isPushed = true;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			String nombreLista = (String) table.getValueAt(row, 0);
			if (label.equals("Modificar")) {
					JOptionPane.showMessageDialog(button, "Lista seleccionada: " + nombreLista );
					JFrame lista = new ListaCumpleaniosView(View.MODIFCAR,nombreLista);
					lista.setVisible(true);
					try {
						recargarTabla();
					} catch (ControlersException e) {
						JOptionPane.showMessageDialog(null, "Error al actualizar lista de usuario", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
			}else if(label.equals("Eliminar") ) {
				if (JOptionPane.showConfirmDialog(button," ¿Esta seguro que desa eliminar la lista " + nombreLista +"?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						controlerListaCumpleanios.eliminarLista(nombreLista);
						JOptionPane.showMessageDialog(null, "La lista se elimino correctamente.", "",
								JOptionPane.INFORMATION_MESSAGE);
						recargarTabla();
						//cargarTabla();
					} catch (ControlersException e) {
						JOptionPane.showMessageDialog(null, "Error al borrar la lista", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
				    // no option
				}
			}
			isPushed = false;
			return label;
		}

		/**
		 * 
		 */
		private void recargarTabla() throws ControlersException{
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}
	}
}
