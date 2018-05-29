package pl.matfro.webstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.matfro.webstore.domain.Cart;
import pl.matfro.webstore.domain.CartItem;
import pl.matfro.webstore.service.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-DispatcherServlet-context.xml")
@WebAppConfiguration
public class CartRestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    MockHttpSession session;

    @Autowired
    ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void create_method_should_create_cart_given() throws Exception{

        Cart cart = new Cart("1234");

        this.mockMvc.perform(post("/webstore/rest/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(cart)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId").value("1234"));
    }

    @Test
    public void read_method_should_return_correct_cart_Json_object() throws Exception {

        this.mockMvc.perform(put("/webstore/rest/cart/add/1").session(session))
                .andExpect(status().is(204));

        this.mockMvc.perform(get("/webstore/rest/cart/"+ session.getId()).session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItems.P1234.product.productId").value(1));
    }

    @Test
    public void update_method_should_update_cart_item_as_given() throws Exception{

        int updatedQuantity = 2;
        Cart cart = new Cart("1234");
        CartItem cartItem = new CartItem(productService.getProductById(1));

        cartItem.setQuantity(updatedQuantity);
        cart.addCartItem(cartItem);

        this.mockMvc.perform(put("/webstore/rest/cart/add/1").session(session))
                .andExpect(status().is(204));


        this.mockMvc.perform(put("/webstore/rest/cart/"+ session.getId()).session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(cart)))
                .andExpect(status().is(204));


        this.mockMvc.perform(get("/webstore/rest/cart/"+session.getId()).session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItems.1.quantity").value(updatedQuantity));
    }

   @Test
    public void delete_method_should_delete_cart_object() throws Exception {

        String cartId = "1234";
        Cart cart = new Cart(cartId);

        this.mockMvc.perform(post("/webstore/rest/cart")
               .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(cart)));

        this.mockMvc.perform(delete("/webstore/rest/cart/"+cartId))
                .andExpect(status().is(204));
   }

   @Test
    public void addProduct_method_should_add_given_product_to_cart() throws Exception {

        long productId = 1;

        this.mockMvc.perform(put("/webstore/rest/cart/add/"+productId).session(session))
               .andExpect(status().is(204));

        this.mockMvc.perform(get("/webstore/rest/cart/"+session.getId()).session(session))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.cartItems.P1234.product.productId").value(productId));
   }

   @Test
    public void removeItem_method_should_remove_given_item_from_cart() throws Exception {

        long itemId = 1;

        this.mockMvc.perform(put("/webstore/rest/cart/add/"+itemId).session(session))
               .andExpect(status().is(204));

        this.mockMvc.perform(put("/webstore/rest/cart/remove/"+itemId).session(session))
               .andExpect(status().is(204));

        this.mockMvc.perform(get("/webstore/rest/cart/"+session.getId()).session(session))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.cartItems."+itemId).doesNotExist());
   }


}
