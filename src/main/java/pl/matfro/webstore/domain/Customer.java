package pl.matfro.webstore.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 2284040482222162898L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    @Column(length = 50)
//    @NotNull(message="{Customer.firstName.NotNull.validation}")
    @Size(max=50, message="{Customer.firstName.Size.validation}")
    private String firstName;

    @Column(length = 50)
//    @NotNull(message="{Customer.lastName.NotNull.validation}")
    @Size(max=50, message="{Customer.lastName.Size.validation}")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address billingAddress;

    @Column(length = 50)
    private String phoneNumber;

    private long noOfOrdersMade = 0;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long id) {
        this.customerId = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setNoOfOrdersMade(long noOfOrdersMade) {
        this.noOfOrdersMade = noOfOrdersMade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public long getNoOfOrdersMade() {
        return noOfOrdersMade;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
        super();
        this.billingAddress = new Address();
    }

    public Customer(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(billingAddress, customer.billingAddress) &&
                Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, billingAddress, customerId, phoneNumber);
    }

    @Override
    public String toString() {
        return "Klient [customerId = C" + customerId + ", nazwa=" + firstName + " " + lastName + ", liczba zamówień: " + noOfOrdersMade + "]";
    }
}
