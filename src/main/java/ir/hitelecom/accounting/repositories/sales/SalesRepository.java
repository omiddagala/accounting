package ir.hitelecom.accounting.repositories.sales;

import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.entities.sales.Sales;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SalesRepository extends PagingAndSortingRepository<Sales, Long> {
    List<Sales> findByCustomer(Customer customer, Pageable pageable);
}
