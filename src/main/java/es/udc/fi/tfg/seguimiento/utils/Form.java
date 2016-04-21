package es.udc.fi.tfg.seguimiento.utils;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Stock;
import es.udc.fi.tfg.seguimiento.model.Usuario;

public class Form {

	private Usuario usuario;
	private Empresa empresa;
	private Centro centro;
	private Producto producto;
	private Stock stock;
	private Long idCentro;
	private Long idIva;
	private Integer stockMin;
	

	public Form(){ 
		
	}
	
	public Form(Usuario usuario, Empresa empresa){
		this.usuario=usuario;
		this.empresa=empresa;
	}
	
	public Form(Usuario usuario, Long idCentro){
		this.usuario=usuario;
		this.idCentro=idCentro;
	}
	
	public Form(Producto producto, Long idIva){
		this.producto=producto;
		this.idIva=idIva;
	}
	
	public Form(Producto producto, Stock stock){
		this.producto=producto;
		this.stock=stock;
	}
	
	public Integer getStockMin() {
		return stockMin;
	}

	public void setStockMin(Integer stockMin) {
		this.stockMin = stockMin;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Long getIdIva() {
		return idIva;
	}

	public void setIdIva(Long idIva) {
		this.idIva = idIva;
	}

	public Long getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
