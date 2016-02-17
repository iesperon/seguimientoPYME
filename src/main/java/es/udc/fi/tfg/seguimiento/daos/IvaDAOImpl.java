package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Iva;

@Repository
@EnableTransactionManagement
public class IvaDAOImpl implements IvaDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Long insert(Iva miiva) {
		if(miiva.getId_iva() != null){
			throw new RuntimeException("Intento de creación de IVA ya persistente");
		}
		return (Long) sessionFactory.getCurrentSession().save(miiva);
	}

	public void update(Iva miiva) {
		sessionFactory.getCurrentSession().update(miiva);
	}

	public void delete(Iva miiva) {
		sessionFactory.getCurrentSession().delete(miiva);
	}
	
	@SuppressWarnings("unchecked")
	public List<Iva> findAll() {
		return (List<Iva>) sessionFactory.getCurrentSession().createQuery("from Iva order by nombre").list();
	}

	public Iva findbyPorcentaje(Integer miporcentaje) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Iva.class);
		criteria.add(Restrictions.eqOrIsNull("porcentaje", miporcentaje));
		if(criteria.list().isEmpty()){
			return null;
		}
		return (Iva) criteria.list().get(0);
	}

}
