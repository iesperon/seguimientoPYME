package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Iva;

public interface IvaDAO {

	public Long insert (Iva miiva);
	public Iva update (Iva miiva);
	public void delete (Iva miiva);
	public Iva find (Integer porcentaje);
	public List<Iva> findAll();
	
}
