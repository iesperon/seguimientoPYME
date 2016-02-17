package es.udc.fi.tfg.seguimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IVA")
public class Iva {
	
	private Long id_iva;
	private String nombre;
	private Integer porcentaje;
	
	public Iva(){
	}
	
	public Iva(String nombre, Integer porcentaje){
		this.nombre=nombre;
		this.porcentaje=porcentaje;
	}
	
	@Id 
	@SequenceGenerator(name="ivaId", sequenceName = "id_iva_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ivaId")
	@Column (name="id_iva")
	public Long getId_iva() {
		return id_iva;
	}

	public void setId_iva(Long id_iva) {
		this.id_iva = id_iva;
	}

	@Column(name="nombre", unique = true)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="porcentaje", unique = true, nullable = false)
	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((porcentaje == null) ? 0 : porcentaje.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Iva [id_iva=" + id_iva + ", nombre=" + nombre + ", porcentaje=" + porcentaje + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Iva other = (Iva) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (porcentaje == null) {
			if (other.porcentaje != null)
				return false;
		} else if (!porcentaje.equals(other.porcentaje))
			return false;
		return true;
	}
	
	
	
}
