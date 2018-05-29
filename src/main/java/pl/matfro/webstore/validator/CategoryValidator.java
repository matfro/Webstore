package pl.matfro.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CategoryValidator implements ConstraintValidator<Category, String> {

    private List<String> allowedCategories;

    @Override
    public void initialize(Category constraintAnnotation) {
        allowedCategories = Arrays.asList("Smartfon", "Laptop", "Tablet", "Słuchawki", "Przykładowa kategoria");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        for (String category : allowedCategories) {
            if (value.equalsIgnoreCase(category))
                if (!value.isEmpty())
                    return true;
        }
        return false;
    }

}
