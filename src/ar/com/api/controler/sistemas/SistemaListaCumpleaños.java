/**
 * 
 */
package ar.com.api.controler.sistemas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ar.com.api.dao.ListaDeCumpleaniosDao;
import ar.com.api.dao.MiembroDeLaListaDao;
import ar.com.api.dao.impl.ListaDeCumplea�osDaoImpl;
import ar.com.api.dao.impl.MiembroDeLaListaDaoImpl;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Estado;
import ar.com.api.negocio.ListaDeCumpleanios;
import ar.com.api.negocio.MiembroDeLaLista;
import ar.com.api.negocio.Usuario;
import ar.com.api.util.ManejadorMensajes;
import ar.com.api.util.MensajesUtils;

/**
 * @author Alejandro Foglino
 *
 */
public class SistemaListaCumplea�os {

	private static SistemaListaCumplea�os SISTEMA_DE_LISTA_CUMPLEA�OS = null;

	private List<ListaDeCumpleanios> listaDeCumpleanios;

	private SistemaUsuarios sistemaUsuarios;
	
	private SistemaDeMail sistemaDeMail;
	
	private ListaDeCumpleaniosDao listaDeCumpleaniosDao;
	
	private MiembroDeLaListaDao miembroDeLaListaDao;
	
	private SistemaListaCumplea�os() {
		super();
		listaDeCumpleanios = new ArrayList<ListaDeCumpleanios>();
		setSistemaUsuarios(SistemaUsuarios.getInstanceUsuario());
		setSistemaDeMail(SistemaDeMail.getInstanceSistemaDeMail());
		listaDeCumpleaniosDao = new ListaDeCumplea�osDaoImpl();
		miembroDeLaListaDao = new MiembroDeLaListaDaoImpl();
	}

	public ListaDeCumpleanios buscarListaDeCumplea�osPorAgasajado(String agasajado) throws DaoExcepcion {
		for (int i = 0; i < getListaDeCumples().size(); i++) {
			ManejadorMensajes.logDebug("Buscar agasajado: " + agasajado + " en la lista: " + getListaDeCumples().get(i).getNombre());
			if(getListaDeCumples().get(i).getAgasajado().equals(agasajado)) {				
				ManejadorMensajes.logDebug("Encontrado agasajado: " + agasajado + " en la lista:  " +  getListaDeCumples().get(i).getNombre());
				if(getListaDeCumples().get(i).getListaDelCumple() == null) {
					ManejadorMensajes.logDebug("Cargar mienbro de la lista del agasajado: " +  agasajado);
					getListaDeCumples().get(i).setListaDelCumple(miembroDeLaListaDao.findMiembroByIDLista(getListaDeCumples().get(i).getId()));
				}
			}
			
			
		}
		for (ListaDeCumpleanios listaDeCumpleanios : getListaDeCumples()) {
			if (listaDeCumpleanios != null) {
				if (listaDeCumpleanios.getAgasajado().equals(agasajado)) {
					
					return listaDeCumpleanios;
				}
			}
		}
		
		return null;
	}

	public MiembroDeLaLista buscarMiembroDeLaListaPorUsuario(String agasajado, String usuario) throws DaoExcepcion {
		ListaDeCumpleanios listaDeCumpleanios = buscarListaDeCumplea�osPorAgasajado(agasajado);
		if (listaDeCumpleanios != null) {
			return listaDeCumpleanios.buscarMienbroPorUsuario(usuario);
		}
		return null;
	}

	public float calculaMontoRacudadoPorAgasajado(String agasajado) throws DaoExcepcion {
		float montoTotal = 0;
		ListaDeCumpleanios listaDeCumpleanios = buscarListaDeCumplea�osPorAgasajado(agasajado);
		if (listaDeCumpleanios != null) {
			return listaDeCumpleanios.getMontoTotaRecuado();
		}
		return montoTotal;
	}

	public void darBajaListaCumplea�os(String agasajado) throws DaoExcepcion {
		ListaDeCumpleanios listaCumple = buscarListaDeCumplea�osPorAgasajado(agasajado);
		if(listaCumple != null) {			
			listaDeCumpleaniosDao.deleteByID(listaCumple.getId());
			getListaDeCumples().remove(listaCumple);
		}
	}

	public void darBajaMiembroListaCumplea�os(String agasajado, String usuario) throws DaoExcepcion {
		ListaDeCumpleanios listaDeCumpleanios = buscarListaDeCumplea�osPorAgasajado(agasajado);
		if (listaDeCumpleanios != null) {
			MiembroDeLaLista miembroDeLaLista = listaDeCumpleanios.buscarMienbroPorUsuario(usuario);
			if (miembroDeLaLista != null) {
				miembroDeLaLista.getUsuario().setEstado(Estado.CANCELADO);
			}
		}
	}

	public void crearListaCumplea�os(String usuarioCreador, String nombre, String agasajado, Date fechaVigencia, List<String> usuarios) throws DaoExcepcion {
		Usuario usuario = sistemaUsuarios.buscarUsuario(usuarioCreador);
		if(usuario != null) {
			ListaDeCumpleanios lista = listaDeCumpleaniosDao.save(new ListaDeCumpleanios(nombre, agasajado, fechaVigencia));
			getListaDeCumples().add(lista);
			miembroDeLaListaDao.guardaMiembroDeLaLista(usuario.getId(),lista.getId(), true);
			//agregraNuevoMiembroAListaCumplea�os(agasajado, usuarioCreador,true);			
		}
	}

	public void mostrarMontoRecuadoPorMiembroDeLaLista(String agasajado) throws DaoExcepcion {
		ListaDeCumpleanios listaDeCumpleanios = buscarListaDeCumplea�osPorAgasajado(agasajado);
		if (listaDeCumpleanios != null) {
			listaDeCumpleanios.mostrarMontoDeLosMiembros();
		}
	}
	
	public void modificarListaCumpleanios(ListaDeCumpleanios listaDeCumpleanios) throws DaoExcepcion {
		listaDeCumpleaniosDao.update(listaDeCumpleanios);
	}
	

	public void agregraNuevoMiembroAListaCumplea�os(String agasajado, String usuario) throws DaoExcepcion {
		ListaDeCumpleanios listaDeCumpleanios = buscarListaDeCumplea�osPorAgasajado(agasajado);
		if (listaDeCumpleanios != null) {
			Usuario user = sistemaUsuarios.buscarUsuario(usuario);
			if(user != null) {				
				listaDeCumpleanios.agregarMiembroAListaDeCumpleanios(miembroDeLaListaDao.guardaMiembroDeLaLista(user.getId(), listaDeCumpleanios.getId(), false));
				sistemaDeMail.enviarMailBienvenida(user, agasajado, listaDeCumpleanios.getNombre());
			}else {
				throw new DaoExcepcion(ManejadorMensajes.consultarMensajePorParametro(MensajesUtils.MSJ_WARNING_OBJETO_NO_ENCONTRADO, user));
			}
		}else {
			throw new DaoExcepcion(ManejadorMensajes.consultarMensajePorParametro(MensajesUtils.MSJ_WARNING_OBJETO_NO_ENCONTRADO, agasajado));
		}
	}

	public void notificarVencimientoMiembroLista() throws DaoExcepcion {
		for (ListaDeCumpleanios listaDeCumpleanios : getListaDeCumples()) {
			if(listaDeCumpleanios != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(listaDeCumpleanios.getFechaVigencia());
			}
			
		}
	}
	
	
	public static synchronized SistemaListaCumplea�os getInstanceSistemaListaCumplea�os() {
		if (SISTEMA_DE_LISTA_CUMPLEA�OS == null) {
			SISTEMA_DE_LISTA_CUMPLEA�OS = new SistemaListaCumplea�os();
		}
		return SISTEMA_DE_LISTA_CUMPLEA�OS;
	}

	public List<ListaDeCumpleanios> getListaDeCumples() throws DaoExcepcion {
		if(listaDeCumpleanios.size() == 0 ) {
			listaDeCumpleanios = listaDeCumpleaniosDao.findAll();
		}
		return listaDeCumpleanios;
	}

	public void setListaDeCumples(List<ListaDeCumpleanios> listaDeCumpleanios) {
		this.listaDeCumpleanios = listaDeCumpleanios;
	}

	public SistemaUsuarios getSistemaUsuarios() {
		return sistemaUsuarios;
	}

	public void setSistemaUsuarios(SistemaUsuarios sistemaUsuarios) {
		this.sistemaUsuarios = sistemaUsuarios;
	}

	public SistemaDeMail getSistemaDeMail() {
		return sistemaDeMail;
	}

	public void setSistemaDeMail(SistemaDeMail sistemaDeMail) {
		this.sistemaDeMail = sistemaDeMail;
	}

}
