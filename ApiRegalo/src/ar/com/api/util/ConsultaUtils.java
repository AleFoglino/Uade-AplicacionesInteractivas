/**
 * 
 */
package ar.com.api.util;

/**
 * @author Alejandro Foglino
 *
 */
public class ConsultaUtils {

	static String FIND_ALL = "SELECT * FROM {0}";
	static String FIND_BY_ID = "SELECT * FROM {0} WHERE ID = {1}";
	static String DELETE_BY_ID = "DELETE FROM {0} WHERE ID = ?";
	static String FIND_BY_KEY = "SELECT * FROM {0} WHERE {1} = {2}";
	public static String PREFIJO_ADMIN = "A";
	public static String PREFIJO_ASISTENTE = "S";

	public static String consultaPorTodos(String nombreTabla) {
		return ManejadorMensajes.consultarMensajePorParametro(FIND_ALL, nombreTabla);
	}

	public static String consultaPorID(String nombreTabla,int id) {
		return ManejadorMensajes.consultarMensajePorParametro(FIND_BY_ID, nombreTabla,id);
	}
	
	public static String consultaPorParametro(String nombreTabla,String key,int id) {
		return ManejadorMensajes.consultarMensajePorParametro(FIND_BY_KEY, nombreTabla,key,id);
	}
	
	public static String eliminarPorID(String nombreTabla) {
		return ManejadorMensajes.consultarMensajePorParametro(DELETE_BY_ID, nombreTabla);
	}
}
