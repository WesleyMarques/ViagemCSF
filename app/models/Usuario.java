package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.format.Formats.NonEmpty;
import play.data.validation.Constraints.Required;

@Entity
public class Usuario {

	@Id
	@Required
	@NonEmpty
	private String email;
	
	@Required
	private String nome;

	@Required
	private String senha;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String nome, String email, String senha){
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	/**
	 * @return o email do usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) throws Exception{
		if (email == null){
			throw new Exception("Email nulo.");
		}
		this.email = email;
	}

	/**
	 * @return a senha do usuario
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 */
	public void setSenha(String senha) throws Exception {
		if (senha == null){
			throw new Exception("Senha nula");
		}
		this.senha = senha;
	}

	@Override
	public String toString() {
		return String.format(email);
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) throws Exception {
		if (nome == null){
			throw new Exception("Nome nulo");
		}
		this.nome = nome;
	}
}
