package controllers;

import static play.data.Form.form;

import java.lang.ProcessBuilder.Redirect;

import models.Usuario;
import models.ViagemLimitada;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.*;
import play.mvc.*;
import views.html.*;
import views.html.defaultpages.badRequest;
import views.html.helper.form;

public class Viagem extends Controller{

	private final static Form<Viagem> VIAGEM_FORM = Form.form(Viagem.class);
	private final static Form<Usuario> USUARIO_FORM = Form.form(Usuario.class);

	@Transactional
	public static Result newTrip() {
		Form<Viagem> novaViagemForm = VIAGEM_FORM.bindFromRequest();

		if (VIAGEM_FORM.hasErrors()) {
			return badRequest();
		} else {
			Viagem novaTrip = novaViagemForm.get();
			Application.getDao().persist(novaTrip);
			Application.getDao().merge(novaTrip);
			Application.getDao().flush();
			return Redirect(routes.Application.index());
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
/*
	@Transactional
	public static Result allTrip() {
		Application.getDao().findAllByClassName("Viagem");
		return null;
	}

	@Transactional
	public static Result showInfoTrip() {
		return null;

	}*/
}
