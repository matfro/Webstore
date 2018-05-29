package pl.matfro.webstore.security;


import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import pl.matfro.webstore.domain.Customer;
import pl.matfro.webstore.validator.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@FetchProfile(name = "user-with-authorities", fetchOverrides = {
        @FetchProfile.FetchOverride(entity = User.class, association = "authorities", mode = FetchMode.JOIN)
})
@PasswordMatches(message = "{pl.matfro.webstore.security.validator.passwordMatches}")
@DynamicUpdate(true)
public class User implements Serializable {

    private static final long serialVersionUID = -653002834394411506L;
    @Id
    @Column(length = 50, nullable = false)
    @UniqueUsername(message = "{pl.matfro.webstore.security.validator.uniqueUsername}")
    @NotNull
    private String username;

    @Column(length = 100, nullable = false)
    @ValidPassword(message = "{pl.matfro.webstore.security.validator.validPassword}")
    private String password;

    @Transient
    private String matchingPassword;

    @Column(length = 100, nullable = false)
    @ValidEmail(message = "{pl.matfro.webstore.security.validator.validEmail}")
    @UniqueEmail(message = "{pl.matfro.webstore.security.validator.uniqueEmail}")
    private String email;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Set<Authorities> authorities = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customer;

    public User() {
        this.customer = new Customer();
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.customer = new Customer();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
