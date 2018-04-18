/**
 * 
 */
package ar.com.api.negocio;

import java.util.Date;

/**
 * @author Alejandro Foglino
 *
 */
public class Administrador extends Rol {

	private boolean esAdminSistema;

	/**
	 * 
	 */
	public Administrador() {
		// TODO Auto-generated constructor stub
	}
	
	public Administrador(String id, String nombre, String estado, Date fechaAlta, String prefijo, boolean esAdminSistema) {
		super(id, nombre, estado, fechaAlta, prefijo);
		this.esAdminSistema = esAdminSistema;
	}






	public boolean isAdminSistema() {
		return esAdminSistema;
	}

	public void setAdminSistema(boolean adminSistema) {
		this.esAdminSistema = adminSistema;
	}

	public Administrador(boolean adminSistema) {
		super();
		this.esAdminSistema = adminSistema;
	}

}
