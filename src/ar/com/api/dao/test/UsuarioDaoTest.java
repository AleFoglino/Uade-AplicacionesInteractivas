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

import ar.com.api.dao.EnviarMailDao;
import ar.com.api.dao.UsuarioDao;
import ar.com.api.dao.impl.EnviarMailDaoImpl;
import ar.com.api.dao.impl.UsuarioDaoImpl;
import ar.com.api.excepciones.DaoExcepcion;
import ar.com.api.negocio.Estado;
import ar.com.api.negocio.Mail;
import ar.com.api.negocio.Usuario;
import ar.com.api.util.ManejadorMensajes;

/**
 * @author Alejandro Foglino
 *
 */
@RunWith(value = Parameterized.class)
public class UsuarioDaoTest {

	UsuarioDao usuarioDao;
	EnviarMailDao enviarMaiDao;

	@Parameter(0)
	public int id;

	@Before
	public void init() {
		usuarioDao = new UsuarioDaoImpl();
		enviarMaiDao = new EnviarMailDaoImpl();
	}

	@Test
	public void insertarUsuario() {

		try {
			Usuario resultado = usuarioDao.save(new Usuario("nombre" + random(100, 35), "usuario" + random(150, 50),
					"mail", new Date(), "password", getEstado(), true));
			Assert.assertNotNull(resultado);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void insertarMail() {

		try {
			List<Usuario> destinatarios = usuarioDao.findAll();
			Mail mail = new Mail("Prueba insert", destinatarios, "lalala","Prueba Insert");
			Mail resultado = enviarMaiDao.save(mail);
			Assert.assertNotEquals(0, resultado.getMail());
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void buscarUsuarioPorId() {

		try {
			Usuario resultado = usuarioDao.findByID(random(10,1));
			Assert.assertNotNull(resultado);
		} catch (DaoExcepcion e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void buscarUsuarioPorNombre() {

		try {
			Usuario resultado = usuarioDao.finadByName("nombreTest1");
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
			Usuario resultado = usuarioDao.findByID(random(4,1));
			Assert.assertNotNull(resultado);
			resultado.setEstado(getEstado());
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
