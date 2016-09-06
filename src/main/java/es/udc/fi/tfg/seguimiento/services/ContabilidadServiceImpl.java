package es.udc.fi.tfg.seguimiento.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.GastoDAO;
import es.udc.fi.tfg.seguimiento.daos.LineaTicketDAO;
import es.udc.fi.tfg.seguimiento.daos.PedidoProveedorDAO;
import es.udc.fi.tfg.seguimiento.daos.ProveedorDAO;
import es.udc.fi.tfg.seguimiento.daos.TicketDAO;
import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Gasto;
import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.PedidoProveedor;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Proveedor;
import es.udc.fi.tfg.seguimiento.model.Ticket;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.utils.Estadisticas;

@Service
@Transactional
public class ContabilidadServiceImpl implements ContabilidadService {
	
	@Autowired
	private GastoDAO gastoDAO = null;
	
	@Autowired
	private ProveedorDAO proveedorDAO = null;
	
	@Autowired
	private PedidoProveedorDAO pedidoDAO = null;
	
	@Autowired
	private LineaTicketDAO lineaTicketDAO = null;
	
	@Autowired
	private TicketDAO ticketDAO = null;
	
	
	//*********************GASTOS************************
	public void setGastoDAO (GastoDAO gastoDAO){
		this.gastoDAO = gastoDAO;
	}

	public void registroGasto(Gasto migasto) {
		gastoDAO.create(migasto);
	}

	public void eliminarGasto(Gasto migasto) {
		gastoDAO.remove(migasto);
	}

	public void actualizarGasto(Gasto migasto) {
		Gasto gastoMod = gastoDAO.findById(migasto.getIdGasto());
		gastoMod.setConcepto(migasto.getConcepto());
		gastoMod.setEstado(migasto.getEstado());
		gastoMod.setFechaEmision(migasto.getFechaEmision());
		gastoMod.setFechaPago(migasto.getFechaPago());
		gastoMod.setImporte(migasto.getImporte());
		
		gastoDAO.update(gastoMod);
	}

	public Gasto buscarGastoPorId(Long miid) {
		return gastoDAO.findById(miid);
	}

	public List<Gasto> buscarGastoPorEstado(String miestado) {
		return gastoDAO.findByEstado(miestado);
	}

//	public List<Gasto> buscarGastoPorConcepto(String miconcepto) {
//		return gastoDAO.findByConcepto(miconcepto);
//	}

	
	public List<Gasto> buscarGastosPorEmpresa(Empresa miempresa) {
		return gastoDAO.findByEmpresa(miempresa);
	}
	
	//*******************PROVEEDORES*********************

	public void registroProveedor(Proveedor miproveedor) {
		Proveedor proveedor = proveedorDAO.findByCIF(miproveedor.getCif(), miproveedor.getEmpresa());
		if(proveedor!=null){
			proveedor.setEnable(true);
			proveedorDAO.update(proveedor);
		}else{
			miproveedor.setEnable(true);
			proveedorDAO.create(miproveedor);
		}
	}

	public void eliminarProveedor(Proveedor miproveedor) {
		Proveedor proveedorMod = proveedorDAO.findById(miproveedor.getIdProveedor());
		proveedorMod.setEnable(false);
		proveedorDAO.update(proveedorMod);
	}

	public void actualizarProveedor(Proveedor miproveedor) {
		Proveedor proveedorMod = proveedorDAO.findById(miproveedor.getIdProveedor());
		proveedorMod.setNombre(miproveedor.getNombre());
		proveedorMod.setCif(miproveedor.getCif());
		
		proveedorDAO.update(proveedorMod);
	}

	public List<Proveedor> buscarProveedorActivoPorEmpresa(Empresa miempresa) {
		List<Proveedor> proveedores = new ArrayList<Proveedor>(proveedorDAO.findByEmpresa(miempresa));
		List<Proveedor> activos =new ArrayList<Proveedor>();
		for(Proveedor proveedor:proveedores){
			if (proveedor.getEnable()==true){
				activos.add(proveedor);
			}
		}
		return activos;
	}
	
	

	public Proveedor buscarProveedorPorId(Long miid) {
		return proveedorDAO.findById(miid);
	}
	
	
	public List<Proveedor> buscarProveedorPorEmpresa(Empresa miempresa) {
		return proveedorDAO.findByEmpresa(miempresa);
	}
	
	@Override
	public List<PedidoProveedor> buscarPedidos(List<Proveedor> proveedores) {
		List<PedidoProveedor> pedidos = new ArrayList<PedidoProveedor>();

		for (Proveedor proveedor:proveedores){
			for (PedidoProveedor pedido : proveedor.getPedido()){
				pedidos.add(pedido);
			}
		}
		return pedidos;
	}

	//******************************PEDIDOS**********************

	public void registroPedido(PedidoProveedor mipedido) {
		pedidoDAO.create(mipedido);
	}

	public void eliminarPedido(PedidoProveedor mipedido) {
		pedidoDAO.remove(mipedido);
		mipedido.getProveedor().getPedido().remove(mipedido);

	}

	public void actualizarPedido(PedidoProveedor mipedido) {
		PedidoProveedor pedidoMod = pedidoDAO.findById(mipedido.getIdPedidoProveedor());
		pedidoMod.setEstado(mipedido.getEstado());
		pedidoMod.setFechaCompra(mipedido.getFechaCompra());
		pedidoMod.setFechaVencimiento(mipedido.getFechaVencimiento());
		pedidoMod.setImporte(mipedido.getImporte());
		
		pedidoDAO.update(pedidoMod);
	}

	public PedidoProveedor buscarPedidoPorId(Long miid) {
		return pedidoDAO.findById(miid);
	}

	public List<PedidoProveedor> buscarPedidoPorEmpresa(Empresa miempresa) {
		return pedidoDAO.findByEmpresa(miempresa);
	}

	
	//**************** ESTADISTICAS ****************************
	@Override
	public List<Estadisticas> productosMasVendidos() {
		return lineaTicketDAO.productosVendidos();
	}

	@Override
	public Long numeroVentasProd(Producto miproducto) {
		return lineaTicketDAO.numVentas(miproducto);
	}

	@Override
	public List<Estadisticas> estVentasProd(List<Producto> productos) {
		List<Estadisticas> estadisticas =new ArrayList<Estadisticas>();
		for(Producto producto:productos){
			Long cantidad = numeroVentasProd(producto);
			Estadisticas estad = new Estadisticas();
			if(cantidad!=null){
				estad.setCantidad(cantidad);
				estad.setProducto(producto);
				estadisticas.add(estad);
			}
		}
		return estadisticas;
	}

	@Override
	public List<Estadisticas> estCentroVent(List<Centro> centros) {
		List<Estadisticas> estadisticas =new ArrayList<Estadisticas>();
		for(Centro centro:centros){
			Estadisticas estad = new Estadisticas();
			Double cantidadEfectivo = ticketDAO.contVentasEfectivo(centro);
			Double cantidadTarjeta = ticketDAO.contVentasTarjeta(centro);

			if (cantidadEfectivo!=null ){
				estad.setEfectivoCentro(cantidadEfectivo);
			}else{
				estad.setEfectivoCentro(0.00);
			}
			if(cantidadTarjeta!=null){
				estad.setTarjetaCentro(cantidadTarjeta);
			}else{
				estad.setTarjetaCentro(0.00);
			}
			estad.setTotalCentro(estad.getEfectivoCentro()+estad.getTarjetaCentro());
			estad.setCentro(centro);
			estadisticas.add(estad);
		}
		return estadisticas;
	}


	public List<Estadisticas> estEmplVent(List<Usuario> usuarios){
		List<Estadisticas> estadisticas = new ArrayList<Estadisticas>();
		for(Usuario usuario:usuarios){
			Double total = ticketDAO.contTotalEmp(usuario);
			Estadisticas estad = new Estadisticas();
			if(total!=null){
				estad.setTotalUsuario(total);
				estad.setUsuario(usuario);
				estadisticas.add(estad);
			}
			
		}
		return estadisticas;
	}

	@Override
	public List<Estadisticas> estPedidoProveedor(List<Proveedor> proveedores) {
		List<Estadisticas> estadisticas = new ArrayList<Estadisticas>();
		for(Proveedor proveedor:proveedores){
			Long pedidos = pedidoDAO.contNumPedidos(proveedor);
			Double total = pedidoDAO.totalComprado(proveedor);
			Estadisticas estad = new Estadisticas();
			if(pedidos!=null){
				estad.setPedidos(pedidos);
				estad.setTotalPedido(total);
				estad.setProveedor(proveedor);
				estadisticas.add(estad);
			}
		}
		return estadisticas;
	}



	
}
