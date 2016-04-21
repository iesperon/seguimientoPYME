package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Ticket;

@Repository
@EnableTransactionManagement
public class TicketDAOImpl implements TicketDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Long create(Ticket miticket) {
		return (Long) sessionFactory.getCurrentSession().save(miticket);
	}

	public void remove(Ticket miticket) {
		sessionFactory.getCurrentSession().delete(miticket);
	}

	public void update(Ticket miticket) {
		sessionFactory.getCurrentSession().update(miticket);
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> findAll() {
		Query q = sessionFactory.getCurrentSession().createQuery("from Ticket");
		return (List<Ticket>) q.list();
	}

	public Ticket findById(Long miid) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Ticket where idTicket=:idTicket");
		q.setParameter("idTicket", miid);
		return (Ticket) q.list().get(0);
	}
}
