package pl.matfro.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart implements Serializable {

    private static final long serialVersionUID = 8521622422274029117L;

    private String cartId;
    //private Map<Long,CartItem> cartItems;
    private List<CartItem> cartItems;
    private BigDecimal grandTotal;

    public Cart() {
        cartItems = new ArrayList<CartItem>();
        grandTotal = new BigDecimal(0);
    }

    public Cart(String cartId) {
        this();
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public void addCartItem(CartItem item) {
        long productId = item.getProduct().getProductId();

        if (cartItems.contains(item)) {
            CartItem existingCartItem = cartItems.get(cartItems.indexOf(item));
            existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
        } else {
            cartItems.add(item);
        }
        updateGrandTotal();
    }

    public void removeCartItem(CartItem item) {
        //long productId = item.getProduct().getProductId();
        cartItems.remove(item);
        updateGrandTotal();
    }

    public void updateGrandTotal() {
        grandTotal = new BigDecimal(0);
        for (CartItem item : cartItems) {
            grandTotal = grandTotal.add(item.getTotalPrice());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cartId);
    }
}
