package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Usuario;

public interface UserService {

	//************Usuario************
	
	//Registro de un usuario 
	public void registroUsuario(Usuario miusuario);
	//Eliminación de un usuario
	public void eliminarUsuario(Usuario miusuario);
	//Actualizar los datos de un usuario
	public void actualizarUsuario(Usuario miusuario);
	//Buscar usuario dentro de un centro por nombre
	public List<Usuario> buscarUsuarioPorNombre (String minombre, Centro micentro);
	//Buscar un usuario por su dni dentro de un centro 
	public Usuario buscarUsuarioPorDni (String midni, Centro micentro);
	//Buscar todos los usuarios de una empresa
	public List<Usuario> buscarUsuarioPorEmpresa(Empresa miempresa);
	//Buscar todos los usuarios de un centro 
	public List<Usuario> buscarUsuarioPorCentro(Centro micentro);
	

	
}
