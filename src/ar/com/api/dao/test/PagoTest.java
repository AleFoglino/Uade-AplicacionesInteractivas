/**
 * 
 */
package ar.com.api.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import ar.com.api.dao.PagoDao;
import ar.com.api.dao.impl.PagoDaoImpl;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Estado;
import ar.com.api.negocio.EstadosDelPago;
import ar.com.api.negocio.MedioPago;
import ar.com.api.negocio.Pago;
import ar.com.api.util.ManejadorMensajes;

/**
 * @author Alejandro Foglino
 *
 */
@RunWith(value = Parameterized.class)
public class PagoTest {

	PagoDao pagoDao;
	/**
	 * 
	 */
	public PagoTest() {
		// TODO Auto-generated constructor stub
	}

	@Parameter(0)
	public int id;

	@Before
	public void init() {
		pagoDao = new PagoDaoImpl();
	}

	@Test
	public void insertarPago() {

		try {
			Pago  pago = pagoDao.save(new Pago(new MedioPago(1232, "VISA"))); 
			Assert.assertNotNull(pago);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void buscarPagoPorId() {

		try {
			Pago resultado = pagoDao.findByID(random(10,1));
			Assert.assertNotNull(resultado);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}
	
	

	@Test
	public void buscarTodosLosPagos() {

		try {
			List<Pago> resultado = pagoDao.findAll();
			Assert.assertNotEquals(0, resultado.size());
			Pago pagoOld = resultado.get(0);
			ManejadorMensajes.logDebug(pagoOld.toString());
			for (int i = 1; i < resultado.size(); i++) {
				ManejadorMensajes.logDebug(resultado.get(i).toString());
				Assert.assertNotEquals(pagoOld, resultado.get(i));
			}

		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void actualizarPago() {

		try {
			Pago resultado = pagoDao.findByID(random(4,1));
			Assert.assertNotNull(resultado);
			resultado.setFechaPago(new Date());;
			resultado.setMontoPagado(10);
			resultado.setEstado(EstadosDelPago.FINALIZADO);
			int r = pagoDao.update(resultado);
			Assert.assertNotEquals(0, r);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void deletePago() {

		try {
			int resultado = pagoDao.deleteByID(id);
			Assert.assertEquals(0, resultado);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Parameters
	public static Object[] data() {
		return new Object[] { random(10, 1), random(10, 1) };
	}

	private static int random(int max, int min) {
		int id = (int) (min + (Math.random() * (max - min)));
		return id;
	}

	private Estado getEstado() {
		int id = random(4, 1);
		Estado estado = null;
		switch (id) {
		case 1:
			estado = Estado.CREADO;
			break;
		case 2:
			estado = Estado.FINALIZADO;
			break;
		case 3:
			estado = Estado.CANCELADO;
			break;
		default:
			estado = Estado.PENDIENTE;
			break;
		}
		return estado;

	}


}
