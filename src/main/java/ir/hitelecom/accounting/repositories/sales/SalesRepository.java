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

import java.time.LocalDate;
import java.util.List;

public interface SalesRepository extends PagingAndSortingRepository<Sales, Long> {
    @Query(value = "from Sales s where (:customer = null or s.customer = :customer) and (:user = null or s.user= :user)  and (:product_size = null or s.productSize =:product_size)and (:status = null or s.status =:status)and (:factor_number = null or s.factorNumber =:factor_number)and (CAST(:add_date AS date) = null or s.addDate =:add_date)and (CAST(:paid_date AS date) = null or s.paidDate =:paid_date)")
    List<Sales> search(@Param("customer") Customer customer, @Param("user") User user, @Param("product_size") ProductSize productSize, @Param("status") Status status, @Param("add_date") LocalDate addDate, @Param("paid_date") LocalDate paidDate, @Param("factor_number") Long factorNumber, Pageable pageable);

    List<Sales> findAllById(Long id);

    @Query("select distinct s.customer from Sales s where" +
            " (:name = null or s.customer.name like %:name%) " +
            "and (:family = null or s.customer.family like %:family%) " +
            "and (:status = null or s.status =:status) " +
            "and (:national_code = null or s.customer.nationalCode like %:national_code%)" +
            "and (:mobile = null or s.customer.mobile like %:mobile%)" +
            "and (CAST(:add_date AS date) = null or s.addDate =:add_date)" +
            "and (CAST(:paid_date AS date) = null or s.paidDate =:paid_date)")
    List<Customer> findCustomerId(@Param("name") String name, @Param("family") String family, @Param("national_code") String nationalCode, @Param("mobile") String mobile, @Param("status") Status status, @Param("add_date") LocalDate addDate, @Param("paid_date") LocalDate paidDate);

    @Query("select distinct s.factorNumber from Sales s where (:customer = null or s.customer =:customer)")
    List<Long> findCustomerFactorNumbers(@Param("customer")Customer customer);
}
