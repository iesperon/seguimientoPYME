package es.udc.fi.tfg.seguimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="ROLES")
public class Rol {

	private Long idRol;
	private Usuario usuario;
	private String rol;
	private String email;
	


	public Rol(){
		
	}
	
	public Rol (Usuario usuario, String rol, String email){
		this.usuario=usuario;
		this.rol=rol;
		this.email=email;
	}
	
	@Id 
	@SequenceGenerator(name="rolId", sequenceName = "id_rol_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="rolId")
	@Column (name="idRol")
	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	
	@OneToOne
	@JoinColumn(name="idUsuario")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="rol")
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
