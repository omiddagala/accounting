package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Order;
import ir.hitelecom.accounting.repositories.UserRepository;
import ir.hitelecom.accounting.repositories.stock.OrderRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService extends BaseService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    public void submit(Order order) {
        User user = userRepository.findByUsername(getLoggedInUsername());
        order.setSubmitter(user);
        orderRepository.save(order);
    }

    public List<Order> fetchOrdersForCurrentUser() {
        User user = userRepository.findByUsername(getLoggedInUsername());
        return orderRepository.findBySourceAndSeen(user.getReservoir(), false);
    }

}
