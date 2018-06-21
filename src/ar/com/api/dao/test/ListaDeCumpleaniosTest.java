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

import ar.com.api.dao.ListaDeCumpleaniosDao;
import ar.com.api.dao.MiembroDeLaListaDao;
import ar.com.api.dao.impl.ListaDeCumpleañosDaoImpl;
import ar.com.api.dao.impl.MiembroDeLaListaDaoImpl;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Estado;
import ar.com.api.negocio.ListaDeCumpleanios;
import ar.com.api.negocio.MiembroDeLaLista;
import ar.com.api.util.ManejadorMensajes;

/**
 * @author Alejandro Foglino
 *
 */
@RunWith(value = Parameterized.class)
public class ListaDeCumpleaniosTest {

	ListaDeCumpleaniosDao listaDeCumpleaniosDao = null;
	MiembroDeLaListaDao miembroDeLaListaDao = null;

	@Parameter(0)
	public int id;
	/**
	 * 
	 */
	public ListaDeCumpleaniosTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void init() {
		listaDeCumpleaniosDao  = new ListaDeCumpleañosDaoImpl();
		miembroDeLaListaDao = new MiembroDeLaListaDaoImpl();
	}
	

	@Test
	public void insertarListaDeCumpleanios() {

		try {
			ListaDeCumpleanios resultado = new ListaDeCumpleanios("nombre"+random(100, 50), "agasajado"+random(100, 50), new Date());
			listaDeCumpleaniosDao.save(resultado);
			Assert.assertNotEquals(0, resultado.getId());
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void buscarListaPorId() {

		try {
			ListaDeCumpleanios resultado = listaDeCumpleaniosDao.findByID(random(5,1));
			Assert.assertNotNull(resultado);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void buscarListaPorAgasajado() {

		try {
			ListaDeCumpleanios resultado = listaDeCumpleaniosDao.findByAgasajado("JUAN");
			Assert.assertNotNull(resultado);
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

	@Test
	public void buscarTodasLasLitas() {

		try {
			List<ListaDeCumpleanios> resultado = listaDeCumpleaniosDao.findAll();
			Assert.assertNotEquals(0, resultado.size());
			ListaDeCumpleanios listaOld = resultado.get(0);
			ManejadorMensajes.logDebug(listaOld.toString());
			for (int i = 1; i < resultado.size(); i++) {
				ManejadorMensajes.logDebug(resultado.get(i).toString());
				Assert.assertNotEquals(listaOld, resultado.get(i));
			}

		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void actualizarLista() {

		try {
			ListaDeCumpleanios resultado = listaDeCumpleaniosDao.findByID(random(4,1));
			Assert.assertNotNull(resultado);
			resultado.setEstado(getEstado());
			resultado.setAgasajado("actualizado"+resultado.getAgasajado());
			resultado.setNombre("actualizado"+resultado.getNombre());
			int r = listaDeCumpleaniosDao.update(resultado);
			Assert.assertNotEquals(0, r);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void buscarMiembroDeUnaLista() {
		try {
			List<MiembroDeLaLista> lista = miembroDeLaListaDao.findMiembroByIDLista(5);
			Assert.assertNotEquals(0, lista.size());
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void deleteLista() {

		try {
			int resultado = listaDeCumpleaniosDao.deleteByID(id);
			Assert.assertEquals(0, resultado);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
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
