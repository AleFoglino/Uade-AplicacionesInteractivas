/**
 * 
 */
package ar.com.api.controler;

import java.util.ArrayList;
import java.util.List;

import ar.com.api.dao.Dao;
import ar.com.api.dao.impl.UsuarioDaoImpl;
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

	Dao<Usuario> dao;
	/**
	 * 
	 */
	public ControlerUsuario() {
		dao = new UsuarioDaoImpl();
	}

	
	public List<UsuarioPojo> obtenerTodosLosUsuarios() throws ControlersException{
		
		List<UsuarioPojo> listUsuario = new ArrayList<UsuarioPojo>();
		try {
			List<Usuario> listResultado = dao.findAll();
			for (Usuario usuario : listResultado) {
				listUsuario.add(UsuarioPojo.toBO(usuario));
			}
			return listUsuario;
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
			throw new ControlersException(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
		}
	}
	
}
