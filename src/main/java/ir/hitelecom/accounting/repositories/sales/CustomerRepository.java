package ir.hitelecom.accounting.repositories.sales;

import ir.hitelecom.accounting.entities.sales.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByNationalCode(String nationalCode);

}
