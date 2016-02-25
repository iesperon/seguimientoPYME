package es.udc.fi.tfg.seguimiento.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.EmpresaService;
import es.udc.fi.tfg.seguimiento.services.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService usuarioService;
	
	@Autowired
	private EmpresaService empresaService;
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("usuario",new Usuario());
		return "index";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(Usuario usuario,Empresa empresa, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "index";
		}else{
			usuarioService.registroUsuario(usuario);
			return "redirect:/";
		}
	}
	
	
	
}
