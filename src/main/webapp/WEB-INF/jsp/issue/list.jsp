<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<h3><s:message code="issues.list.title"/></h3>

<p>
	<a href="<c:url value="addIssueForm"/>">
		<s:message code="issues.list.add"/>
	</a>
</p>

<table border="1">
	<tr>
		<th><s:message code="issues.title"/></th>
		<th><s:message code="issues.description"/></th>
		<th><s:message code="issues.date"/></th>
		<th><s:message code="issues.actions"/></th>
	</tr>

	<c:forEach var="issue" items="${issueList}" varStatus="status">

		<tr>
			<td>${issue.title}</td>
			<td>${issue.description}</td>
			<td>${issue.date}</td>

			<c:url value="updateIssueForm" var="updateURL">
				<c:param name="id" value="${issue.id}" />
			</c:url>

			<c:url value="delete" var="deleteURL">
				<c:param name="id" value="${issue.id}" />
			</c:url>

			<c:url value="details" var="detailsURL">
				<c:param name="id" value="${issue.id}" />
			</c:url>

			<td><a href="${updateURL}"><s:message code="issues.update"/></a> &nbsp; 
				<a href="${deleteURL}"><s:message code="issues.delete"/></a> &nbsp;
				<a href="${detailsURL}"><s:message code="issues.details"/></a>
			</td>
		</tr>

	</c:forEach>

</table>
