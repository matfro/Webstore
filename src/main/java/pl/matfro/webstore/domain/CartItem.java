package pl.matfro.webstore.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "cartItems")
public class CartItem implements Comparable, Serializable {

    private static final long serialVersionUID = 4379590560764656113L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Transient
    private Product product;

    private long productId;
    private int quantity;
    private BigDecimal totalPrice;

    public CartItem() {
        this.quantity = 1;
    }

    public CartItem(Product product) {
        super();
        this.product = product;
        this.productId = product.getProductId();
        this.quantity = 1;
        this.totalPrice = product.getUnitPrice();
    }

    public CartItem(Product product, int quantity) {
        super();
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getUnitPrice().multiply(new BigDecimal(quantity));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.updateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void updateTotalPrice() {
        totalPrice = this.product.getUnitPrice().multiply(new BigDecimal(this.quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(product);
    }

    @Override
    public int compareTo(Object o) {
        CartItem cI = (CartItem) o;
        if (this.product.getProductId() > ((CartItem) o).product.getProductId())
            return 1;
        else if (this.product.getProductId() < ((CartItem) o).product.getProductId())
            return -1;
        return 0;
    }
}