package es.udc.fi.tfg.seguimiento.daos;

import java.sql.Timestamp;
import java.util.List;

import es.udc.fi.tfg.seguimiento.model.CierreCaja;

public interface CierreCajaDAO {

	public Long create (CierreCaja micierre);
	public void remove (CierreCaja micierre);
	public void update (CierreCaja micierre);
	public List<CierreCaja> findAll();
	public CierreCaja finByFecha (Timestamp mifecha);
}
