package pl.matfro.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.matfro.webstore.security.TempEmail;
import pl.matfro.webstore.security.TempPass;
import pl.matfro.webstore.security.User;
import pl.matfro.webstore.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/webstore")
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String welcome(Model model) {
        model.addAttribute("greeting", "Witaj w sklepie internetowym!");
        model.addAttribute("tagline", "Wyjątkowym i jedynym sklepie internetowym");
        return "welcome";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public String userSettings(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "settings";
    }

    @RequestMapping(value = "/settings/userDetailsEdit", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public String userDetailsChangeForm(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "userDetailsEdit";
    }

    @RequestMapping(value = "/settings/userDetailsEdit", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public String userDetailsChange(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "userDetailsEdit";
        }

        userService.updateUser(user);

        return "redirect:/webstore/settings";
    }

    @RequestMapping(value = "/settings/passwordChange", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public String passwordChangeForm(Model model) {
        model.addAttribute("tempPass", new TempPass());
        return "passwordChange";
    }

    @RequestMapping(value = "/settings/passwordChange", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public String passwordChange(@ModelAttribute("tempPass") @Valid TempPass tempPass, BindingResult result) {
        if (result.hasErrors()) {
            return "passwordChange";
        }

        userService.changePassword(tempPass);

        return "redirect:/webstore/settings";
    }

    @RequestMapping(value = "/settings/emailChange", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public String emailChangeForm(Model model) {
        model.addAttribute("tempEmail", new TempEmail());
        return "emailChange";
    }

    @RequestMapping(value = "/settings/emailChange", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public String emailChange(@ModelAttribute("tempEmail") @Valid TempEmail tempEmail, BindingResult result) {
        if (result.hasErrors()) {
            return "emailChange";
        }

        userService.changeEmail(tempEmail);

        return "redirect:/webstore/settings";
    }


}
