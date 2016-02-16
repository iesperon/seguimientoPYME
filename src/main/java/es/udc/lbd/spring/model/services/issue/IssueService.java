package es.udc.lbd.spring.model.services.issue;

import java.util.List;

import es.udc.lbd.spring.model.data.issue.Issue;

public interface IssueService {
	
	public Issue create(Issue issue);
	
	public Issue update(Issue issue);
	
	public void delete(Long id);
	
	public Issue getIssue(Long id);
	
	public List<Issue> getAllIssues();

}
