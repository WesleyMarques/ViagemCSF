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

	/**
	 * @return o email do usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
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
	public void setSenha(String senha) {
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
	public void setNome(String nome) {
		this.nome = nome;
	}
}
