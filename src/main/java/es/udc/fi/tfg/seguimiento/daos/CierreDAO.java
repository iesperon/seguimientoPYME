package es.udc.fi.tfg.seguimiento.daos;

import java.sql.Timestamp;
import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Cierre;

public interface CierreDAO {

	public Long create (Cierre micierre);
	public void remove (Cierre micierre);
	public void update (Cierre micierre);
	public List<Cierre> findAll();
	public Cierre finByFecha (Timestamp mifecha);
}
