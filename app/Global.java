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
	List<Local> locais;
	List<String> descricoes;
	List<Usuario> usuarios;
	
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
		locais = new ArrayList<>();
		locais.add(new Local("Porto Alegre, RS"));
		locais.add(new Local("Goiânia, GO"));
		locais.add(new Local("Vitória, ES"));
		locais.add(new Local("Natal, RN"));
		locais.add(new Local("Brasília, DF"));
		locais.add(new Local("Porto Velho, RO"));
		locais.add(new Local("Joao Pessoa, PB"));
		locais.add(new Local("São Paulo, SP"));
		locais.add(new Local("Rio Branco, AC"));
		locais.add(new Local("Curitiba, PR"));
		locais.add(new Local("Espanha, ES"));
		locais.add(new Local("Florianópolis, SC"));
		locais.add(new Local("Aracaju, SE"));
		locais.add(new Local("Palmas, TO"));
		locais.add(new Local("Belo Horizonte, MG"));
		locais.add(new Local("Macapá, AP"));
		locais.add(new Local("Maceió, AL"));
		locais.add(new Local("França, FR"));
		locais.add(new Local("Recife, PE"));
		locais.add(new Local("Argentina, AR"));
		locais.add(new Local("Fortaleza, CE"));
		locais.add(new Local("Salvador, BA"));
		locais.add(new Local("Rio de Janeiro, RJ"));
		locais.add(new Local("São Luís, MA"));
		locais.add(new Local("Teresina, PI"));
		locais.add(new Local("Cuiabá, MT"));
		locais.add(new Local("Campo Grande, MS"));
		locais.add(new Local("Boa Vista, RR"));
		locais.add(new Local("Manaus, AM"));
		locais.add(new Local("Belém, PA"));
	}
	
	private void criaData(){
	}
	
	private void criaDescricao(){
		descricoes = new ArrayList<>();
		descricoes.add("Porto Alegre é um município brasileiro e a capital do estado mais meridional do Brasil, o Rio Grande do Sul.");
		descricoes.add("Goiânia é um município brasileiro, capital do estado de Goiás. Pertence à Mesorregião do Centro Goiano"
				+ "e à Microrregião de Goiânia, distando 209 km de Brasília, a capital nacional, sendo assim, a capital "
				+ "estadual mais próxima da capital federal");
		descricoes.add("Vitória é a capital do estado do Espírito Santo, na Região Sudeste do Brasil. É uma das três ilhas-capitais do país");
		descricoes.add("Natal é um município brasileiro, capital do estado do Rio Grande do Norte, Região Nordeste do país");
		descricoes.add("Brasília é a capital federal do Brasil e a sede do governo do Distrito Federal. A cidade está localizada "
				+ "na região Centro-Oeste do país, ao longo da região geográfica conhecida como Planalto Central.");
		descricoes.add("Porto Velho é um município brasileiro e capital do estado de Rondônia."
				+ " Situada na margem à leste do Rio Madeira, na Região Norte do Brasil.");
		descricoes.add("João Pessoa é um município brasileiro, capital e principal centro financeiro e econômico do estado da Paraíba.");
		descricoes.add("São Paulo é um município brasileiro, capital do estado de "
				+ "São Paulo e principal centro financeiro, corporativo e mercantil da América do Sul.");
		descricoes.add("Rio Branco é um município brasileiro, capital do estado do Acre,"
				+ "distante 3.123 quilômetros de Brasília, capital federal.");
		descricoes.add("Curitiba é um município brasileiro, capital do estado do Paraná, localizado a 934 metros de altitude "
				+ "no primeiro planalto paranaense, a aproximadamente 110 quilômetros do Oceano Atlântico.");
		descricoes.add("Espanha, oficialmente Reino da Espanha, é um país situado na Europa meridional, na Península Ibérica.");
		descricoes.add("Florianópolis é a capital do estado de Santa Catarina, na Região Sul do Brasil. "
				+ "Destaca-se por ser a capital brasileira com o melhor Índice de Desenvolvimento Humano (IDH), de 0,847.");
		descricoes.add("Aracaju é um município e capital do estado de Sergipe, no Brasil. "
				+ "Localiza-se no litoral, sendo cortada por rios como o Sergipe e o Poxim.");
		descricoes.add("Palmas é um município brasileiro, sendo a capital e também a maior cidade do estado do Tocantins.");
		descricoes.add("Belo Horizonte é um município brasileiro, capital do estado de Minas Gerais.");
		descricoes.add("Macapá é um município brasileiro, capital e maior cidade do estado do Amapá.");
		descricoes.add("Maceió é um município brasileiro, capital do estado de Alagoas, Região Nordeste do país.");
		descricoes.add("França, oficialmente República Francesa, é um país localizado na Europa Ocidental, "
				+ "com várias ilhas e territórios ultramarinos noutros continentes.");
		descricoes.add("Recife é um município brasileiro, capital do estado de Pernambuco, situado na Região Nordeste do país.");
		descricoes.add("Argentina, oficialmente República Argentina, é o segundo maior país da América do Sul em território e "
				+ "o terceiro em população, constituída como uma federação de 23 províncias e uma cidade autônoma, Buenos Aires.");
		descricoes.add("Fortaleza é um município brasileiro, capital do estado do Ceará, situado na Região Nordeste do país. "
				+ "Pertence à mesorregião Metropolitana de Fortaleza e à microrregião de Fortaleza.");
		descricoes.add("Salvador, fundada como São Salvador da Bahia de Todos os Santos,8 é um município brasileiro, capital do estado "
				+ "da Bahia, localizado na Mesorregião Metropolitana de Salvador e Microrregião de Salvador, Região Nordeste do país.");
		descricoes.add("Rio de Janeiro, é um município brasileiro, capital do estado homônimo, situado no Sudeste do país.");
		descricoes.add("São Luís é um município e a capital do estado do Maranhão, no Brasil.");
		descricoes.add("Teresina é a capital e o município mais populoso do estado brasileiro do Piauí.");
		descricoes.add("Cuiabá é a capital e o mais populoso município do estado de Mato Grosso, no Brasil. ");
		descricoes.add("Campo Grande é um município brasileiro da região Centro-Oeste, capital do estado de Mato Grosso do Sul.");
		descricoes.add("Boa Vista é a capital e o município mais populoso do estado brasileiro de Roraima. ");
		descricoes.add("Manaus é um município brasileiro, capital do estado do Amazonas e o principal centro financeiro, "
				+ "corporativo e econômico da Região Norte do Brasil.");
		descricoes.add("Belém é um município brasileiro, capital do estado do Pará, pertencente à Mesorregião "
				+ "Metropolitana de Belém e à Microrregião de Belém.");
	}
	
	private List<Usuario> criarUsuarios(){
		usuarios = new ArrayList<Usuario>();
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
