package es.udc.fi.tfg.seguimiento.web;



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.EmpresaService;
import es.udc.fi.tfg.seguimiento.services.ProductoService;
import es.udc.fi.tfg.seguimiento.services.UserService;
import es.udc.fi.tfg.seguimiento.utils.FileBucket;
import es.udc.fi.tfg.seguimiento.utils.FileUpload;
import es.udc.fi.tfg.seguimiento.utils.Form;

@Controller
public class IndexController {
	
	@Autowired
	private UserService usuarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired 
	private ProductoService productoService;
	
	private static String UPLOAD_LOCATION="C:/Users/iesperon/Pictures/Nueva carpeta";

		

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "logout", required = false) String logout) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("myForm", new Form());
		//mav.addObject("login",  new Login());
		mav.addObject("email", new String());
		mav.addObject("contrasena", new String());

		mav.setViewName("index");
		
		return mav;
		
	}

	@RequestMapping(value = "/403", method = RequestMethod.POST)
	public ModelAndView notAllowed() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("403");
		return mav;
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView notFound() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("404");
		return mav;
	}
	
	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public ModelAndView serverError() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("500");
		return mav;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(Form myForm, BindingResult result, Model model) throws IOException {
		if(result.hasErrors()){
			return "index";
		}else{
			//MultipartFile multipartFile = myForm.getFile();

			// Now do something with file...
			//FileCopyUtils.copy(myForm.getFile().getBytes(), new File( UPLOAD_LOCATION + myForm.getFile().getOriginalFilename()));
			//String fileName = multipartFile.getOriginalFilename();
			
			Empresa empresa = myForm.getEmpresa();
			Usuario usuario = myForm.getUsuario();
			usuario.setEnabled(true);
			usuarioService.registroAdmin(usuario);
			empresa.setAdministrador(usuario);
			empresaService.registroEmpresa(empresa);
			
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value = "/welcome")
    protected View welcome() {
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            return new RedirectView("admin/empresa/miempresa");
        }
        return new RedirectView("user/contabilidad/gastosEmp");
    }
	
	
	@RequestMapping(value = "/catalogo/{id}", method = RequestMethod.GET)
	public ModelAndView catalogo(@PathVariable Long id){
		ModelAndView model = new ModelAndView();
		Empresa miempresa = empresaService.buscarEmpresaPorId(id);
		List<Producto> misproductos = productoService.buscarProductoPorEmpresa(miempresa);
		model.addObject("productoslist", misproductos);
		model.addObject("empresa", miempresa);
		model.setViewName("catalogo");
		return model;
	}
	
}
