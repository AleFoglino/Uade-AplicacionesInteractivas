/**
 * 
 */
package ar.com.api.pojo;

import java.util.Date;

import com.mysql.jdbc.EscapeTokenizer;

import ar.com.api.negocio.Estado;
import ar.com.api.negocio.Usuario;

/**
 * @author Alejandro Foglino
 *
 */
public class UsuarioPojo {

	private int idUsuario;
	private String nombreUsuario;
	private String nombre;
	private String mail;
	private Date fechaCreacionUsuario;
	private String pasword;
	private boolean esAdmin;
	
	
	public UsuarioPojo(int idUsuario, String nombreUsuario, String mail, Date fechaCreacionUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.mail = mail;
		this.fechaCreacionUsuario = fechaCreacionUsuario;
	}
	
	
	

	public UsuarioPojo(String nombreUsuario, String mail, Date fechaCreacionUsuario, String pasword, boolean esAdmin) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.mail = mail;
		this.fechaCreacionUsuario = fechaCreacionUsuario;
		this.pasword = pasword;
		this.esAdmin = esAdmin;
	}




	public UsuarioPojo(int idUsuario, String nombre, String nombreUsuario, String mail, Date fechaCreacionUsuario, String pasword,
			boolean esAdmin) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.mail = mail;
		this.nombre = nombre;
		this.fechaCreacionUsuario = fechaCreacionUsuario;
		this.pasword = pasword;
		this.esAdmin = esAdmin;
	}



	/**
	 * @param i
	 */
	public UsuarioPojo(int i) {
		this.setIdUsuario(i);
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
	
	public static UsuarioPojo toPojo(Usuario usuario){
		return new UsuarioPojo(usuario.getId(), usuario.getNombre(), usuario.getUsuario(), usuario.getMail(),usuario.getFechaAlta(), usuario.getPassword(), usuario.isEsAdminSistema());
	}

	public static Usuario toBO(UsuarioPojo usuario){
		return new Usuario(usuario.getIdUsuario(), usuario.getNombre(), "",usuario.getNombreUsuario(), usuario.getMail(),usuario.getFechaCreacionUsuario(), usuario.getPasword(),Estado.PENDIENTE, usuario.isEsAdmin());
	}
	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
