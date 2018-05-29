package pl.matfro.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matfro.webstore.domain.Customer;
import pl.matfro.webstore.domain.repository.CustomerRepository;
import pl.matfro.webstore.service.CustomerService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.saveCustomer(customer);
    }

    @Override
    public Customer getCustomer(long id) {
        return customerRepository.getCustomer(id);
    }

    @Override
    public boolean customerExists(long id) {
        return customerRepository.customerExists(id);
    }
}
