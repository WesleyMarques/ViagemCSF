/**
 * 
 */
package modelsBD;

import static org.junit.Assert.*;
import static play.data.Form.form;
import geral.AbstractTest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import models.Local;
import models.Usuario;
import models.Viagem;
import models.ViagemAberta;
import models.ViagemLimitada;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Assert;
import org.junit.Test;

import controllers.Application;

/**
 * @author Wesley
 *
 */
public class modelsTest extends AbstractTest{
	GenericDAO dao = new GenericDAOImpl();

	@Test
	public void deveCriarUsuario() {
		Usuario user = new Usuario("Wesley", "welsey@gmail.com", "12345");
		dao.persist(user);
		assertTrue(dao.findAllByClassName("Usuario").size() == 1);
		user = new Usuario("Erick", "Erick@gmail.com", "12345");
		dao.persist(user);
		assertTrue(dao.findAllByClassName("Usuario").size() == 2);
		user = new Usuario("Gustavo", "Gustavo@gmail.com", "12345");
		dao.persist(user);
		assertTrue(dao.findAllByClassName("Usuario").size() == 3);
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
	public void deveCriarEPersistirAViagem() throws Exception{
		Local local = new Local("Joao Pessoa");
		dao.persist(local);
		Date data = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2014, 9, 26);
		data = cal.getTime();
		Viagem viagem = new Viagem();
		viagem.setLocal(local);
		viagem.setDescricao("Viagem para Joao Pessoa");
		viagem.setData(data);
		Usuario user = new Usuario("Gustavo", "Gustavo@gmail.com", "12345");
		dao.persist(user);
		viagem.setAdminUsuario(user.getEmail());
		viagem.addUsuario(user);
		viagem.setFoto(form().bindFromRequest().get("foto"));
		assertTrue(dao.findAllByClassName("Viagem").size() == 0);
		System.out.println((dao.findAllByClassName("Viagem").size() == 0));
		dao.persist(viagem);
		assertTrue(dao.findAllByClassName("Viagem").size() == 1);
		
		
		viagem = new Viagem();
		dao.persist(viagem);
		assertTrue(dao.findAllByClassName("Viagem").size() == 2);
	}
	
	/*@Test
	public void deveCadastrarUsuarioNaViagem(){
		Local local = new Local("Joao Pessoa");
		Date data = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2014, 9, 26);
		data = cal.getTime();
		Viagem viagem = new Viagem();
		viagem.setLocal(local);
		viagem.setDescricao("Viagempara Joao Pessoa");
		viagem.setData(data);
		viagem.setAdminUsuario(Application.getSessionP().getEmail());
		viagem.addUsuario(Application.getSessionP());
		viagem.setFoto(form().bindFromRequest().get("foto"));
		
		
		Local local = new Local("SESI", "Marciel Pinheiro", 10);
		Date data = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2014, 9, 26);
		data = cal.getTime();
		Viagem viagem = new ViagemAberta(local, data, "Viagem de ferias", null);
		dao.persist(viagem);
		dao.flush();
		Viagem viagem2 = new ViagemLimitada(local, data, "Viagem de ferias", "123", null);
		dao.persist(viagem2);
		dao.flush();
		

		Usuario user = new Usuario("Wesley", "welsey@gmail.com", "12345");
		dao.persist(user);
		Usuario user2 = new Usuario("Erick", "Erick@gmail.com", "12345");
		dao.persist(user2);
		
		viagem.addUsuario(user);
		dao.persist(viagem);
		for(Object v : dao.findAllByClassName("Viagem")){
			if(v.equals(viagem)){
				Viagem v2 = (Viagem) v;
				Assert.assertTrue(v2.getUsuarios().size() == 1);
			}
			if(v.equals(viagem2)){
				Viagem v2 = (Viagem) v;
				Assert.assertTrue(v2.getUsuarios().size() == 0);
			}
		}
		
	}*/

}
