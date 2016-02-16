<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<h3>
	<s:message code="issues.add.title" />
</h3>

<form:form action="addIssue" modelAttribute="issue">
	<div>
		<div class="tag">
			<s:message code="issues.title" />
		</div>
		<div class="field">
			<form:input path="title" />
			<form:errors path="title" class="formerrors" />
		</div>
	</div>

	<div>
		<div class="tag">
			<s:message code="issues.description" />
		</div>
		<div class="field">
			<form:input path="description" />
			<form:errors path="description" class="formerrors" />
		</div>
	</div>

	<div>
		<input type="submit" value=<s:message code="issues.add.add"/>>
	</div>
</form:form>
