package es.udc.fi.tfg.seguimiento.model;

import java.util.Date;

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
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="GASTO")
public class Gasto {
	
	private Long idGasto;
	private String concepto;
	private Float importe;
	@DateTimeFormat(iso = ISO.DATE)
	@Type(type="org.joda.time.contrib.hibernate.PersistentYearMonthDay")
	private Date fechaEmision;
	@DateTimeFormat(iso = ISO.DATE)
	@Type(type="org.joda.time.contrib.hibernate.PersistentYearMonthDay")
	private Date fechaPago;
	private String estado;
	private Empresa empresa;
	
	public Gasto (){
		
	}
	
	public Gasto(String concepto, Float importe, Date fechaEmision, Date fechaPago, String estado, Empresa empresa){
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
	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	@Column(name="fechaPago")
	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
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
	//@Cascade({CascadeType.SAVE_UPDATE})
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
