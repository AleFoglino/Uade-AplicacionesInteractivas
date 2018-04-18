/**
 * 
 */
package ar.com.api.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.com.api.convert.ConvertObject;
import ar.com.api.dao.UsuarioDao;
import ar.com.api.excepciones.ConvertDaoException;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Usuario;
import ar.com.api.util.ConnectionUtils;
import ar.com.api.util.ConsultaUtils;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author C02536
 *
 */
public class UsuarioDaoImpl extends UsuarioDao {

	

	public UsuarioDaoImpl() {
		connection = ConnectionUtils.getIntancesConnection();
		className =  ManejadorMensajes.formatoNombreDeClase(Usuario.class.getName().toString().toLowerCase());
	}

	@Override
	public Usuario save(Usuario t) throws DaoExcepcion {
		try {
			int valores = 1;
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(INSERT_USUARIO, className) ;
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparadaPorID(consulta);
			pt.setString(valores++, t.getNombre());
			pt.setString(valores++, t.getMail());
			pt.setDate(valores++, new java.sql.Date(t.getFechaAlta().getTime()));
			pt.setString(valores++, t.getPassword());
			pt.executeUpdate(); 
			ResultSet rs = pt.getGeneratedKeys();
			while(rs.next()){
				t.setId(rs.getInt(1));
				ManejadorMensajes.logDebug("Id Usuario generado: " + t.getId());		
			}
			return t;
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

	@Override
	public List<Usuario> findAll() throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorTodos(className);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			return ConvertObject.convertRSToUsuario(rs);
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} catch (ConvertDaoException e) {
			ManejadorMensajes.logError(e.getMessage(), e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONVERT_POJO, e);
		} finally {
			limpiar();
		}
	}

	@Override
	public Usuario findByID(Object id) throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorID(className, id);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			List<Usuario> list = ConvertObject.convertRSToUsuario(rs);
			if (list.size() == 0)
				throw new DaoExcepcion(ManejadorMensajes
						.consultarMensajePorParametro(MensajesUtils.MSJ_WARNING_OBJETO_NO_ENCONTRADO, className));
			if (list.size() > 1)
				throw new DaoExcepcion(ManejadorMensajes
						.consultarMensajePorParametro(MensajesUtils.MSJ_WARNING_OBJETO_MAS_ENCONTRADO, className));
			return list.get(0);
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} catch (ConvertDaoException e) {
			ManejadorMensajes.logError(e.getMessage(), e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONVERT_POJO, e);
		} finally {
			limpiar();
		}
	}

	@Override
	public int deleteByID(Object id) throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.eliminarPorID(className);
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparada(consulta);
			pt.setInt(1, (int)id);
			return pt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

	@Override
	public int update(Usuario t) throws DaoExcepcion {
		try {
			int valor = 1;
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(UPDATE_USUARIO, className,t.getId());
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparada(consulta);
			pt.setString(valor++, t.getNombre());
			pt.setString(valor++,t.getMail());
			pt.setString(valor++,t.getPassword());
			return pt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

	
	

}
