package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Ticket;

public interface TicketDAO {

	public Long create(Ticket miticket);
	public void remove(Ticket miticket);
	public void update(Ticket miticket);
	public List<Ticket> findAll();
	public Ticket findById(Long miid);
	public List<Ticket> findByFormaPago(String formaPago);
	
	
}
