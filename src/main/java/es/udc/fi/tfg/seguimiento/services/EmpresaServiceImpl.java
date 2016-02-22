package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.CentroDAO;
import es.udc.fi.tfg.seguimiento.daos.EmpresaDAO;
import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;

@Service
@Transactional
public class EmpresaServiceImpl implements EmpresaService{
	
	@Autowired
	private EmpresaDAO empresaDAO = null;
	
	public void setEmpresaDAO (EmpresaDAO empresaDAO){
		this.empresaDAO = empresaDAO;
	}
	
	@Autowired
	private CentroDAO centroDAO = null;
	
	public void setCentroDAO (CentroDAO centroDAO){
		this.centroDAO = centroDAO;
	}

	
	//**********EMPRESA***********
	public void registroEmpresa(Empresa miempresa) {
		empresaDAO.create(miempresa);
	}

	public void eliminarEmpresa(Empresa miempresa) {
			empresaDAO.remove(miempresa);
	}

	public void actualizarEmpresa(Empresa miempresa) {
		empresaDAO.update(miempresa);
	}

	public List<Empresa> obtenerTodasEmpresas() {
		return empresaDAO.findAll();
	}

	public Empresa buscarEmpresaPorCif(String micif) {
		return empresaDAO.findByCif(micif);
	}

	public List<Empresa> buscarEmpresaPorNombre(String minombre) {
		return empresaDAO.findByNombre(minombre);
	}

	//**********CENTRO***********
	public void registroCentro(Centro micentro) {
		centroDAO.create(micentro);
	}

	public void eliminarCentro(Centro micentro) {
		centroDAO.remove(micentro);
	}

	public void actualizarCentro(Centro micentro) {
		centroDAO.update(micentro);
	}

	public List<Centro> obtenerCentros(Empresa miempresa) {
		return centroDAO.findAllByEmpresa(miempresa);
	}
}
