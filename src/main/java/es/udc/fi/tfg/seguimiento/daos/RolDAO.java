package es.udc.fi.tfg.seguimiento.daos;

import es.udc.fi.tfg.seguimiento.model.Rol;

public interface RolDAO {

	public Long create (Rol mirol);
	public void update(Rol mirol);
	public void delete (Rol mirol);
	public Rol findByRol(String mirol);
}
