package seguimientoPYME;

import static org.junit.Assert.*;

import org.apache.tiles.request.ApplicationContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import es.udc.fi.tfg.model.iva.Iva;
import es.udc.fi.tfg.model.services.AdminService;

//@ContextConfiguration(locations = {"spring-module.xml"})
public class testIva {
	@Autowired
	//@Qualifier("adminService")
	private AdminService adminService;

	public Iva alimentos;
	public Iva productos;
	
	@Test
	public void test() {
		
		alimentos = new Iva("Alimentos", null);
		productos = new Iva("Productos", null);
		
		
		adminService.registroIVA(alimentos);
		adminService.registroIVA(productos);
	
		adminService.eliminarIVA(alimentos);
		adminService.eliminarIVA(productos);
	}
}
