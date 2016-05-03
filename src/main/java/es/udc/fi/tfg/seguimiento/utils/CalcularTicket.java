package es.udc.fi.tfg.seguimiento.utils;

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

	public Double calcularSubtotal(List<LineaTicket> lineaticket){
		Double subtotal = 0.0;
		Double totprod = 0.0;
		Double siniva = 0.0;
		for(LineaTicket milinea : lineaticket){
			siniva = milinea.getPrecio()/(1+(milinea.getIva()/100.0));
			totprod = siniva*milinea.getCantidad();
			subtotal = subtotal + totprod;
		}
		return subtotal;
	}
	
	public Double calcularTotal(List<LineaTicket> lineaticket){
		Double total = 0.0;
		Double totprod = 0.0;
		for(LineaTicket milinea : lineaticket){
			totprod = milinea.getCantidad()*milinea.getPrecio();
			total = total + totprod;
		}
		return total;
	}
	
	public Double calcularIVA(List<LineaTicket> lineaticket){
		Double siniva = 0.0;
		Double iva = 0.0;
		Double ivaunitario = 0.0;
		for(LineaTicket milinea : lineaticket){
		siniva = milinea.getPrecio()/(1+(milinea.getIva()/100.0));
		ivaunitario = milinea.getPrecio() - siniva;
		iva = iva + (ivaunitario * milinea.getCantidad());
		}
		return iva;
	}

}
