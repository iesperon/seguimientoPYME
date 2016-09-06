<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$(function() {
		$("form:not(.filter) :input:visible:enabled:first").focus();
	});
	$(document).ready(function() {
		$("#boton").click(function() {
			$("#oculto").slideToggle();
		});

	});
</script>
	

<div class="row">
	<div class="col-md-9">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h2 class="panel-title"></h2>
					<div class="panel-body">
						<div id="buscar_producto">
							<h3>Buscar Producto</h3>
							<div class="row">
								<div class="col-lg-6">
									<form:form action="addLinea" method="POST" modelAttribute="myForm" class="form-inline" role="form">
										<div class="form-group">
											<label for="codProd">Código de barras</label>
											<form:input class="form-control" id="codProd" path="codProd"
												type="text" />
											<form:input class="form-control" path="ticket.idTicket"
												value="${ticket.idTicket}" type="hidden" />
										</div>
										<div class="form-group">
											<input type="submit" value="Buscar"
												class="btn btn-success btn-sm">
										</div>
									</form:form>
									</div>
									<div class="col-lg-6">
										<form:form action="addLineaProducto" method="POST" modelAttribute="myForm" class="form-inline" role="form">
											<div class="form-group">
												<label for="centro">Producto</label>
												<form:select class="form-control" path="idProducto">
													<c:forEach var="productos" items="${productoslist}"	varStatus="status">
														<form:option value="${productos.idProducto}">${productos.nombre}</form:option>
													</c:forEach>
												</form:select>
												<form:input class="form-control" path="ticket.idTicket"	value="${ticket.idTicket}" type="hidden" />
												<input type="submit" value="Añadir"	class="btn btn-success btn-sm">
											</div>
										</form:form>
									</div>
									
							</div><!-- row busqueda -->
						</div><!-- buscar producto -->

						<h3>Ticket</h3>
						<table class="table table-condensed">
							<thead>
								<tr style="color:white">
									<td>Referencia</td>
									<td>Cantidad</td>
									<td>Producto</td>
									<td>IVA</td>
									<td>Descuento</td>
									<td>Precio</td>
									<td></td>
									<td></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="lineas" items="${lineas}" varStatus="status">
								
									<tr>
										<form:form action="editLinea" method="POST" modelAttribute="linea" class="form-horizontal" role="form">
									
										<td>
											<form:input class="form-control" id="referencia" path="producto.codProd" value="${lineas.producto.codProd}"	readonly="true" type="text" />
										</td>
										<td>
											<form:input class="form-control" id="cantidad" path="cantidad" value="${lineas.cantidad}" type="text" />
										</td>
										<td>
											<form:input class="form-control" id="producto" path="producto.nombre" value="${lineas.producto.nombre}"	readonly="true" type="text" />
										</td>
										<td>
											<div class="input-group"> 
												<span class="input-group-addon" >%</span>
												<form:input class="form-control" id="iva" path="iva" value="${lineas.iva}" type="text" />
											</div>										
										</td>
										<td>
											<div class="input-group"> 
												<span class="input-group-addon" >%</span>
												<form:input class="form-control" id="descuento" path="descuento" value="${lineas.descuento}" type="text" />
											</div>		
										</td>
										<td>
											<div class="input-group"> 
												<span class="input-group-addon" >€</span>
												<form:input class="form-control" id="precio" path="precio" value="${lineas.precio}" type="text" />
											</div>		
										</td>
										<form:input class="form-control" path="idLineaTicket" value="${lineas.idLineaTicket}" type="hidden" />
										<form:input class="form-control" path="ticket.idTicket" value="${lineas.ticket.idTicket}" type="hidden" />
										<td>
											<button type="submit" class="btn btn-warning">
												<i class="glyphicon glyphicon-edit"></i>
											</button>
										</td>
										</form:form>
										
										<td>
											<form:form action="eliminarLinea" method="GET" modelAttribute="linea" class="form-horizontal" role="form">
											<button type="submit" class="btn btn-danger">
												<form:input class="form-control" id="referencia" path="producto.codProd" value="${lineas.producto.codProd}" readonly="true" type="hidden" />
												<form:input class="form-control" id="cantidad" path="cantidad" value="${lineas.cantidad}" type="hidden" />
												<form:input class="form-control" id="producto" path="producto.nombre" value="${lineas.producto.nombre}" readonly="true" type="hidden" />
												<form:input class="form-control" id="iva" path="iva" value="${lineas.iva}" type="hidden" />
												<form:input class="form-control" id="descuento" path="descuento" value="${lineas.descuento}" type="hidden" />
												<form:input class="form-control" id="precio" path="precio" value="${lineas.precio}" type="hidden" />
												<form:input class="form-control" path="idLineaTicket"	value="${lineas.idLineaTicket}" type="hidden" />
												<form:input class="form-control" path="ticket.idTicket"	value="${lineas.ticket.idTicket}" type="hidden" />
												<i class="glyphicon glyphicon-remove"></i>
											</button>
											</form:form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-12 text-right">
							<h4>
								Subtotal : <fmt:formatNumber value="${subtotal}" maxFractionDigits="2" /> €
							</h4>
							<h4>
								IVA: <fmt:formatNumber value="${iva}" maxFractionDigits="2" /> €
							</h4>
							<hr>
							<h3>
								TOTAL: <fmt:formatNumber value="${total}" maxFractionDigits="2" /> €
							</h3>
							<h4>
								Cambio:	<fmt:formatNumber value="${ticket.cambio}" maxFractionDigits="2" />	€
							</h4>
						</div>
						<div class="row text-right">
							<form:form action="cerrarTicket" method="POST" modelAttribute="ticket" class="form-inline" role="form">
								<form:input class="form-control" path="subtotal" value="${subtotal}" type="hidden" />
								<form:input class="form-control" path="iva" value="${iva}" type="hidden" />
								<form:input class="form-control" path="total" value="${total}" type="hidden" />
								<form:input class="form-control" path="idTicket" value="${ticket.idTicket}" type="hidden" />
								<div class="col-lg-4 ">
									<div class="form-group">
											<label for="entregado">Entregado</label>
											<form:input class="form-control" id="entregado" path="entregado" type="text" />
									</div>
								</div>
								<div class="col-lg-4">
									<div class="form-group">
										<label for="centro">Forma de pago</label>
										<form:select class="form-control input" path="formaPago">
											<form:option value="Efectivo">Efectivo</form:option>
											<form:option value="Tarjeta">Tarjeta</form:option>
										</form:select>
									</div>
								</div>
								<div class="col-lg-4 ">
									<div class="form-group">
										<button type="submit" class="btn btn-success">Imprimir Ticket</button>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="row">
			<button id="boton" onclick="mostrar()"
				class="btn btn-primary btn-lg btn-block">Enviar
			</button>
		</div>
		<div id="oculto" style="display: none;">
			<form:form action="addEnvio" method="POST" modelAttribute="envioForm"
				class="form-horizontal" role="form">
				<div class="col-sm-12">
					<div>
						<h2>Enviar Compra</h2>
						<div class="row">

							<div class="col-xs-3">
								<label for="nombre">Nombre</label>
								<form:input class="form-control" path="envio.nombre" type="text" />
							</div>

							<div class="col-xs-3">
								<label for="calle">Calle</label>
								<form:input class="form-control" path="envio.calle" type="text" />
							</div>

							<div class="col-xs-3">
								<label for="numero">Numero</label>
								<form:input class="form-control" path="envio.numero" type="text" />
							</div>

							<div class="col-xs-3">
								<label for="poblacion">Puerta</label>
								<form:input class="form-control" path="envio.puerta" type="text" />
							</div>

							<div class="col-xs-3">
								<label for="poblacion">CP</label>
								<form:input class="form-control" path="envio.cp" type="text" />
							</div>

							<div class="col-xs-3">
								<label for="provincia">Provincia</label>
								<form:input class="form-control" path="envio.provincia"
									type="text" />
							</div>

							<div class="col-xs-3">
								<label for="provincia">Poblacion</label>
								<form:input class="form-control" path="envio.poblacion"
									type="text" />
							</div>


							<div class="col-xs-3">
								<label for="pais">Pais</label>
								<form:input class="form-control" path="envio.pais" type="text" />
							</div>

							<div class="col-xs-3">
								<label for="provincia">Empresa Transporte</label>
								<form:input class="form-control" path="envio.empresa"
									type="text" />
							</div>

							<div class="col-xs-3">
								<label for="email">Numero Seguimiento</label>
								<form:input class="form-control" path="envio.numSeguimiento"
									type="text" />
							</div>

							<div class="col-xs-3">
								<label for="provincia">Estado</label>
								<form:input class="form-control" path="envio.estado" type="text" />
							</div>

							<form:input class="form-control" path="idTicket"
								value="${ticket.idTicket}" type="hidden" />


						</div>

						<br>
						<div class="col-xs-6">
							<input type="submit" value="Aceptar" class="btn btn-primary">
						</div>
					</div>
				</div>

			</form:form>

		</div>
	</div>
</div>