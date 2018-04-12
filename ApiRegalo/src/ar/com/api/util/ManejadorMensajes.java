package ar.com.api.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
/**
 * s
 * @author Alejandro Foglino
 *
 */
public class ManejadorMensajes {

	
	public static void logError(String msj, Exception ex){
		System.err.println(msj + "\nExcepción: "+ convertExceptionToString(ex)); 
	}
	
	public static void logDebug(String msj){
		System.out.println(msj); 
	}
	

	public static String consultarMensajePorParametro(String mensajes,Object ...objects){
		return MessageFormat.format(mensajes, objects);
	}
	public static String formatoNombreDeClase(String nombreDeClase){
		return nombreDeClase.substring(nombreDeClase.lastIndexOf(".")+1,nombreDeClase.length());
	}

	/**
	 * Metodo que convierte la excepcion en texto
	 * @param ex excepción de entrada.
	 * @return mensaje de texto del expeción de entrada.
	 */
	private static String convertExceptionToString(Exception ex){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String sStackTrace = sw.toString();
		return sStackTrace;
		
	}
	
}
