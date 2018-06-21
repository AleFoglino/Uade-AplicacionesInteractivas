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
import ar.com.api.dao.EnviarMailDao;
import ar.com.api.excepciones.ConvertDaoException;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Mail;
import ar.com.api.negocio.Usuario;
import ar.com.api.util.ConnectionUtils;
import ar.com.api.util.ConsultaUtils;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 *
 */
public class EnviarMailDaoImpl extends EnviarMailDao {

	/**
	 * 
	 */
	public EnviarMailDaoImpl() {
		connection = ConnectionUtils.getIntancesConnection();
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#save(java.lang.Object)
	 */
	@Override
	public Mail save(Mail t) throws DaoExcepcion {
		try {
			connection.connect();
			for (Usuario u : t.getDestinatarios()) {				
				int valores = 1;
				consulta = INSERT_MAIL;
				ManejadorMensajes.logDebug(consulta);
				PreparedStatement pt = connection.consultaPreparadaPorID(consulta);
				pt.setString(valores++, t.getAsunto());
				pt.setString(valores++, t.getEmisor());
				pt.setString(valores++, t.getContenido());
				pt.setDate(valores++, new Date(t.getFechaEmision().getTime()) );
				pt.setInt(valores++, u.getId());
				pt.executeUpdate(); 
				ResultSet rs = pt.getGeneratedKeys();
				while(rs.next()){
					t.setMail(rs.getInt(1));
					ManejadorMensajes.logDebug("Id Mail generado: " + t.getMail());		
				}
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
	public Mail findByID(Object id) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#deleteByID(java.lang.Object)
	 */
	@Override
	public int deleteByID(Object id) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#findAll()
	 */
	@Override
	public List<Mail> findAll() throws DaoExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#update(java.lang.Object)
	 */
	@Override
	public int update(Mail t) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.EnviarMailDao#findMailByUser(int)
	 */
	@Override
	public List<Mail> findMailByUser(int idDestinatario) throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(FIND_MAIL_USER, idDestinatario);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			List<Mail> list = ConvertObject.convertRSToMail(rs);
			if (list.size() == 0)
				throw new DaoExcepcion(ManejadorMensajes
						.consultarMensajePorParametro(MensajesUtils.MSJ_WARNING_OBJETO_NO_ENCONTRADO, className));
			return list;
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
