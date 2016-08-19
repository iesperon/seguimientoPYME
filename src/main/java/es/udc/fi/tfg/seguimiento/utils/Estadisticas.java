package es.udc.fi.tfg.seguimiento.utils;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Producto;

public class Estadisticas {

	private Producto Producto; 
	private Long cantidad;
	private Centro centro;
	private Double efectivoCentro;
	private Double tarjetaCentro;
	private Double totalCentro;
	
	
	public Producto getProducto() {
		return Producto;
	}
	public void setProducto(Producto producto) {
		Producto = producto;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public Estadisticas() {
		super();
	}
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	public Double getEfectivoCentro() {
		return efectivoCentro;
	}
	public void setEfectivoCentro(Double efectivoCentro) {
		this.efectivoCentro = efectivoCentro;
	}
	public Double getTarjetaCentro() {
		return tarjetaCentro;
	}
	public void setTarjetaCentro(Double tarjetaCentro) {
		this.tarjetaCentro = tarjetaCentro;
	}
	public Double getTotalCentro() {
		return totalCentro;
	}
	public void setTotalCentro(Double totalCentro) {
		this.totalCentro = totalCentro;
	}

	
	
	
}
