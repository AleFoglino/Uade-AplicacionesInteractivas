/**
 * 
 */
package ar.com.api.negocio;



/**
 * @author Alejandro Foglino
 *
 */
public class MiembroDeLaLista implements Observer {

	private Usuario usuario;
	private boolean esAdministrador;
	private Pago pago;	
	private Estado estado;
	
	/**
	 * 
	 */
	public MiembroDeLaLista() {
		this.pago = new Pago(null);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isSoyAdministrador() {
		return esAdministrador;
	}

	public void setSoyAdministrador(boolean soyAdministrador) {
		this.esAdministrador = soyAdministrador;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	/**
	 * @param estadosDelPago
	 */
	@Override	
	public void actualizarPago(EstadosDelPago estadosDelPago) {
		if (this.getPago().getEstado() == EstadosDelPago.PENDIENTE) {
			this.getPago().setFechaPago(estadosDelPago.notificarPago());
			System.out.println("Se efectuo el pago del usuario:  " + this.getUsuario().getNombre()
					+ " Monto pagado: " + this.getPago().getMontoPagado());
		}
	}

	
	@Override
	public void actualizar(Estado estado) {
		setEstado(estado);
		
	}

	public Estado getEstado() {
		return estado;
	}

	private void setEstado(Estado estado) {
		this.estado = estado;
	}
		
	}
