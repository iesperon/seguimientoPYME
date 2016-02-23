package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Usuario;

@Repository
@EnableTransactionManagement
public class UsuarioDAOImpl implements UsuarioDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findByName(String minombre, Centro micentro) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Usuario where nombre LIKE :nombre and idCentro=:idCentro" );
		minombre = "%"+minombre+"%";
		q.setParameter("nombre", minombre);
		q.setParameter("idCentro", micentro.getIdCentro());
		return (List<Usuario>) q.list();		
	}

	public Usuario findByDni(String midni, Centro micentro) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Usuario where dni=:dni and idCentro=:idCentro");
		q.setParameter("dni", midni);
		q.setParameter("idCentro", micentro.getIdCentro());
		return (Usuario) q.list().get(0);
	}

	public List<Usuario> findByEmpresa(Empresa miempresa) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findByCentro(Centro micentro) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Usuario where idCentro=:idCentro");
		q.setParameter("idCentro", micentro.getIdCentro());
		return (List<Usuario>) q.list();
	}

	public Long create(Usuario miusuario) {
		return (Long) sessionFactory.getCurrentSession().save(miusuario);
	}

	public void update(Usuario miusuario) {
		sessionFactory.getCurrentSession().update(miusuario);
	}

	public void remove(Usuario miusuario) {
		sessionFactory.getCurrentSession().delete(miusuario);
	}

}
