package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Usuario;

public interface EmpresaDAO {
	
	public Long create(Empresa miempresa);
	public void remove (Empresa miempresa);
	public void update (Empresa miempresa);
	public List<Empresa> findAll();
	public List<Empresa> findByNombre(String minombre);
	public Empresa findByCif(String micif);
	public Empresa findByAdmin(Usuario miusuario);
	public Empresa findById (Long miid);
}
