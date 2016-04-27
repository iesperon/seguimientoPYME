package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.CentroDAO;
import es.udc.fi.tfg.seguimiento.daos.EmpresaDAO;
import es.udc.fi.tfg.seguimiento.daos.StockDAO;
import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Stock;
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
	private StockDAO stockDAO = null;
	
	public void setStockDAO (StockDAO stockDAO){
		this.stockDAO = stockDAO;
	}
	
	
	//**********EMPRESA***********
	public void registroEmpresa(Empresa miempresa) {
		empresaDAO.create(miempresa);
	}

	public void eliminarEmpresa(Empresa miempresa) {
			empresaDAO.remove(miempresa);
	}

	public void actualizarEmpresa(Empresa miempresa) {
		Empresa empresaMod = empresaDAO.findById(miempresa.getIdEmpresa());
		empresaMod.setNombre(miempresa.getNombre());
		empresaMod.setCif(miempresa.getCif());
		empresaMod.setSector(miempresa.getSector());
		empresaMod.setEmail(miempresa.getEmail());
		empresaMod.setDescripcion(miempresa.getDescripcion());
		empresaMod.setLogo(miempresa.getLogo());
		
		empresaDAO.update(empresaMod);
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
		for (Stock mistock : micentro.getStock()){
			stockDAO.delete(mistock);
		}
		centroDAO.remove(micentro);
		micentro.getEmpresa().getCentro().remove(micentro);
	}

	public void actualizarCentro(Centro micentro) {
		Centro centroMod = centroDAO.findById(micentro.getIdCentro());
		centroMod.setCalle(micentro.getCalle());
		centroMod.setCp(micentro.getCp());
		centroMod.setEmail(micentro.getEmail());
		centroMod.setNombre(micentro.getNombre());
		centroMod.setNumero(micentro.getNumero());
		centroMod.setPoblacion(micentro.getPoblacion());
		centroMod.setProvincia(micentro.getProvincia());
		centroMod.setPais(micentro.getPais());
		centroMod.setTelefono(micentro.getTelefono());
		
		centroDAO.update(centroMod);
	}

	public List<Centro> obtenerCentros(Empresa miempresa) {
		return centroDAO.findAllByEmpresa(miempresa);
	}
	
	public Centro buscarCentroPorId(Long miid){
		return centroDAO.findById(miid);
	}

	public List<Centro> buscarCentroPorEmpresa(Empresa miempresa) {
		return centroDAO.findAllByEmpresa(miempresa);
	}
	
	


	
}
