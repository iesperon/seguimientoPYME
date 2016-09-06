package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Ticket;
import es.udc.fi.tfg.seguimiento.utils.Estadisticas;

@Repository
@EnableTransactionManagement
public class LineaTicketDAOImpl implements LineaTicketDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Long create(LineaTicket milineaticket) {
		Long id = (Long) sessionFactory.getCurrentSession().save(milineaticket);
		return id;

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
	
	@SuppressWarnings("unchecked")
	public List<LineaTicket> findByTicket(Ticket miticket) {
		Query q = sessionFactory.getCurrentSession().createQuery("from LineaTicket where idTicket=:idTicket");
		q.setParameter("idTicket", miticket.getIdTicket());
		return (List<LineaTicket>) q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Estadisticas> productosVendidos() {
		Query q = sessionFactory.getCurrentSession().createQuery("select linea.idProducto, sum(linea.cantidad) cantidad from LineaTicket linea group by linea.idProducto ");
		return (List<Estadisticas>) q.list();
	}

	@Override
	public Long numVentas(Producto producto) {
		try{
		Query q = sessionFactory.getCurrentSession().createQuery("select sum(cantidad) from LineaTicket where idProducto=:idProducto ");
		q.setParameter("idProducto", producto.getIdProducto());
		return Long.parseLong(q.uniqueResult().toString());
		}catch(RuntimeException e){
			return null;
		}
	}
}
