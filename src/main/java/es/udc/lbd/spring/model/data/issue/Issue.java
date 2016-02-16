package es.udc.lbd.spring.model.data.issue;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Issue {
	
	private Long id;
	
	private String title;
	
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	public Issue() {
		
	}
	
	public Issue(String title, String description, Date date) {
		this.title = title;
		this.description = description;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", title=" + title + ", description="
				+ description + ", date=" + date + "]";
	}
	
}
