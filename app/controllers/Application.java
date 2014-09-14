package controllers;

import java.util.List;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	private final static Form<Usuario> USER_FORM = Form.form(Usuario.class);
	private static GenericDAO dao = new GenericDAOImpl();
	private static Usuario sessionUser;


    public static Result index() {
    	if (session().get("email") == null) {
			return redirect(routes.Application.showLogin());
		}
        return ok(index.render("Your new application is ready."));
    }
    
//Login Controller start
    /**
     * 
     * @return 
     */
    public static Result showLogin(){
		if (session().get("email") != null) {
			return redirect(routes.Application.index());			
		}
		return ok(login.render(USER_FORM));
	}
	
	@Transactional
	public static Result authenticate() {
		Form<Usuario> newUserForm = USER_FORM.bindFromRequest();
		Usuario userA;

		if (USER_FORM.hasErrors()) {
			flash("fail", "Erro na captura dos dados");
        	return badRequest(login.render(newUserForm));						
		}else{
			String email = newUserForm.field("email").value();
			String senha = newUserForm.field("senha").value();

	        if (!validate(email, senha)) {
	        	flash("fail", "Email ou Senha Inválidos");
	        	return badRequest(login.render(newUserForm));
	        } else {
	        	Usuario user = (Usuario) dao.findByAttributeName(
	        			"Usuario", "email", email).get(0);
	            session().clear();
	            session("email", user.getEmail());
				sessionUser = user;
	            return redirect(routes.Application.index());
	        }
		}
		
    }
	
	@Transactional
	public static Result logout() {
		session().clear();
		flash("success", "Você saiu do sistema!");
		return ok(login.render(Form.form(Usuario.class)));
	}
	
	private static boolean validate(String email, String senha) {
		List<Usuario> u = dao.findByAttributeName("Usuario", "email", email);
		if (u == null || u.isEmpty()) {
			return false;
		}
		if (!u.get(0).getSenha().equals(senha)) {
			return false;
		}
		return true;
	}
//Login end
//Métodos para registro de novo usuário
	public static Result showRegistry() {
		return ok(registro.render(USER_FORM));
	}
	@Transactional
	public static Result registrar(){

		Form<Usuario> registroPessoa = USER_FORM.bindFromRequest();
		Usuario usuario;

		if (USER_FORM.hasErrors()) {
			flash("fail", "Erro na captura dos dados");
			return badRequest(registro.render(registroPessoa));
		} else {
			usuario = registroPessoa.get();
			if (!validate(usuario.getEmail())) {
				flash("fail", "Email já está em uso");
				return badRequest(registro.render(registroPessoa));
			} else {
				dao.persist(usuario);
				dao.flush();
				return redirect(routes.Application.showLogin());
			}

		}

	}
	
	private static boolean validate(String email) {
		List<Usuario> u = dao.findByAttributeName("Usuario", "email", email);
		if (u != null && u.isEmpty()) {
			return true;
		}
		return false;
	}
//Fim do métodos
//Métodos para BD e SESSION
	public static Usuario getSessionP(){
		return sessionUser;
	}
	
	public static void setSessionP(Usuario user){
		sessionUser = user;
		
	}

	public static GenericDAO getDao(){
		return dao;
	}
//End	
}
