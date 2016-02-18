package es.udc.fi.tfg.seguimiento.daos;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Cierre;

@Repository
@EnableTransactionManagement
public class CierreDAOImpl implements CierreDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Long create(Cierre micierre) {
		micierre.setTotal(new Float (micierre.getEfectivo()+micierre.getTarjeta()));
		micierre.setDiferencia(new Float(micierre.getCaja()-(micierre.getEfectivo()+micierre.getTarjeta())));
		return (Long) sessionFactory.getCurrentSession().save(micierre);
	}

	public void remove(Cierre micierre) {
		sessionFactory.getCurrentSession().delete(micierre);
	}

	public void update(Cierre micierre) {
		micierre.setTotal(new Float (micierre.getEfectivo()+micierre.getTarjeta()));
		micierre.setDiferencia(new Float(micierre.getCaja()-(micierre.getEfectivo()+micierre.getTarjeta())));
		sessionFactory.getCurrentSession().update(micierre);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cierre> findAll() {
		Query q = sessionFactory.getCurrentSession().createQuery("from Cierre order by fecha");
		return (List<Cierre>) q.list();
	}

	public Cierre finByFecha(Timestamp mifecha) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cierre.class);
		criteria.add(Restrictions.eq("fecha",mifecha));
		if(criteria.list().isEmpty()){
			return null;
		}
		return (Cierre) criteria.list().get(0);
	}

}
