/**
 * 
 */
package ar.com.api.negocio;

import java.util.Date;

/**
 * @author Alejandro Foglino
 *
 */
public abstract class Rol {

	private int Id;
	private String nombre;
	private String estado;
	private Date fechaAlta;
	private String prefijo;

	/**
	 * 
	 */

	public Rol() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public Rol(int id, String nombre, String estado, Date fechaAlta, String prefijo) {
		super();
		Id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.fechaAlta = fechaAlta;
		this.prefijo = prefijo;
	}

	public Rol(String nombre, String estado, Date fechaAlta, String prefijo) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.fechaAlta = fechaAlta;
		this.prefijo = prefijo;
	}

}
