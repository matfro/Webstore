package pl.matfro.webstore.domain.repository;

import pl.matfro.webstore.domain.Customer;

import java.util.List;


public interface CustomerRepository {
    List<Customer> getAllCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomer(long id);

    boolean customerExists(long id);

}
