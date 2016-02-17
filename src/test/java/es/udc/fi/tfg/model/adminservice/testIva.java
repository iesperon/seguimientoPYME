package es.udc.fi.tfg.model.adminservice;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.fi.tfg.seguimiento.model.Iva;
import es.udc.fi.tfg.seguimiento.services.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class testIva {
	
	@Autowired
	private AdminService adminService;

	public Iva alimentos;
	public Iva productos;
	
	@Test
	public void test() {
		
		alimentos = new Iva("Alimentos", 10);
		productos = new Iva("Productos", 21);
				
		//Insertamos IVAs
		adminService.registroIVA(alimentos);
		adminService.registroIVA(productos);
		
		//Buscamos los IVAs
		List<Iva> milista = adminService.obtenerTodosIva();
		assertEquals(2, milista.size());
		
		//Buscamos un IVA
		assertEquals(alimentos, (Iva) adminService.buscarIvaPorPorcentaje(alimentos.getPorcentaje()));
		
		//Modificamos un IVA
		productos.setNombre("Productos Nuevos");
		productos.setPorcentaje(18);
		adminService.actualizarIVA(productos);
		
		//Borramos los datos
		adminService.eliminarIVA(alimentos);
		adminService.eliminarIVA(productos);
	}
}
