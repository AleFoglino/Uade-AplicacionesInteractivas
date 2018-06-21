/**
 * 
 */
package ar.com.api.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.com.api.convert.ConvertObject;
import ar.com.api.dao.PagoDao;
import ar.com.api.excepciones.ConvertDaoException;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Pago;
import ar.com.api.util.ConnectionUtils;
import ar.com.api.util.ConsultaUtils;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 *
 */
public class PagoDaoImpl extends PagoDao {

	/**
	 * 
	 */
	public PagoDaoImpl() {
		connection = ConnectionUtils.getIntancesConnection();
		className = ManejadorMensajes.formatoNombreDeClase(Pago.class.getName().toString().toLowerCase());
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#save(java.lang.Object)
	 */
	@Override
	public Pago save(Pago t) throws DaoExcepcion {
		try {
			int valores = 1;
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(INSERT_PAGO, className) ;
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparadaPorID(consulta);
			pt.setDate(valores++, new java.sql.Date(t.getFechaAltaPago().getTime()));
			pt.setDate(valores++, null);
			pt.setFloat(valores++, t.getMontoPagado());
			pt.setInt(valores++, t.getMedioPago().getNumeroTarjeta());
			pt.setString(valores++, t.getMedioPago().getMarca());
			pt.setString(valores++, t.getEstado().name());
			pt.executeUpdate(); 
			ResultSet rs = pt.getGeneratedKeys();
			while(rs.next()){
				t.setId(rs.getInt(1));
				ManejadorMensajes.logDebug("Id Pago generado: " + t.getId());		
			}
			return t;
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#findByID(java.lang.Object)
	 */
	@Override
	public Pago findByID(Object id) throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorID(className, id);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			List<Pago> list = ConvertObject.convertRSToPago(rs);
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

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#deleteByID(java.lang.Object)
	 */
	@Override
	public int deleteByID(Object id) throws DaoExcepcion {
		try {
			connection.connect();
			//consulta = ConsultaUtils.eliminarPorID(className);
			consulta = ManejadorMensajes.consultarMensajePorParametro(DELETE_PAGO, className,id);
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparada(consulta);
			return pt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#findAll()
	 */
	@Override
	public List<Pago> findAll() throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorTodos(className);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			return ConvertObject.convertRSToPago(rs);
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

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#update(java.lang.Object)
	 */
	@Override
	public int update(Pago t) throws DaoExcepcion {
		try {
			int valor = 1;
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(UPDATE_PAGO, className,t.getId());
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparada(consulta);
			pt.setDate(valor++, new Date(t.getFechaPago().getTime()));
			pt.setFloat(valor++,t.getMontoPagado());
			pt.setInt(valor++,t.getMedioPago().getNumeroTarjeta());
			pt.setString(valor++, t.getMedioPago().getMarca());
			pt.setString(valor++, t.getEstado().name());
			return pt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

}
