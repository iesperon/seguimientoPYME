package es.udc.fi.tfg.seguimiento.model;

import java.sql.Timestamp;

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
@Table(name="GASTO")
public class Gasto {
	
	private Long idGasto;
	private String concepto;
	private Float importe;
	private Timestamp fechaEmision;
	private Timestamp fechaPago;
	private String estado;
	private Empresa empresa;
	
	public Gasto (){
		
	}
	
	public Gasto(String concepto, Float importe, Timestamp fechaEmision, Timestamp fechaPago, String estado, Empresa empresa){
		this.concepto=concepto;
		this.empresa=empresa;
		this.estado=estado;
		this.fechaEmision=fechaEmision;
		this.fechaPago=fechaPago;
		this.importe=importe;
	}

	@Id 
	@SequenceGenerator(name="gastoId", sequenceName = "id_gasto_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gastoId")
	@Column (name="idGasto")
	public Long getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(Long idGasto) {
		this.idGasto = idGasto;
	}

	@Column(name="concepto")
	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	@Column(name="importe")
	public Float getImporte() {
		return importe;
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}

	@Column(name="fechaEmision")
	public Timestamp getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	@Column(name="fechaPago")
	public Timestamp getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Timestamp fechaPago) {
		this.fechaPago = fechaPago;
	}

	@Column(name="estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@ManyToOne
	@JoinColumn(name="idEmpresa")
	@Cascade({CascadeType.SAVE_UPDATE})
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
