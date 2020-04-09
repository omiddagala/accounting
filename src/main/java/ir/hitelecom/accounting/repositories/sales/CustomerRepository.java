package ir.hitelecom.accounting.repositories.sales;

import ir.hitelecom.accounting.entities.sales.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    @Query(value = "from Customer c where (:name = null or c.name like %:name%) and (:family = null or c.family like %:family%)  and (:national_code = null or c.nationalCode like %:national_code%)and (:mobile = null or c.mobile like %:mobile%)")
    List<Customer> search(@Param("name") String name, @Param("family") String family, @Param("national_code") String nationalCode, @Param("mobile") String mobile, Pageable pageable);

    List<Customer> findAllById(Long id);

}
