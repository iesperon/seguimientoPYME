package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Cierre;
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
}
