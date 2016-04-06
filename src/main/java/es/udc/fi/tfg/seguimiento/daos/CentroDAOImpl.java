package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;

@Repository
@EnableTransactionManagement
public class CentroDAOImpl implements CentroDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Long create(Centro micentro) {
		return (Long) sessionFactory.getCurrentSession().save(micentro);
	}

	public void remove(Centro micentro) {
		sessionFactory.getCurrentSession().delete(micentro);
	}

	public void update(Centro micentro) {
		sessionFactory.getCurrentSession().update(micentro);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Centro> findAllByEmpresa(Empresa miempresa) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Centro where idEmpresa = :idEmpresa order by nombre");
		q.setParameter("idEmpresa", miempresa.getIdEmpresa());
		return (List<Centro>) q.list();
	}

	public Centro findById(Long miid) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Centro where idCentro = :idCentro");
		q.setParameter("idCentro", miid);
		return (Centro) q.list().get(0);
	}


}
