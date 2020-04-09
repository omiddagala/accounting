package ir.hitelecom.accounting.repositories.sales;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.entities.sales.Sales;
import ir.hitelecom.accounting.entities.sales.Status;
import ir.hitelecom.accounting.entities.stock.ProductSize;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesRepository extends PagingAndSortingRepository<Sales, Long> {
    @Query(value = "from Sales s where (:customer = null or s.customer = :customer) and (:user = null or s.user= :user)  and (:product_size = null or s.productSize =:product_size)and (:status = null or s.status =:status)")
    List<Sales> search(@Param("customer") Customer customer, @Param("user") User user, @Param("product_size") ProductSize productSize, @Param("status") Status status, Pageable pageable);

    List<Sales> findAllById(Long id);
}
