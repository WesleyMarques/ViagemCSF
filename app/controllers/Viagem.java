package controllers;

import models.Usuario;
import models.ViagemLimitada;
import play.data.DynamicForm;
import play.data.Form;
import views.html.index;

public class Viagem {

	private static Form<Viagem> viagemForm = Form.form(Viagem.class);
	private static Form<Usuario> usuarioForm = Form.form(Usuario.class);

	public static result newTrip() {
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

	public static result loginClosedTrip(long idViagem) {
		ViagemLimitada viagemLimitada = Application.getDao().findByEntityId(
				ViagemLimitada.class, idViagem);

		DynamicForm requestData = form().bindFromRequest();
		String senha = requestData.get("senha");

		
		ViagemLimitada viagem = Application.getDao().findByEntityId(ViagemLimitada.class, idViagem);
		
		if(viagem.senhaDaViagemEstaCorreta(senha)){
			// adicionar o usuario na viagem
		}
		return null;

	}

	public static result allTrip() {

	}

	public static result showInfoTrip() {

	}
}
