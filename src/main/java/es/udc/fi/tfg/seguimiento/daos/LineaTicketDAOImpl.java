package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.LineaTicket;

@Repository
@EnableTransactionManagement
public class LineaTicketDAOImpl implements LineaTicketDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Long create(LineaTicket milineaticket) {
		return (Long) sessionFactory.getCurrentSession().save(milineaticket);
	}

	public void remove(LineaTicket milineaticket) {
		sessionFactory.getCurrentSession().delete(milineaticket);
	}

	public void update(LineaTicket milineaticket) {
		sessionFactory.getCurrentSession().update(milineaticket);
	}
	
	@SuppressWarnings("unchecked")
	public List<LineaTicket> findAll() {
		Query q = sessionFactory.getCurrentSession().createQuery("from LineaTicket");
		return (List<LineaTicket>) q.list();
	}

	public LineaTicket findById(Long miid) {
		Query q = sessionFactory.getCurrentSession().createQuery("from LineaTicket where idLineaTicket=:idLineaTicket");
		q.setParameter("idLineaTicket", miid);
		return (LineaTicket) q.list().get(0);
	}
}
