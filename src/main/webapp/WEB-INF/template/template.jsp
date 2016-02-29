<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>



<html>

<head>
    <title> MiPymeOnline </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

 	<style type="text/css">
        <%@include file="../../resources/css/bootstrap.min.css" %>
    </style>
 
    
</head>

<body>

	<nav class="navbar navbar-inverse">
 		 <div class="container-fluid">
    		<div class="navbar-header">
      			<a class="navbar-brand" href="<c:url value="/" />">MiPymeOnline</a>
    		</div>
    		
    		<form class="navbar-form navbar-right" role="search">
        		<div class="form-group">
          			<input type="text" class="form-control" placeholder="Email">
          			<input type="password" class="form-control" placeholder="Contraseña">
        		</div>
        		<button type="submit" class="btn btn-primary">Aceptar</button>
      		</form>
    	</div>
    </nav>
	
	
	<section>
	<div  style="padding-top: auto;">
	<div class="row">	
    	<div class="container">
	
		<div id="contentwrapper">
			<div id="content">
				<tiles:insertAttribute name="content"/> 
			</div>
		</div>
	
		</div>
	</div>
	</div>
    </section>
    

    
</body>
</html>