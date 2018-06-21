/**
 * 
 */
package ar.com.api.dao;

import java.util.List;

import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Mail;

/**
 * @author Alejandro Foglino
 *
 */
public abstract class EnviarMailDao extends Dao<Mail>{

	protected static String INSERT_MAIL = "INSERT INTO inboxmail( asunto, emisor, contenido, fecha_envio, id_destinatario_fk) VALUES (?,?,?,?,?)";
	
	protected static String FIND_MAIL_USER = "SELECT * FROM inboxmail where id_destinatario_fk = {0} ";
	public abstract List<Mail> findMailByUser(int idDestinatario)throws DaoExcepcion;

}
