/**
 * 
 */
package ar.com.api.dao;

import java.util.List;

import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.MiembroDeLaLista;

/**
 * @author Alejandro Foglino
 *
 */
public abstract class MiembroDeLaListaDao extends Dao<MiembroDeLaLista> {

	protected static String INSERT_MIENBRO = "INSERT INTO {0} (id_usuario_Fk, id_lista_Fk, admin_lista, id_pago_Fk, estado) VALUES (?,?,?,?,?)";
	protected static String FIND_USUARIOS_BY_IDLISTA = "SELECT u.*, padre.admin_lista from usuario u, {0} padre where padre.estado !=  '''" + "CANCELADO" + "''' and u.id in (SELECT id_usuario_Fk from {0} hija where hija.id_lista_Fk = padre.id_lista_Fk and hija.id_lista_Fk = {1} )";
	/**
	 * 
	 */
	public MiembroDeLaListaDao() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract MiembroDeLaLista guardaMiembroDeLaLista(int idUsuario, int idLista,boolean admin ) throws DaoExcepcion; 

	public abstract List<MiembroDeLaLista> findMiembroByIDLista(int idLista)throws DaoExcepcion;
}
