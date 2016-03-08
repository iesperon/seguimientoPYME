package es.udc.fi.tfg.seguimiento.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.FormUser;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.EmpresaService;
import es.udc.fi.tfg.seguimiento.services.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired 
	private UserService usuarioService;

	@RequestMapping(value = "/centros", method = RequestMethod.GET)
	public ModelAndView centros() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		List<Centro> centros = empresaService.obtenerCentros(miempresa);
		
		model.addObject("centroslist",centros);
		model.addObject("myCentro", new Centro());
		model.setViewName("centros");
		return model;
	}
	
	@RequestMapping(value = "/addCentro", method = RequestMethod.POST)
	public String addCentro(Centro centro, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "index";
		}else{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String login = auth.getName();
			
			Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
			Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
			centro.setEmpresa(miempresa);
			empresaService.registroCentro(centro);
			if (miusuario.getCentro()==null){
				miusuario.setCentro(centro);
				usuarioService.actualizarUsuario(miusuario);
			}
		
			return "redirect:/admin/centros";
		}
	}
	
	@RequestMapping(value="/eliminarCentro",method = RequestMethod.GET)
	public String eliminarEntrada(Model model, Long idCentro, String tipo){
		empresaService.eliminarCentro(empresaService.buscarCentroPorId(idCentro));
		return "redirect:/admin/centros";
	}
	
	
	@RequestMapping(value = "/empleados", method = RequestMethod.GET)
	public ModelAndView empleados() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		List<Centro> centros = empresaService.obtenerCentros(miempresa);
		List <Usuario> usuarios = usuarioService.buscarUsuarioPorEmpresa(miempresa);
		
		model.addObject("usuarioslist", usuarios);
		model.addObject("centroslist", centros);
		model.addObject("myUsuario", new Usuario());
		model.setViewName("empleados");
		return model;
	}
	
	
	@RequestMapping(value = "/addEmpleado", method = RequestMethod.POST)
	public String addEmpleado(FormUser myForm, BindingResult result, ModelAndView model) {
		Usuario usuario= myForm.getUsuario();
		Long idCentro = myForm.getIdCentro();
		Centro micentro = empresaService.buscarCentroPorId(idCentro);
		usuario.setCentro(micentro);
		usuario.setEnabled(true);
		usuarioService.registroUsuario(usuario);
		model.addObject("empleadoNuevo", usuario);
		return "redirect:/admin/empleados";
		
	}
	
	@RequestMapping(value = "/crearEmpleado",method = RequestMethod.GET)
	public ModelAndView crearEmpleado(Long idCentro, ModelAndView model) {
		model.addObject("idCentro", idCentro);
		model.addObject("myForm", new FormUser());
		model.setViewName("crearEmpleado");
		return model;
	}
	
}
