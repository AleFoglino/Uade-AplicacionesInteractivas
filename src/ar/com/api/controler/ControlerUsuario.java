/**
 * 
 */
package ar.com.api.controler;

import java.util.ArrayList;
import java.util.List;

import ar.com.api.controler.sistemas.SistemaUsuarios;
import ar.com.api.excepciones.ControlersException;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Usuario;
import ar.com.api.pojo.UsuarioPojo;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 *
 */
public class ControlerUsuario {

	SistemaUsuarios sistemaUsuarios;

	private static ControlerUsuario CONTROLER_USER = null;
	/**
	 * 
	 */
	private ControlerUsuario() {
		sistemaUsuarios = SistemaUsuarios.getInstanceUsuario();
	}
	
	public synchronized static ControlerUsuario getInstanceControler() {
		if(CONTROLER_USER == null)
			CONTROLER_USER = new ControlerUsuario();
		return CONTROLER_USER;
	}

	public List<UsuarioPojo> obtenerTodosLosUsuarios() throws ControlersException {

		List<UsuarioPojo> listUsuario = new ArrayList<UsuarioPojo>();
		try {
			List<Usuario> listResultado = sistemaUsuarios.getListUsuarios();
			for (Usuario usuario : listResultado) {
				listUsuario.add(UsuarioPojo.toPojo(usuario));
			}
			return listUsuario;
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
			throw new ControlersException(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
		}
	}

	public UsuarioPojo obtenerUsuarioPorNombre(String usuario) throws ControlersException {

		try {
			Usuario user = sistemaUsuarios.buscarUsuario(usuario);
			return UsuarioPojo.toPojo(user);
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
			throw new ControlersException(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
		}
	}

	public UsuarioPojo validarPasswordeUsuario(String usuario, String password) throws ControlersException {

		try {
			Usuario user = sistemaUsuarios.buscarUsuario(usuario);
			return UsuarioPojo.toPojo(user);
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
			throw new ControlersException(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
		}
	}
	
	public void crearUsuario(String nombre, String usuario, String password, String mail) throws ControlersException {
		try {
			sistemaUsuarios.agregarNuevoUsuario(nombre, "", usuario, mail, password);
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
			throw new ControlersException(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
		}
	}
	
	public void actualizarUsuario(UsuarioPojo user) throws ControlersException {
		try {
			sistemaUsuarios.modificarDatosUsuario(UsuarioPojo.toBO(user));
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
			throw new ControlersException(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
		}
	}

	public void eliminarUsuario(String usuario) throws ControlersException {
		try {
			sistemaUsuarios.darBajaUsuario(usuario);
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
			throw new ControlersException(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
		}
	}
}
