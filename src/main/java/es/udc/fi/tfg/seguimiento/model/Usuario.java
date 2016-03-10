package es.udc.fi.tfg.seguimiento.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="USUARIOS")
public class Usuario {

	private Long idUsuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String dni;
	private String contrasena;
	private Boolean enabled;
	private Centro centro;
	private Rol rol;
	
	public Usuario(){
	}

	public Usuario(String nombre, String apellido1, String apellido2, String email, String dni, 
			String contrasena, Boolean enabled, Centro centro, Rol rol ){
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.email=email;
		this.dni=dni;
		this.contrasena=contrasena;
		this.enabled=enabled;
		this.centro=centro;
		this.enabled=enabled;
		this.rol=rol;
	}
	
	
	
	@Id 
	@SequenceGenerator(name="usuarioId", sequenceName = "id_usuario_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="usuarioId")
	@Column (name="idUsuario")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Column(name="nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="apellido1")
	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	@Column(name="apellido2")
	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	@Column(name="email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="dni", nullable = false, unique = true)
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Column(name="contrasena", nullable=false)
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Column(name="enabled", nullable = false)
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	@ManyToOne
	@JoinColumn(name="idCentro")
	@Cascade({CascadeType.SAVE_UPDATE})
	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	
	@OneToOne
	@JoinColumn(name="idRol")
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Usuario other = (Usuario) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}


	
}
