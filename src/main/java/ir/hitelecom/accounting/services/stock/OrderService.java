package ir.hitelecom.accounting.services.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Order;
import ir.hitelecom.accounting.entities.stock.Product;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.repositories.UserRepository;
import ir.hitelecom.accounting.repositories.stock.OrderRepository;
import ir.hitelecom.accounting.repositories.stock.ProductRepository;
import ir.hitelecom.accounting.repositories.stock.ProductSizeRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService extends BaseService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private ProductRepository productRepository;

    public void submit(Order order) {
        User user = userRepository.findByUsername(getLoggedInUsername());
        order.setSubmitter(user);
        orderRepository.save(order);
    }

    public List<Order> fetchOrdersForCurrentUser() {
        User user = userRepository.findByUsername(getLoggedInUsername());
        return orderRepository.findBySourceAndClosed(user.getReservoir(), false);
    }

    public void close(Long id) {
        orderRepository.deleteById(id);
    }

    public void approve(Order order) {
        Optional<Order> o = orderRepository.findById(order.getId());
        Order fetchedOrder;
        if (o.isPresent()) {
            fetchedOrder = o.get();
            order.getListOrders().forEach(ord -> {
                ProductSize source = productSizeRepository.findByProductReservoirAndProductNameAndSizeId(fetchedOrder.getSource(), fetchedOrder.getProduct().getName(), ord.getId());
                source.setCount(new Long(source.getCount() == null ? 0 : source.getCount() - (ord.getValue() != null && !"".equals(ord.getValue()) ? Long.valueOf(ord.getValue()) : 0)).intValue());
                ProductSize destination = productSizeRepository.findByProductReservoirAndProductNameAndSizeId(fetchedOrder.getDestination(), fetchedOrder.getProduct().getName(), ord.getId());
                if (destination == null) {
                    Product destinationProduct = productRepository.findByReservoirAndName(fetchedOrder.getDestination(), fetchedOrder.getProduct().getName());
                    if (destinationProduct == null) {
                        Optional<Product> op = productRepository.findById(fetchedOrder.getProduct().getId());
                        if (op.isPresent()) {
                            Product product = op.get();
                            Product newProduct = product.clone();
                            newProduct.setReservoir(fetchedOrder.getDestination());
                            Product savedProduct = productRepository.save(newProduct);
                            ProductSize newProductSize = new ProductSize();
                            newProductSize.setCount((ord.getValue() != null && !"".equals(ord.getValue()) ? Integer.valueOf(ord.getValue()) : 0));
                            newProductSize.setProduct(savedProduct);
                            newProductSize.setSize(source.getSize());
                            productSizeRepository.save(newProductSize);
                        }
                    } else {
                        ProductSize newProductSize = new ProductSize();
                        newProductSize.setCount((ord.getValue() != null && !"".equals(ord.getValue()) ? Integer.valueOf(ord.getValue()) : 0));
                        newProductSize.setProduct(destinationProduct);
                        newProductSize.setSize(source.getSize());
                        productSizeRepository.save(newProductSize);
                    }

                } else {
                    destination.setCount(new Long(destination.getCount() == null ? 0 : destination.getCount() +  (ord.getValue() != null && !"".equals(ord.getValue()) ? Long.valueOf(ord.getValue()) : 0)).intValue());
                }
            });
            orderRepository.delete(fetchedOrder);
        }
    }

}
