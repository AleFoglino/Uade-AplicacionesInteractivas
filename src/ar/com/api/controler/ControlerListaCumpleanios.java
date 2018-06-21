/**
 * 
 */
package ar.com.api.controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.api.controler.sistemas.SistemaListaCumpleaños;
import ar.com.api.excepciones.ControlersException;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.ListaDeCumpleanios;
import ar.com.api.pojo.ListaCumpleaniosPojo;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 *
 */
public class ControlerListaCumpleanios {

	private static ControlerListaCumpleanios LISTA;
	private SistemaListaCumpleaños sistemaListaCumpleaños;

	/**
	 * 
	 */
	private ControlerListaCumpleanios() {
		sistemaListaCumpleaños = SistemaListaCumpleaños.getInstanceSistemaListaCumpleaños();
	}

	public static synchronized ControlerListaCumpleanios getInstances() {
		if (LISTA == null)
			LISTA = new ControlerListaCumpleanios();
		return LISTA;
	}

	/**
	 * @param text
	 * @param text2
	 * @param fehaVigencia
	 * @throws ControlersException
	 */
	public void crearLista(String usuario, String nombre, String agasajado, Date fechaVigencia)
			throws ControlersException {
		// TODO Auto-generated method stub
		try {
			sistemaListaCumpleaños.crearListaCumpleaños(usuario, nombre, agasajado, fechaVigencia, null);
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
			throw new ControlersException(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
		}
	}

	public List<ListaCumpleaniosPojo> obtenerTodasLasListas() throws ControlersException {
		// TODO Auto-generated method stub
		List<ListaCumpleaniosPojo> pojos = new ArrayList<ListaCumpleaniosPojo>();
		try {
			for (ListaDeCumpleanios listaCumpleanios : sistemaListaCumpleaños.getListaDeCumples()) {
				pojos.add(ListaCumpleaniosPojo.toPojo(listaCumpleanios));
			}
			return pojos;
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
			throw new ControlersException(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
		}
	}

	public ListaCumpleaniosPojo obtenerListaDeCumpleaniosPorNombre(String nombre) throws ControlersException {
		List<ListaCumpleaniosPojo> lista = obtenerTodasLasListas();
		for (ListaCumpleaniosPojo listaCumpleaniosPojo : lista) {
			if (listaCumpleaniosPojo.getNombreLista().equals(nombre)) {
				return listaCumpleaniosPojo;
			}
		}
		return null;
	}
	
	public void actualizarLista(ListaCumpleaniosPojo listaCumpleaniosPojo) throws ControlersException {
		try {
			sistemaListaCumpleaños.modificarListaCumpleanios(ListaCumpleaniosPojo.toBO(listaCumpleaniosPojo));
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logError(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
			throw new ControlersException(MensajesUtils.MSJ_ERROR_CAPA_CONTROLER_DB, e);
		}
	}

	/**
	 * @param nombreLista
	 */
	public void eliminarLista(String nombreLista) throws ControlersException{
		// TODO Auto-generated method stub
		
	}
	
	public ListaCumpleaniosPojo buscarListaCumpleaniosPorAgasajado(String nombreAgasajado) throws ControlersException {
		ListaDeCumpleanios lista;
		try {
			lista = sistemaListaCumpleaños.buscarListaDeCumpleañosPorAgasajado(nombreAgasajado);
		} catch (DaoExcepcion e) {
			throw new ControlersException("Error al obtener la lista por agasajado");
		}
		if(lista != null) 
			return ListaCumpleaniosPojo.toPojo(lista);
		return null;
	}
	
	public void agregarMiembrosALista(String nombreAgasajado,String usuarioNombre) throws ControlersException {
		try {
			sistemaListaCumpleaños.agregraNuevoMiembroAListaCumpleaños(nombreAgasajado, usuarioNombre);
			
		} catch (DaoExcepcion e) {
			ManejadorMensajes.logDebug(e.getMessage());
			throw new ControlersException("Error al agregar el usuario.", e);
		}
		
		
	}

}
