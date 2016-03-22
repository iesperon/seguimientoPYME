package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Gasto;

public interface ContabilidadService {

	//************GASTOS******************
	
	//Registro de un gasto
	public void registroGasto (Gasto migasto);
	//Eliminacion de un gasto
	public void eliminarGasto (Gasto migasto);
	//Actualizar un gasto
	public void actualizarGasto (Gasto migasto);
	//Buscar gasto por Id
	public Gasto buscarGastoPorId (Long miid);
	//Buscar gasto por estado
	public List<Gasto> buscarGastoPorEstado(String miestado);
	//Buscar gasto por concepto
	public List<Gasto> buscarGastoPorConcepto (String miconcepto);
}
