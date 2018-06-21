/**
 * 
 */
package ar.com.api.negocio;

import java.util.Date;

/**
 * @author Alejandro Foglino
 *
 */
public enum EstadosDelPago {


	 FINALIZADO() {
		 @Override
		    public Date notificarPago() {
		        return new Date();
		    }
	 },
	
	 PENDIENTE() {
		 @Override
		    public Date notificarPago() {
		        return null;
		    }
	 },
	 
	 CANCELADO() {
		 @Override
		    public Date notificarPago() {
		        return null;
		    }
		 },
	 
	 NOHAYREGISTRO() {

		@Override
		public Date notificarPago() {
			// TODO Auto-generated method stub
			return null;
		}
			 },
	 
	 ERROR() {
	    @Override
	    public Date notificarPago() {
	        return null;
	    }
	 };
	 
	
	 
	/**
	 * @param usuario
	 * @return
	 */
	public abstract Date notificarPago() ;
}
