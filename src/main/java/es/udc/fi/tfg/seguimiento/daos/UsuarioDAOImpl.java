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
	public List<Usuario> findByName(String minombre, Empresa empresa) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Usuario where nombre LIKE :nombre" );
		minombre = "%"+minombre+"%";
		q.setParameter("nombre", minombre);
		//q.setParameter("idCentro", micentro.getIdCentro());
		return (List<Usuario>) q.list();		
	}

	public Usuario findByDni(String midni, Centro micentro) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Usuario where dni LIKE :dni and idCentro=:idCentro");
		midni = "%"+midni+"%";
		q.setParameter("dni", midni);
		q.setParameter("idCentro", micentro.getIdCentro());
		return (Usuario) q.list().get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findByEmpresa(Empresa miempresa) {
		Query q = sessionFactory.getCurrentSession().createQuery("select u from Usuario u join u.centro where u.centro.empresa=:idEmpresa");
		q.setParameter("idEmpresa", miempresa);
		return (List<Usuario>) q.list();
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
		Usuario usuarioMod = findById(miusuario.getIdUsuario());
		usuarioMod.setNombre(miusuario.getNombre());
		usuarioMod.setApellido1(miusuario.getApellido1());
		usuarioMod.setApellido2(miusuario.getApellido2());
		usuarioMod.setDni(miusuario.getDni());
		usuarioMod.setEmail(miusuario.getEmail());
		usuarioMod.setContrasena(miusuario.getContrasena());
		usuarioMod.setCentro(miusuario.getCentro());		
		
		sessionFactory.getCurrentSession().update(usuarioMod);
	}

	public void remove(Usuario miusuario) {
		sessionFactory.getCurrentSession().delete(miusuario);
	}

	public Usuario findByEmail(String miemail) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Usuario where email=:email");
		q.setParameter("email", miemail);
		return (Usuario) q.list().get(0);
	}

	public Usuario findById(Long idUsuario) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Usuario where idUsuario=:idUsuario");
		q.setParameter("idUsuario", idUsuario);
		return (Usuario) q.list().get(0);
	}

}
