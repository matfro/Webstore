package pl.matfro.webstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.matfro.webstore.domain.Product;

@Component
public class ProductImageValidator implements Validator {

    private long allowedSize; //1048576;

    public void setAllowedSize(long allowedSize) {
        this.allowedSize = allowedSize;
    }

    public long getAllowedSize() {
        return allowedSize;
    }

    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {

        Product product = (Product) target;
        if (!product.getProductImage().isEmpty() && product.getProductImage().getSize() > allowedSize) {
            errors.rejectValue("ProductImage", "pl.matfro.webstore.validator.ProductImageValidator.message");
        }
    }
}
