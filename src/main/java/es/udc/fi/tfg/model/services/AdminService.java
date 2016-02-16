package es.udc.fi.tfg.model.services;

import es.udc.fi.tfg.model.iva.Iva;

public interface AdminService {

	//IVA
	
	//Registro de un nuevo IVA. Si el IVA ya existe lanza una excepcion.
	void registroIVA(Iva miiva);
	//Elimina un IVA.
	void eliminarIVA (Iva miiva);
	
}
