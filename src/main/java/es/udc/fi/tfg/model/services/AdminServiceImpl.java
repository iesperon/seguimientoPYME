package es.udc.fi.tfg.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.model.iva.Iva;
import es.udc.fi.tfg.model.iva.IvaDAO;

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
