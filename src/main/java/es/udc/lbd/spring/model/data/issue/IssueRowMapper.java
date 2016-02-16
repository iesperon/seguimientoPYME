package es.udc.lbd.spring.model.data.issue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class IssueRowMapper implements RowMapper<Issue> {

	public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Issue issue = new Issue();
		
		issue.setId(rs.getLong("id"));
		issue.setTitle(rs.getString("title"));
		issue.setDescription(rs.getString("description"));
		issue.setDate(rs.getDate("date"));
		
		return issue;
	}
	
	

}
