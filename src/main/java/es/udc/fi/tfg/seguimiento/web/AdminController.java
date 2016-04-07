package es.udc.fi.tfg.seguimiento.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.google.gson.Gson;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Form;
import es.udc.fi.tfg.seguimiento.model.Gasto;
import es.udc.fi.tfg.seguimiento.model.Iva;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Proveedor;
import es.udc.fi.tfg.seguimiento.model.Stock;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.ContabilidadService;
import es.udc.fi.tfg.seguimiento.services.EmpresaService;
import es.udc.fi.tfg.seguimiento.services.ProductoService;
import es.udc.fi.tfg.seguimiento.services.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired 
	private UserService usuarioService;
	
	@Autowired 
	private ProductoService productoService;
	
	@Autowired
	private ContabilidadService contabilidadService;

	@RequestMapping(value = "/centros", method = RequestMethod.GET)
	public ModelAndView centros() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		List<Centro> centros=new ArrayList<Centro>(miempresa.getCentro());
		
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
	public String eliminarEntrada(Model model, Long idCentro){
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
		
		List<Centro> centros =new ArrayList<Centro> (miempresa.getCentro());
		List <Usuario> usuarios = usuarioService.buscarUsuarioPorEmpresa(miempresa);
		
		model.addObject("usuarioslist", usuarios);
		model.addObject("centroslist", centros);
		model.addObject("myForm", new Form());
		model.setViewName("empleados");
		return model;
	}
	
	@RequestMapping(value = "/empleados",params={"nombre"}, method = RequestMethod.GET)
	public @ResponseBody List<Usuario> buscarEmpleadoPorNombre(@RequestParam(value = "nombre") String nombre) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		List<Usuario> u = usuarioService.buscarUsuarioPorNombre(nombre, miempresa);		
		return u;
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
		return "redirect:/admin/empleados";
		
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
			return "redirect:/admin/centros";
	}
	
	@RequestMapping(value="/eliminarEmpleado",method = RequestMethod.GET)
	public String eliminarEmpleado(Model model, Long idUsuario){
		usuarioService.eliminarUsuario(usuarioService.buscarUsuarioPorId(idUsuario));
		return "redirect:/admin/empleados";
	}
	
	@RequestMapping(value="/productos",method = RequestMethod.GET)
	public ModelAndView Productos(){
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		List<Producto> misproductos = new ArrayList<Producto>(miempresa.getProducto());
		List<Iva> ivas = productoService.obtenerTodosIva();
		
		model.addObject("ivas", ivas);
		model.addObject("productoslist", misproductos);
		model.addObject("myForm", new Form());
		model.setViewName("productos");
		return model;
	}
	
	@RequestMapping(value = "/addProducto", method = RequestMethod.POST)
	public String addProducto(Form myForm, BindingResult result, ModelAndView model) {
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
		
		return "redirect:/admin/productos";
		
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
		return "redirect:/admin/productos";
	}
	
	@RequestMapping(value="/eliminarProducto",method = RequestMethod.GET)
	public String eliminarProducto(Model model, Long idProducto){
		productoService.eliminarProducto(productoService.buscarProductoPorId(idProducto));
		return "redirect:/admin/productos";
	}
	
	@RequestMapping(value="/stock",method = RequestMethod.GET)
	public ModelAndView Stock(Long idCentro, ModelAndView model){
		Centro micentro = empresaService.buscarCentroPorId(idCentro);
		Empresa miempresa = micentro.getEmpresa();
		List<Producto> misproductos = new ArrayList<Producto>(miempresa.getProducto());
		//List<Stock> stockCentro =  new ArrayList<Stock>(micentro.getStock());
		//model.addObject("stocklist",stockCentro);
		model.addObject("centroNombre", micentro.getNombre());
		model.addObject("productoslist", misproductos);
		model.setViewName("stock");
		return model;
	}
	
	
	
	
	@RequestMapping(value = "/gastos", method = RequestMethod.GET)
	public ModelAndView gastos() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		List<Gasto> gastos =new ArrayList<Gasto> (miempresa.getGasto());
		
		model.addObject("gastoslist", gastos);
		model.addObject("newGasto", new Gasto());
		model.setViewName("gastos");
		return model;
	}
	
	@RequestMapping(value = "/addGasto", method = RequestMethod.POST)
	public String addGasto(Gasto newGasto, BindingResult result, ModelAndView model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		newGasto.setEmpresa(miempresa);
		contabilidadService.registroGasto(newGasto);
		
		return "redirect:/admin/gastos";
	}
	
	@RequestMapping(value="/eliminarGasto",method = RequestMethod.GET)
	public String eliminarGasto(ModelAndView model, Long idGasto){
		contabilidadService.eliminarGasto(contabilidadService.buscarGastoPorId(idGasto));
		return "redirect:/admin/gastos";
	}
	
	@RequestMapping(value = "/caja", method = RequestMethod.GET)
	public ModelAndView caja() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("caja");
		return mav;
	}	
	
	
	@RequestMapping(value = "/proveedores", method = RequestMethod.GET)
	public ModelAndView proveedores() {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		List<Proveedor> proveedores = new ArrayList<Proveedor> (miempresa.getProveedor());
		
		mav.addObject("proveedorlist", proveedores);
		mav.addObject("proveedor", new Proveedor());
		mav.setViewName("proveedores");
		return mav;
	}
	
	@RequestMapping(value = "/addProveedor", method = RequestMethod.POST)
	public String addProveedor(Proveedor proveedor, BindingResult result, ModelAndView model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		proveedor.setEmpresa(miempresa);
		contabilidadService.registroProveedor(proveedor);
		
		return "redirect:/admin/proveedores";
	}
	
	@RequestMapping(value="/eliminarProveedor",method = RequestMethod.GET)
	public String eliminarProveedor(Model model, Long idProveedor){
		contabilidadService.eliminarProveedor(contabilidadService.buscarProveedorPorId(idProveedor));
		return "redirect:/admin/proveedores";
	}
	
	@RequestMapping(value="/miempresa",method = RequestMethod.GET)
	public ModelAndView miempresa(){
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		mav.addObject("empresa", miempresa);
		mav.setViewName("miempresa");
		return mav;
	}
	
	@RequestMapping(value="/editarGasto",method = RequestMethod.GET)
	public ModelAndView editarGasto(ModelAndView model, Long idGasto){
		Gasto migasto = contabilidadService.buscarGastoPorId(idGasto);
		model.addObject("idGasto", idGasto);
		model.addObject("gasto", migasto);
		model.setViewName("editarGasto");
		return model;
	}
	
	@RequestMapping(value="/confEdGasto",method = RequestMethod.POST)
	public String confEdGasto(Model model, Gasto migasto, Long idGasto){
			contabilidadService.actualizarGasto(migasto);
			model.addAttribute("gastoeditado", migasto);
			return "redirect:/admin/gastos";
	}

	@RequestMapping(value="/editarEmpresa",method = RequestMethod.POST)
	public String editarEmpresa(Model model, Empresa miempresa){
		empresaService.actualizarEmpresa(miempresa);
		model.addAttribute("empresaeditada", miempresa);
		return "redirect:/admin/miempresa";
}
}
