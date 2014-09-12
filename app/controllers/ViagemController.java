package controllers;

import static play.data.Form.form;

import java.util.List;

import models.Usuario;
import models.Viagem;
import models.ViagemLimitada;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class ViagemController extends Controller{

	private final static Form<ViagemController> VIAGEM_FORM = Form.form(ViagemController.class);

	public static Result showNewTrip(){
		return ok(novaViagem.render(VIAGEM_FORM));
	}
	
	
	@Transactional
	public static Result newTrip() {
		Form<ViagemController> novaViagemForm = VIAGEM_FORM.bindFromRequest();

		if (VIAGEM_FORM.hasErrors()) {
			return badRequest();
		} else {
			ViagemController novaTrip = novaViagemForm.get();
			Application.getDao().persist(novaTrip);
			Application.getDao().merge(novaTrip);
			Application.getDao().flush();
			return redirect(routes.Application.index());
		}
	}

	@Transactional
	public static Result loginClosedTrip(long idViagem) {
		ViagemLimitada viagemLimitada = Application.getDao().findByEntityId(ViagemLimitada.class, idViagem);

		String senha = form().bindFromRequest().get("senha");
		
		if(viagemLimitada.senhaDaViagemEstaCorreta(senha)){
			viagemLimitada.addUsuario(Application.getSessionP());
			Application.getDao().persist(viagemLimitada);
			Application.getDao().flush();
			flash("success", "Você foi cadastrado na viagem com sucesso!");
			return null;
		}
		flash("fail", "Senha incorreta!");
		return null;

	}
	
	@Transactional
	public static List<ViagemController> allTrip() {
		return Application.getDao().findAllByClassName("Viagem");
	}

	@Transactional
	public static Result showInfoTrip() {
		return null;

	}
}
