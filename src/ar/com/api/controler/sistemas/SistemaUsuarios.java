/**
 * 
 */
package ar.com.api.controler.sistemas;

import java.util.ArrayList;
import java.util.List;

import ar.com.api.dao.UsuarioDao;
import ar.com.api.dao.impl.UsuarioDaoImpl;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Usuario;

/**
 * @author Alejandro Foglino
 *
 */
public class SistemaUsuarios {

	private List<Usuario> listUsuarios = null;

	private static SistemaUsuarios SISTEMA_USUARIOS = null;
	
	private static UsuarioDao usuarioDao = null;

	private SistemaUsuarios() {
		setListUsuarios(new ArrayList<Usuario>());
	}

	public static synchronized SistemaUsuarios getInstanceUsuario() {
		if (SISTEMA_USUARIOS == null) {
			usuarioDao = new UsuarioDaoImpl();
			SISTEMA_USUARIOS = new SistemaUsuarios();
		}
		return SISTEMA_USUARIOS;
	}

	public void agregarNuevoUsuario(String nombre, String apellido, String usuario, String mail, String password) throws DaoExcepcion {
		
		Usuario user = new Usuario(nombre, apellido, usuario, mail, password);
		user = usuarioDao.save(user);
 		listUsuarios.add(user);
	}

	public Usuario buscarUsuario(String usuario) throws DaoExcepcion {
		
		for (Usuario user : getListUsuarios()) {
			if (user != null) {
				if (user.getUsuario().trim().equals(usuario) ) {
					return user;
				}
			}
		}
		return null;
	}

	public void modificarPasswordUsuario(String usuario, String password) throws DaoExcepcion {
		Usuario user =	buscarUsuario(usuario);
		if(user != null) {
			user.setPassword(password);
		}
	}
	
	public void modificarDatosUsuario(Usuario u) throws DaoExcepcion {		
		usuarioDao.update(u);
		replaceUser(u);
	}
	
	public void darBajaUsuario(String usuario) throws DaoExcepcion {
		Usuario user = buscarUsuario(usuario);
		if(user != null) {
			usuarioDao.deleteByID(user.getId());
			listUsuarios.remove(user);
		}
		
	}
	
	public List<Usuario> getListUsuarios() throws DaoExcepcion {
		if(listUsuarios.size() == 0) {			
			listUsuarios = usuarioDao.findAll();
		}
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}
	
	private void replaceUser(Usuario u) {
		for (int i = 0; i < listUsuarios.size(); i++) {
			if(listUsuarios.get(i).getId() == u.getId()) {
				listUsuarios.set(i, u);
			}
		}
	}

}
