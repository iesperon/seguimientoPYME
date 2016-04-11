package es.udc.fi.tfg.seguimiento.model;

public class FormProveedorPedido {

	private Long idProveedor;
	private PedidoProveedor pedido;
	
	public FormProveedorPedido(){
		
	}

	public FormProveedorPedido(Long idProveedor, PedidoProveedor pedido) {
		super();
		this.idProveedor = idProveedor;
		this.pedido = pedido;
	}

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public PedidoProveedor getPedido() {
		return pedido;
	}

	public void setPedido(PedidoProveedor pedido) {
		this.pedido = pedido;
	}

	
}
