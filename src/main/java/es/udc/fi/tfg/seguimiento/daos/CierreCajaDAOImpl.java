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

import es.udc.fi.tfg.seguimiento.model.CierreCaja;

@Repository
@EnableTransactionManagement
public class CierreCajaDAOImpl implements CierreCajaDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Long create(CierreCaja micierre) {
		return (Long) sessionFactory.getCurrentSession().save(micierre);
	}

	public void remove(CierreCaja micierre) {
		sessionFactory.getCurrentSession().delete(micierre);
	}

	public void update(CierreCaja micierre) {
		sessionFactory.getCurrentSession().update(micierre);
	}
	
	@SuppressWarnings("unchecked")
	public List<CierreCaja> findAll() {
		Query q = sessionFactory.getCurrentSession().createQuery("from Cierre order by fecha");
		return (List<CierreCaja>) q.list();
	}

	public CierreCaja finByFecha(Timestamp mifecha) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CierreCaja.class);
		criteria.add(Restrictions.eq("fecha",mifecha));
		if(criteria.list().isEmpty()){
			return null;
		}
		return (CierreCaja) criteria.list().get(0);
	}

}
