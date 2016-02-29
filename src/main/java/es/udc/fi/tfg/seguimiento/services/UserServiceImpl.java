package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.UsuarioDAO;
import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Usuario;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UsuarioDAO usuarioDAO = null;
	
	public void setUsuarioDAO (UsuarioDAO usuarioDAO){
		this.usuarioDAO = usuarioDAO;
	}
	
	public void registroUsuario(Usuario miusuario) {
		usuarioDAO.create(miusuario);
	}

	public void eliminarUsuario(Usuario miusuario) {
		usuarioDAO.remove(miusuario);
	}

	public void actualizarUsuario(Usuario miusuario) {
		usuarioDAO.update(miusuario);
	}

	public List<Usuario> buscarUsuarioPorNombre(String minombre, Centro micentro) {
		return usuarioDAO.findByName(minombre, micentro);
	}

	public Usuario buscarUsuarioPorDni(String midni, Centro micentro) {
		return usuarioDAO.findByDni(midni, micentro);
	}

	public List<Usuario> buscarUsuarioPorEmpresa(Empresa miempresa) {
		return usuarioDAO.findByEmpresa(miempresa);
	}

	public List<Usuario> buscarUsuarioPorCentro(Centro micentro) {
		return usuarioDAO.findByCentro(micentro);
	}


}
