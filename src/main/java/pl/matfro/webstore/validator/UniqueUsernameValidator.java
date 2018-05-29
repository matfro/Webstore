package pl.matfro.webstore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.matfro.webstore.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UserService userService;

    @Override
    public void initialize(final UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(final String username, final ConstraintValidatorContext context) {
        return !userService.usernameExists(username);
    }
}
