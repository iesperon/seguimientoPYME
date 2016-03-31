package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Proveedor;

public interface ProveedorDAO {

	public Long create (Proveedor miproveedor);
	public void update (Proveedor miproveedor);
	public void remove (Proveedor miproveedor);
	public Proveedor findById (Long miid);
	public List<Proveedor> findByEmpresa(Empresa miempresa);
}
