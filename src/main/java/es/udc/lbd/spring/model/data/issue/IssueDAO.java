package es.udc.lbd.spring.model.data.issue;

import java.util.List;

public interface IssueDAO {
	
	public Issue create(Issue issue);
	
	public Issue update(Issue issue);
	
	public void delete(Long id);
	
	public Issue getIssue(Long id);
	
	public List<Issue> getAllIssues();

}
