package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Producto;

@Repository
@EnableTransactionManagement
public class ProductoDAOImpl implements ProductoDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Long create(Producto miproducto) {
		return (Long) sessionFactory.getCurrentSession().save(miproducto);
	}

	public void remove(Producto miproducto) {
		sessionFactory.getCurrentSession().delete(miproducto);
	}

	public void update(Producto miproducto) {
		sessionFactory.getCurrentSession().update(miproducto);
	}

	@SuppressWarnings("unchecked")
	public List<Producto> findAll() {
		Query q = sessionFactory.getCurrentSession().createQuery("from Producto order by nombre");
		return (List<Producto>) q.list();
	}

	public List<Producto> findByEmpresa(Empresa miempresa) {
		return null;
	}

	public Producto findByCod(Float micodprod) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Empresa where codProd=:codProd");
		q.setParameter("codProd", micodprod);
		return (Producto) q.list().get(0);
	}

}
