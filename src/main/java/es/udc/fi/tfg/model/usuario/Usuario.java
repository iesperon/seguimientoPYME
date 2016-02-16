package es.udc.fi.tfg.model.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fi.tfg.model.tienda.Tienda;

@Entity
@PrimaryKeyJoinColumn(name="id_usuario")
@Table(name="USUARIOS")
public class Usuario {

	private Long id_usuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String dni;
	private String contrasena;
	private Boolean admin;
	private Tienda tienda; 
	
	public Usuario(){
	}

	public Usuario(String nombre, String apellido1, String apellido2, String email, String dni, 
			String contrasena, Boolean admin, Tienda tienda ){
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.email=email;
		this.dni=dni;
		this.contrasena=contrasena;
		this.admin=admin;
		this.tienda=tienda;
	}
	
	@Id 
	@SequenceGenerator(name="usuarioID", sequenceName = "id_usuario_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="usuarioID")
	@Column (name="id_usuario")
	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
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

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="dni")
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Column(name="contrasena")
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Column(name="admin")
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	@ManyToOne
	@JoinColumn(name="tienda")
	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}



	
	
}
