package es.udc.fi.tfg.seguimiento.utils;

import es.udc.fi.tfg.seguimiento.model.Envio;

public class FormTicketEnvio {

	private Long idTicket;
	private Envio envio;
	
	public FormTicketEnvio(){
		
	}

	public FormTicketEnvio(Long idTicket, Envio envio) {
		super();
		this.idTicket = idTicket;
		this.envio = envio;
	}

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}
	
	
}
