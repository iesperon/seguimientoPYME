package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Iva;

public interface AdminService {

	//IVA
	
	//Registro de un nuevo IVA. Si el IVA ya existe lanza una excepcion.
	void registroIVA(Iva miiva);
	//Elimina un IVA.
	void eliminarIVA (Iva miiva);
	//Actualizar los datos del IVA
	void actualizarIVA (Iva miiva);
	//Buscar el IVA metiendo el porcentaje
	Iva buscarIvaPorPorcentaje (Integer miporcentaje);
	//Obtener todos los IVAs
	List<Iva> obtenerTodosIva();
	
}
