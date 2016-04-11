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
public class CajaServiceImpl implements CajaService {

	@Autowired
	private CierreCajaDAO cierreCajaDAO = null;
	

	public CierreCajaDAO getCierreCajaDAO() {
		return cierreCajaDAO;
	}

	public void setCierreCajaDAO(CierreCajaDAO cierreCajaDAO) {
		this.cierreCajaDAO = cierreCajaDAO;
	}

	// **********CIERRE***********
	public void registroCierre(CierreCaja micierre) {
		cierreCajaDAO.create(micierre);
	}

	public void eliminarCierre(CierreCaja micierre) {
		cierreCajaDAO.remove(micierre);
	}

	public void actualizarCierre(CierreCaja micierre) {
		cierreCajaDAO.update(micierre);
	}

	public CierreCaja buscarCierrePorFecha(Timestamp mifecha) {
		return cierreCajaDAO.finByFecha(mifecha);
	}

	public List<CierreCaja> obtenerTodosCierres() {
		return cierreCajaDAO.findAll();
	}

}
