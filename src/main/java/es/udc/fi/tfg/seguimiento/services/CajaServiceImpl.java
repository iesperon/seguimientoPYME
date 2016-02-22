package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.CierreDAO;
import es.udc.fi.tfg.seguimiento.model.Cierre;

@Service
@Transactional
public class CajaServiceImpl implements CajaService{
	
	@Autowired
	private CierreDAO cierreDAO = null;
	
	public void setCierreDAO (CierreDAO cierreDAO){
		this.cierreDAO = cierreDAO;
	}
	
	//**********CIERRE***********
		public void registroCierre(Cierre micierre) {
			cierreDAO.create(micierre);		
		}

		public void eliminarCierre(Cierre micierre) {
			cierreDAO.remove(micierre);
		}

		public void actualizarCierre(Cierre micierre) {
			cierreDAO.update(micierre);
		}

		public Cierre buscarCierrePorFecha(Timestamp mifecha) {
			return cierreDAO.finByFecha(mifecha);
		}

		public List<Cierre> obtenerTodosCierres() {
			return cierreDAO.findAll();
		}
	
}
