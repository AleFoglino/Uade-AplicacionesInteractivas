/**
 * 
 */
package ar.com.api.dao;

import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Usuario;

/**
 * @author Alejandro Foglino
 *
 */
public abstract class UsuarioDao extends Dao<Usuario> {

	protected static String INSERT_USUARIO = "INSERT INTO {0}  (nombre, mail, fecha_alta, estado, usuario, password, admin_sistema) VALUES (?,?,?,?,?,?,?)";

	protected static String UPDATE_USUARIO = "UPDATE {0} SET nombre = ?, mail = ?,  password= ?, estado = ? , usuario = ? WHERE Id = {1}";

	protected static String DELETE_USUARIO = "UPDATE {0} SET  estado =  '''" + "CANCELADO" + "''' WHERE Id = {1}";

	public abstract Usuario finadByName(String nombre) throws DaoExcepcion;
}
