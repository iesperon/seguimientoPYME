package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Gasto;

@Repository
@EnableTransactionManagement
public class GastoDAOImpl implements GastoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Long create(Gasto migasto) {
		return (Long) sessionFactory.getCurrentSession().save(migasto);
	}

	public void update(Gasto migasto) {
		sessionFactory.getCurrentSession().update(migasto);
	}

	public void remove(Gasto migasto) {
		sessionFactory.getCurrentSession().delete(migasto);
	}

	public Gasto findById(Long miid) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Gasto where idGasto=:idGasto");
		q.setParameter("idGasto", miid);
		return (Gasto) q.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Gasto> findByEstado(String miestado) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Gasto where estado LIKE :estado");
		miestado = "%"+miestado+"%";
		q.setParameter("estado", miestado);
		return (List<Gasto>) q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Gasto> findByConcepto(String miconcepto) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Gasto where concepto LIKE :concepto");
		miconcepto = "%"+miconcepto+"%";
		q.setParameter("concepto", miconcepto);
		return (List<Gasto>) q.list();
	}

}
