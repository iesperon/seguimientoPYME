<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Update issue number #${issue.id}</h3>

<form:form action="updateIssue" modelAttribute="issue">
	<form:hidden path="id"/>
	
	<div>
		<div class="tag">Title</div>
		<div class="field"><form:input path="title"/></div>
	</div>
			
	<div>
		<div class="tag">Description</div>
		<div class="field"><form:input path="description"/></div>
	</div>
			
	<div>
		<div class="tag">Date</div>
		<div class="field"><form:input path="date"/></div>
	</div>
		
	<div><input type="submit" value="Update"/></div>
</form:form>