package pl.matfro.webstore.service;

import pl.matfro.webstore.domain.Customer;
import pl.matfro.webstore.domain.Order;
import pl.matfro.webstore.security.TempEmail;
import pl.matfro.webstore.security.TempPass;
import pl.matfro.webstore.security.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    List<User> getAllSupervisors();

    void addNewUser(User user);

    void updateUser(User user);

    void changePassword(TempPass user);

    void changeEmail(TempEmail tempEmail);

    User getUserByUsername(String username);

    User getCurrentUser();

    List<Order> getUsersOrders(Customer customer);

    boolean isUser();

    boolean usernameExists(String username);

    boolean emailExists(String email);
}
