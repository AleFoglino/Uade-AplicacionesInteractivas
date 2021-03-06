/**
 * 
 */
package ar.com.api.controler.sistemas;

import java.util.Date;
import java.util.List;

import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.EstadosDelPago;
import ar.com.api.negocio.FacadeEntidadExternaDePago;
import ar.com.api.negocio.ListaDeCumpleanios;
import ar.com.api.negocio.MedioPago;
import ar.com.api.negocio.MiembroDeLaLista;
import ar.com.api.negocio.TransaccionPago;

/**
 * @author Alejandro Foglino
 *
 */
public class SistemaPago {

	private List<FacadeEntidadExternaDePago> externaDePagos;

	private static SistemaPago SISTEMA_DE_PAGO = null;

	private SistemaListaCumpleaņos sistemaListaCumpleaņos = null;

	private SistemaPago() {
		setSistemaListaCumpleaņos(SistemaListaCumpleaņos.getInstanceSistemaListaCumpleaņos());
	}

	public static synchronized SistemaPago getInstanceSistemaDePago() {
		if (SISTEMA_DE_PAGO == null) {
			SISTEMA_DE_PAGO = new SistemaPago();
		}
		return SISTEMA_DE_PAGO;
	}

	public void efectuarPagoLista(String agasajado, String usuario, float montoPago, MedioPago medioPago)
			throws DaoExcepcion {

			MiembroDeLaLista miembroDeLaLista = sistemaListaCumpleaņos.buscarMiembroDeLaListaPorUsuario(agasajado,
					usuario);
			if(miembroDeLaLista == null) {
				throw new  DaoExcepcion("No se encontro el usuario " +usuario+" del la lista de agasajado: "+ agasajado);
			}
			if (miembroDeLaLista.getUsuario().getNombre().equals(usuario)) {
				if (medioPago == null) {
					miembroDeLaLista.getPago().setEstado(EstadosDelPago.ERROR);
				} else {
					if (medioPago.getNumeroTarjeta() == 0) {
						miembroDeLaLista.getPago().setEstado(EstadosDelPago.ERROR);
					}
				}
				FacadeEntidadExternaDePago entidadPago = buscarEntidadExternaPorMedioPago(medioPago);
				if (entidadPago != null) {
					entidadPago.efectuarPago(agasajado,
							miembroDeLaLista.getUsuario().getNombre(), montoPago, medioPago);
					miembroDeLaLista.getPago().setEstado(EstadosDelPago.PENDIENTE);
					miembroDeLaLista.getPago().setMontoPagado(montoPago);
					miembroDeLaLista.getPago().setFechaAltaPago(new Date());
				} else {
					miembroDeLaLista.getPago().setEstado(EstadosDelPago.CANCELADO);
				}

			}
	}

	public FacadeEntidadExternaDePago buscarEntidadExternaPorMedioPago(MedioPago medioPago) {
		for (FacadeEntidadExternaDePago facadeEntidadExternaDePago : externaDePagos) {
			if (facadeEntidadExternaDePago.aceptaMedioPago(medioPago)) {
				return facadeEntidadExternaDePago;
			}
		}
		return null;
	}

	public List<FacadeEntidadExternaDePago> getExternaDePagos() {
		return externaDePagos;
	}

	public void setExternaDePagos(List<FacadeEntidadExternaDePago> externaDePagos) {
		this.externaDePagos = externaDePagos;
	}

	public void agregarNuevaListaCumpleAņos() {

	}

	public void notificarConfirmacionDePago() throws DaoExcepcion {
		FacadeEntidadExternaDePago externaDePago = null;
		List<TransaccionPago> transaccionesPago = externaDePago.confirmarPago();
		for (TransaccionPago transaccionPago : transaccionesPago) {
			ListaDeCumpleanios listaDeCumpleanios = sistemaListaCumpleaņos
					.buscarListaDeCumpleaņosPorAgasajado(transaccionPago.getAgasajado());
			if (listaDeCumpleanios != null) {
				listaDeCumpleanios.notificarPagosDelGrupo(transaccionPago.getUsuario(),
						transaccionPago.getEstadosDelPago());
			}
		}
	}

	public SistemaListaCumpleaņos getSistemaListaCumpleaņos() {
		return sistemaListaCumpleaņos;
	}

	public void setSistemaListaCumpleaņos(SistemaListaCumpleaņos sistemaListaCumpleaņos) {
		this.sistemaListaCumpleaņos = sistemaListaCumpleaņos;
	}
}
