package pl.matfro.webstore.validator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import pl.matfro.webstore.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-DispatcherServlet-context.xml")
@WebAppConfiguration
public class ProductValidatorTest {

    @Autowired
    private ProductValidator productValidator;


    private String getLocalizedMessage(BindException bindException, String expMsg){

        String errMsg = null;

        errMsg = new String(bindException.getLocalizedMessage().getBytes(),
                bindException.getLocalizedMessage().length() - expMsg.length() -1, expMsg.length());

        return errMsg;

    }

    @Test
    public void product_without_UnitPrice_should_be_invalid() {

        Product product = new Product();
        BindException bindException = new BindException(product, "product");

        ValidationUtils.invokeValidator(productValidator, product, bindException);

        String expMsg = "Błędna cena produktu. Cena nie może być pusta.";
        String errMsg = getLocalizedMessage(bindException, expMsg);

        Assert.assertEquals(1, bindException.getErrorCount());
        Assert.assertEquals(expMsg, errMsg);
    }

    @Test
    public void a_valid_product_should_not_get_any_error_during_validation() {

        Product product = new Product(1,"iPhone 5s", new BigDecimal(500));

        BindException bindException = new BindException(product, "product");

        ValidationUtils.invokeValidator(productValidator, product, bindException);

        Assert.assertEquals(0, bindException.getErrorCount());
    }
}