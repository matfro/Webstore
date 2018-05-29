package pl.matfro.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matfro.webstore.domain.Order;
import pl.matfro.webstore.domain.repository.OrderRepository;
import pl.matfro.webstore.service.CartService;
import pl.matfro.webstore.service.OrderService;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Override
    public void processOrder(long productId, int count) throws IllegalArgumentException {
        orderRepository.processOrder(productId, count);
    }

    @Override
    public Long saveOrder(Order order) {
        Long orderId = orderRepository.saveOrder(order);
        cartService.delete(order.getCart().getCartId());
        return orderId;
    }

    @Override
    public Map<String, Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public Order getOrderById(long orderId) {
        return orderRepository.getOrderById(orderId);
    }
}
