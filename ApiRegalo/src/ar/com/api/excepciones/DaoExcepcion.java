/**
 * 
 */
package ar.com.api.excepciones;

/**
 * @author Alejandro Foglino
 *
 */
public class DaoExcepcion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoExcepcion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DaoExcepcion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DaoExcepcion(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DaoExcepcion(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DaoExcepcion(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
