package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.PedidoProveedor;

public interface PedidoProveedorDAO {

	public Long create (PedidoProveedor mipedido);
	public void remove (PedidoProveedor mipedido);
	public void update (PedidoProveedor mipedido);
	public PedidoProveedor findById (Long miid);
	public List<PedidoProveedor> findByEmpresa(Empresa miempresa);
	
}
