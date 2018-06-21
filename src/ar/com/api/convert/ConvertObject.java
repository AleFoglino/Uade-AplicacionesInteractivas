/**
 * 
 */
package ar.com.api.convert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.api.excepciones.ConvertDaoException;
import ar.com.api.negocio.Estado;
import ar.com.api.negocio.EstadosDelPago;
import ar.com.api.negocio.ListaDeCumpleanios;
import ar.com.api.negocio.Mail;
import ar.com.api.negocio.MedioPago;
import ar.com.api.negocio.MiembroDeLaLista;
import ar.com.api.negocio.Pago;
import ar.com.api.negocio.Usuario;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 *
 */
public class ConvertObject {

	
	
	public static List<Usuario>convertRSToUsuario(ResultSet rs) throws ConvertDaoException, SQLException{
		if(rs == null){
			throw new ConvertDaoException();
		}
		List<Usuario> list = new ArrayList<Usuario>();
		while(rs.next()){
			
			list.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), "", rs.getString("usuario"), rs.getString("mail"), rs.getDate("fecha_alta"), rs.getString("password"), Estado.valueOf(rs.getString("estado")), rs.getBoolean("admin_sistema")));
		}
		return list;
	}
	

	
	public static List<ListaDeCumpleanios>convertRSToListaCumpleanios(ResultSet rs) throws ConvertDaoException, SQLException{
		if(rs == null){
			throw new ConvertDaoException();
		}
		List<ListaDeCumpleanios> list = new ArrayList<ListaDeCumpleanios>();
		while(rs.next()){
			list.add(new ListaDeCumpleanios(rs.getInt("id"), rs.getString("nombre"),rs.getString("agasajado"), rs.getDate("fecha_alta"),rs.getDate("fecha_vigencia") ,Estado.valueOf(rs.getString("estado")) ,rs.getFloat("monto_recaudado")));
		}
		return list;
	}
	
	public static List<Pago>convertRSToPago(ResultSet rs) throws ConvertDaoException, SQLException{
		if(rs == null){
			throw new ConvertDaoException();
		}
		List<Pago> list = new ArrayList<Pago>();
		while(rs.next()){
			Pago pago = new Pago(new MedioPago(rs.getInt("numero_Tarjeta"), rs.getString("marca")));
			pago.setFechaAltaPago(rs.getDate("fecha_Alta"));
			pago.setFechaPago(rs.getDate("fecha_Pago"));
			pago.setId(rs.getInt("id"));
			pago.setMontoPagado(rs.getFloat("monto_Pagado"));
			pago.setEstado(EstadosDelPago.valueOf(rs.getString("estado")));
			list.add(pago);
		}
		return list;
	}
	
	public static List<MiembroDeLaLista>convertRSToUsuarioByMiembro(ResultSet rs) throws ConvertDaoException, SQLException{
		if(rs == null){
			throw new ConvertDaoException();
		}
		List<MiembroDeLaLista> list = new ArrayList<MiembroDeLaLista>();
		while(rs.next()){
			Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nombre"), "", rs.getString("usuario"), rs.getString("mail"), rs.getDate("fecha_alta"), rs.getString("password"), Estado.valueOf(rs.getString("estado")), rs.getBoolean("admin_sistema"));
			MiembroDeLaLista miembroDeLaLista =  new MiembroDeLaLista();
			miembroDeLaLista.setUsuario(usuario);
			miembroDeLaLista.setSoyAdministrador(rs.getBoolean("admin_lista"));
			list.add(miembroDeLaLista);
		}
		return list;
	}



	/**
	 * @param rs
	 * @return
	 * @throws ConvertDaoException 
	 * @throws SQLException 
	 */
	public static List<Mail> convertRSToMail(ResultSet rs) throws ConvertDaoException, SQLException {
		if(rs == null){
			throw new ConvertDaoException();
		}
		List<Mail> list = new ArrayList<Mail>();
		while(rs.next()) {
			Mail mail = new Mail();
			mail.setContenido(rs.getString("contenido"));
			mail.setAsunto(rs.getString("asunto"));
			mail.setEmisor(rs.getString("emisor"));
			mail.setFechaEmision(rs.getDate("fecha_envio"));
			mail.setDestinatarios(new ArrayList<Usuario>());
			mail.getDestinatarios().add(new Usuario(rs.getInt("id_destinatario_fk")));
			list.add(mail);
	}
		return list;
	}
}
