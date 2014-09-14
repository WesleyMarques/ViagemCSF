/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Wesley
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ViagemStrategy {
	
	@Id
	@GeneratedValue
	private Long id;

}
