package es.udc.fi.tfg.seguimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.seguimiento.daos.RolDAO;
import es.udc.fi.tfg.seguimiento.daos.UsuarioDAO;
import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Rol;
import es.udc.fi.tfg.seguimiento.model.Usuario;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UsuarioDAO usuarioDAO = null;
	
	public void setUsuarioDAO (UsuarioDAO usuarioDAO){
		this.usuarioDAO = usuarioDAO;
	}
	
	@Autowired
	private RolDAO rolDAO = null;
	
	public void setRolDAO (RolDAO rolDAO){
		this.rolDAO = rolDAO;
	}
	
	public void registroRol(Rol rol){
		rolDAO.create(rol);
	}
	
	public Rol buscarRolPorRol(String rol){
		return rolDAO.findByRol(rol);
	}

	
	public void registroUsuario(Usuario miusuario) {
		Rol mirol=rolDAO.findByRol("ROLE_USER");
		miusuario.setRol(mirol);
		usuarioDAO.create(miusuario);
		miusuario.getCentro().getUsuario().add(miusuario);
	}

	public void eliminarUsuario(Usuario miusuario) {
		usuarioDAO.remove(miusuario);
		miusuario.getCentro().getUsuario().remove(miusuario);
	}

	public void actualizarUsuario(Usuario miusuario) {
		usuarioDAO.update(miusuario);
	}

	public List<Usuario> buscarUsuarioPorNombre(String minombre, Empresa empresa) {
		return usuarioDAO.findByName(minombre, empresa);
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

	public Usuario buscarUsuarioPorEmail(String miemail) {
		return usuarioDAO.findByEmail(miemail);
	}

	public void registroAdmin(Usuario miusuario) {
		Rol mirol=rolDAO.findByRol("ROLE_ADMIN");
		miusuario.setRol(mirol);
		usuarioDAO.create(miusuario);
	}

	public Usuario buscarUsuarioPorId(Long idUsuario) {
		return usuarioDAO.findById(idUsuario);
	}




}
