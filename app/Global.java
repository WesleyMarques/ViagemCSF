import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;

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
import scala.Array;

public class Global extends GlobalSettings{

	private GenericDAO dao = new GenericDAOImpl();
	List<Local> locais;
	List<String> descricoes;
	List<Usuario> usuarios;
	List<Date> datas;
	List<String> fotos;
	List<ViagemStrategy> tipos;
	
	@Override
	public void onStart(Application app) {

		JPA.withTransaction(new play.libs.F.Callback0() {

			@Override
			public void invoke() throws Throwable {
				criarLocais();
				criarData();
				criaDescricao();
				criarFotos();
				criarUsuarios();
				criarTiposDeViagem();
				try {
					criarViagens();
				} catch (Exception e) {
					Logger.info("Erro Global: " + e.getMessage());
				}
			}
		});
	}

	private void criarViagens() throws Exception {
		for(int i =0; i< 30; i++){
			Viagem viagem = new Viagem(locais.get(i), datas.get(i), descricoes.get(i), tipos.get(i), fotos.get(i));
			viagem.setAdminUsuario(usuarios.get(i).getEmail());
			dao.persist(viagem);
			dao.flush();
			viagem.addUsuario(usuarios.get(i), tipos.get(i).getSenha());
			if(i <= 10){
				for(int j = i+1; j <= (2*i)+1; j++){
					viagem.addUsuario(usuarios.get(j+2), tipos.get(i).getSenha());
				}
			}
			else if(i > 25){
				for(int j = i+1; j <= i+10; j++){
					viagem.addUsuario(usuarios.get(j), tipos.get(i).getSenha());
				}
			}
			else if(i > 10 & i <= 25){
				for(int j = i+1; j <= i+5; j++){
					viagem.addUsuario(usuarios.get(j-5), tipos.get(i).getSenha());
				}
			}
			
			dao.merge(viagem);
			dao.flush();
		}
	}


	private void criarTiposDeViagem() {
		tipos = new ArrayList<ViagemStrategy>();
		for(int i = 0; i<30; i++){
			// tipos.get(i); i%2==0 => viagem aberta
			ViagemStrategy viagemAberta = new ViagemAberta();
			dao.persist(viagemAberta);
			dao.flush();
			tipos.add(viagemAberta);
			// tipos.get(i); i%2!=0 => viagem limitada
			ViagemStrategy viagemLimitada = new ViagemLimitada("12345");
			dao.persist(viagemLimitada);
			dao.flush();
			tipos.add(viagemLimitada);
		}
	}
	
	private void criarLocais(){
		locais = new ArrayList<Local>();
		locais.add(new Local("Porto Alegre, RS"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Goiânia, GO"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Vitória, ES"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Natal, RN"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Brasília, DF"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Porto Velho, RO"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Joao Pessoa, PB"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("São Paulo, SP"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Rio Branco, AC"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Curitiba, PR"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		//2
		locais.add(new Local("Espanha, ES"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Florianópolis, SC"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Aracaju, SE"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Palmas, TO"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Belo Horizonte, MG"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Macapá, AP"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Maceió, AL"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("França, FR"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Recife, PE"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Argentina, AR"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		//3
		locais.add(new Local("Fortaleza, CE"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Salvador, BA"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Rio de Janeiro, RJ"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("São Luís, MA"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Teresina, PI"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Cuiabá, MT"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Campo Grande, MS"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Boa Vista, RR"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Manaus, AM"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
		locais.add(new Local("Belém, PA"));
		dao.persist(locais.get(locais.size()-1));
		dao.flush();
	}
	
	private void criarFotos(){
		fotos = new ArrayList<String>();
		
		fotos.add("http://i17.photobucket.com/albums/b91/latinohunk/14th/panoOrla.jpg");
		fotos.add("http://3.bp.blogspot.com/-L64ZaISB7aQ/Uf7Dqx_YU7I/AAAAAAAAPis/Uug1cqDbMnU/s1600/panor%C3%A2mica-dentro-do-zoologico-goiania.jpg");
		fotos.add("http://i182.photobucket.com/albums/x226/Raphael_San_photos/panoramagrande-1.jpg");
		fotos.add("http://upload.wikimedia.org/wikipedia/commons/6/60/Foto_Panor%C3%A2mica_de_Natal_-_Rio_Grande_do_Norte_-_Morro_do_Careca_-_Ponta_Negra.jpg");
		fotos.add("http://upload.wikimedia.org/wikipedia/commons/6/62/Bras%C3%ADlia_Panor%C3%A2mica.jpg");
		fotos.add("http://i100.photobucket.com/albums/m20/tiagopvh/pvh6.jpg");
		fotos.add("http://img2.imageshack.us/img2/8705/foto431.jpg");
		fotos.add("http://jrholanda.files.wordpress.com/2012/12/dsc02751.jpg");
		fotos.add("http://www.encontraacre.com.br/imgs/acre-panoramica-2.jpg");
		fotos.add("http://www.encontraparana.com.br/imgs/foto-panoramica-parana.jpg");
		//2
		fotos.add("http://www.webdocsolos.com/img/panoramica.jpg");
		fotos.add("http://i1127.photobucket.com/albums/l633/jffj1/Floripa%20-%20Panoramic/044ef158.jpg");
		fotos.add("http://img.photobucket.com/albums/v299/Murilo3ds/panoramica_01_bc.jpg");
		fotos.add("http://images.pangeaimagem.com/4ce2eebf2510cb5350030000.jpg?w=1100");
		fotos.add("http://static.panoramio.com/photos/original/84948765.jpg");
		fotos.add("http://1.bp.blogspot.com/-ze_XhrzzGdw/T4wvQ5tZLxI/AAAAAAAAW2Y/_14SPOz27xY/s1600/panoramica-trapiche+(1)a.jpg");
		fotos.add("http://4.bp.blogspot.com/-WzspvmsH0AU/UeGVBbcPQvI/AAAAAAAAfaQ/Eu62vpCMB9A/s1024/6.jpg");
		fotos.add("http://www.sternaviagens.com/sites/default/files/styles/galdestino-big/public/destinos/vistaaerea1.jpg?itok=qR9fv4yo");
		fotos.add("http://static.panoramio.com/photos/large/46301825.jpg");
		fotos.add("http://www.casti.com.ar/photos/panoramica_necochea.jpg");
		//3
		fotos.add("http://2.bp.blogspot.com/-iTOvfZfnkSg/TbjE9-VXhwI/AAAAAAAAAqI/eIXppP9dR_A/s1600/Do+Porto+Pra+Fortaleza.jpg");
		fotos.add("http://www.encontrabahia.com.br/imgs/foto-panoramica-bahia.jpg");
		fotos.add("http://www.clubedosaventureiros.com/caold/images/zoom/PECABG/viewsize/2009-01-03_14-36-00_-_Panoramica_do_Pao_de_Acucar_e_do_Morro_da_Urca_-_Rio_de_Janeiro_-_RJ.JPG");
		fotos.add("http://i2.photobucket.com/albums/y12/elvysoares/Lagoa-Panoramica.jpg");
		fotos.add("http://mardemes.files.wordpress.com/2010/08/100-teresina-panoramica-vista-da-ponte-estaiada-3a.jpg");
		fotos.add("http://static.panoramio.com/photos/original/76726823.jpg");
		fotos.add("http://1.bp.blogspot.com/_hKKn6qgVi94/S9LLjhca8-I/AAAAAAAAAlk/s3BHzHB0Teo/s1600/vista+santos+praia.jpg");
		fotos.add("https://c1.staticflickr.com/9/8381/8594938253_c5e0bfb992_z.jpg");
		fotos.add("http://upload.wikimedia.org/wikipedia/commons/c/c5/Manaus-Panoramica.jpg");
		fotos.add("http://farm8.staticflickr.com/7301/11718520036_af613fd477_o.jpg");
	}
	
	private void criarData(){
		datas = new ArrayList<Date>();
		Calendar cal = GregorianCalendar.getInstance();
		
		cal.set(2014, 9, 26);
		datas.add(cal.getTime());
		
		cal.set(2014, 9, 30);
		datas.add(cal.getTime());
		
		cal.set(2014, 12, 26);
		datas.add(cal.getTime());
		
		cal.set(2014, 12, 2);
		datas.add(cal.getTime());
		
		cal.set(2015, 4, 6);
		datas.add(cal.getTime());
		
		cal.set(2018, 9, 26);
		datas.add(cal.getTime());
		
		cal.set(2015, 1, 26);
		datas.add(cal.getTime());
		
		cal.set(2015, 10, 13);
		datas.add(cal.getTime());
		
		cal.set(2014, 11, 2);
		datas.add(cal.getTime());
		
		cal.set(2014, 12, 6);
		datas.add(cal.getTime());
		
		//2
		cal.set(2015, 9, 26);
		datas.add(cal.getTime());
		
		cal.set(2014, 12, 30);
		datas.add(cal.getTime());
		
		cal.set(2015, 2, 26);
		datas.add(cal.getTime());
		
		cal.set(2015, 2, 25);
		datas.add(cal.getTime());
		
		cal.set(2015, 3, 6);
		datas.add(cal.getTime());
		
		cal.set(2018, 3, 26);
		datas.add(cal.getTime());
		
		cal.set(2015, 1, 26);
		datas.add(cal.getTime());
		
		cal.set(2015, 1, 13);
		datas.add(cal.getTime());
		
		cal.set(2015, 4, 2);
		datas.add(cal.getTime());
		
		cal.set(2014, 12, 2);
		datas.add(cal.getTime());
		
		//3
		cal.set(2014, 11, 7);
		datas.add(cal.getTime());
		
		cal.set(2015, 12, 7);
		datas.add(cal.getTime());
		
		cal.set(2016, 12, 3);
		datas.add(cal.getTime());
		
		cal.set(2016, 6, 2);
		datas.add(cal.getTime());
		
		cal.set(2015, 4, 2);
		datas.add(cal.getTime());
		
		cal.set(2018, 9, 15);
		datas.add(cal.getTime());
		
		cal.set(2015, 1, 22);
		datas.add(cal.getTime());
		
		cal.set(2015, 10, 14);
		datas.add(cal.getTime());
		
		cal.set(2014, 11, 23);
		datas.add(cal.getTime());
		
		cal.set(2017, 12, 20);
		datas.add(cal.getTime());
	}
	
	private void criaDescricao(){
		descricoes = new ArrayList<String>();
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
	
	private void criarUsuarios(){
		usuarios = new ArrayList<Usuario>();
		try {
			usuarios.add(new Usuario("Barbosa", "barbosa@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Julia", "julia@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Priscila", "priscila@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Marques", "marques@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Ana", "ana@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Tereza", "tereza@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Janaina", "janaina@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Jaqueline", "jaqueline@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Francisco", "francisco@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Tiago", "tiago@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Rafael", "rafael@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Joao", "joao@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Rodrigo", "rodrigo@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Junior", "junior@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Beatriz", "beatriz@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Fernanda", "fernanda@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Pedro", "pedro@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Conceicao", "conceicao@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Francisca", "francisca@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Lurdes", "luders@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Raissa", "raissa@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Rayane", "rayane@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Eduarda", "eduarda@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Celia", "celia@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Maria", "maria@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Isabele", "isalbele@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Laura", "laura@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Leticia", "leticia@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Leonardo", "leonardo@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Diego", "diego@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Carlos", "carlos@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Raquel", "raquel@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Lucas", "lucas@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Larissa", "Larissa@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Gabriel", "gabriel@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Artur", "artur@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Ricardo", "ricardo@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Cassio", "cassio@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Mateus", "mateus@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
			usuarios.add(new Usuario("Marcos", "marcos@mail.com", "123456"));
			dao.persist(usuarios.get(usuarios.size()-1));
			dao.flush();
		} catch (Exception e) {
			Logger.info("Erro Global: " + e.getMessage());
		}		
	}
	
}
