/**
 * 
 */
package ar.com.api.negocio;

/**
 * @author Alejandro Foglino
 *
 */
public enum EstadoDelUsuario {

	ELIMINADO() {
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
	};

	public abstract String getDescripcion();

}
