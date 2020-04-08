package ir.hitelecom.accounting.repositories.sales;

import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.entities.sales.Sales;
import org.springframework.data.repository.CrudRepository;

public interface SalesRepository extends CrudRepository<Sales, Long> {
    Iterable<Sales> findByCustomer(Customer customer);
}
