package es.udc.fi.tfg.seguimiento.utils;

import es.udc.fi.tfg.seguimiento.model.LineaTicket;

public class FormTicketLinea {

	private Long idTicket;
	private LineaTicket linea;
	
	public FormTicketLinea(){
		
	}

	public FormTicketLinea(Long idTicket, LineaTicket linea) {
		super();
		this.idTicket = idTicket;
		this.linea = linea;
	}

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

	public LineaTicket getLinea() {
		return linea;
	}

	public void setLinea(LineaTicket linea) {
		this.linea = linea;
	}
	
	
}
