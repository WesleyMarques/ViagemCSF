/**
 * 
 */
package models;

import static org.junit.Assert.*;
import geral.AbstractTest;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Test;

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
		Local local = new Local("SESI", "Marciel Pinheiro", 10);
		dao.persist(local);
		assertTrue(dao.findAllByClassName("Local").size() > 0);
		local = new Local("UEPB", "Rua João Pessoa", 10);
	    dao.persist(local);
		assertTrue(dao.findAllByClassName("Local").size() > 1);
	}

}
