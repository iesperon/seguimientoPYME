package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.LineaTicket;


public interface LineaTicketDAO {

	public Long create(LineaTicket milineaticket);
	public void remove(LineaTicket milineaticket);
	public void update(LineaTicket milineaticket);
	public List<LineaTicket> findAll();
	public LineaTicket findById(Long miid);
}
