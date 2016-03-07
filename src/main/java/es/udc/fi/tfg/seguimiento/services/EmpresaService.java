package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Usuario;

public interface EmpresaService {

	//**********Empresa**************
	
		//Registro de una empresa
		public void registroEmpresa(Empresa miempresa);
		//Eliminación de una empresa
		public void eliminarEmpresa(Empresa miempresa);
		//Actualizar los datos de una empresa
		public void actualizarEmpresa(Empresa miempresa);
		//Obtener todas las empresas
		public List<Empresa> obtenerTodasEmpresas();
		//Buscar empresa por cif
		public Empresa buscarEmpresaPorCif(String micif);
		//Buscar empresas por nombre
		public List<Empresa> buscarEmpresaPorNombre(String minombre);
		//Buscar empresa por empleado
		public Empresa buscarEmpresaPorAdmin(Usuario miusuario);
		
		
		
		//**********Centro**************
		
		//Registro de un centro
		public void registroCentro(Centro micentro);
		//Eliminación de un centro
		public void eliminarCentro(Centro micentro);
		//Actualizar los datos de un centro
		public void actualizarCentro(Centro micentro);
		//Buscar todos los centros de una empresa
		public List<Centro> obtenerCentros(Empresa miempresa);
		//Buscar centro por id
		public Centro buscarCentroPorId(Long miid);
}
