package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Envio;
import es.udc.fi.tfg.seguimiento.model.Ticket;
@Repository
@EnableTransactionManagement
public class EnvioDAOImpl implements EnvioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Long create(Envio mienvio) {
		return (Long) sessionFactory.getCurrentSession().save(mienvio);
	}

	public void remove(Envio mienvio) {
		sessionFactory.getCurrentSession().delete(mienvio);
	}

	public void update(Envio mienvio) {
		sessionFactory.getCurrentSession().update(mienvio);
	}
	
	@SuppressWarnings("unchecked")
	public List<Envio> findAll() {
		Query q = sessionFactory.getCurrentSession().createQuery("from Envio");
		return (List<Envio>) q.list();
	}

	public Envio findById(Long miid) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Envio where idEnvio=:idEnvio");
		q.setParameter("idEnvio", miid);
		return (Envio) q.list().get(0);
	}

	public Envio findByTicket(Ticket miticket) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Envio where idTicket=:idTicket");
		q.setParameter("idTicket", miticket.getIdTicket());
		return (Envio) q.list().get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Envio> findByCentro(Centro micentro) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Envio where idCentro=:idCentro");
		q.setParameter("idCentro", micentro.getIdCentro());
		return (List<Envio>) q.list();
	}

}
