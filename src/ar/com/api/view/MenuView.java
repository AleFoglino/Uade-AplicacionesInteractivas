package ar.com.api.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import ar.com.api.pojo.UsuarioPojo;

public class MenuView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Menu frame = new Menu();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public MenuView(UsuarioPojo usuario) {
		setTitle("Listas de Regalo - Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		/*******************
		 * Menu Usuario
		 *****************************************************/
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);

		JMenuItem mntmAltaDeUsuario = new JMenuItem("Alta de Usuario");
		mntmAltaDeUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// JFrame Usuario = new Usuario("Alta de Usuario");
				if (usuario.isEsAdmin()) {
					JFrame Usuario = new UsuarioView(View.ALTA);
					// Menu.setVisible(false); //Bloquea el menu
					Usuario.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "El usuario no es valido para generar Usuario", "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnUsuarios.add(mntmAltaDeUsuario);

		JMenuItem mntmBusquedaDeUsuario = new JMenuItem("B\u00FAsqueda de Usuario");
		mntmBusquedaDeUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFrame BusquedaUsuario = new BuscarUsuarioView();
				// Menu.setVisible(false);
				BusquedaUsuario.setVisible(true);
			}
		});
		mnUsuarios.add(mntmBusquedaDeUsuario);
		/*******************
		 * Fin Menu Usuario
		 *****************************************************/
		/*******************
		 * Menu Lista
		 *****************************************************/
		JMenu mnListas = new JMenu("Listas");
		menuBar.add(mnListas);

		JMenuItem mntmCreacionDeLista = new JMenuItem("Creaci\u00F3n de Lista");
		mntmCreacionDeLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ListaCumpleaniosView listaCumpleaniosView = new ListaCumpleaniosView(View.ALTA);
				// Menu.setVisible(false);
				listaCumpleaniosView.setVisible(true);
			}
		});
		mnListas.add(mntmCreacionDeLista);

		JMenuItem mntmBusquedaDeLista = new JMenuItem("B\u00FAsqueda de Lista");
		mntmBusquedaDeLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BuscaListaDeCumpleaniosView listaCumpleaniosView = new BuscaListaDeCumpleaniosView();
				// Menu.setVisible(false);
				listaCumpleaniosView.setVisible(true);
			}
		});
		mnListas.add(mntmBusquedaDeLista);
		/*******************
		 * Fin Menu Lista
		 *****************************************************/
		
		/*******************
		 * Menu Look and feel
		 *****************************************************/

		JMenu mntmLookFeel = new JMenu("Look and Feel");
		JRadioButtonMenuItem metalItems = new JRadioButtonMenuItem(LookAndFeelUI.MetalLookAndFeel.name());
		metalItems.setHorizontalTextPosition(JMenuItem.RIGHT);
		JRadioButtonMenuItem motifItems = new JRadioButtonMenuItem(LookAndFeelUI.MotifLookAndFeel.name());
		motifItems.setHorizontalTextPosition(JMenuItem.RIGHT);
		JRadioButtonMenuItem nimbusItmes = new JRadioButtonMenuItem(LookAndFeelUI.NimbusLookAndFeel.name());
		nimbusItmes.setHorizontalTextPosition(JMenuItem.RIGHT);
		JRadioButtonMenuItem windowItems = new JRadioButtonMenuItem(LookAndFeelUI.WindowsLookAndFeel.name());
		windowItems.setHorizontalTextPosition(JMenuItem.RIGHT);
		ActionListener sliceActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton aButton = (AbstractButton) actionEvent.getSource();
				try {
					UIManager.setLookAndFeel(LookAndFeelUI.valueOf(aButton.getText()).getDescripcion());
					SwingUtilities.updateComponentTreeUI(menuBar);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		metalItems.addActionListener(sliceActionListener);
		motifItems.addActionListener(sliceActionListener);
		nimbusItmes.addActionListener(sliceActionListener);
		windowItems.addActionListener(sliceActionListener);
		ButtonGroup group = new ButtonGroup();
		group.add(metalItems);
		group.add(motifItems);
		group.add(nimbusItmes);
		group.add(windowItems);
		mntmLookFeel.add(metalItems);
		mntmLookFeel.add(motifItems);
		mntmLookFeel.add(nimbusItmes);
		mntmLookFeel.add(windowItems);
		menuBar.add(mntmLookFeel);
		/*******************
		 * Fin Menu Look and feel
		 *****************************************************/
		/*******************
		 * Menu Ayuda
		 *****************************************************/
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Página en Construcción", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnAyuda.add(mntmAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de ...");
		mntmAcercaDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Versión del sistema: 1.0", "Versión",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnAyuda.add(mntmAcercaDe);
		/*******************
		 * Fin Menu Ayuda
		 *****************************************************/
		/*******************
		 * Cierre sesion
		 *****************************************************/
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesi\u00F3n");
		mntmCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Cerrando la sesion del  usuario: " + usuario.getNombreUsuario(),
						"Cerrar Sesion", JOptionPane.INFORMATION_MESSAGE);
				LoginView loginView = new LoginView();
				loginView.setVisible(true);
				setVisible(false);
			}
		});
		mnSistema.add(mntmCerrarSesion);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(EXIT_ON_CLOSE);
				;
			}
		});
		mnSistema.add(mntmSalir);
		/*******************
		 * Fin Menu Cierre sesion
		 *****************************************************/

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
