/**
 * 
 */
package ar.com.api.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.com.api.convert.ConvertObject;
import ar.com.api.dao.MiembroDeLaListaDao;
import ar.com.api.excepciones.ConvertDaoException;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Estado;
import ar.com.api.negocio.MiembroDeLaLista;
import ar.com.api.negocio.Usuario;
import ar.com.api.util.ConnectionUtils;
import ar.com.api.util.ConsultaUtils;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 *
 */
public class MiembroDeLaListaDaoImpl extends MiembroDeLaListaDao {

	/**
	 * 
	 */
	public MiembroDeLaListaDaoImpl() {
		connection = ConnectionUtils.getIntancesConnection();
		className = ManejadorMensajes.formatoNombreDeClase(MiembroDeLaLista.class.getName().toString().toLowerCase());
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#save(java.lang.Object)
	 */
	@Override
	public MiembroDeLaLista save(MiembroDeLaLista t) throws DaoExcepcion {
		return t;
	}
	
	public MiembroDeLaLista guardaMiembroDeLaLista(int idUsuario, int idLista,boolean admin ) throws DaoExcepcion {
		try {
			MiembroDeLaLista t = new MiembroDeLaLista();
			int valores = 1;
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(INSERT_MIENBRO, className) ;
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparadaPorID(consulta);
			pt.setInt(valores++, idUsuario);
			pt.setInt(valores++, idLista);
			pt.setBoolean(valores++, admin);
			pt.setInt(valores++, 1);
			pt.setString(valores++, Estado.CREADO.name());
			pt.executeUpdate(); 
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
	public MiembroDeLaLista findByID(Object id) throws DaoExcepcion {
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
	public List<MiembroDeLaLista> findAll() throws DaoExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#update(java.lang.Object)
	 */
	@Override
	public int update(MiembroDeLaLista t) throws DaoExcepcion {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.MiembroDeLaListaDao#findUserByIDLista(int, int)
	 */
	@Override
	public List<MiembroDeLaLista> findMiembroByIDLista(int idLista) throws DaoExcepcion {
		try {
			connection.connect();
			consulta =  ManejadorMensajes.consultarMensajePorParametro(FIND_USUARIOS_BY_IDLISTA, className, idLista) ;
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			List<MiembroDeLaLista> list = ConvertObject.convertRSToUsuarioByMiembro(rs);
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
