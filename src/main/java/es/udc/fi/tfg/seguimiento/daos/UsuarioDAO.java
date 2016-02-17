package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;
import javax.management.InstanceNotFoundException;

import es.udc.fi.tfg.seguimiento.model.Usuario;

public interface UsuarioDAO {

	public Usuario find (String login) throws InstanceNotFoundException;
	
	public List<Usuario> findAll();
	
	public Usuario insert (Usuario usuario);
	
	public Usuario update (Usuario usuario);
	
	public void remove (String login);
}
