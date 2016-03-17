package es.udc.fi.tfg.seguimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="STOCK")
public class Stock {
	
	private Long idStock;
	private Centro centro;
	private Producto producto;
	private Integer stock_actual;
	private Integer stock_min;
	
	public Stock(){
		
	}

	@Id
	@SequenceGenerator(name="stockId",sequenceName = "id_stock_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="stockId")
	@Column(name="idStock")
	public Long getIdStock() {
		return idStock;
	}


	public void setIdStock(Long idStock) {
		this.idStock = idStock;
	}


	@ManyToOne
	@Cascade({CascadeType.ALL})
	@JoinColumn(name="idCentro")
	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	@ManyToOne
	@Cascade({CascadeType.ALL})
	@JoinColumn(name="idProducto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getStock_actual() {
		return stock_actual;
	}

	public void setStock_actual(Integer stock_actual) {
		this.stock_actual = stock_actual;
	}

	public Integer getStock_min() {
		return stock_min;
	}

	public void setStock_min(Integer stock_min) {
		this.stock_min = stock_min;
	}

	
}
