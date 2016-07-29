package es.udc.fi.tfg.seguimiento.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.CierreCaja;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Envio;
import es.udc.fi.tfg.seguimiento.model.LineaTicket;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Stock;
import es.udc.fi.tfg.seguimiento.model.Ticket;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.CajaService;
import es.udc.fi.tfg.seguimiento.services.EmpresaService;
import es.udc.fi.tfg.seguimiento.services.ProductoService;
import es.udc.fi.tfg.seguimiento.services.UserService;
import es.udc.fi.tfg.seguimiento.utils.CalcularTicket;
import es.udc.fi.tfg.seguimiento.utils.FormCentroCierre;
import es.udc.fi.tfg.seguimiento.utils.FormTicketEnvio;
import es.udc.fi.tfg.seguimiento.utils.FormTicketProducto;

@Controller
@RequestMapping(value = "/user/ventas")
public class UserVentasController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired 
	private UserService usuarioService;
	
	@Autowired 
	private ProductoService productoService;
	
	@Autowired
	private CajaService cajaService;
	
	// ************************ STOCK ***********************
	
		@RequestMapping(value="/stockEmp",method = RequestMethod.GET)
		public ModelAndView Stock(ModelAndView model){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String login = auth.getName();
			Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
			Centro micentro = miusuario.getCentro();
	
			model.addObject("centroNombre", micentro.getNombre());
			model.addObject("stockList", micentro.getStock());

			model.setViewName("stockEmp");
			return model;
		}
		
		@RequestMapping(value="/editarStockEmp",method = RequestMethod.GET)
		public ModelAndView editarStock(ModelAndView model, Long idStock){
			Stock mistock = productoService.buscarStockPorId(idStock);
			model.addObject("stock", mistock);
			//Centro micentro = empresaService.buscarCentroPorId(idCentro);
			model.addObject("idStock",idStock);
			//model.addObject("centro", micentro);
			model.setViewName("editarStockEmp");
			return model;
		}
		
		@RequestMapping(value="/confEditStockEmp",method = RequestMethod.POST)
		public String confEditStock(Model model, Stock stock, Long idStock){
			productoService.actualizarStock(stock);
			//Long idCentro = stock.getCentro().getIdCentro();
			model.addAttribute("stockeditado", stock);
			return "redirect:/user/ventas/stockEmp";
		}
		
		@RequestMapping(value="/notificacionStockEmp",method = RequestMethod.GET)
		public ModelAndView notificacionStock(){
			ModelAndView model = new ModelAndView();
			List<Stock> stocks = productoService.buscarStocksMinimos();
			
			model.addObject("stockList",stocks);
			model.setViewName("notificacionStockEmp");
			return model;
		}
		
		// ******************* CAJA ***************************

		@RequestMapping(value="/cajaEmp",method = RequestMethod.GET)
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
			model.addObject("envioForm", new FormTicketEnvio());
			model.addObject("iva", calculos.calcularIVA(lineas));
			model.addObject("subtotal", calculos.calcularSubtotal(lineas));
			model.addObject("total", calculos.calcularTotal(lineas));
			model.addObject("myForm", myForm);
			model.addObject("ticket", miticket);
			model.addObject("lineas", lineas);
			model.addObject("linea", new LineaTicket());
			model.setViewName("cajaEmp");
			return model;
		}
		
		@RequestMapping(value = "/addTicketEmp", method = { RequestMethod.GET, RequestMethod.POST })
		public ModelAndView addTicket(Long idCentro, final RedirectAttributes redirectAttributes) {
			ModelAndView model = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String login = auth.getName();
			Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
			Centro micentro = miusuario.getCentro();
			
			Ticket miticket = new Ticket();
			miticket.setCentro(micentro);
			cajaService.registroTicket(miticket);
			
			redirectAttributes.addFlashAttribute("ticket", miticket);
			model.setViewName("redirect:/user/ventas/cajaEmp");
			return model; 
		}
		
		@RequestMapping(value = "/addLineaEmp", method =RequestMethod.POST )
		public ModelAndView addLinea(@ModelAttribute("myForm") FormTicketProducto myForm, BindingResult result, ModelAndView model, final RedirectAttributes redirectAttributes) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String login = auth.getName();
			
			Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
			Empresa miempresa = miusuario.getCentro().getEmpresa();
			
			Producto miproducto = productoService.buscarProductoPorCodigo(myForm.getCodProd(), miempresa.getIdEmpresa());
			Ticket ticket = cajaService.buscarTicketPorId(myForm.getTicket().getIdTicket());
			LineaTicket linea = new LineaTicket();

			linea.setTicket(ticket);
			linea.setProducto(miproducto);
			linea.setCantidad(1);
			linea.setIva(miproducto.getIva().getPorcentaje());
			linea.setPrecio(miproducto.getPrecio());
			
			cajaService.registroLineaTicket(linea);

			redirectAttributes.addFlashAttribute("ticket", ticket);
			model.setViewName("redirect:/user/ventas/cajaEmp");
			return model;
		}
		
		@RequestMapping(value = "/editLineaEmp", method = RequestMethod.POST)
		public ModelAndView editLinea(LineaTicket linea, BindingResult result, ModelAndView model, final RedirectAttributes redirectAttributes) {
			cajaService.actualizarLineaTicket(linea);
			Ticket ticket = cajaService.buscarTicketPorId(linea.getTicket().getIdTicket());
			redirectAttributes.addFlashAttribute("ticket", ticket);
			model.setViewName("redirect:/user/ventas/cajaEmp");
			return model;
		}
		
		@RequestMapping(value = "/eliminarLinea", method = RequestMethod.GET)
		public ModelAndView eliminarLinea(LineaTicket linea,  BindingResult result, ModelAndView model, final RedirectAttributes redirectAttributes) {
			Ticket ticket = cajaService.buscarTicketPorId(linea.getTicket().getIdTicket());
			linea.setProducto(null);
			cajaService.eliminarLineaTicket(linea);
			
			redirectAttributes.addFlashAttribute("ticket", ticket);
			model.setViewName("redirect:/user/ventas/cajaEmp");
			return model;
		}
		
		// ****************** TICKETS ******************
		
		@RequestMapping(value = "/ticketsEmp", method = RequestMethod.GET)
		public ModelAndView tickets() {
			ModelAndView model = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String login = auth.getName();
			
			Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
			Centro micentro = miusuario.getCentro();
			List<Ticket> tickets = new ArrayList<Ticket>(micentro.getTicket());
			
			model.addObject("tickets", tickets);
			model.setViewName("ticketsEmp");
			return model;
		}
		
		@RequestMapping(value = "/verTicketEmp", method = RequestMethod.GET)
		public ModelAndView verTicket(Long idTicket, ModelAndView model ) {
			Ticket ticket = cajaService.buscarTicketPorId(idTicket);
			List<LineaTicket> lineas = new ArrayList<LineaTicket>(ticket.getLineaTicket());
			Envio envio = cajaService.buscarEnvioPorTicket(ticket);
			model.addObject("envio", envio);
			model.addObject("editEnvio", new Envio());
			model.addObject("lineas", lineas);
			model.addObject("ticket", ticket);
			model.setViewName("verTicketEmp");
			return model;
		}
		
		@RequestMapping(value = "/cerrarTicketEmp", method = RequestMethod.POST)
		public ModelAndView cerrarTicket(Ticket ticket, BindingResult result, ModelAndView model, final RedirectAttributes redirectAttributes) {
			Date date = new Date();
			Ticket ticketCent=cajaService.buscarTicketPorId(ticket.getIdTicket());
			ticket.setFecha(date);
			Double cambio = ticket.getEntregado() - ticket.getTotal();
			ticket.setCambio(cambio);
			List<LineaTicket> lineas = cajaService.buscarLineaPorTicket(ticket);
			Centro centro = ticketCent.getCentro();
			productoService.descontarStock(centro, lineas);
			cajaService.actualizarTicket(ticket);
			redirectAttributes.addFlashAttribute("ticket", ticket);
			model.setViewName("redirect:/user/ventas/cajaEmp");
			return model;
		}
		
		// *********************** CIERRES DE CAJA ********************
		
		@RequestMapping(value = "/cierresCajaEmp", method = RequestMethod.GET)
		public ModelAndView cierresCaja() {
			ModelAndView model = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String login = auth.getName();
			
			Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
			Centro micentro = miusuario.getCentro();
			Empresa miempresa = miusuario.getCentro().getEmpresa();
			List<Centro> centros = empresaService.buscarCentroPorEmpresa(miempresa);
			List<CierreCaja> cierres = cajaService.buscarCierrePorCentros(centros);
			
			model.addObject("cierreslist", cierres);
			//model.addObject("newGasto", new Gasto());
			model.setViewName("cierresCajaEmp");
			return model;
		}
		
		@RequestMapping(value = "/registrarCierreEmp", method = RequestMethod.GET)
		public ModelAndView registrarCierre(Long idCentro, ModelAndView model) {
			Centro micentro = empresaService.buscarCentroPorId(idCentro);
			List<Ticket> tickets = new ArrayList<Ticket>(micentro.getTicket());
			List<Ticket> ticketSinCierre = new ArrayList<Ticket>();
			FormCentroCierre newFormCierre = new FormCentroCierre();
			for(Ticket miticket:tickets){
				if (miticket.getCierreCaja() ==null){
					//newFormCierre.getCierre().getTicket().add(miticket);
					ticketSinCierre.add(miticket);
				}
			}
			Double tarjeta = 0.0;
			Double efectivo = 0.0;
			for(Ticket ticket:ticketSinCierre){
				if (ticket.getFormaPago()=="Tarjeta"){
					tarjeta=tarjeta+ticket.getTotal();
				}else{
					efectivo=efectivo+ticket.getTotal();
				}
			}
			Double total = tarjeta + efectivo;
			model.addObject("total", total);
			model.addObject("tarjeta",tarjeta);
			model.addObject("efectivo",efectivo);
			model.addObject("idCentro", idCentro);
			model.addObject("newFormCierre", newFormCierre);
			model.setViewName("registrarCierreEmp");
			return model;
		}
		
		
		@RequestMapping(value = "/addCierreEmp", method =RequestMethod.POST )
		public ModelAndView addCierre(@ModelAttribute("newFormCierre") FormCentroCierre newFormCierre, BindingResult result, ModelAndView model, final RedirectAttributes redirectAttributes) {
			Centro centro = empresaService.buscarCentroPorId(newFormCierre.getIdCentro());
			Usuario miusuario = centro.getEmpresa().getAdministrador();
			CierreCaja cierre = newFormCierre.getCierre();
			List<Ticket> tickets = new ArrayList<Ticket>(centro.getTicket());
			Double diferencia= cierre.getCaja() - cierre.getTotal();
			cierre.setDiferencia(diferencia);
			cierre.setCentro(centro);
			//Date date = new Date();
			cierre.setFecha(new Date());
			cajaService.registroCierre(cierre);
			for(Ticket miticket:tickets){
				if (miticket.getCierreCaja() ==null){
					miticket.setCierreCaja(cierre);
					cajaService.cerrarTicket(miticket);
				}
			}
			cajaService.EnviarNotificacion(miusuario, centro);;
			redirectAttributes.addFlashAttribute("cierre", cierre);
			redirectAttributes.addFlashAttribute("idCentro", cierre.getCentro().getIdCentro());
			model.setViewName("redirect:/user/ventas/cierresCajaEmp");
			return model;
		}
		
		@RequestMapping(value = "/cierreCentroEmp", method = RequestMethod.GET)
		public ModelAndView cierreCentro() {
			ModelAndView mav = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String login = auth.getName();
			
			Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
			Empresa miempresa = miusuario.getCentro().getEmpresa();
			List<Centro> centros = empresaService.buscarCentroPorEmpresa(miempresa);

			mav.addObject("centros", centros);
			mav.setViewName("cierreCentroEmp");
			return mav;
		}
		
		// **************** ENVIOS **************************
		
		@RequestMapping(value = "/addEnvioEmp", method = RequestMethod.POST)
		public String addEnvio(FormTicketEnvio envioForm, BindingResult result, ModelAndView model) {
			Envio envio = envioForm.getEnvio();
			Long idTicket = envioForm.getIdTicket();
			Ticket ticket = cajaService.buscarTicketPorId(idTicket);
			//Centro micentro = empresaService.buscarCentroPorId(idCentro);
			//usuario.setCentro(micentro);
			//usuario.setEnabled(true);
			envio.setTicket(ticket);
			envio.setCentro(ticket.getCentro());
			cajaService.registroEnvio(envio);
			//usuarioService.registroUsuario(usuario);
			//model.addObject("empleadoNuevo", usuario);
			return "redirect:/user/ventas/ticketsEmp";
			
		}
		
		@RequestMapping(value = "/enviosEmp", method = RequestMethod.GET)
		public ModelAndView envios() {
			ModelAndView model = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			String login = auth.getName();
			Usuario miusuario = usuarioService.buscarUsuarioPorEmail(login);
			Empresa miempresa = miusuario.getCentro().getEmpresa();
			List<Centro> centros=empresaService.buscarCentroPorEmpresa(miempresa);
			List<Envio> envios = new ArrayList<Envio>();
			for(Centro micentro:centros){
				List<Envio> envioCentro = cajaService.buscarEnvioPorCentro(micentro);
				for (Envio envio:envioCentro){
					envios.add(envio);
				}
			}
			model.addObject("enviosList",envios);
			model.setViewName("enviosEmp");
			return model;
		}
		
		@RequestMapping(value = "/verEnvio", method = RequestMethod.GET)
		public ModelAndView verEnvio(Long idEnvio, ModelAndView model ) {
			Envio envio = cajaService.buscarEnvioPorId(idEnvio);
			Ticket ticket = envio.getTicket();
			List<LineaTicket> lineas = new ArrayList<LineaTicket>(ticket.getLineaTicket());
			model.addObject("envio", envio);
			model.addObject("editEnvio", new Envio());
			model.addObject("lineas", lineas);
			model.addObject("ticket", ticket);
			model.setViewName("verEnvioEmp");
			return model;
		}
		
		@RequestMapping(value="/editarEnvioEmp",method = RequestMethod.POST)
		public String editarEnvio(Model model, Envio envio){
			cajaService.actualizarEnvio(envio);
			model.addAttribute("envioeditado",envio);
			return "redirect:/user/ventas/enviosEmp";
		}
}
