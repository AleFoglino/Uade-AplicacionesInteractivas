/**
 * 
 */
package ar.com.api.negocio;

import java.util.Date;
import java.util.List;

/**
 * @author Alejandro Foglino
 *
 */
public class Grupo {

	private int id;
	private String nombre;
	private String agasajado;
	private Date fechaAlta;
	private List<MiembroDelGrupo> miembroDelGrupos;
	private float montoTotalRecudado;

	/**
	 * 
	 */
	public Grupo() {
		// TODO Auto-generated constructor stub
	}

	
	public Grupo(int id, String nombre, String agasajado, Date fechaAlta, float montoTotalRecudado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.agasajado = agasajado;
		this.fechaAlta = fechaAlta;
		this.montoTotalRecudado = montoTotalRecudado;
	}



	public List<MiembroDelGrupo> getMiembroDelGrupos() {
		return miembroDelGrupos;
	}

	public void setMiembroDelGrupos(List<MiembroDelGrupo> miembroDelGrupos) {
		this.miembroDelGrupos = miembroDelGrupos;
	}

	public float getMontoTotalRecudado() {
		return montoTotalRecudado;
	}

	public void setMontoTotalRecudado(float montoTotalRecudado) {
		this.montoTotalRecudado = montoTotalRecudado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAgasajado() {
		return agasajado;
	}

	public void setAgasajado(String agasajado) {
		this.agasajado = agasajado;
	}


	public Date getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
