package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.Ticket;


public interface LineaTicketDAO {

	public Long create(LineaTicket milineaticket);
	public void remove(LineaTicket milineaticket);
	public void update(LineaTicket milineaticket);
	public List<LineaTicket> findAll();
	public List<LineaTicket> findByTicket (Ticket miticket);
	public LineaTicket findById(Long miid);
}
