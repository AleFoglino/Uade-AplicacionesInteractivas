/**
 * 
 */
package ar.com.api.negocio;

import java.util.Date;
import java.util.List;

/**
 * @author Alejandro Foglino
 *
 */
public class ListaDeCumpleanios {

	private List<MiembroDeLaLista> listaDelCumple;
	private int id;
	private String nombre;
	private String agasajado;
	private Date fechaAlta;
	private Date fechaVigencia;
	private Estado estado;
	private float montoRecuado;

	public ListaDeCumpleanios(String nombre, String agasajado, Date fechaVigencia) {
		super();
		this.nombre = nombre;
		this.agasajado = agasajado;
		this.fechaAlta = new Date();
		this.fechaVigencia = fechaVigencia;
		this.montoRecuado = 0;
		this.setEstado(Estado.CREADO);
	}
	
	

	public ListaDeCumpleanios(int id, String nombre, String agasajado, Date fechaAlta, Date fechaVigencia,
			Estado estado, float montoRecuado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.agasajado = agasajado;
		this.fechaAlta = fechaAlta;
		this.fechaVigencia = fechaVigencia;
		this.estado = estado;
		this.montoRecuado = montoRecuado;
	}



	/**
	 * @param idLista
	 * @param nombreLista
	 * @param agasajado2
	 * @param fechaVigencia2
	 * @param montoTotalRecaudado
	 */
	public ListaDeCumpleanios(int idLista, String nombreLista, String agasajado2, Date fechaVigencia2,
			float montoTotalRecaudado) {
		this.id = idLista;
		this.nombre = nombreLista;
		this.agasajado = agasajado2;
		this.fechaVigencia = fechaVigencia2;
		this.montoRecuado = montoTotalRecaudado;
	}



	public List<MiembroDeLaLista> getListaDelCumple() {
		return listaDelCumple;
	}

	public void setListaDelCumple(List<MiembroDeLaLista> listaDelCumple) {
		this.listaDelCumple = listaDelCumple;
	}

	public void agregarMiembroAListaDeCumpleanios(MiembroDeLaLista miembroDelCumple) {
		
		if(miembroDelCumple != null) {
			this.listaDelCumple.add(miembroDelCumple);
			miembroDelCumple.actualizar(Estado.CREADO);
		}
			
	}

	public void eliminarMiembroAListaDeCumpleanios(Observer miembroDelCumple) {
		MiembroDeLaLista miembro = getMiembroToObserver(miembroDelCumple);
		if(miembro != null) {
			miembro.actualizar(Estado.CANCELADO);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAgasajado() {
		return agasajado;
	}

	public void setAgasajado(String agasajado) {
		this.agasajado = agasajado;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void notificarPagosDelGrupo(String usuario, EstadosDelPago estadosDelPago) {
		MiembroDeLaLista miembroDeLaLista = buscarMienbroPorUsuario(usuario);
		miembroDeLaLista.actualizarPago(estadosDelPago);
	}

	public MiembroDeLaLista buscarMienbroPorUsuario(String usuario) {
		MiembroDeLaLista miembroDeLaLista = null;
		for (Observer observer : listaDelCumple) {
			miembroDeLaLista = getMiembroToObserver(observer);
			if (miembroDeLaLista.getUsuario() != null) {
				if (miembroDeLaLista.getUsuario().getNombre().equals(usuario)) {
					return miembroDeLaLista;
				}
			}
		}
		return null;
	}


	public float getMontoTotaRecuado() {
		
//		MiembroDeLaLista miembroDeLaLista = null;
//		for (Observer observer : listaDelCumple) {
//			miembroDeLaLista = getMiembroToObserver(observer);
//			if (miembroDeLaLista != null) {
//				if (miembroDeLaLista.getPago() != null) {
//					montoRecuado = montoRecuado + miembroDeLaLista.getPago().getMontoPagado();
//				}
//			}
//		}
		return montoRecuado;
	}

	

	public void mostrarMontoDeLosMiembros() {
		MiembroDeLaLista miembroDeLaLista = null;
		for (Observer observer : listaDelCumple) {
			miembroDeLaLista = getMiembroToObserver(observer);
			if(miembroDeLaLista != null) {				
				System.out.println("Nombre Usuario: " + miembroDeLaLista.getUsuario().getNombre() + "\nMonto Pagado: "
						+ miembroDeLaLista.getPago().getMontoPagado());
			}
		}
	}

	private MiembroDeLaLista getMiembroToObserver(Observer observer) {
		if (observer instanceof MiembroDeLaLista) {
			MiembroDeLaLista miembroDeLaLista= (MiembroDeLaLista) observer;
			return miembroDeLaLista;
		}
		return null;
	}

	public boolean esAdministradorDeLaLista(String usuario) {
		MiembroDeLaLista miembroDeLaLista = buscarMienbroPorUsuario(usuario);
		if(miembroDeLaLista != null) {
			return miembroDeLaLista.isSoyAdministrador();
		}
		return false;
	}
	
	public float getMontoRecuado() {
		return montoRecuado;
	}
}
