package pl.matfro.webstore.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class ShippingDetail implements Serializable {

    private static final long serialVersionUID = 6350930334140807514L;

    private String firstName;

    private String lastName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Timestamp datePlaced;

    private Address shippingAddress;

    public ShippingDetail() {
        this.shippingAddress = new Address();
        this.datePlaced = new Timestamp(System.currentTimeMillis() + 2 * 60 * 60 * 1000);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Timestamp datePlaced) {
        this.datePlaced = datePlaced;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
