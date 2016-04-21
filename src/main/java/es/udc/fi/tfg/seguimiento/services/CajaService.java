package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.List;

import es.udc.fi.tfg.seguimiento.model.CierreCaja;
import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.Ticket;

public interface CajaService {

	//**********Cierre de caja**************
	
	//Registro de un cierre de caja.
	public void registroCierre(CierreCaja micierre);
	//Eliminación de un cierre de caja
	public void eliminarCierre(CierreCaja micierre);
	//Actualizar cierre de caja
	public void actualizarCierre(CierreCaja micierre);
	//Buscar cierre por fecha
	public CierreCaja buscarCierrePorFecha(Timestamp mifecha);
	//Obtener todos los cierres 
	public List<CierreCaja> obtenerTodosCierres();
	
	
	//******************** TICKET *******************
	//Registro de un ticket 
	public void registroTicket (Ticket miticket);
	//Eliminacion de un ticket
	public void eliminarTicket (Ticket miticket);
	//Actualizar ticket
	public void actualizarTicket (Ticket miticket);
	//Buscar todos los ticket
	public List<Ticket> obtenerTickets();
	//Buscar ticket por id
	public Ticket buscarTicketPorId(Long miid);
	
	//******************* LINEA DE TICKET ***************
	//Registro de una linea de ticket 
	public void registroLineaTicket (LineaTicket milineaticket);
	//Eliminacion de una linea de ticket
	public void eliminarLineaTicket (LineaTicket milineaticket);
	//Actualizar una linea de ticket
	public void actualizarLineaTicket (LineaTicket milineaticket);
	//Buscar todos las lineas ticket
	public List<LineaTicket> obtenerLineaTickets();
	//Buscar linea de ticket por id
	public LineaTicket buscarLineaTicketPorId(Long miid);
}
