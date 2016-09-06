package es.udc.fi.tfg.seguimiento.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Gasto;
import es.udc.fi.tfg.seguimiento.model.PedidoProveedor;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Proveedor;
import es.udc.fi.tfg.seguimiento.model.Ticket;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.ContabilidadService;
import es.udc.fi.tfg.seguimiento.services.EmpresaService;
import es.udc.fi.tfg.seguimiento.services.ProductoService;
import es.udc.fi.tfg.seguimiento.services.UserService;
import es.udc.fi.tfg.seguimiento.utils.Estadisticas;
import es.udc.fi.tfg.seguimiento.utils.FormProveedorPedido;
import es.udc.fi.tfg.seguimiento.validator.GastoValidator;
import es.udc.fi.tfg.seguimiento.validator.ProveedorValidator;

@Controller
@RequestMapping(value = "/admin/contabilidad")
public class AdminContabilidadController {

	
	@Autowired 
	private UserService usuarioService;
		
	@Autowired
	private ContabilidadService contabilidadService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private ProveedorValidator proveedorValidator;
	
	@InitBinder("proveedor")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(proveedorValidator);
	}
	
	@Autowired
	private GastoValidator gastoValidator;
	
	@InitBinder("newGasto")
	protected void initBinderGasto(WebDataBinder binder) {
		binder.setValidator(gastoValidator);
	}
	
	// *********************** GASTOS *********************
	
	@RequestMapping(value = "/gastos", method = RequestMethod.GET)
	public ModelAndView gastos() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		List<Gasto> gastos = contabilidadService.buscarGastosPorEmpresa(miempresa);
		
		model.addObject("gastoslist", gastos);
		model.addObject("newGasto", new Gasto());
		model.setViewName("gastos");
		return model;
	}
	
	@RequestMapping(value = "/addGasto", method = RequestMethod.POST)
	public String addGasto(@Validated Gasto newGasto, BindingResult result, ModelAndView model) {
		if(result.hasErrors()){
			return "gastos";
		}else{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		newGasto.setEmpresa(miempresa);
		contabilidadService.registroGasto(newGasto);
		
		return "redirect:/admin/contabilidad/gastos";}
	}
	
	@RequestMapping(value="/eliminarGasto",method = RequestMethod.GET)
	public String eliminarGasto(ModelAndView model, Long idGasto){
		contabilidadService.eliminarGasto(contabilidadService.buscarGastoPorId(idGasto));
		return "redirect:/admin/contabilidad/gastos";
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
			return "redirect:/admin/contabilidad/gastos";
	}
	
	// ******************* PROVEEDORES **********************
	
	@RequestMapping(value = "/proveedores", method = RequestMethod.GET)
	public ModelAndView proveedores() {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		List<Proveedor> proveedores = contabilidadService.buscarProveedorActivoPorEmpresa(miempresa);
		List<Proveedor> allProveedor = contabilidadService.buscarProveedorPorEmpresa(miempresa);
		List<PedidoProveedor> pedidos = contabilidadService.buscarPedidos(allProveedor);

		mav.addObject("pedidoslist", pedidos);
		mav.addObject("proveedorlist", proveedores);
		mav.addObject("proveedor", new Proveedor());
		mav.setViewName("proveedores");
		return mav;
	}
	
	@RequestMapping(value = "/addProveedor", method = RequestMethod.POST)
	public String addProveedor(@Validated Proveedor proveedor, BindingResult result, ModelAndView model) {
		if (result.hasErrors()) {
			
			return "proveedores";		
			
		} else {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		proveedor.setEmpresa(miempresa);
		contabilidadService.registroProveedor(proveedor);
		
		return "redirect:/admin/contabilidad/proveedores";
		}
	}
	
	@RequestMapping(value="/eliminarProveedor",method = RequestMethod.GET)
	public String eliminarProveedor(Model model, Long idProveedor){
		contabilidadService.eliminarProveedor(contabilidadService.buscarProveedorPorId(idProveedor));
		return "redirect:/admin/contabilidad/proveedores";
	}
	
	@RequestMapping(value="/editarProveedor",method = RequestMethod.GET)
	public ModelAndView editarProveedor(ModelAndView model, Long idProveedor){
		Proveedor miproveedor = contabilidadService.buscarProveedorPorId(idProveedor);
		
		model.addObject("idProveedor", idProveedor);
		model.addObject("proveedor", miproveedor);
		model.setViewName("editarProveedor");
		return model;
	}
	
	@RequestMapping(value="/confEdProveedor",method = RequestMethod.POST)
	public String confEdProveedor(Model model, Proveedor miproveedor, Long idProveedor){
		contabilidadService.actualizarProveedor(miproveedor);	
		model.addAttribute("proveedoreditado", miproveedor);
		return "redirect:/admin/contabilidad/proveedores";
	}
	// ******************** PEDIDOS **********************
	@RequestMapping(value = "/crearPedido", method = RequestMethod.GET)
	public ModelAndView crearPedido(Long idProveedor, ModelAndView model) {
		model.addObject("idProveedor", idProveedor);
		model.addObject("pedido", new FormProveedorPedido());
		model.setViewName("crearPedido");
		return model;
	}
	
	@RequestMapping(value = "/addPedido", method = RequestMethod.POST)
	public String addPedido(FormProveedorPedido form, BindingResult result, ModelAndView model) {
		
		PedidoProveedor pedido = form.getPedido();
		Proveedor proveedor = contabilidadService.buscarProveedorPorId(form.getIdProveedor());
		
		pedido.setProveedor(proveedor);
		pedido.setEmpresa(proveedor.getEmpresa());
		contabilidadService.registroPedido(pedido);
		return "redirect:/admin/contabilidad/proveedores";
	}
	
	@RequestMapping(value="/eliminarPedido",method = RequestMethod.GET)
	public String eliminarPedido(Model model, Long idPedido){
		contabilidadService.eliminarPedido(contabilidadService.buscarPedidoPorId(idPedido));
		return "redirect:/admin/contabilidad/proveedores";
	}
	
	@RequestMapping(value="/editarPedido",method = RequestMethod.GET)
	public ModelAndView editarPedido(ModelAndView model, Long idPedido){
		PedidoProveedor mipedido = contabilidadService.buscarPedidoPorId(idPedido);
		model.addObject("idPedido", idPedido);
		model.addObject("pedido", mipedido);
		model.setViewName("editarPedido");
		return model;
	}
	
	@RequestMapping(value="/confEdicPedido",method = RequestMethod.POST)
	public String confEdicPedido(Model model, PedidoProveedor mipedido, Long idPedido){
		contabilidadService.actualizarPedido(mipedido);
		model.addAttribute("pedidoeditado", mipedido);
		return "redirect:/admin/contabilidad/proveedores";
	}
	
	//****************************** ESTADISTICAS ************************************
	
	@RequestMapping(value = "/estadisticas", method = RequestMethod.GET)
	public ModelAndView estadisticas( ModelAndView model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		//List<Estadisticas> estadisticas =new ArrayList<Estadisticas>();
		List<Producto> productos = productoService.buscarProductoPorEmpresa(miempresa);
		List<Centro> centros = empresaService.obtenerCentros(miempresa);
		List<Usuario> usuarios = usuarioService.buscarUsuarioPorEmpresa(miempresa);
		List<Proveedor> proveedores = contabilidadService.buscarProveedorActivoPorEmpresa(miempresa);
//		for(Producto producto:productos){
//			Long cantidad = contabilidadService.numeroVentasProd(producto);
//			Estadisticas estad = new Estadisticas();
//			if(cantidad!=null){
//				estad.setCantidad(cantidad);
//				estad.setProducto(producto);
//				estadisticas.add(estad);
//			}
//		}
		List<Estadisticas> estadisticasProd = contabilidadService.estVentasProd(productos);
		List<Estadisticas> estadisticasCentroVentas = contabilidadService.estCentroVent(centros); 
		List<Estadisticas> estadisticasEmplVentas = contabilidadService.estEmplVent(usuarios);
		List<Estadisticas> estadisticasPedidoProveedor = contabilidadService.estPedidoProveedor(proveedores);
		model.addObject("estadisticasProd", estadisticasProd);
		model.addObject("estadisticasCentroVentas", estadisticasCentroVentas);
		model.addObject("estadisticasEmplVentas", estadisticasEmplVentas);
		model.addObject("estadisticasPedidoProveedor", estadisticasPedidoProveedor);
		model.setViewName("estadisticas");
		return model;
	}
}
