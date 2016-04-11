package es.udc.fi.tfg.seguimiento.daos;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.PedidoProveedor;
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

}
