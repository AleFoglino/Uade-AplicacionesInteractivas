/**
 * 
 */
package ar.com.api.view;

/**
 * @author Alejandro Foglino
 *
 */
public enum LookAndFeelUI {

	MotifLookAndFeel() {
		@Override
		public String getDescripcion() {
			return "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		}
	},

	NimbusLookAndFeel() {
		@Override
		public String getDescripcion() {
			return "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		}
	},

	MetalLookAndFeel() {
		@Override
		public String getDescripcion() {
			return "javax.swing.plaf.metal.MetalLookAndFeel";
		}
	},
	
	WindowsLookAndFeel() {
		@Override
		public String getDescripcion() {
			return "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		}
	};

	
	public abstract String getDescripcion();
}
