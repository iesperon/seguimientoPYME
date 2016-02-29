package es.udc.fi.tfg.seguimiento.model;

public class FormUser {

	private Usuario usuario;
	private Empresa empresa;
	
	public FormUser(){ 
		
	}
	
	public FormUser(Usuario usuario, Empresa empresa){
		this.usuario=usuario;
		this.empresa=empresa;
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
