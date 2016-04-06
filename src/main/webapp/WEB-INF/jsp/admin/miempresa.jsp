<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container">
      <div class="row">

        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
   
   
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title"> ${empresa.nombre}</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3 " align="center"> <img alt="Empresa logo" src="https://image.freepik.com/vector-gratis/empresa-senal-logo_355-612.jpg" class="img-circle img-responsive"> </div>

                <div class=" col-md-9 col-lg-9 "> 
                  <table class="table table-user-information">
                    <tbody>
                      <tr>
                        <td>CIF:</td>
                        <td>${empresa.cif}</td>
                      </tr>
                      <tr>
                        <td>Sector:</td>
                        <td>${empresa.sector}</td>
                      </tr>
                      <tr>
                        <td>Email:</td>
                        <td><a href="mailto:${empresa.email}">${empresa.email}</a></td>
                      </tr>
                      <tr>
                        <td>Descripción:</td>
                        <td>${empresa.descripcion}
                        </td>
                           
                      </tr>
                     
                    </tbody>
                  </table>
                  <a href="#" class="btn btn-primary">Contabilidad</a>
                  <a href="#" class="btn btn-primary">Avisos Stock</a>
                  <a href="#" class="btn btn-primary">Cierres de Caja</a>
                </div>
              </div>
            </div>
                 <div class="panel-footer">
                        <span class="pull-right">
                            <!-- Button trigger modal -->
							<button type="button" class="btn btn-sm btn-warning " data-toggle="modal" data-target="#myModal">
								<i class="glyphicon glyphicon-edit"></i>							
							</button>
                		</span>
                		<div class="clearfix"></div>
                </div>
            
           
          </div>
        </div>
      </div>
    </div>
    
    
    
<!-- Modal -->
	<form:form action="editarEmpresa" method="POST" modelAttribute="empresa" class="form-horizontal" role="form">
			
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  	<div class="modal-dialog modal-lg" >
    	<div class="modal-content">
      		<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        	<h4 class="modal-title" id="myModalLabel">Editar Empresa</h4>
      	</div>
		<div class="modal-body">
		<div class="row">
			
			<div class="col-xs-3">
				<label for="nombre">Nombre</label>
				<form:input class="form-control" path="nombre" value="${empresa.nombre}" type="text"/>
			</div>

			<div class="col-xs-2">
				<label for="cif">CIF</label>
				<form:input class="form-control" path="cif" value="${empresa.cif}" type="text"/>
			</div>
			
			<div class="col-xs-3">
				<label for="sector">Sector</label>
				<form:input class="form-control" path="sector" value="${empresa.sector}" type="text"/>
			</div>
			
			<div class="col-xs-3">
				<label for="email">Email</label>
				<form:input class="form-control" path="email" value="${empresa.email}" type="text"/>
			</div>
			
			<div class="col-xs-3">
				<label for="descripcion">Descripcion</label>
				<form:textarea class="form-control" rows="3" path="descripcion" value="${empresa.descripcion}" type="text"/>
			</div>
			
			<div class="col-xs-3">
				<label for="logo">Logo</label>
				<form:input class="form-control" path="logo" value="${empresa.logo}" type="text"/>
			</div>
			
			<form:input class="form-control" path="idEmpresa" value="${empresa.idEmpresa}" type="hidden"/>
			

			</div>
					
			</div>
			 <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button type="submit" class="btn btn-primary">Aceptar</button>
      </div>
    </div>
  </div>
</div>
</form:form>    
