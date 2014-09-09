/**
 * 
 */
package models;

import java.sql.Date;

import javax.persistence.Entity;

/**
 * @author Wesley
 *
 */
@Entity
public class ViagemAberta extends Viagem{

	/**
	 * 
	 */
	public ViagemAberta(Local local, Date data, String descricao) {
		super(local, data, descricao);
	}
	

}
