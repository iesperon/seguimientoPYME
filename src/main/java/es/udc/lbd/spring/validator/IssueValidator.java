package es.udc.lbd.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.udc.lbd.spring.model.data.issue.Issue;

@Component
public class IssueValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Issue.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		
		Issue issue = (Issue) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.issue.title.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.issue.description.empty");
		
		if (issue.getDescription() != null && ! issue.getDescription().equals("") &&
				issue.getDescription().length() < 10) {
			errors.rejectValue("description", "error.issue.description.tooshort");
		}
		
	}

}
