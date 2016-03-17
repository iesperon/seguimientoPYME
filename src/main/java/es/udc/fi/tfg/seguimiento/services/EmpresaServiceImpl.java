package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.CentroDAO;
import es.udc.fi.tfg.seguimiento.daos.EmpresaDAO;
import es.udc.fi.tfg.seguimiento.daos.UsuarioDAO;
import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Usuario;


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
	
	@Autowired
	private UsuarioDAO usuarioDAO = null;
	
	public void setUsuarioDAO (UsuarioDAO usuarioDAO){
		this.usuarioDAO = usuarioDAO;
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
	
	public Empresa buscarEmpresaPorAdmin(Usuario miusuario) {
		return empresaDAO.findByAdmin(miusuario);
	}
	

	//**********CENTRO***********
	public void registroCentro(Centro micentro) {
		centroDAO.create(micentro);
		micentro.getEmpresa().getCentro().add(micentro);
	}

	public void eliminarCentro(Centro micentro) {
		centroDAO.remove(micentro);
		micentro.getEmpresa().getCentro().remove(micentro);
	}

	public void actualizarCentro(Centro micentro) {
		centroDAO.update(micentro);
	}

	public List<Centro> obtenerCentros(Empresa miempresa) {
		return centroDAO.findAllByEmpresa(miempresa);
	}
	
	public Centro buscarCentroPorId(Long miid){
		return centroDAO.findById(miid);
	}


	
}
