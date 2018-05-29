package pl.matfro.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.matfro.webstore.domain.Order;
import pl.matfro.webstore.service.OrderService;
import pl.matfro.webstore.service.UserService;

import javax.persistence.Tuple;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/webstore")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping("/orders")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "allOrders";
    }

    @RequestMapping("/orders/order")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public String order(@RequestParam("id") String id, Model model) {
        long orderId = Long.parseLong(id);
        model.addAttribute("order", orderService.getOrderById(orderId));
        return "orderDetails";
    }

    @RequestMapping(value = "/myOrders", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public String usersOrders(Model model) {
        model.addAttribute("orders", userService.getUsersOrders(userService.getCurrentUser().getCustomer()));
        model.addAttribute("username", userService.getCurrentUser().getUsername());
        return "orders";
    }

    @RequestMapping(value = "/myOrders/order", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public String usersOrder(@RequestParam("id") String id, Model model) {
        long orderId = Long.parseLong(id);
        model.addAttribute("order", orderService.getOrderById(orderId));
        return "order";
    }

}
