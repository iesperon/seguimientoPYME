package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Producto;

public interface ProductoDAO {

	public Long create (Producto miproducto);
	public void remove (Producto miproducto);
	public void update (Producto miproducto);
	public List<Producto> findAll();
	public List<Producto> findByEmpresa(Empresa miempresa);
	public Producto findByCod (String micodprod, Empresa miempresa);
	public Producto findById(Long miid);
//	public Producto findByIdEmpresa(Long miid, Empresa miempresa);
	
}
