package pl.matfro.webstore.domain;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;
import pl.matfro.webstore.validator.Category;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@XmlRootElement
@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 3786821105667429681L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Size(min = 4, max = 50, message = "{Size.Product.name.validation}")
    private String name;

    @Min(value = 0, message = "{Min.Product.unitPrice.validation}")
    @Digits(integer = 8, fraction = 2, message = "{Digits.Product.unitPrice.validation}")
    @NotNull(message = "{NotNull.Product.unitPrice.validation}")
    private BigDecimal unitPrice;

    @Size(min = 1, max = 200, message = "{Size.Product.description.validation}")
    private String description;

    @Size(min = 4, max = 50, message = "{Size.Product.manufacturer.validation}")
    private String manufacturer;

    @Size(min = 4, max = 50, message = "{Size.Product.category.validation}")
//    @Category
    private String category;

    @NotNull(message = "{NotNull.Product.unitsInStock.validation}")
    @Min(value = 0, message = "{Min.Product.unitsInStock.validation}")
    private long unitsInStock;

    @Min(value = 0, message = "{Min.Product.unitsInOrder.validation}")
    private long unitsInOrder;

    private boolean discontinued;

    @Column(columnDefinition = "VARCHAR(10)")
    @NotNull(message = "{NotNull.Product.condition.validation}")
    private String conditionn;

    @JsonIgnore
    @Transient
    //@NotNull(message="{NotNull.Product.productManual.validation}", payload=Severity.Error.class)
    private MultipartFile productManual;

    @JsonIgnore
    @Transient
    //@NotNull(message="{NotNull.Product.productImage.validation}", payload=Severity.Info.class)
    private MultipartFile productImage;

    public Product() {
        this.category = "Tablet";
        this.conditionn = "Nowy";
    }

    public Product(long productId, String name, BigDecimal unitPrice) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.conditionn = "Nowy";
        this.category = "Przykładowa kategoria";
    }


    public Product(long productId, String name, BigDecimal unitPrice, String category) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.conditionn = "Nowy";
        this.category = category;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setConditionn(String condition) {
        this.conditionn = condition;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public void setUnitsInOrder(long unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }

    public void setProductManual(MultipartFile productManual) {
        this.productManual = productManual;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public String getConditionn() {
        return conditionn;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public long getUnitsInOrder() {
        return unitsInOrder;
    }

    public boolean setDiscontinued() {
        return discontinued;
    }

    @XmlTransient
    public MultipartFile getProductImage() {
        return productImage;
    }

    @XmlTransient
    public MultipartFile getProductManual() {

        return productManual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "Produkt [productId=" + productId + ", nazwa=" + name + "]";
    }
}