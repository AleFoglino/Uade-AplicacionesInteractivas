/**
 * 
 */
package ar.com.api.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.com.api.convert.ConvertObject;
import ar.com.api.dao.GrupoDao;
import ar.com.api.excepciones.ConvertDaoException;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Grupo;
import ar.com.api.negocio.Usuario;
import ar.com.api.util.ConnectionUtils;
import ar.com.api.util.ConsultaUtils;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 *
 */
public class GrupoDaoImpl extends GrupoDao {

	/**
	 * 
	 */
	public GrupoDaoImpl() {
		connection = ConnectionUtils.getIntancesConnection();
		className = ManejadorMensajes.formatoNombreDeClase(Usuario.class.getName().toString().toLowerCase());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.api.dao.Dao#save(java.lang.Object)
	 */
	@Override
	public Grupo save(Grupo t) throws DaoExcepcion {
		try {
			int valores = 1;
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(INSERT_GRUPO, className);
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparadaPorID(consulta);
			pt.setString(valores++, t.getNombre());
			pt.setDate(valores++, new java.sql.Date(t.getFechaAlta().getTime()));
			pt.setString(valores++, t.getAgasajado());
			pt.setFloat(valores++, t.getMontoTotalRecudado());
			pt.executeUpdate();
			ResultSet rs = pt.getGeneratedKeys();
			while (rs.next()) {
				t.setId(rs.getInt(1));
				ManejadorMensajes.logDebug("Id Grupo generado: " + t.getId());
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
	 * @see ar.com.api.dao.Dao#findAll()
	 */
	@Override
	public List<Grupo> findAll() throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorTodos(className);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			return ConvertObject.convertRSToGrupo(rs);
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
	 * @see ar.com.api.dao.Dao#findByID(java.lang.Object)
	 */
	@Override
	public Grupo findByID(Object id) throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorID(className, id);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			List<Grupo> list = ConvertObject.convertRSToGrupo(rs);
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
	 * @see ar.com.api.dao.Dao#deleteByID(int)
	 */
	@Override
	public int deleteByID(Object id) throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorParametro("roles_usuario_grupo", "Id_Grupo_FK", id);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			List<Grupo> list = ConvertObject.convertRSToGrupo(rs);
			if (list.size() > 0) {
				throw new SQLException("No se puede borrar el Grupo, debido que esta asigando a un usuarios y un rol.");
			}
			consulta = ConsultaUtils.eliminarPorID(className);
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparada(consulta);
			pt.setString(1, (String) id);
			return pt.executeUpdate();
		} catch (ClassNotFoundException | SQLException | ConvertDaoException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(e.getMessage(), e);
		} finally {
			limpiar();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.api.dao.Dao#update(java.lang.Object)
	 */
	@Override
	public int update(Grupo t) throws DaoExcepcion {
		try {
			int valor = 1;
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(UPDATE_GRUPO, className, t.getId());
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparada(consulta);
			pt.setString(valor++, t.getNombre());
			pt.setString(valor++, t.getAgasajado());
			pt.setFloat(valor++, t.getMontoTotalRecudado());
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
	 * @see ar.com.api.dao.GrupoDao#saveParticipante(int, java.lang.String, int)
	 */
	@Override
	public int saveParticipante(int idUsuario, String idRol, int idGrupo) throws DaoExcepcion {
		try {
			int valores = 1;
			connection.connect();
			consulta = "INSERT INTO roles_usuario_grupo  VALUES (?,?,?,?)";
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparadaPorID(consulta);
			pt.setInt(valores++, idUsuario);
			pt.setInt(valores++, idGrupo);
			pt.setFloat(valores++, 0);
			pt.setString(valores++, idRol);
			return pt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.GrupoDao#payParticipante(int, java.lang.String, int, float)
	 */
	@Override
	public int payParticipante(int idUsuario, String idRol, int idGrupo, float monto) throws DaoExcepcion {
		try {
			int valor = 1;
			connection.connect();
			consulta = "UPDATE `roles_usuario_grupo` SET MontoDepositado = ?  WHERE Id_Usuario_FK={0} and Id_Grupo_FK={1} and Id_Rol_FK= {2}";
			consulta = ConsultaUtils.consultaByParametros(consulta, idUsuario, idGrupo,idRol );
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparada(consulta);
			pt.setFloat(valor++, monto);
			return pt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

}
