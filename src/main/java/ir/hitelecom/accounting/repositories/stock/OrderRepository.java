package ir.hitelecom.accounting.repositories.stock;

import ir.hitelecom.accounting.entities.stock.Order;
import ir.hitelecom.accounting.entities.stock.Reservoir;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Long> {

    List<Order> findBySourceAndSeen(Reservoir reservoir, boolean seen);
}
