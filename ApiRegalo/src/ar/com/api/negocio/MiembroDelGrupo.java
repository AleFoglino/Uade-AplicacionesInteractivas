/**
 * 
 */
package ar.com.api.negocio;

/**
 * @author Alejandro Foglino
 *
 */
public class MiembroDelGrupo {

	private Usuario usuario;
	private Rol rol;
	private float montoRecaudar;
	
	/**
	 * 
	 */
	public MiembroDelGrupo() {
		// TODO Auto-generated constructor stub
	}

	
	public MiembroDelGrupo(Usuario usuario, Rol rol) {
		super();
		this.usuario = usuario;
		this.rol = rol;
	}

	

	public MiembroDelGrupo(Usuario usuario, Rol rol, float montoRecaudar) {
		super();
		this.usuario = usuario;
		this.rol = rol;
		this.montoRecaudar = montoRecaudar;
	}


	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public float getMontoRecaudar() {
		return montoRecaudar;
	}


	public void setMontoRecaudar(float montoRecaudar) {
		this.montoRecaudar = montoRecaudar;
	}

}
