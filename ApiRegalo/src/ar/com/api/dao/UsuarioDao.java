package ar.com.api.dao;

import ar.com.api.negocio.Usuario;

public abstract class UsuarioDao extends Dao<Usuario> {
	
	protected static String UPDATE_USUARIO = "UPDATE {0} SET Nombre = ?, Mail = ?, FechaAlta = ?, Password= ? WHERE Id = {1}";
	protected static String INSERT_USUARIO = "Insert into {0} (Nombre, Mail,Password) values (?,?,?)";


}
