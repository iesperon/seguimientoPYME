package es.udc.fi.tfg.seguimiento.daos;

import es.udc.fi.tfg.seguimiento.model.Rol;
import es.udc.fi.tfg.seguimiento.model.Usuario;

public interface RolDAO {

	public Long create (Rol mirol);
	public void update(Rol mirol);
	public void delete (Rol mirol);
	public Rol findByUsuario (Usuario miusuario);
	public Rol findByEmail (String email);
}
