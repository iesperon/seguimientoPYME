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
	public String addGasto(Gasto newGasto, BindingResult result, ModelAndView model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		newGasto.setEmpresa(miempresa);
		contabilidadService.registroGasto(newGasto);
		
		return "redirect:/admin/contabilidad/gastos";
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
	public String addProveedor(Proveedor proveedor, BindingResult result, ModelAndView model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		proveedor.setEmpresa(miempresa);
		contabilidadService.registroProveedor(proveedor);
		
		return "redirect:/admin/contabilidad/proveedores";
	}
	
	@RequestMapping(value="/eliminarProveedor",method = RequestMethod.GET)
	public String eliminarProveedor(Model model, Long idProveedor){
		contabilidadService.eliminarProveedor(contabilidadService.buscarProveedorPorId(idProveedor));
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

//		for(Producto producto:productos){
//			Long cantidad = contabilidadService.numeroVentasProd(producto);
//			Estadisticas estad = new Estadisticas();
//			if(cantidad!=null){
//				estad.setCantidad(cantidad);
//				estad.setProducto(producto);
//				estadisticas.add(estad);
//			}
//		}
		List<Estadisticas> estadisticas = contabilidadService.estVentasProd(productos); 
		model.addObject("estadisticas", estadisticas);
		model.setViewName("estadisticas");
		return model;
	}
}
