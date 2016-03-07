package es.udc.fi.tfg.seguimiento.daos;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Rol;
import es.udc.fi.tfg.seguimiento.model.Usuario;

@Repository
@EnableTransactionManagement
public class RolDAOImpl implements RolDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Long create(Rol mirol) {
		return (Long) sessionFactory.getCurrentSession().save(mirol);
	}

	public void update(Rol mirol) {
		sessionFactory.getCurrentSession().update(mirol);
	}

	public void delete(Rol mirol) {
		sessionFactory.getCurrentSession().delete(mirol);
	}

	public Rol findByUsuario(Usuario miusuario) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Roles where idUsuario=:idUsuario");
		q.setParameter("idUsuario", miusuario.getIdUsuario());
		return (Rol) q.list().get(0);
	}

	public Rol findByEmail(String email) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Roles where email=:email");
		q.setParameter("email", email);
		return (Rol) q.list().get(0);
	}

}
