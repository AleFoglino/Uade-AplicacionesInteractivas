/**
 * 
 */
package ar.com.api.pojo;

import java.util.Date;

import ar.com.api.negocio.Usuario;

/**
 * @author Alejandro Foglino
 *
 */
public class UsuarioPojo {

	private int idUsuario;
	private String nombreUsuario;
	private String mail;
	private Date fechaCreacionUsuario;
	
	
	public UsuarioPojo(int idUsuario, String nombreUsuario, String mail, Date fechaCreacionUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.mail = mail;
		this.fechaCreacionUsuario = fechaCreacionUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getFechaCreacionUsuario() {
		return fechaCreacionUsuario;
	}

	public void setFechaCreacionUsuario(Date fechaCreacionUsuario) {
		this.fechaCreacionUsuario = fechaCreacionUsuario;
	}
	
	public static UsuarioPojo toBO(Usuario usuario){
		return new UsuarioPojo(usuario.getId(), usuario.getNombre(), usuario.getMail(), usuario.getFechaAlta());
	}
	
	
}
