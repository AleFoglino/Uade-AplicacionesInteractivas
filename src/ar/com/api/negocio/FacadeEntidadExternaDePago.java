/**
 * 
 */
package ar.com.api.negocio;

import java.util.List;

/**
 * @author Alejandro Foglino
 *
 */
public interface FacadeEntidadExternaDePago {

	public List<TransaccionPago> confirmarPago();
	public void efectuarPago(String agasajado, String usuario,float montoDelPago , MedioPago medioPago);
	public boolean aceptaMedioPago(MedioPago medioPago);
}
