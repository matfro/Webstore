package pl.matfro.webstore.security;

import pl.matfro.webstore.validator.UniqueEmail;
import pl.matfro.webstore.validator.ValidEmail;

import java.io.Serializable;

public class TempEmail implements Serializable {
    private static final long serialVersionUID = -325404272023748735L;

    @ValidEmail(message = "{pl.matfro.webstore.security.validator.validEmail}")
    @UniqueEmail(message = "{pl.matfro.webstore.security.validator.uniqueEmail}")
    private String email;

    public TempEmail() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
