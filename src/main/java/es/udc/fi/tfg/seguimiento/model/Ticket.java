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
@Table(name="TICKET")
public class Ticket {

	public Long idTicket;
	@DateTimeFormat(iso = ISO.DATE)
	@Type(type="org.joda.time.contrib.hibernate.PersistentYearMonthDay")
    private Date fecha;
	private Double subtotal;
	private Double iva;
	private Double total;
	private Double entregado;
	private Double cambio;
	private String formaPago;
	private Centro centro;
	private CierreCaja cierreCaja;
	private Set<LineaTicket> lineaTicket = new HashSet<LineaTicket>();
	
	
	public Ticket(){
		
	}
	


	public Ticket(Long idTicket, Date fecha, Double subtotal, Double total, Double entregado, Double cambio,
			String formaPago, Centro centro, CierreCaja cierreCaja) {
		super();
		this.idTicket = idTicket;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.total = total;
		this.entregado = entregado;
		this.cambio = cambio;
		this.formaPago = formaPago;
		this.centro = centro;
		this.cierreCaja = cierreCaja;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idTicket")	
	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}
	
	@Column (name="fecha")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column (name="subtotal")
	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	@Column (name="total")
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column (name="cambio")
	public Double getCambio() {
		return cambio;
	}

	public void setCambio(Double cambio) {
		this.cambio = cambio;
	}

	@Column (name="formaPago")
	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	@Column (name="entregado")
	public Double getEntregado() {
		return entregado;
	}


	public void setEntregado(Double entregado) {
		this.entregado = entregado;
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

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idCierre")
	public CierreCaja getCierreCaja() {
		return cierreCaja;
	}

	public void setCierreCaja(CierreCaja cierreCaja) {
		this.cierreCaja = cierreCaja;
	}

	@OneToMany(mappedBy="ticket", fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	public Set<LineaTicket> getLineaTicket() {
		return lineaTicket;
	}


	public void setLineaTicket(Set<LineaTicket> lineaTicket) {
		this.lineaTicket = lineaTicket;
	}
	
	@Column (name="iva")
	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}


	
	
	
	
	

	
}
