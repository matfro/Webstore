package pl.matfro.webstore.service;

import pl.matfro.webstore.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomer(long id);

    boolean customerExists(long id);
}
