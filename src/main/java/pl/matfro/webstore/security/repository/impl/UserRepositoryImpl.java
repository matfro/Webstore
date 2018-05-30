package pl.matfro.webstore.security.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.matfro.webstore.domain.Address;
import pl.matfro.webstore.domain.Customer;
import pl.matfro.webstore.domain.Order;
import pl.matfro.webstore.security.Authorities;
import pl.matfro.webstore.security.TempEmail;
import pl.matfro.webstore.security.TempPass;
import pl.matfro.webstore.security.User;
import pl.matfro.webstore.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.enableFetchProfile("user-with-authorities");
        Query query = session.
                createQuery("select u " +
                        "from User u " +
                        "join u.authorities a " +
                        "where a.authority = 'ROLE_USER'");
        return query.getResultList();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<User> getAllSupervisors() {
        Session session = sessionFactory.getCurrentSession();
        session.enableFetchProfile("user-with-authorities");
        Query query = session.
                createQuery("select u " +
                        "from User u " +
                        "join u.authorities a " +
                        "where a.authority = 'ROLE_SUPERVISOR'");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void addNewUser(User user) {

        user.setEnabled(true);

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        Authorities authorities = new Authorities("ROLE_USER", user);

        Set<Authorities> authSet = new HashSet<>();

        authSet.add(authorities);

        user.setAuthorities(authSet);

        Customer customer = new Customer();

        customer.setBillingAddress(new Address());

        user.setCustomer(customer);

        Session session = sessionFactory.getCurrentSession();

        session.save(customer);

        session.save(user);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        User userOld = getCurrentUser();
        System.out.println(userOld.getUsername());
        user.getCustomer().setCustomerId(userOld.getCustomer().getCustomerId());
        userOld.setCustomer(user.getCustomer());
        session.update(userOld);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public void changePassword(TempPass tempPass) {
        Session session = sessionFactory.getCurrentSession();
        User userOld = getCurrentUser();
        userOld.setPassword(new BCryptPasswordEncoder().encode(tempPass.getPassword()));
        session.update(userOld);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public void changeEmail(TempEmail tempEmail) {
        Session session = sessionFactory.getCurrentSession();
        User userOld = getCurrentUser();
        userOld.setEmail(tempEmail.getEmail());
        session.update(userOld);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public User getUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        session.enableFetchProfile("user-with-authorities");
        Query query = session.
                createQuery("select u " +
                        "from User u " +
                        "where u.username = :username");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public User getCurrentUser() {
        Session session = sessionFactory.getCurrentSession();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return session.get(User.class, userDetails.getUsername());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public List<Order> getUsersOrders(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.enableFetchProfile("order-with-cartItemsList");
        Query query = session.
                createQuery("select o " +
                        "from Order o " +
                        "where o.customer = :customer");
        query.setParameter("customer", customer);

        return query.getResultList();
    }


    @Override
    @Transactional
    public boolean isUser() {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (authorities.size() > 0)
            if (authorities.iterator().next().getAuthority().equals("ROLE_USER"))
                return true;
        return false;
    }

    @Override
    @Transactional
    public boolean usernameExists(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, username);
        return user != null;
    }

    @Override
    @Transactional
    public boolean emailExists(String email) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.
                createQuery("select u " +
                        "from User u " +
                        "where u.email = :email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();

        return user != null;
    }
}
