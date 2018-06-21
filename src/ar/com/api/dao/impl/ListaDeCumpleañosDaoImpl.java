/**
 * 
 */
package ar.com.api.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.com.api.convert.ConvertObject;
import ar.com.api.dao.ListaDeCumpleaniosDao;
import ar.com.api.excepciones.ConvertDaoException;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Estado;
import ar.com.api.negocio.ListaDeCumpleanios;
import ar.com.api.util.ConnectionUtils;
import ar.com.api.util.ConsultaUtils;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 *
 */
public class ListaDeCumpleañosDaoImpl extends ListaDeCumpleaniosDao {

	/**
	 * 
	 */
	public ListaDeCumpleañosDaoImpl() {
		connection = ConnectionUtils.getIntancesConnection();
		className = ManejadorMensajes.formatoNombreDeClase(ListaDeCumpleanios.class.getName().toString().toLowerCase());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.api.dao.bis.Dao#save(java.lang.Object)
	 */
	@Override
	public ListaDeCumpleanios save(ListaDeCumpleanios t) throws DaoExcepcion {
		try {
			int valores = 1;
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(INSERT_LISTA, className);
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparadaPorID(consulta);
			pt.setString(valores++, t.getNombre());
			pt.setDate(valores++, new java.sql.Date(t.getFechaAlta().getTime()));
			pt.setDate(valores++, new java.sql.Date(t.getFechaVigencia().getTime()));
			pt.setString(valores++, t.getAgasajado());
			pt.setFloat(valores++, t.getMontoRecuado());
			pt.setString(valores++, t.getEstado().name());
			pt.executeUpdate();
			ResultSet rs = pt.getGeneratedKeys();
			while (rs.next()) {
				t.setId(rs.getInt(1));
				ManejadorMensajes.logDebug("Id Lista de cumpleaños generado: " + t.getId());
			}
			return t;
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.api.dao.bis.Dao#findByID(java.lang.Object)
	 */
	@Override
	public ListaDeCumpleanios findByID(Object id) throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorID(className, id);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			List<ListaDeCumpleanios> list = ConvertObject.convertRSToListaCumpleanios(rs);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.api.dao.bis.Dao#deleteByID(java.lang.Object)
	 */
	@Override
	public int deleteByID(Object id) throws DaoExcepcion {
		ListaDeCumpleanios l = findByID(id);
		l.setEstado(Estado.CANCELADO);
		return update(l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.api.dao.bis.Dao#findAll()
	 */
	@Override
	public List<ListaDeCumpleanios> findAll() throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorTodos(className);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			return ConvertObject.convertRSToListaCumpleanios(rs);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.api.dao.bis.Dao#update(java.lang.Object)
	 */
	@Override
	public int update(ListaDeCumpleanios t) throws DaoExcepcion {
		try {
			int valor = 1;
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(UPDATE_LISTA, className, t.getId());
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparada(consulta);
			pt.setString(valor++, t.getNombre());
			pt.setString(valor++, t.getAgasajado());
			pt.setFloat(valor++, t.getMontoRecuado());
			pt.setDate(valor++, new java.sql.Date(t.getFechaVigencia().getTime()));
			pt.setString(valor++, t.getEstado().name());
			return pt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.api.dao.ListaDeCumpleaniosDao#findByAgasajado(java.lang.String)
	 */
	@Override
	public ListaDeCumpleanios findByAgasajado(String agasajado) throws DaoExcepcion {
		try {
			connection.connect();
			consulta = "SELECT * FROM {0} WHERE agasajado = '''" + agasajado + "''' and " + ConsultaUtils.PREFIX_STATE;
			consulta = ManejadorMensajes.consultarMensajePorParametro(consulta, className);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			List<ListaDeCumpleanios> list = ConvertObject.convertRSToListaCumpleanios(rs);
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

}
