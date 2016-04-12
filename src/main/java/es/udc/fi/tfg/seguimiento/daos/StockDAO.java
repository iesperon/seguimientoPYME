package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Stock;

public interface StockDAO {
	
	public Long create (Stock mistock);
	public void update (Stock mistock);
	public void delete (Stock mistock);
	public List<Stock> findAll();
	public Stock findByProducto(Producto miproducto);
	public Stock findByProductoCentro (Producto miproducto, Centro micentro);
	public Stock findById(Long miid);
	public List<Stock> findStockMin();
}
