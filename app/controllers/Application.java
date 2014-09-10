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
	
	private static Form<Usuario> userForm = Form.form(Usuario.class);
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
		return ok(login.render(userForm));
	}
	
	@Transactional
	public static Result authenticate() {

		Form<Usuario> userForm = userForm.bindFromRequest();
		Usuario userA;

		if (userForm.hasErrors()) {
			flash("fail", "Erro na captura dos dados");
        	return badRequest(login.render(userForm));						
		}else{
			userA = userForm.get();
			String email = userA.getEmail();
			String senha = userA.getSenha();

	        if (!validate(email, senha)) {
	        	flash("fail", "Email ou Senha Inválidos");
	        	return badRequest(login.render(userForm));
	        } else {
	        	Usuario user = (Usuario) dao.findByAttributeName(
	        			"Usuario", "email", userA.getEmail()).get(0);
	            session().clear();
	            session("email", user.getEmail());
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
	@Transactional
	public static Result show() {
		return ok(registro.render(userForm));
	}

	@Transactional
	public static Result registrar(){

		Form<Usuario> registroPessoa = userForm.bindFromRequest();
		Usuario usuario;

		if (userForm.hasErrors()) {
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
