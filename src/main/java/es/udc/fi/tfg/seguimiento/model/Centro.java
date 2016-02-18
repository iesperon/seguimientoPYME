package es.udc.fi.tfg.seguimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity
@Table(name="CENTRO")
public class Centro {

	private Long id_centro;
	private String nombre;
	private String calle;
	private String numero;
	private String cp;
	private String poblacion;
	private String provincia;
	private String pais;
	private String email;
	private String telefono;
	private Empresa empresa;
	
	public Centro(){
	}
	
	public Centro (String nombre, String calle, String numero, String cp, String poblacion, String provincia, String pais, String email, String telefono, Empresa empresa){
		this.nombre=nombre;
		this.calle=calle;
		this.numero=numero;
		this.cp=cp;
		this.poblacion=poblacion;
		this.provincia=provincia;
		this.pais=pais;
		this.email=email;
		this.telefono=telefono;
		this.empresa=empresa;
	}

	
	@Id
	@SequenceGenerator(name="centroId",sequenceName = "id_centro_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="centroId")
	@Column(name="id_centro")
	public Long getId_centro() {
		return id_centro;
	}

	public void setId_centro(Long id_centro) {
		this.id_centro = id_centro;
	}

	@Column (name="nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column (name="calle")
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column (name="numero")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column (name="cp")
	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column (name="poblacion")
	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	@Column (name="provincia")
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Column (name="pais")
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Column (name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column (name="telefono")
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_empresa", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE})
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Centro [id_centro=" + id_centro + ", nombre=" + nombre + ", calle=" + calle + ", numero=" + numero
				+ ", cp=" + cp + ", poblacion=" + poblacion + ", provincia=" + provincia + ", pais=" + pais + ", email="
				+ email + ", telefono=" + telefono + ", empresa=" + empresa.getNombre() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
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
		Centro other = (Centro) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	
	
}
