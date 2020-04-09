package ir.hitelecom.accounting.repositories.sales;

import ir.hitelecom.accounting.entities.sales.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Optional<Customer> findByNationalCode(String nationalCode);
}
