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
import es.udc.fi.tfg.seguimiento.model.Rol;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.EmpresaService;
import es.udc.fi.tfg.seguimiento.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class testEmpresa {
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private UserService usuarioService;
	
	public Empresa empresa1;
	public Empresa empresa2;
	
	public Centro centro1;
	public Centro centro2;
	
	public Usuario usuario1;
	public Usuario usuario2;
	public Usuario usuario3;
	
	public Rol rol1;
	public Rol rol2;
	
	@Test
	public void test() {
		
		//ROLES
		rol1 = new Rol("ADMIN_ROLE");
		usuarioService.registroRol(rol1);
		System.out.println("************************************* "+usuarioService.buscarRolPorRol("ADMIN_ROLE").getRol());
		
		System.out.println("********************************************************");
		
		rol2=new Rol("USER_ROLE");
		usuarioService.registroRol(rol2);
		
	
		
		//USUARIO ADMINISTRADOR
		
		usuario1 = new Usuario("Iván","Esperón","Cespón","ivanesperon@gmail.com","76933725J","76933725J",true,null,rol1);
				
		//Insertamos el usuario
		usuarioService.registroUsuario(usuario1);
		
		
		//EMPRESA
		
		empresa1 = new Empresa("Prueba1", "76933725J", "Alimentacion", "Empresa del sector de la alimentación", "ivanesperon@gmail.com", null,usuario1);
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
		
		
		//**********CENTROS
		centro1 = new Centro("T001", "C/ Sin nombre", "BAJO","36003","Pontevedra","Pontevedra","España","tienda@tienda.es","+34986105232",empresa1);
		centro2 = new Centro("T002", "C/ Del Centro", "10","36005","Pontevedra","Pontevedra","España","tiendanueva@tienda.es","+34626268512",empresa1);
		
		//Añadimos un centro
		empresaService.registroCentro(centro1);
		empresaService.registroCentro(centro2);
		
		//Modificamos un centro
		centro1.setNombre("NUEVO_CENTRO!!!");
		empresaService.actualizarCentro(centro1);
		
		//Las listamos por empresa
		List<Centro> milista3 = (List<Centro>) empresaService.obtenerCentros(empresa1);
		assertEquals(2, milista3.size());
		//empresa1.getCentro().add(centro1);
		//empresa1.getCentro().add(centro2);
		
		
		
		//***************USUARIOS EMPLEADOS
		usuario2 = new Usuario("Martín", "Gonzalez","Cespon","mart.gon@gmail.com","8541259H", "123456",true,centro1,rol2);
		centro1.getUsuario().add(usuario2);
		
		//INSERTAMOS 
		usuarioService.registroUsuario(usuario2);
		
		List<Usuario> milista4 = usuarioService.buscarUsuarioPorEmpresa(empresa1);
		System.out.println("***********************************************************"+milista4.get(0).getNombre());
		assertEquals(usuario2, milista4.get(0));
		//ACTUALIZAMOS 
		//usuario2.setCentro(centro2);
		//usuarioService.actualizarUsuario(usuario2);
		//centro1.getUsuario().remove(usuario2);
		//centro2.getUsuario().add(usuario2);
		
		
		//BUSQUEDAS
		//List<Usuario> milista4 = (List<Usuario>) usuarioService.buscarUsuarioPorCentro(centro2);
		//assertEquals(1, milista4.size());
		
		//assertEquals(usuario2, usuarioService.buscarUsuarioPorDni("541", centro2));
		
		
		//Borramos un centro
		//empresaService.eliminarCentro(centro1);
		//empresa1.getCentro().remove(centro1);
		//adminService.eliminarCentro(centro2);
		
		//Borramos los datos
		//empresaService.eliminarEmpresa(empresa1);
		//adminService.eliminarEmpresa(empresa2);
		
		//Borramos los usuarios
		//usuarioService.eliminarUsuario(usuario1);
		//usuarioService.eliminarUsuario(usuario2);
				
	}

}
