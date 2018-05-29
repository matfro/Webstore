package pl.matfro.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matfro.webstore.domain.Customer;
import pl.matfro.webstore.domain.Order;
import pl.matfro.webstore.security.TempEmail;
import pl.matfro.webstore.security.TempPass;
import pl.matfro.webstore.security.User;
import pl.matfro.webstore.security.repository.UserRepository;
import pl.matfro.webstore.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public List<User> getAllSupervisors() {
        return userRepository.getAllSupervisors();
    }

    @Override
    public void addNewUser(User user) {
        userRepository.addNewUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void changePassword(TempPass user) {
        userRepository.changePassword(user);
    }

    @Override
    public void changeEmail(TempEmail tempEmail) {
        userRepository.changeEmail(tempEmail);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    @Override
    public List<Order> getUsersOrders(Customer customer) {
        return userRepository.getUsersOrders(customer);
    }

    @Override
    public boolean isUser() {
        return userRepository.isUser();
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.usernameExists(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.emailExists(email);
    }

}
