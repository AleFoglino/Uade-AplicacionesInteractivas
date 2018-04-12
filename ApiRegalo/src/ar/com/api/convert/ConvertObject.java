/**
 * 
 */
package ar.com.api.convert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.api.dao.RolDao;
import ar.com.api.excepciones.ConvertDaoException;
import ar.com.api.negocio.Administrador;
import ar.com.api.negocio.Asistente;
import ar.com.api.negocio.Rol;
import ar.com.api.negocio.Usuario;
import ar.com.api.util.ConsultaUtils;

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
			list.add(new Usuario(rs.getInt("Id"),rs.getString("Nombre"),rs.getString("Mail"),rs.getDate("FechaAlta"),rs.getString("Password")));
		}
		return list;
	}
	
	public static List<Rol>convertRSToRol(ResultSet rs) throws ConvertDaoException, SQLException{
		if(rs == null){
			throw new ConvertDaoException();
		}
		List<Rol> list = new ArrayList<Rol>();
		Rol rol = null;
		while(rs.next()){
			if(rs.getString("Prefijo") == ConsultaUtils.PREFIJO_ADMIN){
				rol = new Administrador(rs.getInt("Id"),rs.getString("Nombre"),rs.getString("Estado"),rs.getDate("FechaAlta"),rs.getString("Prefijo"),rs.getBoolean("EsAdminSistema"));				
			}else if (rs.getString("Prefijo") == ConsultaUtils.PREFIJO_ASISTENTE){
				rol = new Asistente(rs.getInt("Id"),rs.getString("Nombre"),rs.getString("Estado"),rs.getDate("FechaAlta"),rs.getString("Prefijo"));
			}
			list.add(rol);
		}
		return list;
	}
	
}
