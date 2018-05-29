package pl.matfro.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.matfro.webstore.domain.Order;
import pl.matfro.webstore.security.User;
import pl.matfro.webstore.service.OrderService;
import pl.matfro.webstore.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "/webstore/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public String list(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegistrationForm(Model model) {
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationForm(@ModelAttribute("newUser") @Valid User newUser, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        userService.addNewUser(newUser);

        String[] suppressedFields = result.getSuppressedFields();

        if (suppressedFields.length > 0) {
            throw new RuntimeException("Próba wi¹zania niedozwolonych pól:" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        return "redirect:/webstore/users/registrationComplete";
    }

    @RequestMapping(value = "/registrationComplete", method = RequestMethod.GET)
    public String registrationConfirmation(Model model) {
        return "registrationComplete";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public String getUserByUsername(@RequestParam("id") String username, Model model) {
        model.addAttribute("user", userService.getUserByUsername(username));
        return "user";
    }

    @RequestMapping(value = "/orders/user", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public String getUsersOrders(@RequestParam("id") String username, Model model) {
        model.addAttribute("orders", userService.getUsersOrders(userService.getUserByUsername(username).getCustomer()));
        model.addAttribute("username", username);
        return "orders";
    }

    @RequestMapping(value = "/orders/{Criteria}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public String getUsersOrder(@MatrixVariable(pathVar = "Criteria") Map<String, String> filterParams, Model model) {
        String username = filterParams.get("userId");
        long orderId = Long.parseLong(filterParams.get("orderId"));
        model.addAttribute("username", username);
        model.addAttribute("order", orderService.getOrderById(orderId));
        return "order";
    }

    @RequestMapping(value = "/supervisors", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getSupervisors(Model model) {
        model.addAttribute("users", userService.getAllSupervisors());
        return "supervisors";
    }
}
