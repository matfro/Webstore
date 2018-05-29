package pl.matfro.webstore.domain.repository;

import pl.matfro.webstore.domain.Order;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;

public interface OrderRepository {
    Long saveOrder(Order order);

    void processOrder(long productId, int count);

    Map<String, Order> getAllOrders();

    Order getOrderById(long orderId);
}
