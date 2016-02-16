package es.udc.lbd.spring.model.services.issue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.spring.model.data.issue.Issue;
import es.udc.lbd.spring.model.data.issue.IssueDAO;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
public class IssueServiceImpl implements IssueService {
	
	@Autowired
	private IssueDAO issueDAO;

	public Issue create(Issue issue) {
		return issueDAO.create(issue);
	}

	public Issue update(Issue issue) {
		return issueDAO.update(issue);
	}

	public void delete(Long id) {
		issueDAO.delete(id);
	}

	@Transactional(readOnly = true)
	public Issue getIssue(Long id) {
		return issueDAO.getIssue(id);
	}

	@Transactional(readOnly = true)
	public List<Issue> getAllIssues() {
		return issueDAO.getAllIssues();
	}

}
