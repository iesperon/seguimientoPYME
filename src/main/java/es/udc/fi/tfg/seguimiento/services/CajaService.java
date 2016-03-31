package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.List;

import es.udc.fi.tfg.seguimiento.model.CierreCaja;

public interface CajaService {

	//**********Cierre de caja**************
	
	//Registro de un cierre de caja.
	public void registroCierre(CierreCaja micierre);
	//Eliminación de un cierre de caja
	public void eliminarCierre(CierreCaja micierre);
	//Actualizar cierre de caja
	public void actualizarCierre(CierreCaja micierre);
	//Buscar cierre por fecha
	public CierreCaja buscarCierrePorFecha(Timestamp mifecha);
	//Obtener todos los cierres 
	public List<CierreCaja> obtenerTodosCierres();
}
