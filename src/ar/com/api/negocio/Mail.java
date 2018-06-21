/**
 * 
 */
package ar.com.api.negocio;

import java.util.Date;
import java.util.List;

/**
 * @author Alejandro Foglino
 *
 */
public class Mail {

	private int mail;
	private String contenido;
	private List<Usuario>destinatarios;
	private String emisor;
	private Date fechaEmision;
	private String asunto;
	
	/**
	 * 
	 */
	public Mail() {
		// TODO Auto-generated constructor stub
	}
	
	

	public int getMail() {
		return mail;
	}

	public Mail(String emisor, List<Usuario> destinatarios, String asunto, String contenido) {
		super();
		this.emisor = emisor;
		this.destinatarios = destinatarios;
		this.asunto = asunto;
		this.contenido = contenido;
		this.fechaEmision = new Date();
	}



	public void setMail(int mail) {
		this.mail = mail;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public List<Usuario> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<Usuario> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public Mail(String contenido, List<Usuario> destinatarios, String emisor) {
		super();
		this.contenido = contenido;
		this.destinatarios = destinatarios;
		this.emisor = emisor;
		this.fechaEmision = new Date();
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	
	

}
