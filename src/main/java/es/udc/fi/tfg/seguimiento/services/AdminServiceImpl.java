package es.udc.fi.tfg.seguimiento.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.CierreDAO;
import es.udc.fi.tfg.seguimiento.daos.IvaDAO;
import es.udc.fi.tfg.seguimiento.model.Cierre;
import es.udc.fi.tfg.seguimiento.model.Iva;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private IvaDAO ivaDAO = null;
	
	public void setIvaDAO (IvaDAO ivaDAO){
		this.ivaDAO = ivaDAO;
	}
	
	@Autowired
	private CierreDAO cierreDAO = null;
	
	public void setCierreDAO (CierreDAO cierreDAO){
		this.cierreDAO = cierreDAO;
	}

	//**********IVA***********
	public void registroIVA(Iva miiva) {
		ivaDAO.insert(miiva);	
	}

	public void eliminarIVA(Iva miiva) {
		ivaDAO.delete(miiva);
	}

	public void actualizarIVA(Iva miiva) {
		ivaDAO.update(miiva);
	}

	public Iva buscarIvaPorPorcentaje(Integer miporcentaje) {
		return ivaDAO.findbyPorcentaje(miporcentaje);
	}

	public List<Iva> obtenerTodosIva() {
		return ivaDAO.findAll();
	}

	//**********Cierre***********
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
