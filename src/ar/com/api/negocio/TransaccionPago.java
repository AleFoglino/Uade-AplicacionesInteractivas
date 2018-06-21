/**
 * 
 */
package ar.com.api.negocio;

/**
 * @author Alejandro Foglino
 *
 */
public class TransaccionPago {

	private int codigoTransaccion;
	private String agasajado;
	private String usuario;
	private EstadosDelPago estadosDelPago;

	public TransaccionPago(String agasajado, String usuario, EstadosDelPago estadosDelPago) {
		super();
		this.setCodigoTransaccion((int) (Math.random() * 10) + 1);
		this.agasajado = agasajado;
		this.usuario = usuario;
		this.setEstadosDelPago(estadosDelPago);
	}
	public String getAgasajado() {
		return agasajado;
	}
	public void setAgasajado(String agasajado) {
		this.agasajado = agasajado;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public int getCodigoTransaccion() {
		return codigoTransaccion;
	}
	public void setCodigoTransaccion(int codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}
	public EstadosDelPago getEstadosDelPago() {
		return estadosDelPago;
	}
	public void setEstadosDelPago(EstadosDelPago estadosDelPago) {
		this.estadosDelPago = estadosDelPago;
	}
	
	
}
