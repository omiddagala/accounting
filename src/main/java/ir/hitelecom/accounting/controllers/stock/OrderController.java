package ir.hitelecom.accounting.controllers.stock;

import ir.hitelecom.accounting.entities.stock.Order;
import ir.hitelecom.accounting.services.stock.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    @ResponseBody
    public void submit(@RequestBody Order order) {
        orderService.submit(order);
    }

    @PostMapping("/fetch")
    @ResponseBody
    public List<Order> fetch() {
        return orderService.fetchOrdersForCurrentUser();
    }
}
