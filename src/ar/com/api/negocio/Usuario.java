package ar.com.api.negocio;

import java.util.Date;

public class Usuario {

	private int id;
	private String nombre;
	private String apellido;
	private String usuario;
	private String mail;
	private Date fechaAlta;
	private String password;
	private Estado estado;
	private boolean esAdminSistema;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Usuario(String nombre, String apellido, String usuario, String mail, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.mail = mail;
		this.password = password;
		this.fechaAlta = new Date();
		this.estado = Estado.CREADO;
		this.esAdminSistema = false;
	}

	public Usuario(String nombre, String mail, String password) {
		super();
		this.nombre = nombre;
		this.mail = mail;
		this.fechaAlta = new Date();
		this.password = password;
		this.estado = Estado.CREADO;
		this.esAdminSistema = false;
	}
	
	

	

	public Usuario(String nombre, String usuario, String mail, Date fechaAlta, String password,
			Estado estado, boolean esAdminSistema) {
		super();
		this.nombre = nombre;
		this.esAdminSistema = esAdminSistema;
		this.usuario = usuario;
		this.mail = mail;
		this.fechaAlta = fechaAlta;
		this.password = password;
		this.estado = estado;
		this.esAdminSistema = esAdminSistema;
	}
	
	

	public Usuario(int id, String nombre, String apellido, String usuario, String mail, Date fechaAlta, String password,
			Estado estado, boolean esAdminSistema) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.mail = mail;
		this.fechaAlta = fechaAlta;
		this.password = password;
		this.estado = estado;
		this.esAdminSistema = esAdminSistema;
	}

	/**
	 * @param int1
	 */
	public Usuario(int int1) {
		this.setId(int1);
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "El Usuario tiene los siguientes valores:\nId: " + this.getId() + "\nNombre: " + this.getNombre()
				+ "\nMail: " + this.getMail() + "\nFechaAlta: " + this.getFechaAlta();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public boolean isEsAdminSistema() {
		return esAdminSistema;
	}

	public void setEsAdminSistema(boolean esAdminSistema) {
		this.esAdminSistema = esAdminSistema;
	}
}
