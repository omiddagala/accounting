package ir.hitelecom.accounting.repositories.sales;

import ir.hitelecom.accounting.entities.sales.BankAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankAccountRepository extends PagingAndSortingRepository<BankAccount, Long> {
    @Query(value = "from BankAccount b where (:bank = null or b.bank like %:bank%) and (:account_number = null or b.accountNumber like %:account_number%)")
    List<BankAccount> search(@Param("bank") String bank, @Param("account_number") String accountNumber, Pageable pageable);

    List<BankAccount> findAllById(Long id);
}
