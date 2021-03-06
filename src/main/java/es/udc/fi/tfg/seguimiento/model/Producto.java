package es.udc.fi.tfg.seguimiento.model;

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

@Entity
@Table(name="PRODUCTO")
public class Producto {

	private Long idProducto;
	private String codProd;
	private String nombre;
	private Double precio;
	private String marca;
	private String descripcion;
	private String foto;
	private Double descuento;
	private Iva iva;
	private Empresa empresa;
	private Boolean enable;
	private Set<Stock> stock = new HashSet<Stock>();

	
	
	public Producto(){
		
	}
	
	public Producto(String codProd, String nombre, Double precio, String marca, String descripcion, String foto, Double descuento, Iva iva, Empresa empresa){
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
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
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
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Column (name="descuento")
	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idIva")
	public Iva getIva() {
		return iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idEmpresa", nullable = false)
	//@Cascade({CascadeType.SAVE_UPDATE})
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@OneToMany(mappedBy="producto", fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	public Set<Stock> getStock() {
		return stock;
	}

	public void setStock(Set<Stock> stock) {
		this.stock = stock;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	

	
	
	
}
