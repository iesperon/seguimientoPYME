package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Stock;
import es.udc.fi.tfg.seguimiento.model.Usuario;

@Repository
@EnableTransactionManagement
public class StockDAOImpl implements StockDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Long create(Stock mistock) {
		return (Long) sessionFactory.getCurrentSession().save(mistock);
	}

	public void update(Stock mistock) {
		sessionFactory.getCurrentSession().update(mistock);
	}

	public void delete(Stock mistock) {
		sessionFactory.getCurrentSession().delete(mistock);
	}
	
	@SuppressWarnings("unchecked")
	public List<Stock> findAll() {
		return (List<Stock>) sessionFactory.getCurrentSession().createQuery("from Stock").list();
	}

	public Stock findByProducto(Producto miproducto) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Stock where idProducto=:idProducto");
		q.setParameter("idProducto", miproducto.getIdProducto());
		return (Stock) q.list().get(0);
	}

	public Stock findByProductoCentro(Producto miproducto, Centro micentro) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Stock where idProducto=:idProducto and idCentro=:idCentro");
		q.setParameter("idProducto", miproducto.getIdProducto());
		q.setParameter("idCentro", micentro.getIdCentro());
		return (Stock) q.list().get(0);
	}
	
	

}
