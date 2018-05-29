package pl.matfro.webstore.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {

    private Cart cart;

    @Before
    public void setup() {
        cart = new Cart();
    }

    @Test
    public void cart_grandTotal_should_be_equal_to_products_quantity_times_its_unit_prices(){

        BigDecimal grandTotal = new BigDecimal(0);
        BigDecimal unitPrice = new BigDecimal(500);

        for (int i=1; i<3; i++){
            cart.addCartItem(new CartItem(new Product(1+i, "iPhone 5s", unitPrice),i));
            grandTotal = grandTotal.add(unitPrice.multiply(new BigDecimal (i)));
        }

        Assert.assertEquals(grandTotal,cart.getGrandTotal());
    }


}
