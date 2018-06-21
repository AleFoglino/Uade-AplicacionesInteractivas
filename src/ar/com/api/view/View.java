/**
 * 
 */
package ar.com.api.view;

/**
 * @author Alejandro Foglino
 *
 */
public enum View {

	ALTA() {
		@Override
		public String getDescripcion() {
			return "Alta de ";
		}
	},

	CANCELADO() {
		@Override
		public String getDescripcion() {
			return "Eliminación de ";
		}
	},

	MODIFCAR() {
		@Override
		public String getDescripcion() {
			return "Modificación de ";
		}
	};

	
	public abstract String getDescripcion();
}
