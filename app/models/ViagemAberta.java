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
public class ViagemAberta extends ViagemStrategy{

	/**
	 * 
	 */
	public ViagemAberta() {
	}

	@Override
	public String getSenha() {
		return "";
	}
	
	

}
