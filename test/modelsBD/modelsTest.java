/**
 * 
 */
package modelsBD;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import geral.AbstractTest;
import models.Local;
import models.Usuario;
import models.Viagem;
import models.ViagemAberta;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Test;


/**
 * @author Wesley
 *
 */
public class modelsTest extends AbstractTest{
	
	private GenericDAO dao = new GenericDAOImpl();
	
	@Test
	public void deveCriarUsuario() {
		Usuario usuario = new Usuario("Wesley", "welsey@gmail.com", "12345");
		dao.persist(usuario);
		dao.flush();
		assertTrue(dao.findAllByClassName("Usuario").size() > 0);
		
		usuario = new Usuario("Erick", "Erick@gmail.com", "12345");
		dao.persist(usuario);
		dao.flush();
		assertTrue(dao.findAllByClassName("Usuario").size() > 1);
	}
	
	@Test
	public void deveCriarLocal(){
		Local local = new Local("SESI");
		dao.persist(local);
		assertTrue(dao.findAllByClassName("Local").size() > 0);
		local = new Local("UEPB");
	    dao.persist(local);
		assertTrue(dao.findAllByClassName("Local").size() > 1);
	}
	
	@Test 
	public void deveCriarEPersistirAViagem(){
		Local local = new Local("Joao Pessoa");
		dao.persist(local);
		dao.flush();
		Date data = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2014, 9, 26);
		data = cal.getTime();
		Viagem viagem = new Viagem();
		try {
			viagem.setLocal(local);
		} catch (Exception e) {
			fail();
		}
		try {
			viagem.setDescricao("Viagem para Joao Pessoa");
		} catch (Exception e) {
			fail();
		}
		try {
			viagem.setData(data);
		} catch (Exception e) {
			fail();
		}
		Usuario user = new Usuario("Gustavo", "Gustavo@gmail.com", "12345");
		dao.persist(user);
		dao.flush();
		viagem.setAdminUsuario(user.getEmail());
		viagem.addUsuario(user);
		ViagemAberta v = new ViagemAberta();
		viagem.setEstrategia(v);
		dao.persist(v);
		dao.flush();
		assertTrue(dao.findAllByClassName("Viagem").size() == 0);
		viagem.setFoto("abc");
		dao.persist(viagem);
		dao.flush();
		assertTrue(dao.findAllByClassName("Viagem").size() == 1);
		
		
		Viagem viagem2 = new Viagem();
		try {
			viagem2.setLocal(local);
		} catch (Exception e) {
			fail();
		}
		try {
			viagem2.setDescricao("Viagem para Recife");
		} catch (Exception e) {
			fail();
		}
		try {
			viagem2.setData(data);
		} catch (Exception e) {
			fail();
		}
		viagem2.setFoto("abc");
		viagem2.setAdminUsuario(user.getEmail());
		viagem2.setEstrategia(v);
		viagem2.addUsuario(user);
		dao.persist(viagem2);
		dao.flush();
		assertTrue(dao.findAllByClassName("Viagem").size() == 2);
	}
	
	@Test
	public void deveCadastrarUsuarioNaViagem(){
		Local local = new Local("Natal");
		dao.persist(local);
		dao.flush();
		Date data = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2014, 11, 20);
		data = cal.getTime();
		Viagem viagem = new Viagem();
		try {
			viagem.setLocal(local);
		} catch (Exception e) {
			fail();
		}
		try {
			viagem.setDescricao("Viagem para Natal");
		} catch (Exception e) {
			fail();
		}
		try {
			viagem.setData(data);
		} catch (Exception e) {
			fail();
		}
		Usuario user = new Usuario("Joao", "joao@gmail.com", "12345");
		dao.persist(user);
		dao.flush();
		viagem.setAdminUsuario(user.getEmail());
		viagem.addUsuario(user);
		ViagemAberta v = new ViagemAberta();
		viagem.setEstrategia(v);
		dao.persist(v);
		dao.flush();
		viagem.setFoto("abc");
		dao.persist(viagem);
		dao.flush();
		
		viagem.addUsuario(user);

		Viagem v2 = (Viagem) dao.findByAttributeName("Viagem", "descricao", "Viagem para Natal").get(0);
		assertTrue(v2.getUsuarios().size() > 0);
	}

}
