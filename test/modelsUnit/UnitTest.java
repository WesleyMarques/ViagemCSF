package modelsUnit;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import models.Local;
import models.Usuario;
import models.Viagem;
import models.ViagemAberta;
import models.ViagemLimitada;
import models.ViagemStrategy;

import org.junit.Before;
import org.junit.Test;

public class UnitTest {

	Local local;
	Usuario usuario;
	Viagem viagem;
	
	@Before
	public void preCond(){
		local = new Local("Joao Pessoa");
		usuario = new Usuario("Gustavo", "gustavo@gmail.com", "123");
		viagem = new Viagem();
		
		Date data = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2014, 9, 26);

		try {
			viagem.setData(data);
		} catch (Exception e) {
			fail("Data invalida");
		}
		try {
			viagem.setDescricao("Viagem para Joao Pessoa.");
		} catch (Exception e) {
			fail("Descricao invalida.");
		}
		try {
			viagem.setLocal(local);
		} catch (Exception e) {
			fail("Local invalido.");
		}
		
		
		viagem.setAdminUsuario(usuario.getEmail());
		viagem.setFoto("link da foto.");
		//Viagem Sem Tipo.
	}
	
	@Test
	public void deveCadastrarUsuarioNaViagem() {
		assertTrue(viagem.getUsuarios().size() == 0);
		ViagemStrategy va = new ViagemAberta();
		viagem.setEstrategia(va);
		try {
			viagem.addUsuario(usuario, "");
			assertTrue(viagem.getUsuarios().size() == 1);
		} catch (Exception e) {
			fail();
		}
		assertTrue(viagem.getUsuarios().size() == 1);
		assertTrue(viagem.getUsuarios().get(0).getNome().equals("Gustavo"));
	}
	
	@Test
	public void deveColocarSenhaParaSeCadastrarNumaViagemLimitada(){
		ViagemStrategy vl = new ViagemLimitada("senhadificil");
		viagem.setEstrategia(vl);
		try {
			viagem.addUsuario(usuario,"");
			fail();
		} catch (Exception e) {}
		assertTrue(viagem.getUsuarios().size() == 0);
	}

	@Test
	public void naoDeveCadastrarUsuarioNumaViagemLimitadaComSenhaIncorreta(){
		ViagemStrategy vl = new ViagemLimitada("senhadificil");
		viagem.setEstrategia(vl);
		try {
			viagem.addUsuario(usuario,"senhafacil");
			fail();
		} catch (Exception e) {}
		assertTrue(viagem.getUsuarios().size() == 0);
	}
	
	@Test
	public void deveCadastrarUsuarioNumaViagemLimitadaComSenhaCorreta(){
		ViagemStrategy vl = new ViagemLimitada("senhadificil");
		viagem.setEstrategia(vl);
		try {
			viagem.addUsuario(usuario,"senhadificil");
		} catch (Exception e) {
			fail();
		}
		assertTrue(viagem.getUsuarios().size() == 1);
	}
	
	@Test
	public void deveMudarEstrategiaDaViagem(){
		ViagemStrategy vl = new ViagemLimitada("senhadificil");
		viagem.setEstrategia(vl);
		try {
			viagem.addUsuario(usuario,"senhadificil");
		} catch (Exception e) {
			fail();
		}
		assertTrue(viagem.getUsuarios().size() == 1);
		
		ViagemStrategy va = new ViagemAberta();
		viagem.setEstrategia(va);
		Usuario usuario2 = new Usuario("Wesley", "wesley@gmail.com", "123");
		try{
			viagem.addUsuario(usuario2, "");
		} catch (Exception e) {
			fail();
		}
		assertTrue(viagem.getUsuarios().size() == 2);
		
		viagem.setEstrategia(vl);
		assertTrue(viagem.getTipoDeViagem().getSenha().equals("senhadificil"));
	}
}
