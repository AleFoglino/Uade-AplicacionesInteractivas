/**
 * 
 */
package ar.com.api.util;

/**
 * @author Alejandro Foglino
 *
 */
public class ConsultaUtils {

	static String FIND_ALL = "SELECT * FROM {0} WHERE estado not in ('''"+"CANCELADO"+"''','''"+"ELIMINADO"+"''')" ;
	static String FIND_BY_ID = "SELECT * FROM {0} WHERE ID = {1} and estado not in ('''"+"CANCELADO"+"''','''"+"ELIMINADO"+"''')";
	static String DELETE_BY_ID = "DELETE FROM {0} WHERE ID = ?";
	static String FIND_BY_KEY = "SELECT * FROM {0} WHERE {1} = {2}";
	static String FIND_BY_STRING = "SELECT * FROM {0} WHERE {1} = '''"+ " {2} "+"'''";
	public static String PREFIX_STATE = "estado not in ('''"+"CANCELADO"+"''','''"+"ELIMINADO"+"''')";  
	public static String PREFIJO_ADMIN = "A";
	public static String PREFIJO_ASISTENTE = "S";

	public static String consultaPorTodos(String nombreTabla) {
		return ManejadorMensajes.consultarMensajePorParametro(FIND_ALL, nombreTabla);
	}

	public static String consultaPorID(String nombreTabla,Object id) {
		return ManejadorMensajes.consultarMensajePorParametro(FIND_BY_ID, nombreTabla,id);
	}
	
	public static String consultaPorParametroInt(String nombreTabla,String key,Object id) {
		return ManejadorMensajes.consultarMensajePorParametro(FIND_BY_KEY, nombreTabla,key,id);
	}
	
	public static String consultaPorParametroString(String nombreTabla,String key,String id) {
		return ManejadorMensajes.consultarMensajePorParametro(FIND_BY_STRING, nombreTabla,key,id);
	}
	
	public static String eliminarPorID(String nombreTabla) {
		return ManejadorMensajes.consultarMensajePorParametro(DELETE_BY_ID, nombreTabla);
	}
	
	public static String consultaByParametros(String consulta,Object ...key) {
		return ManejadorMensajes.consultarMensajePorParametro(DELETE_BY_ID, key);
	}
}
