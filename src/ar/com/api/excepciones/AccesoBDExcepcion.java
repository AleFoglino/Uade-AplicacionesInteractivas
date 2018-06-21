/**
 * 
 */
package ar.com.api.excepciones;

/**
 * @author Alejandro Foglino
 *
 */
public class AccesoBDExcepcion  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AccesoBDExcepcion() {
		// TODO Auto-generated constructor stub
	}

	public AccesoBDExcepcion(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public AccesoBDExcepcion(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public AccesoBDExcepcion(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public AccesoBDExcepcion(Throwable arg0) {
		super("Error al conectarse en la base de datos.");
		// TODO Auto-generated constructor stub
	}

	
	
}
