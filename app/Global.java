import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import models.Local;
import models.ViagemStrategy;
import models.Usuario;
import models.Viagem;
import models.ViagemAberta;
import models.ViagemLimitada;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class Global extends GlobalSettings{

	private GenericDAO dao = new GenericDAOImpl();
	
	@Override
	public void onStart(Application arg0) {

		JPA.withTransaction(new play.libs.F.Callback0() {

			@Override
			public void invoke() throws Throwable {
				try {
					//criarViagens();
				} catch (Exception e) {
					Logger.info("Erro Global: " + e.getMessage());
				}
			}
		});
	}
	
	private void criarLocais(){
		List<Local> locais = new ArrayList<>();
		locais.add(new Local("Joao Pessoa, PB"));
		locais.add(new Local("Joao Pessoa, PB"));
		locais.add(new Local("Joao Pessoa, PB"));
		locais.add(new Local("Palmas, TO"));
		locais.add(new Local("Aracaju, SE"));
		locais.add(new Local("São Paulo, SP"));
		locais.add(new Local("Florianópolis, SC"));
		locais.add(new Local("Boa Vista, RR"));
		locais.add(new Local("Porto Velho, RO"));
		locais.add(new Local("Porto Alegre, RS"));
		locais.add(new Local("Natal, RN"));
		locais.add(new Local("Teresina, PI"));
		locais.add(new Local("Rio de Janeiro, RJ"));
		locais.add(new Local("Joao Pessoa, PB"));
		locais.add(new Local("Recife, PE"));
		locais.add(new Local("Curitiba, PR"));
		locais.add(new Local("Belém, PA"));
		locais.add(new Local("Belo Horizonte, MG"));
		locais.add(new Local("Campo Grande, MS"));
		locais.add(new Local("Cuiabá, MT"));
		locais.add(new Local("São Luís, MA"));
		locais.add(new Local("Goiânia, GO"));
		locais.add(new Local("Vitória, ES"));
		locais.add(new Local("Brasília, DF"));
		locais.add(new Local("Fortaleza, CE"));
		locais.add(new Local("Salvador, BA"));
		locais.add(new Local("Manaus, AM"));
		locais.add(new Local("Macapá, AP"));
		locais.add(new Local("Maceió, AL"));
		locais.add(new Local("Rio Branco, AC"));
	}
	
	private List<Usuario> criarUsuarios(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			usuarios.add(new Usuario("Barbosa", "barbosa@mail.com", "123456"));
			usuarios.add(new Usuario("Julia", "julia@mail.com", "123456"));
			usuarios.add(new Usuario("Priscila", "priscila@mail.com", "123456"));
			usuarios.add(new Usuario("Marques", "marques@mail.com", "123456"));
			usuarios.add(new Usuario("Ana", "ana@mail.com", "123456"));
			usuarios.add(new Usuario("Tereza", "tereza@mail.com", "123456"));
			usuarios.add(new Usuario("Janaina", "janaina@mail.com", "123456"));
			usuarios.add(new Usuario("Jaqueline", "jaqueline@mail.com", "123456"));
			usuarios.add(new Usuario("Francisco", "francisco@mail.com", "123456"));
			usuarios.add(new Usuario("Tiago", "tiago@mail.com", "123456"));
			usuarios.add(new Usuario("Rafael", "rafael@mail.com", "123456"));
			usuarios.add(new Usuario("Joao", "joao@mail.com", "123456"));
			usuarios.add(new Usuario("Rodrigo", "rodrigo@mail.com", "123456"));
			usuarios.add(new Usuario("Junior", "junior@mail.com", "123456"));
			usuarios.add(new Usuario("Beatriz", "beatriz@mail.com", "123456"));
			usuarios.add(new Usuario("Fernanda", "fernanda@mail.com", "123456"));
			usuarios.add(new Usuario("Pedro", "pedro@mail.com", "123456"));
			usuarios.add(new Usuario("Conceicao", "conceicao@mail.com", "123456"));
			usuarios.add(new Usuario("Francisca", "francisca@mail.com", "123456"));
			usuarios.add(new Usuario("Lurdes", "luders@mail.com", "123456"));
			usuarios.add(new Usuario("Raissa", "raissa@mail.com", "123456"));
			usuarios.add(new Usuario("Rayane", "rayane@mail.com", "123456"));
			usuarios.add(new Usuario("Eduarda", "eduarda@mail.com", "123456"));
			usuarios.add(new Usuario("Celia", "celia@mail.com", "123456"));
			usuarios.add(new Usuario("Maria", "maria@mail.com", "123456"));
			usuarios.add(new Usuario("Isabele", "isalbele@mail.com", "123456"));
			usuarios.add(new Usuario("Laura", "laura@mail.com", "123456"));
			usuarios.add(new Usuario("Leticia", "leticia@mail.com", "123456"));
			usuarios.add(new Usuario("Leonardo", "leonardo@mail.com", "123456"));
			usuarios.add(new Usuario("Diego", "diego@mail.com", "123456"));
			usuarios.add(new Usuario("Carlos", "carlos@mail.com", "123456"));
			usuarios.add(new Usuario("Raquel", "raquel@mail.com", "123456"));
			usuarios.add(new Usuario("Lucas", "lucas@mail.com", "123456"));
			usuarios.add(new Usuario("Larissa", "Larissa@mail.com", "123456"));
			usuarios.add(new Usuario("Gabriel", "gabriel@mail.com", "123456"));
			usuarios.add(new Usuario("Artur", "artur@mail.com", "123456"));
			usuarios.add(new Usuario("Ricardo", "ricardo@mail.com", "123456"));
			usuarios.add(new Usuario("Cassio", "cassio@mail.com", "123456"));
			usuarios.add(new Usuario("Mateus", "mateus@mail.com", "123456"));
			usuarios.add(new Usuario("Marcos", "marcos@mail.com", "123456"));
		} catch (Exception e) {
			Logger.info("Erro Global: " + e.getMessage());
		}
		return usuarios;		
	}
	
}
