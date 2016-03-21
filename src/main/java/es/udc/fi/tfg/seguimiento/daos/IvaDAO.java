package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Iva;

public interface IvaDAO {

	public Long create (Iva miiva);
	public void update (Iva miiva);
	public void delete (Iva miiva);
	public Iva findbyPorcentaje (Integer miporcentaje);
	public List<Iva> findAll();
	public Iva findById(Long idIva);
	
}
