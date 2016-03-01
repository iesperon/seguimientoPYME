<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


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
    		
    		   		
    	</div>
    </nav>
	
	<sec:authorize access="isAuthenticated()">
    		HOLAAAAAAAAAAAAAAAA, ESTAS AUTENTICADO!!!!
    </sec:authorize>
	
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