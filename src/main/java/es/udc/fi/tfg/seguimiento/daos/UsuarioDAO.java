package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;
import javax.management.InstanceNotFoundException;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Usuario;

public interface UsuarioDAO {

	public List<Usuario> findByName (String minombre, Centro micentro);
	public Usuario findByDni (String midni, Centro micentro);
	public List<Usuario> findByEmpresa(Empresa miempresa);
	public List<Usuario> findByCentro(Centro micentro);
	public Long create (Usuario miusuario);
	public void update (Usuario miusuario);
	public void remove (Usuario miusuario);
}
