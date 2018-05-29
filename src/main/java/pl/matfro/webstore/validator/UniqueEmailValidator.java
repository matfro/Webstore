package pl.matfro.webstore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.matfro.webstore.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserService userService;

    @Override
    public void initialize(final UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        return !userService.emailExists(email);
    }
}
