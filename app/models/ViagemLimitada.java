/**
 * 
 */
package models;

import java.sql.Date;

import javax.persistence.Entity;

import play.data.validation.Constraints.Required;

/**
 * @author Wesley
 *
 */
@Entity
public class ViagemLimitada extends Viagem{
	
	@Required
	private String senha;

	/**
	 * 
	 */
	public ViagemLimitada(Local local, Date data, String descricao, String senha) {
		super(local, data, descricao);
		this.senha = senha;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}