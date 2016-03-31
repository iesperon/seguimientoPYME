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
	private Integer stockActual;
	private Integer stockMin;
	
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
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="idCentro")
	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	@ManyToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="idProducto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getStockActual() {
		return stockActual;
	}

	public void setStockActual(Integer stockActual) {
		this.stockActual = stockActual;
	}

	public Integer getStockMin() {
		return stockMin;
	}

	public void setStockMin(Integer stockMin) {
		this.stockMin = stockMin;
	}

	
}
