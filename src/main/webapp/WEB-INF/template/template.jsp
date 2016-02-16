<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title><tiles:insertAttribute name="title" /></title>
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"
	type="text/css" />
</head>
<body>

	<div id="header">
		<h1>
			<s:message code="header.title" />
		</h1>
	</div>

	<div id="menuwrapper">
		<div id="menu">
			<p class="alignleft">
				<a href=<c:url value="/"/>><s:message code="menu.home" /></a>
				&nbsp;

				<!-- The user is authenticated -->
				<sec:authorize access="isAuthenticated()">
					<a href=<c:url value="/issue/list"/>><s:message
							code="menu.issues" /></a> &nbsp;

				</sec:authorize>

				<!-- The user is not authenticated -->
				<sec:authorize access="not isAuthenticated()">
					<a href=<c:url value="/login"/>><s:message
							code="users.login.login" /></a>
				</sec:authorize>
			</p>
			<p class="alignright">
				<s:message code="menu.language" />
				: <a href=<c:url value="?language=en_EN"/>><s:message
						code="menu.en" /></a> - <a href=<c:url value="?language=es_ES"/>><s:message
						code="menu.es" /></a>

				<sec:authorize access="isAuthenticated()">
					<form action=<c:url value="/logout"/> method="post" id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>

					<script>
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script>

					<a href="javascript:formSubmit()"><s:message
							code="users.login.logout" /> (<s:message code="users.logged.as" />
					<i><sec:authentication property="principal.username" /></i>) </a> &nbsp;
				</sec:authorize>
			</p>
		</div>
	</div>

	<div id="contentwrapper">
		<div id="content">
			<tiles:insertAttribute name="content" />
		</div>
	</div>

	<div id="footer">
		<s:message code="footer.copyright" />
	</div>

</body>
</html>