package controllers;

import static play.data.Form.form;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import models.Local;
import models.Viagem;
import models.ViagemAberta;
import models.ViagemLimitada;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class ViagemController extends Controller{

	private final static Form<Viagem> VIAGEM_FORM = Form.form(Viagem.class);
	
	public static Result showNewTrip(){
		return ok(novaViagem.render(VIAGEM_FORM));
	}
	
	public static Result showViagensAdmin(){
		List<Viagem> viagens = Application.getDao().findByAttributeName("Viagem", "adminUsuario", Application.getSessionP().getEmail());
		return ok(minhasViagens.render(viagens));
	}
	
	@Transactional
	public static Result showViagemInfo(Long id){
		Viagem viagem = (Viagem) Application.getDao().findByEntityId(Viagem.class, id);
		return ok(viagemInfo.render(viagem));
	}
	
	
	@Transactional
	public static Result newTrip() throws Exception {

		if (VIAGEM_FORM.hasErrors()) {
			return badRequest();
		} else {
			Viagem novaTrip = new Viagem();
			
			novaTrip.setLocal((Local)persistAux(new Local(form().bindFromRequest().get("local"))));
			novaTrip.setDescricao(form().bindFromRequest().get("descricao"));
			novaTrip.setData(getDataFormatada(form().bindFromRequest().get("data")));
			novaTrip.setAdminUsuario(Application.getSessionP().getEmail());
			novaTrip.setFoto(form().bindFromRequest().get("foto"));
			
			String viagemTipo = form().bindFromRequest().get("viagemTipo");
			String senha = form().bindFromRequest().get("senha");
			
			if (viagemTipo.equals("LIMITADA")) {
				novaTrip.setEstrategia((ViagemLimitada) persistAux(new ViagemLimitada(senha)));
			} else{
				novaTrip.setEstrategia((ViagemAberta) persistAux(new ViagemAberta()));
			}			
			
			novaTrip.addUsuario(Application.getSessionP(), senha);
			
			
			Application.getDao().persist(novaTrip);
			Application.getDao().flush();
			
			flash("success", "Viagem criada com sucesso!");
			
			return redirect(routes.Application.index());
		}
	}
	
	@Transactional
	public static <T> Object persistAux(Object object) {
		List<T> result = Application.getDao().findAllByClassName(object.getClass().getSimpleName());
		if (!result.contains(object)) {
			Application.getDao().persist(object);
			Application.getDao().flush();
		}
		return getObjectBD(object);
	}
	
	@Transactional
	private static <T> Object getObjectBD(Object object) {
		List<T> result = Application.getDao().findAllByClassName(object.getClass().getSimpleName());
		for (Object o : result) {
			if(o.equals(object)){
				return o;
			}
		}
		return null;
	}

	private static Date getDataFormatada(String data) throws Exception {
		try {
			String[] splitData = data.split("-");
			Integer dia = Integer.parseInt(splitData[2]);
			Integer mes = Integer.parseInt(splitData[1]);
			Integer ano = Integer.parseInt(splitData[0]);
			return new GregorianCalendar(ano, mes-1, dia).getTime();
		} catch (Exception e) {
			throw new Exception("Data Inválida");
		}
	}
	
	@Transactional
	public static Result loginTrip(long idViagem) throws Exception {
		Viagem viagem = Application.getDao().findByEntityId(Viagem.class, idViagem);
		String senha = form().bindFromRequest().get("senha");
		
		if(senha.equals(viagem.getTipoDeViagem().getSenha())){
			viagem.addUsuario(Application.getSessionP(), senha);
			Application.getDao().merge(viagem);
			Application.getDao().flush();
			return redirect(routes.Application.index());
		}
		flash("fail", "Senha incorreta!");
		return badRequest();

	}
	
	@Transactional
	public static List<Viagem> allTrip() {
		return Application.getDao().findAllByClassName("Viagem");
	}
	
	@Transactional
	public static List<String> imagensBanner(){
		return Application.getDao().createQuery("FROM Viagem LIMIT 3").getResultList();
	}

	@Transactional
	public static Result showInfoTrip() {
		return null;

	}
	
	
	
}
