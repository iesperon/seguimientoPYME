package es.udc.fi.tfg.seguimiento.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.IvaDAO;
import es.udc.fi.tfg.seguimiento.daos.ProductoDAO;
import es.udc.fi.tfg.seguimiento.daos.StockDAO;
import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Iva;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Stock;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private IvaDAO ivaDAO = null;
	
	public void setIvaDAO (IvaDAO ivaDAO){
		this.ivaDAO = ivaDAO;
	}
	
	@Autowired
	private ProductoDAO productoDAO = null;
	
	public void setProductoDAO (ProductoDAO productoDAO){
		this.productoDAO = productoDAO;
	}
	
	@Autowired
	private StockDAO stockDAO = null;
	private List<Stock> misStocks;
	
	public void setStockDAO (StockDAO stockDAO){
		this.stockDAO = stockDAO;
	}
	
	//**********IVA***********
	
		public void registroIVA(Iva miiva) {
			ivaDAO.create(miiva);	
		}

		public void eliminarIVA(Iva miiva) {
			ivaDAO.delete(miiva);
		}

		public void actualizarIVA(Iva miiva) {
			ivaDAO.update(miiva);
		}

		public Iva buscarIvaPorPorcentaje(Integer miporcentaje) {
			return ivaDAO.findbyPorcentaje(miporcentaje);
		}

		public List<Iva> obtenerTodosIva() {
			return ivaDAO.findAll();
		}
		
		public Iva buscarIvaPorId(Long idIva) {
			return ivaDAO.findById(idIva);
		}
	

	//*************PRODUCTO****************
		
		public void registroProducto(Producto miproducto) {
			productoDAO.create(miproducto);
		}

		public void eliminarProducto(Producto miproducto) {
			productoDAO.remove(miproducto);
		}

		public void actualizarProducto(Producto miproducto) {
			Producto productoMod = productoDAO.findById(miproducto.getIdProducto()); 
			productoMod.setCodProd(miproducto.getCodProd());
			productoMod.setDescripcion(miproducto.getDescripcion());
			productoMod.setDescuento(miproducto.getDescuento());
			productoMod.setFoto(miproducto.getFoto());
			productoMod.setMarca(miproducto.getMarca());
			productoMod.setNombre(miproducto.getNombre());
			productoMod.setPrecio(miproducto.getPrecio());
			
			productoDAO.update(productoMod);
		}

		public Producto buscarProductoPorId(Long miid) {
			return productoDAO.findById(miid);
		}
		
		//********STOCK*************

		public Stock buscarStockProductoCentro(Producto miproducto, Centro micentro) {
			return stockDAO.findByProductoCentro(miproducto, micentro);
		}
		
		public void registroStock (Stock mistock){
			stockDAO.create(mistock);
		}

	
}
