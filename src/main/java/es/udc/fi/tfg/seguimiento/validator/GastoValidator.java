package es.udc.fi.tfg.seguimiento.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.udc.fi.tfg.seguimiento.model.Gasto;

@Component
public class GastoValidator implements Validator{
	
	public boolean supports(Class<?> clazz) {
		return Gasto.class.equals(clazz);
	}
	
public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "concepto", "Campo obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "importe", "Campo obligatorio");
		
	}
}
