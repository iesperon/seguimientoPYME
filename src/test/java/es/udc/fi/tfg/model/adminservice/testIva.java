package es.udc.fi.tfg.model.adminservice;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.model.iva.Iva;
import es.udc.fi.tfg.model.services.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
@Transactional
public class testIva {
	
	@Autowired
	private AdminService adminService;

	public Iva alimentos;
	public Iva productos;
	
	@Test
	public void test() {
		
		alimentos = new Iva("Alimentos", 10);
		productos = new Iva("Productos", 21);
		
		
		System.out.println("HOLAAAAAAA");
		adminService.registroIVA(alimentos);
		System.out.println("PRUEBAAAAAAAAAAAAAA");
		adminService.registroIVA(productos);
		System.out.println("QQQQQ");
		
		//adminService.eliminarIVA(alimentos);
		//adminService.eliminarIVA(productos);
	}
}
