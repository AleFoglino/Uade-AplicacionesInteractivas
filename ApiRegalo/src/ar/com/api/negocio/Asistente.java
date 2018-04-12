/**
 * 
 */
package ar.com.api.negocio;

import java.util.Date;

/**
 * @author Alejandro Foglino
 *
 */
public class Asistente extends Rol{

	/**
	 * 
	 */
	public Asistente() {
		// TODO Auto-generated constructor stub
	}

	public Asistente(int id, String nombre, String estado, Date fechaAlta, String prefijo) {
		super(id, nombre, estado, fechaAlta, prefijo);
		// TODO Auto-generated constructor stub
	}

	public Asistente(String nombre, String estado, Date fechaAlta, String prefijo) {
		super(nombre, estado, fechaAlta, prefijo);
		// TODO Auto-generated constructor stub
	}
	
	

}
