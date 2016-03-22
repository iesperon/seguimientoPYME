package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Gasto;

public interface GastoDAO {

	public Long create (Gasto migasto);
	public void update (Gasto migasto);
	public void remove (Gasto migasto);
	public Gasto findById (Long miid);
	public List<Gasto> findByEstado (String miestado);
	public List<Gasto> findByConcepto (String miconcepto);
}
