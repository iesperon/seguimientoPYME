package es.udc.fi.tfg.seguimiento.model;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="CIERRE")
public class CierreCaja {

	private Long idCierre;
	private Timestamp fecha;
	private Float efectivo;
	private Float tarjeta;
	private Float total;
	private String incidencias;
	private Float caja;
	private Float diferencia;
	private Set<Ticket> ticket = new HashSet<Ticket>();
	
	
	

	public CierreCaja(){
		
	}
	
	
	public CierreCaja(Long idCierre, Timestamp fecha, Float efectivo, Float tarjeta, Float total, String incidencias,
			Float caja, Float diferencia, Set<Ticket> ticket) {
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
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	@Column (name="efectivo")
	public Float getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(Float efectivo) {
		this.efectivo = efectivo;
	}

	@Column (name="tarjeta")
	public Float getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Float tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	@Column (name="total")
	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
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
	public Float getCaja() {
		return caja;
	}

	public void setCaja(Float caja) {
		this.caja = caja;
	}
	
	@Column (name="diferencia")
	public Float getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(Float diferencia) {
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
