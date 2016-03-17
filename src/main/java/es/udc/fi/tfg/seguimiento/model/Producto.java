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
@Table(name="PRODUCTO")
public class Producto {

	private Long idProducto;
	private String codProd;
	private String nombre;
	private Float precio;
	private String marca;
	private String descripcion;
	private byte[] foto;
	private Float descuento;
	private Iva iva;
	private Empresa empresa;
	
	
	public Producto(){
		
	}
	
	public Producto(String codProd, String nombre, Float precio, String marca, String descripcion, byte[] foto, Float descuento, Iva iva, Empresa empresa){
		this.codProd=codProd;
		this.nombre=nombre;
		this.precio=precio;
		this.marca=marca;
		this.descripcion=descripcion;
		this.foto=foto;
		this.descuento=descuento;
		this.iva=iva;
		this.empresa=empresa;
	}

	@Id
	@SequenceGenerator(name="productoId",sequenceName = "id_producto_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="productoId")
	@Column(name="idProducto")
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	@Column (name="codProd", nullable = false, unique = true)
	public String getCodProd() {
		return codProd;
	}

	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}

	@Column (name="nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column (name="precio")
	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	@Column (name="marca")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column (name="descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column (name="foto")
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Column (name="descuento")
	public Float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idIva")
	@Cascade({CascadeType.SAVE_UPDATE})
	public Iva getIva() {
		return iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idEmpresa", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE})
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
