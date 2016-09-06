package es.udc.fi.tfg.seguimiento.web;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.EmpresaService;
import es.udc.fi.tfg.seguimiento.services.ProductoService;
import es.udc.fi.tfg.seguimiento.services.UserService;
import es.udc.fi.tfg.seguimiento.utils.Form;
import es.udc.fi.tfg.seguimiento.validator.UsuarioValidator;

@Controller
public class IndexController {
	
	@Autowired
	private UserService usuarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired 
	private ProductoService productoService;
	
	@Autowired
	UsuarioValidator usuarioValidator;
	
	@InitBinder("myForm")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(usuarioValidator);
	}
	

	private static String UPLOAD_LOCATION="C:/Windows/Temp/";


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
	public String addUser(@Validated Form myForm, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "index";
		}else{
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
	
	@RequestMapping(value = "/showImage/{idProducto}", method = RequestMethod.GET)
	public void showImage(@PathVariable("idProducto")Long idProducto,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Producto miproducto = productoService.buscarProductoPorId(idProducto);
	
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		ServletOutputStream out;
		out = response.getOutputStream();
		FileInputStream fin = new FileInputStream(UPLOAD_LOCATION+miproducto.getFoto());
		
		BufferedInputStream bin = new BufferedInputStream(fin);
		BufferedOutputStream bout = new BufferedOutputStream(out);
		int ch =0; ;
		while((ch=bin.read())!=-1){
			bout.write(ch);
		}
	
		bin.close();fin.close();bout.close(); out.close();
	}
}
