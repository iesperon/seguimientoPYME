package es.udc.fi.tfg.seguimiento.utils;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Usuario;

public class FormEmpresaAdmin {
	private Usuario usuario;
	private Empresa empresa;
	
	public FormEmpresaAdmin(){
		
	}
	
	public FormEmpresaAdmin(Usuario usuario, Empresa empresa) {
		super();
		this.usuario = usuario;
		this.empresa = empresa;
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
