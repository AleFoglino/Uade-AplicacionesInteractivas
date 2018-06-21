/**
 * 
 */
package ar.com.api.dao;

import ar.com.api.negocio.Pago;

/**
 * @author Alejandro Foglino
 *
 */
public abstract class PagoDao extends Dao<Pago> {

	protected static String INSERT_PAGO = "INSERT INTO {0} ( fecha_Alta, fecha_Pago, monto_Pagado, numero_Tarjeta,marca,estado) VALUES (?,?,?,?,?,?)";

	protected static String UPDATE_PAGO = "UPDATE {0} SET fecha_Pago = ?, monto_Pagado = ?,  numero_Tarjeta= ?, marca = ? , estado = ? WHERE Id = {1}";

	protected static String DELETE_PAGO = "UPDATE {0} SET  fecha_Pago =  null, monto_Pagado = 0 , estado =  '''" + "CANCELADO" + "''' WHERE Id = {1}";

}
