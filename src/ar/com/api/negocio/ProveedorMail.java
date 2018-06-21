/**
 * 
 */
package ar.com.api.negocio;

import ar.com.api.dao.EnviarMailDao;
import ar.com.api.dao.impl.EnviarMailDaoImpl;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.util.ManejadorMensajes;

/**
 * @author Alejandro Foglino
 *
 */
public class ProveedorMail implements FacadeProveedorMail {

	EnviarMailDao enviarMailDao ;
	/**
	 * 
	 */
	public ProveedorMail() {
		enviarMailDao = new  EnviarMailDaoImpl();
	}

	/* (non-Javadoc)
	 * @see ar.com.api.negocio.FacadeProveedorMail#enviarMail(ar.com.api.negocio.Mail)
	 */
	@Override
	public void enviarMail(Mail mail) {
		try {
			enviarMailDao.save(mail);
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError("Error al enviar mail", e);
		}

	}

}
