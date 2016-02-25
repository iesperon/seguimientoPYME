package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;

public interface CentroDAO {
	
	public Long create(Centro micentro);
	public void remove(Centro micentro);
	public void update(Centro micentro);
	public List<Centro> findAllByEmpresa(Empresa miempresa);
	public Centro findById(Long miid);
	

}
