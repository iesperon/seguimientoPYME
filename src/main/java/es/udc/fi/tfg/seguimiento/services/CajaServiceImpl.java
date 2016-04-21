package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.CierreCajaDAO;
import es.udc.fi.tfg.seguimiento.daos.LineaTicketDAO;
import es.udc.fi.tfg.seguimiento.daos.TicketDAO;
import es.udc.fi.tfg.seguimiento.model.CierreCaja;
import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.Ticket;

@Service
@Transactional
public class CajaServiceImpl implements CajaService {

	@Autowired
	private CierreCajaDAO cierreCajaDAO = null;
	
	public CierreCajaDAO getCierreCajaDAO() {
		return cierreCajaDAO;
	}

	public void setCierreCajaDAO(CierreCajaDAO cierreCajaDAO) {
		this.cierreCajaDAO = cierreCajaDAO;
	}
	
	@Autowired
	private TicketDAO ticketDAO = null;
	
	public TicketDAO getTicketDAO() {
		return ticketDAO;
	}

	public void setTicketDAO(TicketDAO ticketDAO) {
		this.ticketDAO = ticketDAO;
	}
	
	@Autowired
	private LineaTicketDAO lineaTicketDAO = null;
	

	public LineaTicketDAO getLineaTicketDAO() {
		return lineaTicketDAO;
	}

	public void setLineaTicketDAO(LineaTicketDAO lineaTicketDAO) {
		this.lineaTicketDAO = lineaTicketDAO;
	}

	// **********CIERRE***********
	public void registroCierre(CierreCaja micierre) {
		cierreCajaDAO.create(micierre);
	}

	public void eliminarCierre(CierreCaja micierre) {
		cierreCajaDAO.remove(micierre);
	}

	public void actualizarCierre(CierreCaja micierre) {
		cierreCajaDAO.update(micierre);
	}

	public CierreCaja buscarCierrePorFecha(Timestamp mifecha) {
		return cierreCajaDAO.finByFecha(mifecha);
	}

	public List<CierreCaja> obtenerTodosCierres() {
		return cierreCajaDAO.findAll();
	}

	//****************** TICKET *****************
	
	public void registroTicket(Ticket miticket) {
		ticketDAO.create(miticket);
	}

	public void eliminarTicket(Ticket miticket) {
		ticketDAO.remove(miticket);
	}

	public void actualizarTicket(Ticket miticket) {
		Ticket ticketMod = ticketDAO.findById(miticket.getIdTicket());
		ticketMod.setCambio(miticket.getCambio());
		ticketMod.setCentro(miticket.getCentro());
		ticketMod.setCierreCaja(miticket.getCierreCaja());
		ticketMod.setEntregado(miticket.getEntregado());
		ticketMod.setFecha(miticket.getFecha());
		ticketMod.setFormaPago(miticket.getFormaPago());
		ticketMod.setLineaTicket(miticket.getLineaTicket());
		ticketMod.setSubtotal(miticket.getSubtotal());
		ticketMod.setTotal(miticket.getTotal());
		
		ticketDAO.update(ticketMod);
	}

	public List<Ticket> obtenerTickets() {
		return ticketDAO.findAll();
	}

	public Ticket buscarTicketPorId(Long miid) {
		return ticketDAO.findById(miid);
	}

	//********************** LINEA TICKET *******************
	
	public void registroLineaTicket(LineaTicket milineaticket) {
		lineaTicketDAO.create(milineaticket);
	}

	public void eliminarLineaTicket(LineaTicket milineaticket) {
		lineaTicketDAO.remove(milineaticket);
	}

	public void actualizarLineaTicket(LineaTicket milineaticket) {
		LineaTicket lineaMod = lineaTicketDAO.findById(milineaticket.getIdLineaTicket());
		lineaMod.setCantidad(milineaticket.getCantidad());
		lineaMod.setIva(milineaticket.getIva());
		lineaMod.setPrecio(milineaticket.getPrecio());
		lineaMod.setProducto(milineaticket.getProducto());
		lineaMod.setTicket(milineaticket.getTicket());
		
		lineaTicketDAO.update(lineaMod);
	}

	public List<LineaTicket> obtenerLineaTickets() {
		return lineaTicketDAO.findAll();
	}

	public LineaTicket buscarLineaTicketPorId(Long miid) {
		return lineaTicketDAO.findById(miid);
	}

}
