package es.udc.fi.tfg.seguimiento.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String index(Model model) {
		model.addAttribute("usuario",new Usuario());
		model.addAttribute("empresa",new Empresa());
		//model.addAttribute("myForm", new FormUser());
		return "index";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public void addUser(Usuario usuario, BindingResult result, Model model) {
		//if(result.hasErrors()){
		//	return "index";
		//}else{
			usuarioService.registroUsuario(usuario);
			
		//	return "redirect:/";
		//}
	}
	
	@RequestMapping(value = "/addEmpresa", method = RequestMethod.POST)
	public String addUser(Empresa empresa, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "index";
		}else{
			empresaService.registroEmpresa(empresa);
			return "redirect:/";
		}
	}
	
	/*@RequestMapping(value = "/addUser", method = RequestMethod.POST)
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
	}*/
	
	
	
	
}
