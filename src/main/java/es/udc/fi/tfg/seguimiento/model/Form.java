package es.udc.fi.tfg.seguimiento.model;

public class Form {

	private Usuario usuario;
	private Empresa empresa;
	private Centro centro;
	private Producto producto;
	private Stock stock;
	private Long idCentro;
	private Long idIva;
	
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
