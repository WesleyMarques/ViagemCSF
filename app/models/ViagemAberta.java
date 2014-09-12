/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

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
	public ViagemAberta(Local local, Date data, String descricao, List<Usuario> usuarios) {
		super(local, data, descricao, usuarios);
	}
	

}
