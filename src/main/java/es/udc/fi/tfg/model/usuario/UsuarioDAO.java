package es.udc.fi.tfg.model.usuario;

import java.util.List;
import javax.management.InstanceNotFoundException;

public interface UsuarioDAO {

	public Usuario find (String login) throws InstanceNotFoundException;
	
	public List<Usuario> findAll();
	
	public Usuario insert (Usuario usuario);
	
	public Usuario update (Usuario usuario);
	
	public void remove (String login);
}
