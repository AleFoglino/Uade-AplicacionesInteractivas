package ar.com.api.dao;

import java.util.List;

import ar.com.api.excepciones.DaoExcepcion;

public interface Dao<T> {

	public T save(T t) throws DaoExcepcion;
	public List<T> findAll()throws DaoExcepcion;
	public T findByID(int id)throws DaoExcepcion;
	public int deleteByID(int id)throws DaoExcepcion;
	public int update(T t)throws DaoExcepcion;
	
	
}
