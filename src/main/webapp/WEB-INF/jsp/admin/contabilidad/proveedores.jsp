<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="row">
	<div class="col-md-9">
	
	<img src="<c:url value="/resources/images/proveedores.jpg" />"	 alt="Empleados" width="1000" height="120"  class="center-block img-rounded" >
	
		<h2>
			Proveedores
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">Registrar Proveedor</button>
		</h2>
		<br>
		<!-- Modal -->
		<form:form action="addProveedor" method="POST"
			modelAttribute="proveedor" class="form-horizontal" role="form">

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myLargeModalLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Registro de
								proveedor</h4>
						</div>
						<div class="modal-body">
							<div class="row">

								<div class="col-xs-5">
									<label for="nombre">Nombre</label>
									<form:input class="form-control" path="nombre" type="text" />
								</div>

								<div class="col-xs-5">
									<label for="cif">CIF</label>
									<form:input class="form-control" path="cif" type="text" />
									<form:errors path="cif" class="alert alert-danger"/>
			
								</div>
							</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cerrar</button>
							<button type="submit" class="btn btn-primary">Aceptar</button>
						</div>
					</div>
				</div>
			</div>
		</form:form>


		<script type="text/javascript" charset="utf-8">
			$(document)
					.ready(
							function() {
								$('#proveedores')
										.dataTable(
												{
													"language" : {
														"lengthMenu" : "Mostrar _MENU_ registros por página",
														"zeroRecords" : "Ningún resultado encontrado",
														"info" : "Mostrando página _PAGE_ de _PAGES_",
														"infoEmpty" : "Ningún resultado encontrado",
														"infoFiltered" : "(filtrados _MAX_ registros en total)",
														"sSearch" : "Buscar:",
														"paginate" : {
															"first" : "Primera",
															"last" : "Ultima",
															"next" : "Siguiente",
															"previous" : "Anterior"
														}
													}
												});
								$('#pedidos')
										.dataTable(
												{
													"language" : {
														"lengthMenu" : "Mostrar _MENU_ registros por página",
														"zeroRecords" : "Ningún resultado encontrado",
														"info" : "Mostrando página _PAGE_ de _PAGES_",
														"infoEmpty" : "Ningún resultado encontrado",
														"infoFiltered" : "(filtrados _MAX_ registros en total)",
														"sSearch" : "Buscar:",
														"paginate" : {
															"first" : "Primera",
															"last" : "Ultima",
															"next" : "Siguiente",
															"previous" : "Anterior"
														}
													}
												});
							});
			
		</script>
		<div class="col-md-12">

			<table class="table table-hover" id="proveedores">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>CIF</th>
						<th>Añadir pedido </th>
						<th> Opciones</th>

					</tr>
				</thead>

				<tbody>
					<c:forEach var="proveedor" items="${proveedorlist}"
						varStatus="status">
						<tr>
							<td>${proveedor.nombre}</td>
							<td>${proveedor.cif}</td>
							<c:url var="proveedorUrl" value="eliminarProveedor">
								<c:param name="idProveedor" value="${proveedor.idProveedor}" />
							</c:url>
							<c:url var="proveedorUrl3" value="editarProveedor">
								<c:param name="idProveedor" value="${proveedor.idProveedor}" />
							</c:url>
							<c:url var="proveedorUrl2" value="crearPedido">
								<c:param name="idProveedor" value="${proveedor.idProveedor}" />
							</c:url>
							<td><a href='<c:out value="${proveedorUrl2}"/>'
								title="Añadir pedido" data-toggle="tooltip" type="button"
								class="btn btn-sm btn-success"><i
									class="glyphicon glyphicon-plus"></i></a> 
							</td>
							<td>
								<a href='<c:out value="${proveedorUrl3}"/>' data-original-title="Editar cierre" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a> <a href='' data-toggle="modal" data-target="#deleteModalProveedor" data-original-title="Eliminar proveedor" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="col-md-12">
			<br>
			<br>
			<h2>Pedidos</h2>
			<br>
			<table class="table table-hover" id="pedidos">
				<thead>
					<tr>
						<th>Nombre empresa</th>
						<th>Fecha de compra</th>
						<th>Fecha de vencimiento</th>
						<th>Importe</th>
						<th>Estado</th>
						<th>Opciones</th>

					</tr>
				</thead>

				<tbody>
					<c:forEach var="pedido" items="${pedidoslist}" varStatus="status">
						<tr>
							<td>${pedido.proveedor.nombre}</td>
							<td><fmt:formatDate value="${pedido.fechaCompra}" pattern="dd-MM-yyyy" /></td>
							<td><fmt:formatDate value="${pedido.fechaVencimiento}" pattern="dd-MM-yyyy" /></td>
							<td>${pedido.importe} €</td>
							<td>${pedido.estado}</td>
							<c:url var="pedidoUrl" value="editarPedido">
								<c:param name="idPedido" value="${pedido.idPedidoProveedor}" />
							</c:url>
							<c:url var="pedidoUrl2" value="eliminarPedido">
								<c:param name="idPedido" value="${pedido.idPedidoProveedor}" />
							</c:url>
							<td><a href='<c:out value="${pedidoUrl}"/>' title="Editar Pedido" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>  <a href='' data-toggle="modal" data-target="#deleteModalPedido" data-original-title="Eliminar pedido" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	<div class="modal fade bs-example-modal-lg" id="deleteModalPedido" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Eliminar Pedido</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<h4>¿Está seguro de que desea eliminar este pedido?</h4>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancelar</button>
							<a href='<c:out value="${pedidoUrl2}"/>' type="button"	class="btn btn-danger">Eliminar </a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
			<div class="modal fade bs-example-modal-lg" id="deleteModalProveedor" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Eliminar Proveedor</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<h4>¿Está seguro de que desea eliminar este proveedor?</h4>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancelar</button>
							<a href='<c:out value="${proveedorUrl}"/>' type="button"	class="btn btn-danger">Eliminar </a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</div>
