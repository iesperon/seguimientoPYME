package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Iva;

public interface ProductoService {

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
}
