package es.udc.fi.tfg.seguimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EMPRESA")
public class Empresa {
	
	private Long id_empresa;
	private String nombre;
	private String cif;
	private String sector;
	private String descripcion;
	private String email;
	private byte[] logo; 

	public Empresa(){
	}
	
	public Empresa(String nombre, String cif, String sector, String descripcion, String email, byte[] logo){
		this.nombre = nombre;
		this.cif = cif;
		this.sector = sector;
		this.descripcion = descripcion;
		this.email = email;
		this.logo = logo;
	}

	@Id
	@SequenceGenerator(name="empresaId",sequenceName = "id_empresa_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="empresaId")
	@Column(name="id_empresa")
	public Long getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Long id_empresa) {
		this.id_empresa = id_empresa;
	}

	@Column (name="nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column (name="cif", nullable = false, unique = true)
	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	@Column (name="sector")
	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}
	
	@Column (name="descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column (name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column (name="logo")
	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Empresa other = (Empresa) obj;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	//*********Falta el toString que hay que meter el nombre del administrador
	
	
}
