/**
 * 
 */
package ar.com.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.com.api.convert.ConvertObject;
import ar.com.api.excepciones.ConvertDaoException;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Administrador;
import ar.com.api.negocio.Asistente;
import ar.com.api.negocio.Rol;
import ar.com.api.util.ConnectionUtils;
import ar.com.api.util.ConsultaUtils;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 * @param <T>
 *
 */
public abstract class RolDao<T> extends Dao<Rol>{


	protected static String UPDATE_ROL = "UPDATE {0} SET Nombre = ?, Estado = ?  WHERE Id = {1}";
	protected static String INSERT_ROL = "Insert into {0} (Nombre, Estado,FechaAlta,EsAdminSistema) values (?,?,?,?)";
	
	protected ConnectionUtils connection;
	String consulta;
	String className;
	
	public RolDao(){
		
		connection = ConnectionUtils.getIntancesConnection();
		className = ManejadorMensajes.formatoNombreDeClase(Rol.class.getName().toString().toLowerCase());
		consulta = "";
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#save(java.lang.Object)
	 */
	@Override
	public Rol save(Rol t) throws DaoExcepcion {
		int valores = 1;
		try {
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(INSERT_ROL, className);
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparadaPorID(consulta);
			t.setId(getPrefijo(t));
			pt.setString(valores++, t.getId());
			pt.setString(valores++, t.getNombre());
			pt.setString(valores++, t.getEstado());
			pt.setDate(valores++, new java.sql.Date(t.getFechaAlta().getTime()));
			pt.setBoolean(valores++, esAdminSistema(t));
			pt.executeUpdate();
			return t;
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}

	/**
	 * @param t 
	 * @return
	 */
	private boolean esAdminSistema(Rol t) {
		if (t instanceof Administrador) {
			Administrador a = (Administrador)t;
			return a.isAdminSistema();
		}
		return false;
	}

	

	/**
	 * @param t
	 * @return
	 * @throws ClassNotFoundException 
	 */
	private String getPrefijo(Rol t) throws ClassNotFoundException   {
		if (t instanceof Administrador) {
			return ConsultaUtils.PREFIJO_ADMIN;
		}else if(t instanceof Asistente){
			return ConsultaUtils.PREFIJO_ASISTENTE;
		}
		throw  new ClassNotFoundException("Error al obtener el prefijo del rol.");
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#findAll()
	 */
	@Override
	public List<Rol> findAll() throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorTodos(className);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			return ConvertObject.convertRSToRol(rs);
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
	 * @see ar.com.api.dao.Dao#findByID(int)
	 */
	@Override
	public Rol findByID(Object id) throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorID(className, id);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			List<Rol> list = ConvertObject.convertRSToRol(rs);
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
	 * @see ar.com.api.dao.Dao#deleteByID(int)
	 */
	@Override
	public int deleteByID(Object id) throws DaoExcepcion {
		try {
			connection.connect();
			consulta = ConsultaUtils.consultaPorParametro("roles_usuario_grupo", "Id_Rol_FK", id);
			ManejadorMensajes.logDebug(consulta);
			ResultSet rs = connection.ejecutarConsulta(consulta);
			List<Rol> list = ConvertObject.convertRSToRol(rs);
			if(list.size() >0){
				throw new SQLException("No se puede borrar el rol, debido que esta asigando a un usuarios y un grupo.");
			}
			consulta = ConsultaUtils.eliminarPorID(className);
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparada(consulta);
			pt.setString(1, (String)id);
			return pt.executeUpdate();
		} catch (ClassNotFoundException | SQLException | ConvertDaoException  e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(e.getMessage(), e);
		} finally {
			limpiar();
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.api.dao.Dao#update(java.lang.Object)
	 */
	@Override
	public int update(Rol t) throws DaoExcepcion {
		try {
			int valor = 1;
			connection.connect();
			consulta = ManejadorMensajes.consultarMensajePorParametro(UPDATE_ROL, className,t.getId());
			ManejadorMensajes.logDebug(consulta);
			PreparedStatement pt = connection.consultaPreparada(consulta);
			pt.setString(valor++, t.getNombre());
			pt.setString(valor++,t.getEstado());
			return pt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
			throw new DaoExcepcion(MensajesUtils.MSJ_ERROR_CONECTARSE_DB, e);
		} finally {
			limpiar();
		}
	}
	
	
}
