package es.udc.fi.tfg.seguimiento.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.udc.fi.tfg.seguimiento.model.Proveedor;

@Component
public class ProveedorValidator implements Validator{
	
	public boolean supports(Class<?> clazz){
		return Proveedor.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cif", "cif");

	}
}
