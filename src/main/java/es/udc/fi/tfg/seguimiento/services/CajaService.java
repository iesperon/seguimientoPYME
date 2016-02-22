package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Cierre;

public interface CajaService {

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
