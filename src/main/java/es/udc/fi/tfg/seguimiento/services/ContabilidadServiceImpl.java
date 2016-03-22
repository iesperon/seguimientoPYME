package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.GastoDAO;
import es.udc.fi.tfg.seguimiento.model.Gasto;

@Service
@Transactional
public class ContabilidadServiceImpl implements ContabilidadService {
	
	@Autowired
	private GastoDAO gastoDAO = null;
	
	public void setGastoDAO (GastoDAO gastoDAO){
		this.gastoDAO = gastoDAO;
	}

	public void registroGasto(Gasto migasto) {
		gastoDAO.create(migasto);
	}

	public void eliminarGasto(Gasto migasto) {
		gastoDAO.remove(migasto);
	}

	public void actualizarGasto(Gasto migasto) {
		gastoDAO.update(migasto);
	}

	public Gasto buscarGastoPorId(Long miid) {
		return gastoDAO.findById(miid);
	}

	public List<Gasto> buscarGastoPorEstado(String miestado) {
		return gastoDAO.findByEstado(miestado);
	}

	public List<Gasto> buscarGastoPorConcepto(String miconcepto) {
		return gastoDAO.findByConcepto(miconcepto);
	}

}
