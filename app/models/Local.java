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

	
	public Local() {
		// TODO Auto-generated constructor stub
	}
	
	public Local(String nome){
		this.nome = nome;
		
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
	public void setNome(String nome) throws Exception {
		if (nome == null){
			throw new Exception("Nome nulo.");
		}
		this.nome = nome;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Local other = (Local) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
}
