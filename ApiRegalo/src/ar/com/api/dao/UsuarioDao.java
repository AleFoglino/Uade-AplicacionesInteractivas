package ar.com.api.dao;

import ar.com.api.negocio.Usuario;

public interface UsuarioDao extends Dao<Usuario> {
	
	static String UPDATE_USUARIO = "UPDATE {0} SET Nombre = ?, Mail = ?, FechaAlta = ?, Password= ? WHERE Id = {1}";
	static String INSERT_USUARIO = "Insert into {0} (Nombre, Mail,FechaAlta,Password) values (?,?,?,?)";


}
