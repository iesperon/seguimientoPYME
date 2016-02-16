package es.udc.fi.tfg.model.iva;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.annotations.Immutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
@EnableTransactionManagement
@Immutable
public class JdbcIvaDAO implements IvaDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Long insert(Iva miiva) {
		if(miiva.getId_iva() != null){
			throw new RuntimeException("Intento de creación de IVA ya persistente");
		}
		return (Long) sessionFactory.getCurrentSession().save(miiva);
	}

	public Iva update(Iva iva) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Iva miiva) {
		sessionFactory.getCurrentSession().delete(miiva);
	}

	public Iva find(Integer porcentaje) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Iva> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
