/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import play.data.validation.Constraints.Required;

/**
 * @author Wesley
 *
 */
@Entity
public class ViagemLimitada extends ViagemStrategy{
	
	@Required
	private String senha;

	/**
	 * 
	 */
	public ViagemLimitada(String senha) {
		this.senha = senha;
	}
	
	public ViagemLimitada() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the senha
	 */
	@Override
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Autentica ou nao a senha da viagem para que o usuario participe
	 * @param senha
	 * @return Verdadeiro, se a senha passada for igual a senha definida pelo criador da viagem.
	 * 		   Falso, se a senha passada for diferente a senha definida pelo criador da viagem.
	 */
	public boolean senhaDaViagemEstaCorreta(String senha){
		return senha.equals(this.senha);
	}
}
