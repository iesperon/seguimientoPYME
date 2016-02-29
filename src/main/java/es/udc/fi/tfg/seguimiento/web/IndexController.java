package es.udc.fi.tfg.seguimiento.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.FormUser;
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
	public ModelAndView index(@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "logout", required = false) String logout) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("myForm", new FormUser());
		
		mav.setViewName("index");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(FormUser myForm, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "index";
		}else{
			Empresa empresa = myForm.getEmpresa();
			Usuario usuario = myForm.getUsuario();
			usuarioService.registroUsuario(usuario);
			empresa.setAdministrador(usuario);
			empresaService.registroEmpresa(empresa);
			return "redirect:/";
		}
	}
	
	
	
	
}
