package es.udc.fi.tfg.seguimiento.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.GastoDAO;
import es.udc.fi.tfg.seguimiento.daos.PedidoProveedorDAO;
import es.udc.fi.tfg.seguimiento.daos.ProveedorDAO;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Gasto;
import es.udc.fi.tfg.seguimiento.model.PedidoProveedor;
import es.udc.fi.tfg.seguimiento.model.Proveedor;

@Service
@Transactional
public class ContabilidadServiceImpl implements ContabilidadService {
	
	@Autowired
	private GastoDAO gastoDAO = null;
	
	@Autowired
	private ProveedorDAO proveedorDAO = null;
	
	@Autowired
	private PedidoProveedorDAO pedidoDAO = null;
	
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

	public List<Gasto> buscarGastoPorConcepto(String miconcepto) {
		return gastoDAO.findByConcepto(miconcepto);
	}

	
	public List<Gasto> buscarGastosPorEmpresa(Empresa miempresa) {
		return gastoDAO.findByEmpresa(miempresa);
	}
	
	//*******************PROVEEDORES*********************

	public void registroProveedor(Proveedor miproveedor) {
		Proveedor proveedor = proveedorDAO.findByCIF(miproveedor.getCif(), miproveedor.getEmpresa().getIdEmpresa());
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
	
	@Override
	public List<Proveedor> buscarProveedorPorEmpresa(Empresa miempresa) {
		return proveedorDAO.findByEmpresa(miempresa);
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

	@Override
	public List<PedidoProveedor> buscarPedidoPorEmpresa(Empresa miempresa) {
		return pedidoDAO.findByEmpresa(miempresa);
	}




	
}
