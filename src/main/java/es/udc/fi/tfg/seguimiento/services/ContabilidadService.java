package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Gasto;
import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.PedidoProveedor;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Proveedor;
import es.udc.fi.tfg.seguimiento.model.Ticket;
import es.udc.fi.tfg.seguimiento.utils.Estadisticas;

public interface ContabilidadService {

	//************GASTOS******************
	
	//Registro de un gasto
	public void registroGasto (Gasto migasto);
	//Eliminacion de un gasto
	public void eliminarGasto (Gasto migasto);
	//Actualizar un gasto
	public void actualizarGasto (Gasto migasto);
	//Buscar gasto por Id
	public Gasto buscarGastoPorId (Long miid);
	//Buscar gasto por estado
	public List<Gasto> buscarGastoPorEstado(String miestado);
	//Buscar gasto por concepto
//	public List<Gasto> buscarGastoPorConcepto (String miconcepto);
	public List<Gasto> buscarGastosPorEmpresa (Empresa miempresa);
	
	
	//******************* PROVEEDORES ***********************
	
	//Registrar un proveedor
	public void registroProveedor (Proveedor miproveedor);
	//Eliminar un proveedor
	public void eliminarProveedor (Proveedor miproveedor);
	//Actualizar un proveedor
	public void actualizarProveedor (Proveedor miproveedor);
	//Buscar todos los proveedores 
	public List<Proveedor> buscarProveedorActivoPorEmpresa (Empresa miempresa);
	//Buscar proveedor por ID
	public Proveedor buscarProveedorPorId (Long miid);
	public List<Proveedor> buscarProveedorPorEmpresa(Empresa miempresa);
	
	//******************PEDIDOS*****************************
	
	//Registrar un pedido
	public void registroPedido (PedidoProveedor mipedido);
	//Eliminar un pedido 
	public void eliminarPedido (PedidoProveedor mipedido);
	//Actualizar un pedido 
	public void actualizarPedido (PedidoProveedor mipedido);
	//Buscar todos los pedidos
	public PedidoProveedor buscarPedidoPorId (Long miid);
	public List<PedidoProveedor> buscarPedidoPorEmpresa(Empresa miempresa);
	public List<PedidoProveedor> buscarPedidos(List<Proveedor> proveedores);
	
	//**************** ESTADISTICAS ****************************
	
	public List<Estadisticas> productosMasVendidos();
	public Long numeroVentasProd(Producto miproducto);
	public List<Estadisticas> estVentasProd(List<Producto> productos);
}
