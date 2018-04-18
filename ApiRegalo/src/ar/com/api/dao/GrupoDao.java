/**
 * 
 */
package ar.com.api.dao;

import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Grupo;

/**
 * @author Alejandro Foglino
 *
 */
public abstract class GrupoDao extends Dao<Grupo> {

	protected static String UPDATE_GRUPO = "UPDATE {0} SET Nombre = ?, Agasajado = ?, MontoTotal = ?  WHERE Id = {1}";
	protected static String INSERT_GRUPO = "Insert into {0} (Nombre, Agasajado,	MontoTotal) values (?,?,?)";

	public abstract int saveParticipante(int idUsuario,String idRol, int idGrupo) throws DaoExcepcion;
	public abstract int payParticipante(int idUsuario,String idRol, int idGrupo, float monto)throws DaoExcepcion;
}
