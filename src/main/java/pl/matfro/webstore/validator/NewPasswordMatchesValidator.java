package pl.matfro.webstore.validator;

import pl.matfro.webstore.security.TempPass;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NewPasswordMatchesValidator implements ConstraintValidator<NewPasswordMatches, Object> {

    @Override
    public void initialize(NewPasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        TempPass user = (TempPass) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
