package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Empresa;

public interface EmpresaDAO {
	
	public Long create(Empresa miempresa);
	public void remove (Empresa miempresa);
	public void update (Empresa miempresa);
	public List<Empresa> findAll();
	public List<Empresa> findByNombre(String minombre);
	public Empresa findByCif(String micif);
}
