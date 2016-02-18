package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Cierre;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Iva;

public interface AdminService {

	//**********IVA**************
	
	//Registro de un nuevo IVA. Si el IVA ya existe lanza una excepcion.
	public void registroIVA(Iva miiva);
	//Elimina un IVA.
	public void eliminarIVA (Iva miiva);
	//Actualizar los datos del IVA
	public void actualizarIVA (Iva miiva);
	//Buscar el IVA metiendo el porcentaje
	public Iva buscarIvaPorPorcentaje (Integer miporcentaje);
	//Obtener todos los IVAs
	public List<Iva> obtenerTodosIva();
	
	
	//**********Cierre de caja**************
	
	//Registro de un cierre de caja.
	public void registroCierre(Cierre micierre);
	//Eliminación de un cierre de caja
	public void eliminarCierre(Cierre micierre);
	//Actualizar cierre de caja
	public void actualizarCierre(Cierre micierre);
	//Buscar cierre por fecha
	public Cierre buscarCierrePorFecha(Timestamp mifecha);
	//Obtener todos los cierres 
	public List<Cierre> obtenerTodosCierres();
	
	
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
	
	
	//**********Centro**************
	
	//Registro de un centro
	public void registroCentro(Centro micentro);
	//Eliminación de un centro
	public void eliminarCentro(Centro micentro);
	//Actualizar los datos de un centro
	public void actualizarCentro(Centro micentro);
	//Buscar todos los centros de una empresa
	public List<Centro> obtenerCentros(Empresa miempresa);
	
}
