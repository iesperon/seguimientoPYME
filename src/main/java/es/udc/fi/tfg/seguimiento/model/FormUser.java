package es.udc.fi.tfg.seguimiento.model;

public class FormUser {

	private Usuario usuario;
	private Empresa empresa;
	private Centro centro;
	private Long idCentro;
	
	public FormUser(){ 
		
	}
	
	public FormUser(Usuario usuario, Empresa empresa){
		this.usuario=usuario;
		this.empresa=empresa;
	}
	
	public FormUser(Usuario usuario, Long idCentro){
		this.usuario=usuario;
		this.idCentro=idCentro;
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
