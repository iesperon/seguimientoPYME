<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div><s:message code="home.welcome"/></div> 

<div style="margin: 10px 0;">
	<s:message code="home.startwith"/><a href="<c:url value="issue/list"/>"><s:message code="home.listofissues"/></a>
</div>
