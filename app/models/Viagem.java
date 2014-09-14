/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.Required;

/**
 * @author Wesley
 *
 */
@Entity
public class Viagem{
	
	@Id
	@GeneratedValue
	private long id;
	
	@Required
	@ManyToOne
	private Local local;
	
	@Required
	private Date data;
	
	@Required
	private String adminUsuario;
	
	@Required
	private String descricao;
	
	@Required
	@OneToOne
	private ViagemStrategy tipoDeViagem;
	
	@ManyToMany
	private List<Usuario> usuarios;

	@Required
	private String foto;
	
	

	public Viagem() {
		usuarios = new ArrayList<Usuario>();
	}
	
	
	
	/**
	 * 
	 * @param email
	 */
	public void setAdminUsuario(String email){
		this.adminUsuario = email;
		
	}
	
	/**
	 * @return the adminUsuario
	 */
	public String getAdminUsuario() {
		return adminUsuario;
	}

	/**
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}
	

	/**
	 * @return the local
	 */
	public Local getLocal() {
		return local;
	}

	/**
	 * @param local the local to set
	 */
	public void setLocal(Local local) throws Exception{
		if (local == null){
			throw new Exception("Local nulo.");
		}
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
	public void setData(Date data) throws Exception{
		if (data == null){
			throw new Exception("Data nula.");
		}
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
	public void setDescricao(String descricao) throws Exception{
		if (descricao == null){
			throw new Exception("Descricao nula.");
		}
		this.descricao = descricao;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * 
	 * @return list of users
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * 
	 * @param usuarios the list of users 
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * 
	 * @param usuario to add
	 */
	public void addUsuario(Usuario usuario){
		this.usuarios.add(usuario);
	}
	
	/**
	 * 
	 * @param usuario to remove
	 * @throws Exception if usuario is null, or usuario isn't into usuarios
	 */
	public void removeUsuario(Usuario usuario) throws Exception{
		if(!usuarios.contains(usuario)){
			throw new Exception("O usuario nao esta cadastrado nesta viagem.");
		}
		else if(usuario == null){
			throw new Exception("Usuario nulo.");
		}
		usuarios.remove(usuario);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viagem other = (Viagem) obj;
		if (id != other.id)
			return false;
		return true;
	}



	public void setFoto(String foto) {
		this.foto = foto;		
	}



	public void setEstrategia(ViagemStrategy viagem) {
		this.tipoDeViagem = viagem;
	}



	public ViagemStrategy getTipoDeViagem() {
		return tipoDeViagem;
	}
	
	public String dataFormatada(){
		return data.getDay()+"/"+data.getMonth()+"/"+(data.getYear()+1900);
	}

}
