package pl.matfro.webstore.security;

import pl.matfro.webstore.validator.NewPasswordMatches;
import pl.matfro.webstore.validator.OldPasswordValid;
import pl.matfro.webstore.validator.ValidPassword;

import java.io.Serializable;

@NewPasswordMatches(message = "{pl.matfro.webstore.security.validator.passwordMatches}")
public class TempPass implements Serializable {

    private static final long serialVersionUID = 6880580902496701908L;

    @ValidPassword(message = "{pl.matfro.webstore.security.validator.validPassword}")
    private String password;

    private String matchingPassword;

    @OldPasswordValid(message = "{pl.matfro.webstore.security.validator.oldPassword}")
    private String oldPassword;

    public TempPass() {
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
