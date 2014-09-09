/**
 * 
 */
package models;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;

/**
 * @author Wesley
 *
 */
public abstract class Viagem {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Required
	@ManyToOne
	private Local local;
	
	@Required
	private Date data;
	
	@Required
	private String descricao;
	
	/**
	 * @return the local
	 */
	public Local getLocal() {
		return local;
	}

	/**
	 * @param local the local to set
	 */
	public void setLocal(Local local) {
		this.local = local;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
