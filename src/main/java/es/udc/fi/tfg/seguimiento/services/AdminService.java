package es.udc.fi.tfg.seguimiento.services;

import es.udc.fi.tfg.seguimiento.model.Iva;

public interface AdminService {

	//IVA
	
	//Registro de un nuevo IVA. Si el IVA ya existe lanza una excepcion.
	void registroIVA(Iva miiva);
	//Elimina un IVA.
	void eliminarIVA (Iva miiva);
	
}
