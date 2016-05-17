package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Envio;
import es.udc.fi.tfg.seguimiento.model.Ticket;

public interface EnvioDAO {

	public Long create(Envio mienvio);
	public void remove(Envio mienvio);
	public void update(Envio mienvio);
	public List<Envio> findAll();
	public Envio findById(Long miid);
	public Envio findByTicket(Ticket miticket);
	public List<Envio> findByCentro (Centro micentro);
}
