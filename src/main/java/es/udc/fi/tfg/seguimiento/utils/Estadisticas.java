package es.udc.fi.tfg.seguimiento.utils;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Proveedor;
import es.udc.fi.tfg.seguimiento.model.Usuario;

public class Estadisticas {

	private Producto Producto; 
	private Long cantidad;
	private Centro centro;
	private Double efectivoCentro;
	private Double tarjetaCentro;
	private Double totalCentro;
	private Double totalUsuario;
	private Usuario usuario;
	private Long pedidos;
	private Double totalPedido;
	private Proveedor proveedor;
	
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
	public Double getTotalUsuario() {
		return totalUsuario;
	}
	public void setTotalUsuario(Double totalUsuario) {
		this.totalUsuario = totalUsuario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Long getPedidos() {
		return pedidos;
	}
	public void setPedidos(Long pedidos) {
		this.pedidos = pedidos;
	}
	public Double getTotalPedido() {
		return totalPedido;
	}
	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	
	
	
}
