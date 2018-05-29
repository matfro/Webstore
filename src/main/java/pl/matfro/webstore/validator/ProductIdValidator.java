package pl.matfro.webstore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.matfro.webstore.domain.Product;
import pl.matfro.webstore.exception.ProductNotFoundException;
import pl.matfro.webstore.service.ProductService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIdValidator implements ConstraintValidator<ProductId, String> {

    @Autowired
    private ProductService productService;

    @Override
    public void initialize(ProductId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Product product;

        try {
            product = productService.getProductById(Long.valueOf(value).longValue());

        } catch (ProductNotFoundException e) {
            return true;
        }

        if (product != null) {
            return false;
        }

        return true;
    }

}