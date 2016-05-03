package es.udc.fi.tfg.seguimiento.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Gasto;
import es.udc.fi.tfg.seguimiento.model.Iva;
import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.PedidoProveedor;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Proveedor;
import es.udc.fi.tfg.seguimiento.model.Stock;
import es.udc.fi.tfg.seguimiento.model.Ticket;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.CajaService;
import es.udc.fi.tfg.seguimiento.services.ContabilidadService;
import es.udc.fi.tfg.seguimiento.services.EmpresaService;
import es.udc.fi.tfg.seguimiento.services.ProductoService;
import es.udc.fi.tfg.seguimiento.services.UserService;
import es.udc.fi.tfg.seguimiento.utils.CalcularTicket;
import es.udc.fi.tfg.seguimiento.utils.Form;
import es.udc.fi.tfg.seguimiento.utils.FormProveedorPedido;
import es.udc.fi.tfg.seguimiento.utils.FormTicketProducto;
import es.udc.fi.tfg.seguimiento.utils.SaveImage;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	private static String UPLOAD_LOCATION = "C:/Users/iesperon/Desktop/";
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired 
	private UserService usuarioService;
	
	@Autowired 
	private ProductoService productoService;
	
	@Autowired
	private ContabilidadService contabilidadService;
	
	@Autowired
	private CajaService cajaService;

	@RequestMapping(value = "/centros", method = RequestMethod.GET)
	public ModelAndView centros() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		List<Centro> centros=empresaService.buscarCentroPorEmpresa(miempresa);
		
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
			for(Producto miproducto : miempresa.getProducto()){
				Stock mistock = new Stock();
				mistock.setCentro(centro);
				mistock.setProducto(miproducto);
				mistock.setStockActual(0);
				mistock.setStockMin(0);
				productoService.registroStock(mistock);
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
		
		List<Centro> centros =empresaService.buscarCentroPorEmpresa(miempresa);
		List <Usuario> usuarios = usuarioService.buscarUsuarioPorEmpresa(miempresa);
		
		model.addObject("usuarioslist", usuarios);
		model.addObject("centroslist", centros);
		model.addObject("myForm", new Form());
		model.setViewName("empleados");
		return model;
	}
	
	@RequestMapping(value = "/empleados",params={"nombre"}, method = RequestMethod.GET)
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
		
		List<Producto> misproductos = productoService.buscarProductoPorEmpresa(miempresa);
		List<Iva> ivas = productoService.obtenerTodosIva();
		
		model.addObject("ivas", ivas);
		model.addObject("productoslist", misproductos);
		model.addObject("myForm", new Form());
		model.setViewName("productos");
		return model;
	}

	@RequestMapping(value = "/addProducto", method = RequestMethod.POST)
	public String addProducto(Form myForm, BindingResult result, ModelAndView model, @RequestParam(value="image", required=false)MultipartFile image) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String login = auth.getName();
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		
		Producto producto = myForm.getProducto();
		Long idIva = myForm.getIdIva();
		Iva miIva = productoService.buscarIvaPorId(idIva);
		
		producto.setIva(miIva);
		producto.setEmpresa(miempresa);
		
		if(!image.isEmpty()){
			SaveImage save = new SaveImage();
			save.validateImage(image);
			save.saveImage(producto.getCodProd() + ".jpg", image);
		}
		
		//MultipartFile multipartFile = myForm.getFile();
		
		//FileCopyUtils.copy(myForm.getFile().getBytes(), new File(UPLOAD_LOCATION + myForm.getFile().getOriginalFilename()));
		//String fileName = multipartFile.getOriginalFilename();

		productoService.registroProducto(producto);
		List<Centro> centros = empresaService.buscarCentroPorEmpresa(miempresa);
		for (Centro centro : centros) {
			Stock stock = new Stock();
			stock.setCentro(centro);
			stock.setProducto(producto);
			stock.setStockActual(0);
			stock.setStockMin(0);
			productoService.registroStock(stock);
		}
		
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
	
		model.addObject("centroNombre", micentro.getNombre());
		model.addObject("stockList", micentro.getStock());

		model.setViewName("stock");
		return model;
	}
	
	@RequestMapping(value="/editarStock",method = RequestMethod.GET)
	public ModelAndView editarStock(ModelAndView model, Long idStock){
		Stock mistock = productoService.buscarStockPorId(idStock);
		model.addObject("stock", mistock);
		//Centro micentro = empresaService.buscarCentroPorId(idCentro);
		model.addObject("idStock",idStock);
		//model.addObject("centro", micentro);
		model.setViewName("editarStock");
		return model;
	}
	
	@RequestMapping(value="/confEditStock",method = RequestMethod.POST)
	public String confEditStock(Model model, Stock stock, Long idStock){
		productoService.actualizarStock(stock);
		//Long idCentro = stock.getCentro().getIdCentro();
		model.addAttribute("stockeditado", stock);
		return "redirect:/admin/centros";
	}
	
	
	
	
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
		
		return "redirect:/admin/gastos";
	}
	
	@RequestMapping(value="/eliminarGasto",method = RequestMethod.GET)
	public String eliminarGasto(ModelAndView model, Long idGasto){
		contabilidadService.eliminarGasto(contabilidadService.buscarGastoPorId(idGasto));
		return "redirect:/admin/gastos";
	}
	
	@RequestMapping(value = "/cajaCentro", method = RequestMethod.GET)
	public ModelAndView cajaCentro() {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		List<Centro> centros = empresaService.buscarCentroPorEmpresa(miempresa);

		mav.addObject("centros", centros);
		mav.setViewName("cajaCentro");
		return mav;
	}	
	
	
	@RequestMapping(value = "/proveedores", method = RequestMethod.GET)
	public ModelAndView proveedores() {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		List<Proveedor> proveedores = contabilidadService.buscarProveedorPorEmpresa(miempresa);
		List<PedidoProveedor> pedidos = new ArrayList<>();
		for (Proveedor proveedor:proveedores){
			for (PedidoProveedor pedido : proveedor.getPedido()){
				pedidos.add(pedido);
			}
		}
		//List<PedidoProveedor> pedidos = new ArrayList<PedidoProveedor>(miempresa.getPedido());
		
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
		Empresa miempresa = empresaService.buscarEmpresaPorAdmin(miusuario);
		
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
		return "redirect:/admin/proveedores";
	}
	
	@RequestMapping(value="/eliminarPedido",method = RequestMethod.GET)
	public String eliminarPedido(Model model, Long idPedido){
		contabilidadService.eliminarPedido(contabilidadService.buscarPedidoPorId(idPedido));
		return "redirect:/admin/proveedores";
	}
	
	@RequestMapping(value="/notificacionStock",method = RequestMethod.GET)
	public ModelAndView notificacionStock(){
		ModelAndView model = new ModelAndView();
		List<Stock> stocks = productoService.buscarStocksMinimos();
		
		model.addObject("stockList",stocks);
		model.setViewName("notificacionStock");
		return model;
	}
	
	@RequestMapping(value="/caja",method = RequestMethod.GET)
	public ModelAndView caja(@ModelAttribute("ticket") Ticket ticket ,BindingResult result, ModelAndView model ){
		Ticket miticket = cajaService.buscarTicketPorId(ticket.getIdTicket());
		List<LineaTicket> lineas = cajaService.buscarLineaPorTicket(miticket);
		FormTicketProducto myForm = new FormTicketProducto();
		//myForm.setIdTicket(idTicket);
		myForm.setTicket(miticket);
		//LineaTicket linea = new LineaTicket();
		//linea.setTicket(ticket);
		//model.addObject("linea", linea);
		CalcularTicket calculos = new CalcularTicket();
		model.addObject("iva", calculos.calcularIVA(lineas));
		model.addObject("subtotal", calculos.calcularSubtotal(lineas));
		model.addObject("total", calculos.calcularTotal(lineas));
		model.addObject("myForm", myForm);
		model.addObject("ticket", miticket);
		model.addObject("lineas", lineas);
		model.addObject("linea", new LineaTicket());
		model.setViewName("caja");
		return model;
	}
	
	@RequestMapping(value = "/addTicket", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addTicket(Long idCentro, final RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		Ticket miticket = new Ticket();
		miticket.setCentro(empresaService.buscarCentroPorId(idCentro));
		cajaService.registroTicket(miticket);
		
		redirectAttributes.addFlashAttribute("ticket", miticket);
		model.setViewName("redirect:/admin/caja");
		return model; 
	}
	
	@RequestMapping(value = "/addLinea", method =RequestMethod.POST )
	public ModelAndView addLinea(@ModelAttribute("myForm") FormTicketProducto myForm, BindingResult result, ModelAndView model, final RedirectAttributes redirectAttributes) {
		Producto miproducto = productoService.buscarProductoPorCodigo(myForm.getCodProd());
		Ticket ticket = cajaService.buscarTicketPorId(myForm.getTicket().getIdTicket());
		LineaTicket linea = new LineaTicket();

		linea.setTicket(ticket);
		linea.setProducto(miproducto);
		linea.setCantidad(1);
		linea.setIva(miproducto.getIva().getPorcentaje());
		linea.setPrecio(miproducto.getPrecio());
		
		cajaService.registroLineaTicket(linea);

		redirectAttributes.addFlashAttribute("ticket", ticket);
		model.setViewName("redirect:/admin/caja");
		return model;
	}
	
	@RequestMapping(value = "/editLinea", method = RequestMethod.POST)
	public ModelAndView editLinea(LineaTicket linea, BindingResult result, ModelAndView model, final RedirectAttributes redirectAttributes) {
		cajaService.actualizarLineaTicket(linea);
		Ticket ticket = cajaService.buscarTicketPorId(linea.getTicket().getIdTicket());
		redirectAttributes.addFlashAttribute("ticket", ticket);
		model.setViewName("redirect:/admin/caja");
		return model;
	}
	
		
	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public ModelAndView tickets() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		
		Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
		Empresa miempresa = miusuario.getCentro().getEmpresa();
		List<Centro> centros = empresaService.buscarCentroPorEmpresa(miempresa);
		List<Ticket> tickets = new ArrayList<Ticket>();
		for(Centro centro:centros){
			for(Ticket ticket:centro.getTicket()){
				tickets.add(ticket);
			}
		}
		model.addObject("tickets", tickets);
		model.setViewName("tickets");
		return model;
	}
	
	@RequestMapping(value = "/verTicket", method = RequestMethod.GET)
	public ModelAndView verTicket(Long idTicket, ModelAndView model ) {
		Ticket ticket = cajaService.buscarTicketPorId(idTicket);
		List<LineaTicket> lineas = new ArrayList<LineaTicket>(ticket.getLineaTicket());
		//Envio envio = cajaService.buscarEnvioPorTicket(ticket);
		
		//model.addObject("envio", envio);
		model.addObject("lineas", lineas);
		model.addObject("ticket", ticket);
		model.setViewName("verTicket");
		return model;
	}

	@RequestMapping(value = "/cerrarTicket", method = RequestMethod.POST)
	public ModelAndView cerrarTicket(Ticket ticket, BindingResult result, ModelAndView model, final RedirectAttributes redirectAttributes) {
		Date date = new Date();
		ticket.setFecha(date);
		Double cambio = ticket.getEntregado() - ticket.getTotal();
		ticket.setCambio(cambio);
		cajaService.actualizarTicket(ticket);
		redirectAttributes.addFlashAttribute("ticket", ticket);
		model.setViewName("redirect:/admin/caja");
		return model;
	}

	
	
}
