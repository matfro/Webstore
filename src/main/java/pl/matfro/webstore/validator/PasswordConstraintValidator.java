package pl.matfro.webstore.validator;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // @formatter:off
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(5, 60),
//            new UppercaseCharacterRule(1),
//            new DigitCharacterRule(1),
//            new SpecialCharacterRule(1),
//            new NumericalSequenceRule(3,false),
//            new AlphabeticalSequenceRule(3,false),
//            new QwertySequenceRule(3,false),
                new WhitespaceRule()));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        return false;
    }

}
