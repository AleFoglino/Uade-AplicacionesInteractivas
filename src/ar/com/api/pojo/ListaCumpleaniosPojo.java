/**
 * 
 */
package ar.com.api.pojo;

import java.util.Date;

import ar.com.api.negocio.ListaDeCumpleanios;

/**
 * @author Alejandro Foglino
 *
 */
public class ListaCumpleaniosPojo {

	private int idLista;
	private String nombreLista;
	private String agasajado;
	private Date fechaVigencia;
	private float montoTotalRecaudado;
	/**
	 * 
	 */
	public ListaCumpleaniosPojo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ListaCumpleaniosPojo(int idLista, String nombreLista, String agasajado, Date fechaVigencia,
			float montoTotalRecaudado) {
		super();
		this.idLista = idLista;
		this.nombreLista = nombreLista;
		this.agasajado = agasajado;
		this.fechaVigencia = fechaVigencia;
		this.montoTotalRecaudado = montoTotalRecaudado;
	}


	public int getIdLista() {
		return idLista;
	}
	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}
	public String getNombreLista() {
		return nombreLista;
	}
	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}
	public String getAgasajado() {
		return agasajado;
	}
	public void setAgasajado(String agasajado) {
		this.agasajado = agasajado;
	}
	public Date getFechaVigencia() {
		return fechaVigencia;
	}
	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}
	public float getMontoTotalRecaudado() {
		return montoTotalRecaudado;
	}
	public void setMontoTotalRecaudado(float montoTotalRecaudado) {
		this.montoTotalRecaudado = montoTotalRecaudado;
	}
	
	public static ListaCumpleaniosPojo toPojo(ListaDeCumpleanios listaDeCumpleanios) {
		return new ListaCumpleaniosPojo(listaDeCumpleanios.getId(), listaDeCumpleanios.getNombre(), listaDeCumpleanios.getAgasajado(), listaDeCumpleanios.getFechaVigencia(), listaDeCumpleanios.getMontoTotaRecuado());
	}
	
	public static ListaDeCumpleanios toBO(ListaCumpleaniosPojo listaCumpleaniosPojo) {
		return new ListaDeCumpleanios(listaCumpleaniosPojo.getIdLista(), listaCumpleaniosPojo.getNombreLista(), listaCumpleaniosPojo.getAgasajado(),listaCumpleaniosPojo.getFechaVigencia(),listaCumpleaniosPojo.getMontoTotalRecaudado());
	}

}
