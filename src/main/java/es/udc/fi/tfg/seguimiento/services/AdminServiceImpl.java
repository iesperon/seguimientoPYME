package es.udc.fi.tfg.seguimiento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.IvaDAO;
import es.udc.fi.tfg.seguimiento.model.Iva;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private IvaDAO ivaDAO = null;
	
	public void setIvaDAO (IvaDAO ivaDAO){
		this.ivaDAO = ivaDAO;
	}

	public void registroIVA(Iva miiva) {
		ivaDAO.insert(miiva);	
	}

	public void eliminarIVA(Iva miiva) {
		ivaDAO.delete(miiva);
	}
	
}
