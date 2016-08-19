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
	
	@SuppressWarnings("unchecked")
	public List<Producto> findByEmpresa(Empresa miempresa) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Producto where idEmpresa=:idEmpresa");
		q.setParameter("idEmpresa", miempresa.getIdEmpresa());
		return (List<Producto>) q.list();
	}

	public Producto findByCod(String micodprod, Empresa miempresa) {
	try{	
		Query q = sessionFactory.getCurrentSession().createQuery("from Producto where codProd=:codProd AND idEmpresa= :idEmpresa");
		q.setParameter("codProd", micodprod);
		q.setParameter("idEmpresa", miempresa.getIdEmpresa());
		return (Producto) q.list().get(0);
	}catch(RuntimeException e){
		e.printStackTrace();
	}
	return null;
	}


	public Producto findById(Long miid) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Producto where idProducto=:idProducto");
		q.setParameter("idProducto", miid);
		return (Producto) q.list().get(0);
	}

//	@Override
//	public Producto findByIdEmpresa(Long miid, Empresa miempresa) {
//		try{
//		Query q = sessionFactory.getCurrentSession().createQuery("from Producto where idProducto=:idProducto AND idEmpresa=:idEmpresa");
//		q.setParameter("idProducto", miid);
//		q.setParameter("idEmpresa", miempresa.getIdEmpresa());
//		return (Producto) q.list().get(0);
//		}catch(RuntimeException e){
//			e.printStackTrace();
//		}
//		return null;
//	}

}
