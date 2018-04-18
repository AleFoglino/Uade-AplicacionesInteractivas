package ar.com.api.dao;

import java.util.List;

import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.util.ConnectionUtils;

public abstract class Dao<T> {

	protected ConnectionUtils connection;
	protected String consulta;
	protected String className;
	
	public abstract T save(T t) throws DaoExcepcion;
	public abstract List<T> findAll()throws DaoExcepcion;
	public abstract T findByID(Object id)throws DaoExcepcion;
	public abstract int deleteByID(Object id)throws DaoExcepcion;
	public abstract int update(T t)throws DaoExcepcion;
	
	
	protected void limpiar() {
		connection.disconnect();
		consulta = "";
	}
	
}
