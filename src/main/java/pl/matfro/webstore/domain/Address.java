package pl.matfro.webstore.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Addresses")
public class Address implements Serializable {

    private static final long serialVersionUID = -530086768384258062L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    @Column(length = 10)
    @NotNull(message="{Address.doorNo.NotNull.validation}")
    @Size(max=10, message="{Address.doorNo.Size.validation}")
    private String doorNo;

    @Column(length = 50)
    @NotNull(message="{Address.streetName.NotNull.validation}")
    @Size(max=50, message="{Address.streetName.Size.validation}")
    private String streetName;

    @Column(length = 50)
    @NotNull(message="{Address.areaName.NotNull.validation}")
    @Size(max=50, message="{Address.areaName.Size.validation}")
    private String areaName;

    @Column(length = 50)
    @Size(max=50, message="{Address.state.Size.validation}")
    private String state;

    @Column(length = 50)
    @NotNull(message="{Address.country.NotNull.validation}")
    @Size(max=50, message="{Address.country.Size.validation}")
    private String country;

    @Column(length = 10)
    @NotNull(message="{Address.zipCode.NotNull.validation}")
    @Size(max=10, message="{Address.zipCode.Size.validation}")
    private String zipCode;

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(doorNo, address.doorNo) &&
                Objects.equals(streetName, address.streetName) &&
                Objects.equals(areaName, address.areaName) &&
                Objects.equals(state, address.state) &&
                Objects.equals(country, address.country) &&
                Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(doorNo, streetName, areaName, state, country, zipCode);
    }
}