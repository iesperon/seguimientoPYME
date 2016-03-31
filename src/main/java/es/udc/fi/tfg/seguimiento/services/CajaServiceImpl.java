package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.CierreCajaDAO;
import es.udc.fi.tfg.seguimiento.model.CierreCaja;

@Service
@Transactional
public class CajaServiceImpl implements CajaService{
	
	@Autowired
	private CierreCajaDAO cierreDAO = null;
	
	public void setCierreDAO (CierreCajaDAO cierreDAO){
		this.cierreDAO = cierreDAO;
	}
	
	//**********CIERRE***********
		public void registroCierre(CierreCaja micierre) {
			cierreDAO.create(micierre);		
		}

		public void eliminarCierre(CierreCaja micierre) {
			cierreDAO.remove(micierre);
		}

		public void actualizarCierre(CierreCaja micierre) {
			cierreDAO.update(micierre);
		}

		public CierreCaja buscarCierrePorFecha(Timestamp mifecha) {
			return cierreDAO.finByFecha(mifecha);
		}

		public List<CierreCaja> obtenerTodosCierres() {
			return cierreDAO.findAll();
		}
	
}
