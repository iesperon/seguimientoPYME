package es.udc.fi.tfg.seguimiento.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.IvaDAO;
import es.udc.fi.tfg.seguimiento.daos.ProductoDAO;
import es.udc.fi.tfg.seguimiento.daos.StockDAO;
import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Iva;
import es.udc.fi.tfg.seguimiento.model.LineaTicket;
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
			Producto producto = productoDAO.findByCod(miproducto.getCodProd(), miproducto.getEmpresa());
			if(producto!=null){
				producto.setEnable(true);
				productoDAO.update(producto);
			}else{
				miproducto.setEnable(true);
				productoDAO.create(miproducto);	
			}
			
		}
		
		public void eliminarProducto(Producto miproducto) {
			/*for(Stock mistock : miproducto.getStock()){	
				stockDAO.delete(mistock);
			}*/
			Producto productoMod = productoDAO.findByCod(miproducto.getCodProd(), miproducto.getEmpresa());
			productoMod.setEnable(false);
			productoDAO.update(productoMod);
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


		public Producto buscarProductoPorCodigo(String micodigo, Empresa miempresa) {
			return productoDAO.findByCod(micodigo, miempresa);
		}
		
		public List<Producto> buscarProductoPorEmpresa(Empresa miempresa) {
			return productoDAO.findByEmpresa(miempresa);
		}
		
//		@Override
//		public Producto buscarPorIdEmpresa(Long idProducto, Empresa miempresa) {
//			return productoDAO.findByIdEmpresa(idProducto, miempresa);
//		}
//		
		
		//********STOCK*************

		public Stock buscarStockProductoCentro(Producto miproducto, Centro micentro) {
			return stockDAO.findByProductoCentro(miproducto, micentro);
		}
		
		public void registroStock (Stock mistock){
			stockDAO.create(mistock);
		}
		
		public Stock buscarStockPorId(Long miid) {
			return stockDAO.findById(miid);
		}

		public void actualizarStock(Stock mistock) {
			Stock stockMod = stockDAO.findById(mistock.getIdStock());
			stockMod.setStockActual(mistock.getStockActual());
			stockMod.setStockMin(mistock.getStockMin());
			
			stockDAO.update(stockMod);
			
			
		}

		public List<Stock> buscarStocksMinimos() {
			return stockDAO.findStockMin();
		}
		
		public void stockBajo(Stock mistock){
			if(mistock.getStockActual()<=mistock.getStockMin()){
				
			}
		}

		
		public void actualizarStockCaja(Stock mistock) {
			Stock stockMod = stockDAO.findById(mistock.getIdStock());
			stockMod.setStockActual(mistock.getStockActual());
			
			stockDAO.update(stockMod);
			
			
		}

		
		public void descontarStock(Centro centro, List<LineaTicket> lineas) {
			for (LineaTicket linea:lineas){
				List<Stock> stocks= new ArrayList<Stock>(linea.getProducto().getStock());
				for(Stock stock:stocks ){
					if(centro.getIdCentro()==stock.getCentro().getIdCentro()){
						stock.setStockActual(stock.getStockActual()-linea.getCantidad());
						actualizarStockCaja(stock);;
					}
				}
			}
		}

		@Override
		public void registroStockIni(List<Producto> productos, Centro micentro) {
			for(Producto miproducto : productos){
				Stock mistock = new Stock();
				mistock.setCentro(micentro);
				mistock.setProducto(miproducto);
				mistock.setStockActual(0);
				mistock.setStockMin(0);
				registroStock(mistock);
			}
			
		}

		@Override
		public void registroStockProd(List<Centro> centros, Producto producto) {
			for (Centro centro : centros) {
				Stock stock = new Stock();
				stock.setCentro(centro);
				stock.setProducto(producto);
				stock.setStockActual(0);
				stock.setStockMin(0);
				registroStock(stock);
			}
			
		}

	






	
}
