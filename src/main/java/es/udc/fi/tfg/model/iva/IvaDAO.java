package es.udc.fi.tfg.model.iva;

import java.util.List;

public interface IvaDAO {

	public Long insert (Iva iva);
	public Iva update (Iva iva);
	public void delete (Iva iva);
	public Iva find (Integer porcentaje);
	public List<Iva> findAll();
	
}
