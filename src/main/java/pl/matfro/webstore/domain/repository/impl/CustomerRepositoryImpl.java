package pl.matfro.webstore.domain.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import pl.matfro.webstore.domain.Customer;
import pl.matfro.webstore.domain.repository.CustomerRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    SessionFactory sessionFactory;

    public CustomerRepositoryImpl() {
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
        criteriaQuery.from(Customer.class);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(customer);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public Customer getCustomer(long id) {
        Session session = sessionFactory.getCurrentSession();
        Customer c = session.get(Customer.class, id);
        return c;
    }

    @Override
    public boolean customerExists(long id) {
        if (getCustomer(id) != null)
            return true;
        return false;
    }
}
