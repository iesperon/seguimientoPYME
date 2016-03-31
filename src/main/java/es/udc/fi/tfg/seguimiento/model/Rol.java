package es.udc.fi.tfg.seguimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="ROL")
public class Rol {

	private Long idRol;
	private String rol;
	


	public Rol(){
		
	}
	
	public Rol (String rol){
		this.rol=rol;
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
	
	@Column(name="rol")
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
