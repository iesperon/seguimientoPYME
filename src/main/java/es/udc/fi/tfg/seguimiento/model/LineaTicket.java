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
@Table(name="LINEATICKET")
public class LineaTicket {

	private Long idLineaTicket;
	private Integer cantidad;
	private Float precio;
	private Integer iva;
	private Producto producto;
	private Ticket ticket;
	
	public LineaTicket(){
		
	}
	
	public LineaTicket(Integer cantidad, Float precio, Integer iva, Producto producto, Ticket ticket) {
		super();
		this.cantidad = cantidad;
		this.precio = precio;
		this.iva = iva;
		this.producto = producto;
		this.ticket = ticket;
	}
	
	@Id
	@SequenceGenerator(name="lineaTicketId",sequenceName = "id_lineaTicket_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lineaTicketId")
	@Column(name="idLineaTicket")
	public Long getIdLineaTicket() {
		return idLineaTicket;
	}

	public void setIdLineaTicket(Long idLineaTicket) {
		this.idLineaTicket = idLineaTicket;
	}
	
	@Column (name="cantidad")
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Column (name="precio")
	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	@Column (name="iva")
	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idProducto")
	//@Cascade({CascadeType.SAVE_UPDATE})
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idTicket")
	//@Cascade({CascadeType.SAVE_UPDATE})
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	
	
	
	
}
