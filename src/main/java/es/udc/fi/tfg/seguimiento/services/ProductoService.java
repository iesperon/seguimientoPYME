package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Iva;
import es.udc.fi.tfg.seguimiento.model.Producto;

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

	//**********PRODUCTO**************
	//Registro del producto
	public void registroProducto(Producto miproducto);
	//Eliminar un producto
	public void eliminarProducto(Producto miproducto);
	//Actualizar los datos de un producto
	public void actualizarProducto (Producto miproducto);
	//Buscar un producto por ID
	public Producto buscarProductoPorId(Long miid);

}
