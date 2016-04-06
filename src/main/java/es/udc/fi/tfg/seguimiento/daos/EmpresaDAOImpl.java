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
public class EmpresaDAOImpl implements EmpresaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Long create(Empresa miempresa) {
		return (Long) sessionFactory.getCurrentSession().save(miempresa);
	}

	public void remove(Empresa miempresa) {
		sessionFactory.getCurrentSession().delete(miempresa);
	}

	public void update(Empresa miempresa) {
		Empresa empresaMod = findById(miempresa.getIdEmpresa());
		empresaMod.setNombre(miempresa.getNombre());
		empresaMod.setCif(miempresa.getCif());
		empresaMod.setSector(miempresa.getSector());
		empresaMod.setEmail(miempresa.getEmail());
		empresaMod.setDescripcion(miempresa.getDescripcion());
		empresaMod.setLogo(miempresa.getLogo());
				
		sessionFactory.getCurrentSession().update(empresaMod);
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> findAll() {
		Query q = sessionFactory.getCurrentSession().createQuery("from Empresa order by nombre");
		return (List<Empresa>) q.list();
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> findByNombre(String minombre) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Empresa where nombre LIKE :nombre");
		minombre = "%"+minombre+"%";
		q.setParameter("nombre", minombre);
		return (List<Empresa>) q.list();
	}

	public Empresa findByCif(String micif) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Empresa where cif=:cif");
		q.setParameter("cif", micif);
		return (Empresa) q.list().get(0);
	}

	public Empresa findByAdmin(Usuario miusuario) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Empresa where idadmin=:idadmin");
		q.setParameter("idadmin", miusuario.getIdUsuario());
		return (Empresa) q.list().get(0);
	}

	public Empresa findById(Long miid) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Empresa where idEmpresa=:idEmpresa");
		q.setParameter("idEmpresa", miid);
		return (Empresa) q.list().get(0);
	}


	
	

}
