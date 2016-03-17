package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.IvaDAO;
import es.udc.fi.tfg.seguimiento.daos.ProductoDAO;
import es.udc.fi.tfg.seguimiento.model.Iva;
import es.udc.fi.tfg.seguimiento.model.Producto;

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

	//*************PRODUCTO****************
		
		public void registroProducto(Producto miproducto) {
			Iva miiva = ivaDAO.findbyPorcentaje(21);
			miproducto.setIva(miiva);
			productoDAO.create(miproducto);
		}

		public void eliminarProducto(Producto miproducto) {
			productoDAO.remove(miproducto);
		}

		public void actualizarProducto(Producto miproducto) {
			productoDAO.update(miproducto);
		}

		public Producto buscarProductoPorId(Long miid) {
			return productoDAO.findById(miid);
		}
	
}
