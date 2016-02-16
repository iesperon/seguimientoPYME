package es.udc.fi.tfg.model.iva;

import java.util.List;
import es.udc.fi.tfg.model.iva.Iva;

public interface IvaDAO {

	public Long insert (Iva miiva);
	public Iva update (Iva miiva);
	public void delete (Iva miiva);
	public Iva find (Integer porcentaje);
	public List<Iva> findAll();
	
}
