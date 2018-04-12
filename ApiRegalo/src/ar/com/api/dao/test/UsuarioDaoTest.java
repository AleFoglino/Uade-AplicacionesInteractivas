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

import ar.com.api.dao.UsuarioDao;
import ar.com.api.dao.impl.UsuarioDaoImpl;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Usuario;
import ar.com.api.util.ManejadorMensajes;

/**
 * @author Alejandro Foglino
 *
 */
@RunWith(value = Parameterized.class)
public class UsuarioDaoTest {

	UsuarioDao usuarioDao;

	@Parameter(0)
	public int id;

	@Before
	public void inti() {
		usuarioDao = new UsuarioDaoImpl();
	}

	@Test
	public void insertarUsuario() {

		try {
			Usuario resultado = usuarioDao.save(new Usuario("PruebaInsert", "PruebaInsSert", new Date(),"xxxxdsadas"));
			Assert.assertNotNull(resultado);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void buscarUsuarioPorId() {

		try {
			Usuario resultado = usuarioDao.findByID(1);
			Assert.assertNotNull(resultado);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void buscarTodosLosUsuarios() {

		try {
			List<Usuario> resultado = usuarioDao.findAll();
			Assert.assertNotEquals(0, resultado.size());
			Usuario usuarioOld = resultado.get(0);
			ManejadorMensajes.logDebug(usuarioOld.toString());
			for (int i = 1; i < resultado.size(); i++) {
				ManejadorMensajes.logDebug(resultado.get(i).toString());
				Assert.assertNotEquals(usuarioOld, resultado.get(i));
			}

		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void actualizarUsuario() {

		try {
			Usuario resultado = usuarioDao.findByID(1);
			Assert.assertNotNull(resultado);
			resultado.setFechaAlta(new Date());
			resultado.setMail("actualizado" + resultado.getMail());
			resultado.setNombre("actualizado" + resultado.getNombre());
			int r = usuarioDao.update(resultado);
			Assert.assertNotEquals(0, r);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void deleteUsuario() {

		try {
			int resultado = usuarioDao.deleteByID(id);
			Assert.assertEquals(0, resultado);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	 @Parameters
	 public static Object[] data() {
	        return new Object[] { 2, 4 };
	 }

}
