package es.udc.fi.tfg.seguimiento.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Iva;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Stock;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.EmpresaService;
import es.udc.fi.tfg.seguimiento.services.ProductoService;
import es.udc.fi.tfg.seguimiento.services.UserService;
import es.udc.fi.tfg.seguimiento.utils.Form;
import es.udc.fi.tfg.seguimiento.utils.FormEmpresaAdmin;

@Controller
@RequestMapping(value = "/admin/empresa")
public class AdminEmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired 
	private UserService usuarioService;
	
	@Autowired 
	private ProductoService productoService;
	

	//	*********************** CENTROS ***********************
	
	@RequestMapping(value = "/centros", method = RequestMethod.GET)
	public ModelAndView centros() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		List<Centro> centros=empresaService.obtenerCentrosActivos(miempresa);
		
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
			empresaService.registroCentro(centro,miempresa);
			if (miusuario.getCentro()==null){
				miusuario.setCentro(centro);
				usuarioService.actualizarUsuario(miusuario);
			}
			for(Producto miproducto : miempresa.getProducto()){
				Stock mistock = new Stock();
				mistock.setCentro(centro);
				mistock.setProducto(miproducto);
				mistock.setStockActual(0);
				mistock.setStockMin(0);
				productoService.registroStock(mistock);
			}
		
			return "redirect:/admin/empresa/centros";
		}
	}
	
	@RequestMapping(value="/eliminarCentro",method = RequestMethod.GET)
	public String eliminarEntrada(Model model, Long idCentro){
		empresaService.eliminarCentro(empresaService.buscarCentroPorId(idCentro));
		return "redirect:/admin/empresa/centros";
	}
	
	@RequestMapping(value="/editarCentro",method = RequestMethod.GET)
	public ModelAndView editarCentro(ModelAndView model, Long idCentro){
		Centro micentro = empresaService.buscarCentroPorId(idCentro);
		model.addObject("idCentro",idCentro);
		model.addObject("centro", micentro);
		model.setViewName("editarCentro");
		return model;
	}
	
	@RequestMapping(value="/confirmarEdicion",method = RequestMethod.POST)
	public String confirmarEdicion(Model model, Centro micentro, Long idCentro){
			empresaService.actualizarCentro(micentro);
			model.addAttribute("centroeditado",micentro);
			return "redirect:/admin/empresa/centros";
	}
	
	// ********************* EMPLEADOS *****************************
	

	
	@RequestMapping(value = "/empleados", method = RequestMethod.GET)
	public ModelAndView empleados() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		
		List<Centro> centros =empresaService.buscarCentroPorEmpresa(miempresa);
		List <Usuario> usuarios = usuarioService.buscarUsuarioPorEmpresa(miempresa);
		
				
		model.addObject("usuarioslist", usuarios);
		model.addObject("centroslist", centros);
		model.addObject("myForm", new Form());
		model.setViewName("empleados");
		return model;
	}
	
	@RequestMapping(value = "/addEmpleado", method = RequestMethod.POST)
	public String addEmpleado(Form myForm, BindingResult result, ModelAndView model) {
		Usuario usuario= myForm.getUsuario();
		Long idCentro = myForm.getIdCentro();
		Centro micentro = empresaService.buscarCentroPorId(idCentro);
		usuario.setCentro(micentro);
		usuario.setEnabled(true);
		usuarioService.registroUsuario(usuario);
		model.addObject("empleadoNuevo", usuario);
		return "redirect:/admin/empresa/empleados";
		
	}
	
	@RequestMapping(value="/editarEmpleado",method = RequestMethod.GET)
	public ModelAndView editarEmpleado(ModelAndView model, Long idUsuario){
		Usuario miusuario = usuarioService.buscarUsuarioPorId(idUsuario);
		Form miForm = new Form();
		miForm.setUsuario(miusuario);
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		List<Centro> centros =new ArrayList<Centro> (miempresa.getCentro());
		
		model.addObject("idUsuario",idUsuario);
		//model.addObject("usuario", miusuario);
		model.addObject("centroslist", centros);
		model.addObject("myForm", miForm);
		model.setViewName("editarEmpleado");
		return model;
	}
	
	@RequestMapping(value = "/crearEmpleado",method = RequestMethod.GET)
	public ModelAndView crearEmpleado(Long idCentro, ModelAndView model) {
		model.addObject("idCentro", idCentro);
		model.addObject("myForm", new Form());
		model.setViewName("crearEmpleado");
		return model;
	}
	
	@RequestMapping(value="/eliminarEmpleado",method = RequestMethod.GET)
	public String eliminarEmpleado(Model model, Long idUsuario){
		usuarioService.eliminarUsuario(usuarioService.buscarUsuarioPorId(idUsuario));
		return "redirect:/admin/empleados";
	}
	
	//******************* PRODUCTOS *************************
	
	@RequestMapping(value="/productos",method = RequestMethod.GET)
	public ModelAndView Productos(){
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		List<Producto> misproductos = productoService.buscarProductoPorEmpresa(miempresa);
		List<Iva> ivas = productoService.obtenerTodosIva();
		
		model.addObject("ivas", ivas);
		model.addObject("productoslist", misproductos);
		model.addObject("myForm", new Form());
		model.setViewName("productos");
		return model;
	}
	/*@RequestMapping(value = "/empleados",params={"nombre"}, method = RequestMethod.GET)
	public @ResponseBody List<String> buscarEmpleadoPorNombre(@RequestParam(value = "nombre") String nombre) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		List<Usuario> u = usuarioService.buscarUsuarioPorNombre(nombre, miempresa);		
		Usuario u1 = u.get(0);
		u1.setCentro(null);
		List<String> u2 = new ArrayList<>();
		u2.add(u1.getNombre()+" "+u1.getApellido1());
		return u2;
	}*/
	@RequestMapping(value = "/producto",params={"idProducto"}, method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Producto producto(@RequestParam(value="idProducto") Long idProducto){
		Producto miproducto = productoService.buscarProductoPorId(idProducto);
		
		Producto productoMod = new Producto();
		productoMod.setCodProd(miproducto.getCodProd());
		productoMod.setDescripcion(miproducto.getDescripcion());
		productoMod.setDescuento(miproducto.getDescuento());
		productoMod.setFoto(miproducto.getFoto());
		productoMod.setMarca(miproducto.getMarca());
		productoMod.setNombre(miproducto.getNombre());
		productoMod.setPrecio(miproducto.getPrecio());
		productoMod.setIdProducto(miproducto.getIdProducto());
		
		return productoMod;
	
	}

	
	@RequestMapping(value = "/addProducto", method = RequestMethod.POST)
	public String addProducto(Form myForm, BindingResult result, ModelAndView model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		Producto producto = myForm.getProducto();
		Long idIva = myForm.getIdIva();
		Iva miIva = productoService.buscarIvaPorId(idIva);
		
		producto.setIva(miIva);
		producto.setEmpresa(miempresa);

		productoService.registroProducto(producto);
		for (Centro centro : miempresa.getCentro()) {
			Stock stock = new Stock();
			stock.setCentro(centro);
			stock.setProducto(producto);
			stock.setStockActual(0);
			stock.setStockMin(0);
			productoService.registroStock(stock);
		}
		
		return "redirect:/admin/empresa/productos";
		
	}
	
	@RequestMapping(value="/editarProducto",method = RequestMethod.GET)
	public ModelAndView editarProducto(ModelAndView model, Long idProducto){
		Producto miproducto = productoService.buscarProductoPorId(idProducto);
		model.addObject("idProducto",idProducto);
		model.addObject("producto", miproducto);
		model.setViewName("editarProducto");
		return model;
	}
	
	@RequestMapping(value="/confEdicProd",method = RequestMethod.POST)
	public String confEdicProd(Model model, Producto miproducto, Long idProducto){
		productoService.actualizarProducto(miproducto);
		model.addAttribute("productoeditado", miproducto);
		return "redirect:/admin/empresa/productos";
	}
	
	@RequestMapping(value="/eliminarProducto",method = RequestMethod.GET)
	public String eliminarProducto(Model model, Long idProducto){
		productoService.eliminarProducto(productoService.buscarProductoPorId(idProducto));
		return "redirect:/admin/empresa/productos";
	}
	
	// ********************** EMPRESA *****************************
	
	
	@RequestMapping(value="/miempresa",method = RequestMethod.GET)
	public ModelAndView miempresa(){
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();

		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		FormEmpresaAdmin form = new FormEmpresaAdmin(miusuario, miempresa);
		
		mav.addObject("usuario", miusuario);
		mav.addObject("empresa", miempresa);
		mav.addObject("form", form);
		mav.setViewName("miempresa");
		return mav;
	}
	

	@RequestMapping(value="/editarEmpresa",method = RequestMethod.POST)
	public String editarEmpresa(Model model, FormEmpresaAdmin form){
		Empresa miempresa = form.getEmpresa();
		Usuario miusuario = form.getUsuario();
		usuarioService.actualizarAdmin(miusuario);
		empresaService.actualizarEmpresa(miempresa);
		model.addAttribute("usuarioeditado", miusuario);
		model.addAttribute("empresaeditada", miempresa);
		return "redirect:/admin/empresa/miempresa";
	}
	
}
