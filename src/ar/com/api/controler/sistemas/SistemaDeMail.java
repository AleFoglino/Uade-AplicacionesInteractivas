/**
 * 
 */
package ar.com.api.controler.sistemas;

import java.util.ArrayList;

import ar.com.api.negocio.FacadeProveedorMail;
import ar.com.api.negocio.Mail;
import ar.com.api.negocio.ProveedorMail;
import ar.com.api.negocio.Usuario;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 *
 */
public class SistemaDeMail {

	
	private static SistemaDeMail SISTEMA_DE_MAIL;
	
	private FacadeProveedorMail proveedorMail;
	
	private SistemaDeMail() {
		proveedorMail = new ProveedorMail();
	}
	
	public static synchronized SistemaDeMail getInstanceSistemaDeMail() {
		if(SISTEMA_DE_MAIL == null) {
			SISTEMA_DE_MAIL = new SistemaDeMail();
		}
		return SISTEMA_DE_MAIL;
	}
	
	public void enviarMailBienvenida(Usuario usuario,String agasajado, String nombreLista) {
		Mail mail = new Mail();
		mail.setContenido(ManejadorMensajes.consultarMensajePorParametro(MensajesUtils.MSJ_MAIL_BIENVENIDA, nombreLista,agasajado));
		mail.setAsunto("Bienvenido a lista " + nombreLista);
		mail.setEmisor("envioAutomatico@listacumple.com");
		mail.setDestinatarios(new ArrayList<Usuario>());
		mail.getDestinatarios().add(usuario);
		proveedorMail.enviarMail(mail);
	}

	public FacadeProveedorMail getProveedorMail() {
		return proveedorMail;
	}

	public void setProveedorMail(FacadeProveedorMail proveedorMail) {
		this.proveedorMail = proveedorMail;
	}
	
	
}
