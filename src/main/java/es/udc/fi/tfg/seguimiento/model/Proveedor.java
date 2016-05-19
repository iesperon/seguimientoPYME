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
@Table(name="PROVEEDOR")
public class Proveedor {
	
	private Long idProveedor;
	private String cif;
	private String nombre;
	private Empresa empresa;
	private Boolean enable;
	private Set<PedidoProveedor> pedido = new HashSet<PedidoProveedor>();;
	
	public Proveedor(){
		
	}

	public Proveedor(Long idProveedor, String cif, String nombre) {
		super();
		this.idProveedor = idProveedor;
		this.cif = cif;
		this.nombre = nombre;
	}

	@Id 
	@SequenceGenerator(name="proveedorId", sequenceName = "id_proveedor_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="proveedorId")
	@Column (name="idProveedor")
	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Column(name="cif", unique = true)
	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	@Column(name="nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@ManyToOne
	@JoinColumn(name="idEmpresa")
	//@Cascade({CascadeType.SAVE_UPDATE})
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@OneToMany(mappedBy="proveedor", fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	public Set<PedidoProveedor> getPedido() {
		return pedido;
	}

	public void setPedido(Set<PedidoProveedor> pedido) {
		this.pedido = pedido;
	}
	
	@Column(name="enable")
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	
	
}
