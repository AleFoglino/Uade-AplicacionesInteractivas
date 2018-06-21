/**
 * 
 */
package ar.com.api.negocio;

/**
 * @author Alejandro Foglino
 *
 */
public interface Observer {

	public void actualizar(Estado estado);
	public void actualizarPago(EstadosDelPago estados);
}
