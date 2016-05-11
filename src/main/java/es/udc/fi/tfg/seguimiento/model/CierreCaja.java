package es.udc.fi.tfg.seguimiento.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name="CIERRE")
public class CierreCaja {

	private Long idCierre;
	@DateTimeFormat(iso = ISO.DATE)
	@Type(type="org.joda.time.contrib.hibernate.PersistentYearMonthDay")
	private Date fecha;
	private Double efectivo;
	private Double tarjeta;
	private Double total;
	private String incidencias;
	private Double caja;
	private Double diferencia;
	private Set<Ticket> ticket = new HashSet<Ticket>();
	private Centro centro;

	
	

	public CierreCaja(){
		
	}
	
	
	public CierreCaja(Long idCierre, Date fecha, Double efectivo, Double tarjeta, Double total, String incidencias,
			Double caja, Double diferencia, Set<Ticket> ticket) {
		super();
		this.idCierre = idCierre;
		this.fecha = fecha;
		this.efectivo = efectivo;
		this.tarjeta = tarjeta;
		this.total = total;
		this.incidencias = incidencias;
		this.caja = caja;
		this.diferencia = diferencia;
		this.ticket = ticket;
	}




	@Id
	@SequenceGenerator(name="cierreId",sequenceName = "id_cierre_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cierreId")
	@Column(name="idCierre")
	public Long getIdCierre() {
		return idCierre;
	}

	public void setIdCierre(Long idCierre) {
		this.idCierre = idCierre;
	}

	@Column (name="fecha", nullable = false, unique = true)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Column (name="efectivo")
	public Double getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(Double efectivo) {
		this.efectivo = efectivo;
	}

	@Column (name="tarjeta")
	public Double getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Double tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	@Column (name="total")
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	@Column (name="incidencias")
	public String getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(String incidencias) {
		this.incidencias = incidencias;
	}
	
	@Column (name="caja")
	public Double getCaja() {
		return caja;
	}

	public void setCaja(Double caja) {
		this.caja = caja;
	}
	
	@Column (name="diferencia")
	public Double getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(Double diferencia) {
		this.diferencia = diferencia;
	}
	
	@OneToMany(mappedBy="cierreCaja", fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	public Set<Ticket> getTicket() {
		return ticket;
	}


	public void setTicket(Set<Ticket> ticket) {
		this.ticket = ticket;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idCentro")
	@Cascade({CascadeType.SAVE_UPDATE})
	public Centro getCentro() {
		return centro;
	}


	public void setCentro(Centro centro) {
		this.centro = centro;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
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
		CierreCaja other = (CierreCaja) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cierre [idCierre=" + idCierre + ", fecha=" + fecha + ", efectivo=" + efectivo + ", tarjeta=" + tarjeta
				+ ", total=" + total + ", incidencias=" + incidencias + ", diferencia=" + diferencia + "]";
	}

	
	
	
}
