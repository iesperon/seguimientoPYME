package es.udc.fi.tfg.seguimiento.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Gasto;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.ContabilidadService;
import es.udc.fi.tfg.seguimiento.services.UserService;

@Controller
@RequestMapping(value = "/user/contabilidad")
public class UserContabilidadController {

	@Autowired 
	private UserService usuarioService;
		
	@Autowired
	private ContabilidadService contabilidadService;
	
	// *********************** GASTOS *********************
	
		@RequestMapping(value = "/gastosEmp", method = RequestMethod.GET)
		public ModelAndView gastos() {
			ModelAndView model = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String login = auth.getName();
			
			Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
			Empresa miempresa = miusuario.getCentro().getEmpresa();
			List<Gasto> gastos = contabilidadService.buscarGastosPorEmpresa(miempresa);
			
			model.addObject("gastoslist", gastos);
			model.addObject("newGasto", new Gasto());
			model.setViewName("gastosEmp");
			return model;
		}
		
		@RequestMapping(value = "/addGastoEmp", method = RequestMethod.POST)
		public String addGasto(Gasto newGasto, BindingResult result, ModelAndView model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String login = auth.getName();
			
			Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
			Empresa miempresa = miusuario.getCentro().getEmpresa();
			
			newGasto.setEmpresa(miempresa);
			contabilidadService.registroGasto(newGasto);
			
			return "redirect:/user/contabilidad/gastosEmp";
		}
		
		@RequestMapping(value="/eliminarGastoEmp",method = RequestMethod.GET)
		public String eliminarGasto(ModelAndView model, Long idGasto){
			contabilidadService.eliminarGasto(contabilidadService.buscarGastoPorId(idGasto));
			return "redirect:/user/contabilidad/gastosEmp";
		}
		
		@RequestMapping(value="/editarGastoEmp",method = RequestMethod.GET)
		public ModelAndView editarGasto(ModelAndView model, Long idGasto){
			Gasto migasto = contabilidadService.buscarGastoPorId(idGasto);
			model.addObject("idGasto", idGasto);
			model.addObject("gasto", migasto);
			model.setViewName("editarGastoEmp");
			return model;
		}
		
		@RequestMapping(value="/confEdGastoEmp",method = RequestMethod.POST)
		public String confEdGasto(Model model, Gasto migasto, Long idGasto){
				contabilidadService.actualizarGasto(migasto);
				model.addAttribute("gastoeditado", migasto);
				return "redirect:/user/contabilidad/gastosEmp";
		}
}
