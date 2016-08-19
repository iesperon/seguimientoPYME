package es.udc.fi.tfg.model.servicetest;

import static org.junit.Assert.*;

import javax.management.InstanceNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.fi.tfg.seguimiento.model.CierreCaja;
import es.udc.fi.tfg.seguimiento.model.Ticket;
import es.udc.fi.tfg.seguimiento.services.CajaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class CajaServiceTest {
	@Autowired
	private CajaService cajaService;
	
	public Ticket ticket1;
	 
	@Test
	public void testbuscarTicketPorId() throws InstanceNotFoundException{
		
		ticket1 = new Ticket();
		ticket1.setIdTicket(new Long(1));
		ticket1.setFormaPago("Prueba");
		cajaService.registroTicket(ticket1);
		Ticket ticket = cajaService.buscarTicketPorId(new Long(1));	 
		assertEquals(ticket1.getFormaPago(), ticket.getFormaPago());
				
	}
	
	@Test(expected=InstanceNotFoundException.class)
	public void testbuscarTicketPorIdM() throws InstanceNotFoundException{
		cajaService.buscarTicketPorFormaPago("Fallo");
	}
}
