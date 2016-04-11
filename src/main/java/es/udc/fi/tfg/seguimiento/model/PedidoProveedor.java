package es.udc.fi.tfg.seguimiento.model;

import java.sql.Timestamp;

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
@Table(name="PEDIDOPROVEEDOR")
public class PedidoProveedor {
	
	private Long idPedidoProveedor;
	private Timestamp fechaCompra;
	private Timestamp fechaVencimiento;
	private Float importe;
	private String estado;
	private Empresa empresa;
	private Proveedor proveedor;
	
	public PedidoProveedor() {
		
	}
	
	
	public PedidoProveedor(Timestamp fechaCompra, Timestamp fechaVencimiento, Float importe, String estado,
			Empresa empresa, Proveedor proveedor) {
		super();
		this.fechaCompra = fechaCompra;
		this.fechaVencimiento = fechaVencimiento;
		this.importe = importe;
		this.estado = estado;
		this.empresa = empresa;
		this.proveedor = proveedor;
	}
	
	@Id
	@SequenceGenerator(name="pedidoproveedorId",sequenceName = "id_pedidoproveedor_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pedidoproveedorId")
	@Column(name="idPedidoProveedor")
	public Long getIdPedidoProveedor() {
		return idPedidoProveedor;
	}


	public void setIdPedidoProveedor(Long idPedidoProveedor) {
		this.idPedidoProveedor = idPedidoProveedor;
	}

	@Column (name="fechaCompra")
	public Timestamp getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Timestamp fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@Column (name="fechaVencimiento")
	public Timestamp getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Timestamp fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	@Column (name="importe")
	public Float getImporte() {
		return importe;
	}


	public void setImporte(Float importe) {
		this.importe = importe;
	}

	@Column (name="estado")
	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
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

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idProveedor", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE})
	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	
	

}