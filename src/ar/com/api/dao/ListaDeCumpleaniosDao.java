/**
 * 
 */
package ar.com.api.dao;

import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.ListaDeCumpleanios;

/**
 * @author Alejandro Foglino
 *
 */
public abstract class ListaDeCumpleaniosDao extends Dao<ListaDeCumpleanios> {

	protected static String UPDATE_LISTA = "UPDATE {0} SET nombre = ?, agasajado = ?, monto_recaudado = ?,fecha_vigencia = ?, estado= ?   WHERE Id = {1}";
	protected static String INSERT_LISTA = "Insert into {0} (nombre, fecha_alta,fecha_vigencia,agasajado,monto_recaudado,estado) values (?,?,?,?,?,?)";
	public abstract ListaDeCumpleanios findByAgasajado(String agasajado)throws DaoExcepcion;
}
