<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<h3><s:message code="issues.issue"/> #${issue.id}</h3>
		
<div><i><s:message code="issues.id"/>:</i> ${issue.id}</div>
<div><i><s:message code="issues.title"/>:</i> ${issue.title}</div>
<div><i><s:message code="issues.description"/>:</i> ${issue.description}</div>
<div><i><s:message code="issues.date"/>:</i> ${issue.date}</div>

<div style="margin: 10px 0;">
	<a href="<c:url value="list"/>"><s:message code="issues.details.back"/></a>
</div>