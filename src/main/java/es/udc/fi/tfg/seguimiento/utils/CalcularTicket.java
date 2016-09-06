package es.udc.fi.tfg.seguimiento.utils;

import java.util.ArrayList;
import java.util.List;

import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.Ticket;

public class CalcularTicket {
	
	private Ticket ticket;
	private LineaTicket lineaticket;
	
	public CalcularTicket(){
		
	}

	public CalcularTicket(Ticket ticket) {
		super();
		this.ticket = ticket;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	public LineaTicket getLineaticket() {
		return lineaticket;
	}

	public void setLineaticket(LineaTicket lineaticket) {
		this.lineaticket = lineaticket;
	}
	


	

	


}
