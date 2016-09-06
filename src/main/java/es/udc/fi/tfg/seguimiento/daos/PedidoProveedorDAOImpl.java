package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.PedidoProveedor;
import es.udc.fi.tfg.seguimiento.model.Proveedor;
@Repository
@EnableTransactionManagement
public class PedidoProveedorDAOImpl implements PedidoProveedorDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Long create(PedidoProveedor mipedido) {
		return (Long) sessionFactory.getCurrentSession().save(mipedido);
	}

	public void remove(PedidoProveedor mipedido) {
		sessionFactory.getCurrentSession().delete(mipedido);
	}

	public void update(PedidoProveedor mipedido) {
		sessionFactory.getCurrentSession().update(mipedido);
	}

	public PedidoProveedor findById(Long miid) {
		Query q = sessionFactory.getCurrentSession().createQuery("from PedidoProveedor where idPedidoProveedor=:idPedidoProveedor");
		q.setParameter("idPedidoProveedor", miid);
		return (PedidoProveedor) q.list().get(0);
	}

	@SuppressWarnings("unchecked")
	public List<PedidoProveedor> findByEmpresa(Empresa miempresa) {
	try{
		Query q = sessionFactory.getCurrentSession().createQuery("from PedidoProveedor where idEmpresa=:idEmpresa");
		q.setParameter("idEmpresa", miempresa.getIdEmpresa());
		return (List<PedidoProveedor>) q.list();
	}catch(RuntimeException e){
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public Long contNumPedidos(Proveedor proveedor) {
		try{
		Query q = sessionFactory.getCurrentSession().createQuery("select count(idPedidoProveedor) from PedidoProveedor where idProveedor=:idProveedor ");
		q.setParameter("idProveedor", proveedor.getIdProveedor());
		return Long.parseLong(q.uniqueResult().toString());
		}catch(RuntimeException e){
			return null;
		}
	}
	
	@Override
	public Double totalComprado(Proveedor proveedor) {
		try{
		Query q = sessionFactory.getCurrentSession().createQuery("select sum(importe) from PedidoProveedor where idProveedor=:idProveedor ");
		q.setParameter("idProveedor", proveedor.getIdProveedor());
		return Double.parseDouble(q.uniqueResult().toString());
		}catch(RuntimeException e){
			return null;
		}
	}
}
