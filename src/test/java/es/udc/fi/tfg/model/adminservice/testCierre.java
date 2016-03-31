package es.udc.fi.tfg.model.adminservice;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.fi.tfg.seguimiento.model.CierreCaja;
import es.udc.fi.tfg.seguimiento.services.CajaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class testCierre {
	@Autowired
	private CajaService cajaService;
	
	public CierreCaja cierre1;

	@Test
	public void test() {
		
		cierre1 = new CierreCaja(new Timestamp(Calendar.getInstance().getTimeInMillis()), new Float(100.82) ,new Float(100.5), "Ninguna",new Float(210.00));
		
		//Insertamos 1 registro
		cajaService.registroCierre(cierre1);
		
		//Buscamos los cierres
		List<CierreCaja> milista = cajaService.obtenerTodosCierres();
		assertEquals(1, milista.size());
		
		//Modificamos un cierre
		cierre1.setEfectivo(new Float(300));
		cajaService.actualizarCierre(cierre1);
		
		//Borramos datos 
		cajaService.eliminarCierre(cierre1);
	
		
	}

}
