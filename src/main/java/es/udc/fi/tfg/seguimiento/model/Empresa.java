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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="EMPRESA")
public class Empresa {
	
	private Long idEmpresa;
	private String nombre;
	private String cif;
	private String sector;
	private String descripcion;
	private String email;
	private String logo; 
	private Set<Centro> centro = new HashSet<Centro>();
	private Set<Producto> producto = new HashSet<Producto>();
	private Set<Gasto> gasto =  new HashSet<Gasto>();
	private Set<Proveedor> proveedor = new HashSet<Proveedor>();
	//private Set<PedidoProveedor> pedido = new HashSet<PedidoProveedor>();
	private Usuario administrador;
	

	public Empresa(){
	}
	
	//Constructor sin administrador para el test
	public Empresa(String nombre, String cif, String sector, String descripcion, String email, String logo){
		this.nombre = nombre;
		this.cif = cif;
		this.sector = sector;
		this.descripcion = descripcion;
		this.email = email;
		this.logo = logo;
	}
	
	public Empresa(String nombre, String cif, String sector, String descripcion, String email, String logo, Usuario administrador){
		this.nombre = nombre;
		this.cif = cif;
		this.sector = sector;
		this.descripcion = descripcion;
		this.email = email;
		this.logo = logo;
		this.administrador=administrador;
	}
	
	@Id
	@SequenceGenerator(name="empresaId",sequenceName = "id_empresa_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="empresaId")
	@Column(name="idEmpresa")
	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Column (name="nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column (name="cif", nullable = false, unique = true)
	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	@Column (name="sector")
	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}
	
	@Column (name="descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column (name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column (name="logo")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@OneToMany(mappedBy="empresa", fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	public Set<Centro> getCentro() {
		return centro;
	}

	public void setCentro(Set<Centro> centro) {
		this.centro = centro;
	}
	
	@OneToMany(mappedBy="empresa", fetch=FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	public Set<Gasto> getGasto() {
		return gasto;
	}

	public void setGasto(Set<Gasto> gasto) {
		this.gasto = gasto;
	}
	
	@OneToMany(mappedBy="empresa", fetch=FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	public Set<Proveedor> getProveedor() {
		return proveedor;
	}

	public void setProveedor(Set<Proveedor> proveedor) {
		this.proveedor = proveedor;
	}
	
/*	@OneToMany(mappedBy="empresa", fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	public Set<PedidoProveedor> getPedido() {
		return pedido;
	}

	public void setPedido(Set<PedidoProveedor> pedido) {
		this.pedido = pedido;
	}*/

	@OneToOne
	@JoinColumn(name="idAdmin")
	public Usuario getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Usuario administrador) {
		this.administrador = administrador;
	}
	
	@OneToMany(mappedBy="empresa", fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	public Set<Producto> getProducto() {
		return producto;
	}

	public void setProducto(Set<Producto> producto) {
		this.producto = producto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	
}
