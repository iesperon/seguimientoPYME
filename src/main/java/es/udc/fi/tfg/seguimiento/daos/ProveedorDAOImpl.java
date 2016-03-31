package es.udc.fi.tfg.seguimiento.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Proveedor;

@Repository
@EnableTransactionManagement
public class ProveedorDAOImpl implements ProveedorDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Long create(Proveedor miproveedor) {
		return (Long) sessionFactory.getCurrentSession().save(miproveedor);
	}

	public void update(Proveedor miproveedor) {
		Proveedor proveedorMod = findById(miproveedor.getIdProveedor());
		proveedorMod.setNombre(miproveedor.getNombre());
		proveedorMod.setCif(miproveedor.getCif());
		
		sessionFactory.getCurrentSession().update(proveedorMod);
		
	}

	public void remove(Proveedor miproveedor) {
		sessionFactory.getCurrentSession().delete(miproveedor);
	}

	public Proveedor findById(Long miid) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Proveedor where idProveedor=:idProveedor");
		q.setParameter("idProveedor", miid);
		return (Proveedor) q.list().get(0);
		}
	
	@SuppressWarnings("unchecked")
	public List<Proveedor> findByEmpresa(Empresa miempresa) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Proveedor where idEmpresa = :idEmpresa order by nombre");
		q.setParameter("idEmpresa", miempresa.getIdEmpresa());
		return (List<Proveedor>) q.list();
	}
}
