package es.udc.fi.tfg.seguimiento.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.udc.fi.tfg.seguimiento.utils.Form;

@Component
public class UsuarioValidator implements Validator {

	public boolean supports(Class<?> clazz){
		return Form.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.email", "Email obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.dni", "DNI obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.contrasena", "Contraseña obligatoria");

	}
}
