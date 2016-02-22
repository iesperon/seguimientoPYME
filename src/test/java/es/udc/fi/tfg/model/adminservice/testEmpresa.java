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
import es.udc.fi.tfg.seguimiento.services.EmpresaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class testEmpresa {
	@Autowired
	private EmpresaService empresaService;
	
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
		empresaService.registroEmpresa(empresa1);
		empresaService.registroEmpresa(empresa2);
		
		//Buscamos por nombre (Buscamos alguna empresa que en su nombre tenga un '1')
		List<Empresa> milista = empresaService.buscarEmpresaPorNombre("1");
		assertEquals(1, milista.size());
		
		//Obtenemos todos
		List<Empresa> milista2 = empresaService.obtenerTodasEmpresas();
		assertEquals(2, milista2.size());
		
		//Buscamos por CIF
		assertEquals(empresa1, (Empresa) empresaService.buscarEmpresaPorCif(empresa1.getCif()));
		
		//Modificamos una empresa 
		empresa1.setNombre("Hola1");
		empresaService.actualizarEmpresa(empresa1);
		
	
		
		//empresa3= (Empresa) adminService.buscarEmpresaPorCif(empresa1.getCif());
		centro1 = new Centro("T001", "C/ Sin nombre", "BAJO","36003","Pontevedra","Pontevedra","España","tienda@tienda.es","+34986105232",empresa1);
		centro2 = new Centro("T002", "C/ Del Centro", "10","36005","Pontevedra","Pontevedra","España","tiendanueva@tienda.es","+34626268512",empresa1);
		
		//Añadimos un centro
		empresaService.registroCentro(centro1);
		empresaService.registroCentro(centro2);
		
		//Modificamos un centro
		centro1.setNombre("NUEVA_CENTRO!!!");
		empresaService.actualizarCentro(centro1);
		
		//Las listamos por empresa
		List<Centro> milista3 = (List<Centro>) empresaService.obtenerCentros(empresa1);
		assertEquals(2, milista3.size());
		empresa1.getCentro().add(centro1);
		empresa1.getCentro().add(centro2);
		
		//Borramos un centro
		empresaService.eliminarCentro(centro1);
		empresa1.getCentro().remove(centro1);
		//adminService.eliminarCentro(centro2);
		
		//Borramos los datos
		empresaService.eliminarEmpresa(empresa1);
		//adminService.eliminarEmpresa(empresa2);
		
		
	}

}
