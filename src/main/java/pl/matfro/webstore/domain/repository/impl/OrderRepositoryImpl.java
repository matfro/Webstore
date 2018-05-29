package pl.matfro.webstore.domain.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import pl.matfro.webstore.domain.CartItem;
import pl.matfro.webstore.domain.Customer;
import pl.matfro.webstore.domain.Order;
import pl.matfro.webstore.domain.Product;
import pl.matfro.webstore.domain.repository.OrderRepository;
import pl.matfro.webstore.security.User;
import pl.matfro.webstore.service.ProductService;
import pl.matfro.webstore.service.UserService;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public OrderRepositoryImpl() {
    }

    @Override
    @Transactional
    public Long saveOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();

        List<CartItem> cartItems = order.getCart().getCartItems();
        order.setCartItemsList(cartItems);

        for (CartItem cI : cartItems) {
            processOrder(cI.getProduct().getProductId(), cI.getQuantity());
        }

        Customer customer = order.getCustomer();
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            User user = userService.getCurrentUser();
            if (user.getCustomer().getCustomerId() != 0) {
                customer.setCustomerId(user.getCustomer().getCustomerId());
                customer.getBillingAddress().setAddressId(user.getCustomer().getBillingAddress().getAddressId());
                order.setUsername(user.getUsername());
            }
            session.clear();
        }

        customer.setNoOfOrdersMade(customer.getNoOfOrdersMade() + 1);
        order.setGrandTotal(order.getCart().getGrandTotal());
        session.save(order);
        session.saveOrUpdate(customer);

        return order.getOrderId();
    }

    @Override
    @Transactional
    public void processOrder(long productId, int count) {
        Product productById = productService.getProductById(productId);

        if (productById.getUnitsInStock() < count) {
            throw new IllegalArgumentException("Zbyt ma³o towaru. Obecna liczba sztuk w magazynie: " + productById.getUnitsInStock());
        }
        productById.setUnitsInStock(productById.getUnitsInStock() - count);
        productById.setUnitsInOrder(productById.getUnitsInOrder() + count);

        Session session = sessionFactory.getCurrentSession();

        session.update(productById);

        session.flush();
        session.clear();
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public Map<String, Order> getAllOrders() {
        Session session = sessionFactory.getCurrentSession();
        session.enableFetchProfile("order-with-cartItemsList");

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> tupleQuery = criteriaBuilder.createTupleQuery();
        Root<Order> orderRoot = tupleQuery.from(Order.class);
        Root<User> userRoot = tupleQuery.from(User.class);
        Path<String> username = userRoot.get("username");
        tupleQuery.multiselect(username, orderRoot);
        tupleQuery.where(criteriaBuilder.equal(orderRoot.get("customer"), userRoot.get("customer")));

        List<Order> anonymousOrdersList = session.createQuery("select o from Order o where o.username is null").getResultList();

        List<Tuple> tuples = session.createQuery(tupleQuery).getResultList();

        Map<String, Order> map = new HashMap<>();

        for (Tuple tuple : tuples)
            map.put((String) tuple.get(0), (Order) tuple.get(1));
        for (Order order : anonymousOrdersList)
            map.put("Anonymous" + order.getOrderId(), order);

        return map;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')")
    public Order getOrderById(long orderId) {
        Session session = sessionFactory.getCurrentSession();
        session.enableFetchProfile("order-with-cartItemsList");
        Query Query = session.createQuery("select o from Order o where o.orderId = :orderId");
        Query.setParameter("orderId", orderId);
        Order order = (Order) Query.getSingleResult();
        for (CartItem item : order.getCartItemsList()) {
            item.setProduct(productService.getProductById(item.getProductId()));
        }
        return order;
    }
}
