package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.CierreCajaDAO;
import es.udc.fi.tfg.seguimiento.daos.EnvioDAO;
import es.udc.fi.tfg.seguimiento.daos.LineaTicketDAO;
import es.udc.fi.tfg.seguimiento.daos.TicketDAO;
import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.CierreCaja;
import es.udc.fi.tfg.seguimiento.model.Envio;
import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Ticket;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.utils.Cierre;
import es.udc.fi.tfg.seguimiento.utils.MailMail;

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

	@Autowired
	private EnvioDAO envioDAO = null;
	
	public EnvioDAO getEnvioDAO() {
		return envioDAO;
	}

	public void setEnvioDAO(EnvioDAO envioDAO) {
		this.envioDAO = envioDAO;
	}
	
	@Autowired
	private ApplicationContext context;

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
//
//	public CierreCaja buscarCierrePorFecha(Timestamp mifecha) {
//		return cierreCajaDAO.finByFecha(mifecha);
//	}
//
//	public List<CierreCaja> obtenerTodosCierres() {
//		return cierreCajaDAO.findAll();
//	}
	
	public List<CierreCaja> buscarCierrePorCentros(List<Centro> centros){
		List<CierreCaja> cierres = new ArrayList<CierreCaja>();
		for (Centro centro:centros){
			for(CierreCaja cierre:centro.getCierre()){
				cierres.add(cierre);
			}
		}
		return cierres;
	}

	@Override
	public Cierre registrarCierre(List<Ticket> tickets) {
		List<Ticket> ticketSinCierre = new ArrayList<Ticket>();
		Cierre cierre = new Cierre();
		for(Ticket miticket:tickets){
			if (miticket.getCierreCaja() ==null && miticket.getFecha()!=null){
				ticketSinCierre.add(miticket);
			}
		}
		Double tarjeta = 0.0;
		Double efectivo = 0.0;
		for(Ticket ticket:ticketSinCierre){
			if (ticket.getFormaPago().equals("Tarjeta")){
				tarjeta=tarjeta+ticket.getTotal();

			}else{
				efectivo=efectivo+ticket.getTotal();
			}
		}
		Double total = tarjeta + efectivo;
		cierre.setTarjeta(tarjeta);
		cierre.setEfectivo(efectivo);
		cierre.setTotal(total);
		return cierre;
	}
	
	@Override
	public void cerrarTicketsAbiertos(List<Ticket> tickets, CierreCaja cierre) {
		for(Ticket miticket:tickets){
			if (miticket.getCierreCaja() ==null){
				miticket.setCierreCaja(cierre);
				cerrarTicket(miticket);
			}
		}
		
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
		ticketMod.setCierreCaja(miticket.getCierreCaja());
		ticketMod.setEntregado(miticket.getEntregado());
		ticketMod.setFecha(miticket.getFecha());
		ticketMod.setFormaPago(miticket.getFormaPago());
		ticketMod.setLineaTicket(miticket.getLineaTicket());
		ticketMod.setSubtotal(miticket.getSubtotal());
		ticketMod.setTotal(miticket.getTotal());
		ticketMod.setIva(miticket.getIva());
		
		ticketDAO.update(ticketMod);
	}

//	public List<Ticket> obtenerTickets() {
//		return ticketDAO.findAll();
//	}

	public Ticket buscarTicketPorId(Long miid)  {
		return ticketDAO.findById(miid);
	}
	
	
	public List<Ticket> buscarTicketPorFormaPago(String formaPago) {
		return ticketDAO.findByFormaPago(formaPago);
		
	}

	
	public void cerrarTicket(Ticket ticket) {
		Ticket ticketMod = ticketDAO.findById(ticket.getIdTicket());
		ticketMod.setCierreCaja(ticket.getCierreCaja());
		ticketDAO.update(ticketMod);

	}
	
	@Override
	public List<Ticket> buscarTicketCentros(List<Centro> centros) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		for(Centro centro:centros){
			for(Ticket ticket:centro.getTicket()){
				tickets.add(ticket);
			}
		}
		return tickets;
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
		lineaMod.setDescuento(milineaticket.getDescuento());
		//lineaMod.setProducto(milineaticket.getProducto());
		//lineaMod.setTicket(milineaticket.getTicket());
		
		lineaTicketDAO.update(lineaMod);
	}

	public List<LineaTicket> obtenerLineaTickets() {
		return lineaTicketDAO.findAll();
	}

	public LineaTicket buscarLineaTicketPorId(Long miid) {
		return lineaTicketDAO.findById(miid);
	}
	
	
	public List<LineaTicket> buscarLineaPorTicket(Ticket miticket) {
		return lineaTicketDAO.findByTicket(miticket);
	}
	
	@Override
	public void addLinea(Ticket ticket, Producto producto) {
		LineaTicket linea = new LineaTicket();

		linea.setTicket(ticket);
		linea.setProducto(producto);
		linea.setCantidad(1);
		linea.setIva(producto.getIva().getPorcentaje());
		linea.setPrecio(producto.getPrecio());
		
		lineaTicketDAO.create(linea);
		
	}

	//************************* ENVIOS *************************

	public void registroEnvio(Envio mienvio) {
		envioDAO.create(mienvio);
	}

	public void eliminarEnvio(Envio mienvio) {
		envioDAO.remove(mienvio);
	}

	public void actualizarEnvio(Envio mienvio) {
		Envio envioMod = envioDAO.findById(mienvio.getIdEnvio());
		envioMod.setCalle(mienvio.getCalle());
		envioMod.setCp(mienvio.getCp());
		envioMod.setEmpresa(mienvio.getEmpresa());
		envioMod.setEstado(mienvio.getEstado());
		envioMod.setNombre(mienvio.getNombre());
		envioMod.setNumero(mienvio.getNumero());
		envioMod.setNumSeguimiento(mienvio.getNumSeguimiento());
		envioMod.setPais(mienvio.getPais());
		envioMod.setPoblacion(mienvio.getPoblacion());
		envioMod.setProvincia(mienvio.getProvincia());
		envioMod.setPuerta(mienvio.getPuerta());
		
		envioDAO.update(envioMod);
	}

	public List<Envio> obtenerEnvios() {
		return envioDAO.findAll();
	}

	public Envio buscarEnvioPorId(Long miid) {
		return envioDAO.findById(miid);
	}

	public Envio buscarEnvioPorTicket(Ticket miticket) {
		return envioDAO.findByTicket(miticket);
	}

	
	public List<Envio> buscarEnvioPorCentros(List<Centro> centros) {
		List<Envio> envios = new ArrayList<Envio>();
		for(Centro micentro:centros){
			List<Envio> envioCentro =  envioDAO.findByCentro(micentro);
			for (Envio envio:envioCentro){
				envios.add(envio);
			}
		}
		
		return envios;
	}

	public LineaTicket calcularDescuento(LineaTicket lineaticket){
//		List<LineaTicket> mislineas = new ArrayList<LineaTicket>();
//		for(LineaTicket milinea:lineaticket){
			LineaTicket milinea = buscarLineaTicketPorId(lineaticket.getIdLineaTicket());
			if(lineaticket.getDescuento()!=null){
				Double precioDesc = (milinea.getProducto().getPrecio()-((lineaticket.getDescuento()*milinea.getProducto().getPrecio())/100));
				lineaticket.setPrecio(precioDesc);
			}
//			mislineas.add(milinea);
			return lineaticket;

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
	
	public void EnviarNotificacion(Usuario miusuario, Centro micentro) {
		
			
		// @Service("crunchifyEmail") <-- same annotation you specified in CrunchifyEmailAPI.java
		//CrunchifyEmailAPI crunchifyEmailAPI = (CrunchifyEmailAPI) context.getBean("mailMail");
		MailMail mail = (MailMail) context.getBean("mailMail");
		String toAddr = miusuario.getEmail();
		String fromAddr = "mipymeon@gmail.com";
 
		// email subject
		String subject = "Cierre de caja "+micentro.getNombre();
 
		// email body
		String body = "Hola " + miusuario.getNombre()+" " + miusuario.getApellido1() + miusuario.getApellido2()  +", \n\n "
				+ "Le informamos que el centro: "+micentro.getNombre() +", "+ micentro.getCalle() +", "+ micentro.getPoblacion()+" ha realizado"
						+ " el cierre de caja diario y ya lo tiene disponible en la web. \n\n Un saludo \n\n MiPymeOnline";
		mail.sendMail(fromAddr, toAddr, subject, body);
	}









		

}
