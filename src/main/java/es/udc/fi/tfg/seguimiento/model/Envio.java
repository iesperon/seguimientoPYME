package es.udc.fi.tfg.seguimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="ENVIO")
public class Envio {

	private Long idEnvio;
	private String nombre;
	private String calle;
	private String numero;
	private String puerta;
	private String cp;
	private String provincia;
	private String poblacion;
	private String pais;
	private String empresa;
	private String numSeguimiento;
	private String estado;
	private Centro centro;
	private Ticket ticket;
	
	public Envio(){
		
	}

	public Envio(String nombre, String calle, String numero, String puerta, String cp, String provincia,
			String poblacion, String pais, String empresa, String numSeguimiento, String estado, Centro centro) {
		super();
		this.nombre = nombre;
		this.calle = calle;
		this.numero = numero;
		this.puerta = puerta;
		this.cp = cp;
		this.provincia = provincia;
		this.poblacion = poblacion;
		this.pais = pais;
		this.empresa = empresa;
		this.numSeguimiento = numSeguimiento;
		this.estado = estado;
		this.centro = centro;
	}

	@Id 
	@SequenceGenerator(name="envioId", sequenceName = "id_envio_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="envioId")
	@Column (name="idEnvio")	
	public Long getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(Long idEnvio) {
		this.idEnvio = idEnvio;
	}

	@Column(name="nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="calle")
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column(name="numero")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name="puerta")
	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	@Column(name="cp")
	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column(name="provincia")
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Column(name="poblacion")
	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	@Column(name="pais")
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Column(name="empresa")
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Column(name="numSeguimiento")
	public String getNumSeguimiento() {
		return numSeguimiento;
	}

	public void setNumSeguimiento(String numSeguimiento) {
		this.numSeguimiento = numSeguimiento;
	}

	@Column(name="estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@ManyToOne
	@JoinColumn(name="idCentro")
	@Cascade({CascadeType.SAVE_UPDATE})
	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	
	@OneToOne
	@JoinColumn(name="idTicket")
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	
	
	
}
