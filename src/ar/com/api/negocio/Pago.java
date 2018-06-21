/**
 * 
 */
package ar.com.api.negocio;

import java.util.Date;

/**
 * @author Alejandro Foglino
 *
 */
public class Pago {

	private EstadosDelPago estado;
	private Date fechaPago;
	private float montoPagado;
	private Date fechaAltaPago;
	private MedioPago medioPago;
	private int id;

	/**
	 * 
	 */
	public Pago(MedioPago medioPago) {
		this.estado = EstadosDelPago.NOHAYREGISTRO;
		this.montoPagado = 0;
		this.fechaAltaPago = new Date();
		this.medioPago = medioPago;
	}

	public EstadosDelPago getEstado() {
		return estado;
	}

	public void setEstado(EstadosDelPago estado) {
		this.estado = estado;
	}

	public float getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(float montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Date getFechaAltaPago() {
		return fechaAltaPago;
	}

	public void setFechaAltaPago(Date fechaAltaPago) {
		this.fechaAltaPago = fechaAltaPago;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Los valores del pago son:\nID:" + this.getId() + "\nFecha Alta: " + this.getFechaAltaPago()
				+ "\nFecha del Pago:" + this.getFechaPago() + "\nMonto Pagado: " + this.getMontoPagado()+"\nEstado: " + this.getEstado().name();
	}

}
