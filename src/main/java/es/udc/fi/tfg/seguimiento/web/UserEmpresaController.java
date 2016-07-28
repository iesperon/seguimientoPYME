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

import es.udc.fi.tfg.seguimiento.model.Centro;
import es.udc.fi.tfg.seguimiento.model.Empresa;
import es.udc.fi.tfg.seguimiento.model.Iva;
import es.udc.fi.tfg.seguimiento.model.Producto;
import es.udc.fi.tfg.seguimiento.model.Stock;
import es.udc.fi.tfg.seguimiento.model.Usuario;
import es.udc.fi.tfg.seguimiento.services.ProductoService;
import es.udc.fi.tfg.seguimiento.services.UserService;
import es.udc.fi.tfg.seguimiento.utils.Form;

@Controller
@RequestMapping(value = "/user/empresa")
public class UserEmpresaController {
	
	@Autowired 
	private UserService usuarioService;
	
	@Autowired 
	private ProductoService productoService;
	
	//******************* PRODUCTOS *************************
	
		@RequestMapping(value="/productosEmp",method = RequestMethod.GET)
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
			model.setViewName("productosEmp");
			return model;
		}
		
		
		@RequestMapping(value = "/addProductoEmp", method = RequestMethod.POST)
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
			
			return "redirect:/user/empresa/productosEmp";
			
		}
		
		@RequestMapping(value="/editarProductoEmp",method = RequestMethod.GET)
		public ModelAndView editarProducto(ModelAndView model, Long idProducto){
			Producto miproducto = productoService.buscarProductoPorId(idProducto);
			model.addObject("idProducto",idProducto);
			model.addObject("producto", miproducto);
			model.setViewName("editarProductoEmp");
			return model;
		}
		
		@RequestMapping(value="/confEdicProdEmp",method = RequestMethod.POST)
		public String confEdicProd(Model model, Producto miproducto, Long idProducto){
			productoService.actualizarProducto(miproducto);
			model.addAttribute("productoeditado", miproducto);
			return "redirect:/user/empresa/productosEmp";
		}
		
		@RequestMapping(value="/eliminarProductoEmp",method = RequestMethod.GET)
		public String eliminarProducto(Model model, Long idProducto){
			productoService.eliminarProducto(productoService.buscarProductoPorId(idProducto));
			return "redirect:/user/empresa/productosEmp";
		}
}
