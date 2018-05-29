package pl.matfro.webstore.service;

import pl.matfro.webstore.domain.Order;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;

public interface OrderService {

    void processOrder(long productId, int count);

    Long saveOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(long orderId);
}
