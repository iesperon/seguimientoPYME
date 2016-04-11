package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Iva;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Stock;

public interface ProductoService {

	//**********IVA**************
	
	//Registro de un nuevo IVA. Si el IVA ya existe lanza una excepcion.
	public void registroIVA(Iva miiva);
	//Elimina un IVA.
	public void eliminarIVA (Iva miiva);
	//Actualizar los datos del IVA
	public void actualizarIVA (Iva miiva);
	//Buscar el IVA metiendo el porcentaje
	public Iva buscarIvaPorPorcentaje (Integer miporcentaje);
	//Obtener todos los IVAs
	public List<Iva> obtenerTodosIva();
	//Buscar IVA por ID
	public Iva buscarIvaPorId(Long idIva);

	//**********PRODUCTO**************
	//Registro del producto
	public void registroProducto(Producto miproducto);
	//Eliminar un producto
	public void eliminarProducto(Producto miproducto);
	//Actualizar los datos de un producto
	public void actualizarProducto (Producto miproducto);
	//Buscar un producto por ID
	public Producto buscarProductoPorId(Long miid);
	
	//*****************STOCK****************
	public Stock buscarStockProductoCentro(Producto miproducto, Centro micentro);
	public void registroStock(Stock mistock);
	public Stock buscarStockPorId(Long miid);
	public void actualizarStock(Stock mistock);

}
