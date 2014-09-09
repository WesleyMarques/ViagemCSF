/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;

/**
 * @author Wesley
 *
 */
@Entity
public class Local {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Required
	private String nome;

	@Required
	private String descricao;

	
	public Local() {
		// TODO Auto-generated constructor stub
	}
	
	public Local(String nome, String desc, int max){
		this.nome = nome;
		this.descricao = desc;
		
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
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

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return this.getNome()+"-"+this.getDescricao();
	}
}
