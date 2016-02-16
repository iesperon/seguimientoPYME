package es.udc.lbd.spring.model.data.issue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcIssueDAO implements IssueDAO {
	
	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static String CREATE_SQL =
			"INSERT INTO Issue (title, description, date) VALUES (:title, :description, :date)";
	
	private static String UPDATE_SQL =
			"UPDATE Issue SET title = :title, description = :description, date = :date WHERE id = :id";
	
	private static String DELETE_SQL =
			"DELETE FROM Issue WHERE id = :id";
	
	private static String GET_ALL_ISSUES_SQL =
			"SELECT id, title, description, date FROM Issue";
	
	private static String GET_ISSUE_SQL =
			GET_ALL_ISSUES_SQL + " WHERE id = :id";
	
	public Issue create(Issue issue) {
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("id", issue.getId())
			.addValue("title", issue.getTitle())
			.addValue("description", issue.getDescription())
			.addValue("date", issue.getDate());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(CREATE_SQL, params, keyHolder,
				new String[] { "id" });

		issue.setId(keyHolder.getKey().longValue());

		return issue;
	}

	public Issue update(Issue issue) {
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("id", issue.getId())
			.addValue("title", issue.getTitle())
			.addValue("description", issue.getDescription())
			.addValue("date", issue.getDate());
		
		jdbcTemplate.update(UPDATE_SQL, params);
		
		return issue;
	}

	public void delete(Long id) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

		jdbcTemplate.update(DELETE_SQL, params);
	}

	public Issue getIssue(Long id) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

		return jdbcTemplate.queryForObject(GET_ISSUE_SQL, params, new IssueRowMapper());
	}

	public List<Issue> getAllIssues() {
		SqlParameterSource params = new MapSqlParameterSource();
		return jdbcTemplate.query(GET_ALL_ISSUES_SQL, params, new IssueRowMapper());
	}

}
