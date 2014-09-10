package controllers;

import static play.data.Form.form;


import models.Usuario;
import models.ViagemLimitada;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

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
