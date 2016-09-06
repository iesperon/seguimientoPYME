package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.CierreCaja;
import es.udc.fi.tfg.seguimiento.model.Envio;
import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Ticket;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.utils.Cierre;

public interface CajaService {

	//**********Cierre de caja**************
	
	//Registro de un cierre de caja.
	public void registroCierre(CierreCaja micierre);
	//Eliminación de un cierre de caja
	public void eliminarCierre(CierreCaja micierre);
	//Actualizar cierre de caja
	public void actualizarCierre(CierreCaja micierre);
	//Buscar cierre por fecha
//	public CierreCaja buscarCierrePorFecha(Timestamp mifecha);
	//Obtener todos los cierres 
//	public List<CierreCaja> obtenerTodosCierres();
	public List<CierreCaja> buscarCierrePorCentros(List<Centro> centros);
	public Cierre registrarCierre(List<Ticket> tickets);
	public void cerrarTicketsAbiertos(List<Ticket> tickets, CierreCaja cierre);
	
	//******************** TICKET *******************
	//Registro de un ticket 
	public void registroTicket (Ticket miticket);
	//Eliminacion de un ticket
	public void eliminarTicket (Ticket miticket);
	//Actualizar ticket
	public void actualizarTicket (Ticket miticket);
	//Buscar todos los ticket
	//public List<Ticket> obtenerTickets();
	//Buscar ticket por id
	public Ticket buscarTicketPorId(Long miid);
	//public List<Ticket> buscarTicketPorCentros(List<Centro> centros);
	public List<Ticket> buscarTicketPorFormaPago (String formaPago);
	public void cerrarTicket(Ticket ticket);
	public List<Ticket> buscarTicketCentros(List<Centro> centros);
	
	
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
	public List<LineaTicket> buscarLineaPorTicket (Ticket miticket);
	public void addLinea (Ticket ticket, Producto producto);
	public LineaTicket calcularDescuento(LineaTicket lineaticket);	
	public Double calcularIVA(List<LineaTicket> lineaticket);
	public Double calcularTotal(List<LineaTicket> lineaticket);
	public Double calcularSubtotal(List<LineaTicket> lineaticket);
	
	//**************** ENVIOS *************************
	public void registroEnvio (Envio mienvio);
	public void eliminarEnvio (Envio mienvio);
	public void actualizarEnvio (Envio mienvio);
	public List<Envio> obtenerEnvios();
	public Envio buscarEnvioPorId (Long miid);
	public Envio buscarEnvioPorTicket (Ticket miticket);
	public List<Envio> buscarEnvioPorCentros (List<Centro> centros);
	
	public void EnviarNotificacion(Usuario miusuario, Centro micentro);
}
