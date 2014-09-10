package controllers;

import models.Usuario;
import models.ViagemLimitada;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import views.html.index;
import play.*;
import play.mvc.*;
import views.html.*;
import views.html.helper.form;

public class Viagem {

	private static Form<Viagem> viagemForm = Form.form(Viagem.class);
	private static Form<Usuario> usuarioForm = Form.form(Usuario.class);

	public static Result newTrip() {
		Form<Viagem> novaViagemForm = viagemForm.bindFromRequest();

		if (novaViagemForm.hasErrors()) {
			return badRequest();
		} else {
			Viagem novaTrip = novaViagemForm.get();
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

		
		ViagemLimitada viagem = Application.getDao().findByEntityId(ViagemLimitada.class, idViagem);
		
		if(viagem.senhaDaViagemEstaCorreta(senha)){
			// adicionar o usuario na viagem
		}
		return null;

	}

	public static Result allTrip() {
		Application.getDao().findAllByClassName("Viagem");
	}

	public static Result showInfoTrip() {

	}
}
