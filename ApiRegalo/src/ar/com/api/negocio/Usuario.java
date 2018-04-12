package ar.com.api.negocio;

import java.util.Date;

public class Usuario {

	private int id;
	private String nombre;
	private String mail;
	private Date fechaAlta;
	private String password;

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

	

	public Usuario(String nombre, String mail, Date fechaAlta, String password) {
		super();
		this.nombre = nombre;
		this.mail = mail;
		this.fechaAlta = fechaAlta;
		this.password = password;
	}
	
	

	public Usuario(int id, String nombre, String mail, Date fechaAlta, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.fechaAlta = fechaAlta;
		this.password = password;
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
}
