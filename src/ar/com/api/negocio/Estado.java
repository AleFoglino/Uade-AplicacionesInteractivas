/**
 * 
 */
package ar.com.api.negocio;

/**
 * @author Alejandro Foglino
 *
 */
public enum Estado {

	FINALIZADO() {
		@Override
		public String getDescripcion() {
			return "Estado Finalizado";
		}
	},

	CANCELADO() {
		@Override
		public String getDescripcion() {
			return "Estado Cancelado";
		}
	},

	CREADO() {
		@Override
		public String getDescripcion() {
			return "Estado creado";
		}
	},

	PENDIENTE() {

		@Override
		public String getDescripcion() {
			return "Estado Pendiente";
		}
	
	};

	public abstract String getDescripcion();
}
