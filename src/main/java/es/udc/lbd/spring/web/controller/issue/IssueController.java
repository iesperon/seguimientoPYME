package es.udc.lbd.spring.web.controller.issue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.udc.lbd.spring.model.data.issue.Issue;
import es.udc.lbd.spring.model.services.issue.IssueService;
import es.udc.lbd.spring.validator.IssueValidator;

@Controller
@RequestMapping(value = "/issue")
public class IssueController {

	@Autowired
	private IssueService issueService;
	
	@Autowired
	IssueValidator issueValidator;
	
	@InitBinder("issue")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(issueValidator);
	}

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
	  binder.registerCustomEditor(Date.class, orderDateEditor);
	}
	
	// List of issues
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		model.addAttribute("issueList", issueService.getAllIssues());
	}
	
	// See issue details
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Model details(Long id, Model model) {
		model.addAttribute(issueService.getIssue(id));
		return model;
	}
	
	// Add a new issue
	@RequestMapping(value = "/addIssueForm", method = RequestMethod.GET)
	public ModelAndView addIssueForm(ModelAndView modelAndView) {
		modelAndView.addObject(new Issue());
		modelAndView.setViewName("issue/add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addIssue", method = RequestMethod.POST)
	public String addIssue(@Validated Issue issue, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			
			return "issue/add";		
			
		} else {
		
			issue.setDate(new Date(System.currentTimeMillis()));
			issueService.create(issue);
		
			return "redirect:/issue/list";
		}
	}
	
	// Delete an issue
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Long id) {
		issueService.delete(id);
		return "redirect:/issue/list";
	}
	
	// Update an issue
	@RequestMapping(value = "/updateIssueForm", method = RequestMethod.GET)
	public ModelAndView updateIssueForm(Long id, ModelAndView modelAndView) {
		Issue issue = issueService.getIssue(id);
		System.out.println(issue);
		modelAndView.addObject("issue", issue);
		modelAndView.setViewName("issue/update");
		return modelAndView;
	}
	
	@RequestMapping(value = "/updateIssue", method = RequestMethod.POST)
	public String updateIssue(@ModelAttribute("issue") Issue issue) {
		System.out.println(issue);
		issueService.update(issue);
		return "redirect:/issue/list";
	}

}
