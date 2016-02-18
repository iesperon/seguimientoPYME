package es.udc.fi.tfg.model.adminservice;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.services.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class testEmpresa {
	@Autowired
	private AdminService adminService;
	
	public Empresa empresa1;
	public Empresa empresa2;
	public Empresa empresa3;
	
	public Centro centro1;
	public Centro centro2;
	
	@Test
	public void test() {
		
		empresa1 = new Empresa("Prueba1", "76933725J", "Alimentacion", "Empresa del sector de la alimentación", "ivanesperon@gmail.com", null);
		empresa2 = new Empresa("Prueba2", "66666666J", "Deportes", "Empresa del sector de los deportes", "ivan.esperon@udc.es", null);
		
		

		//Insertamos 
		adminService.registroEmpresa(empresa1);
		adminService.registroEmpresa(empresa2);
		
		//Buscamos por nombre (Buscamos alguna empresa que en su nombre tenga un '1')
		List<Empresa> milista = adminService.buscarEmpresaPorNombre("1");
		assertEquals(1, milista.size());
		
		//Obtenemos todos
		List<Empresa> milista2 = adminService.obtenerTodasEmpresas();
		assertEquals(2, milista2.size());
		
		//Buscamos por CIF
		assertEquals(empresa1, (Empresa) adminService.buscarEmpresaPorCif(empresa1.getCif()));
		
		//Modificamos una empresa 
		empresa1.setNombre("Hola1");
		adminService.actualizarEmpresa(empresa1);
		
		//Borramos los datos
		//adminService.eliminarEmpresa(empresa1);
		//adminService.eliminarEmpresa(empresa2);
		
		empresa3= (Empresa) adminService.buscarEmpresaPorCif(empresa1.getCif());
		centro1 = new Centro("T001", "C/ Sin nombre", "BAJO","36003","Pontevedra","Pontevedra","España","tienda@tienda.es","+34986105232",empresa3);
		centro2 = new Centro("T002", "C/ Del Centro", "10","36005","Pontevedra","Pontevedra","España","tiendanueva@tienda.es","+34626268512",empresa3);
		
		//Añadimos un centro
		adminService.registroCentro(centro1);
		adminService.registroCentro(centro2);
		
		
		
		
	}

}
