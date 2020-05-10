package ir.hitelecom.accounting.services.sales;

import ir.hitelecom.accounting.dto.sales.BankAccountListDTO;
import ir.hitelecom.accounting.entities.sales.BankAccount;
import ir.hitelecom.accounting.repositories.sales.BankAccountRepository;
import ir.hitelecom.accounting.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankAccountService extends BaseService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> fetchAll(BankAccountListDTO dto){
        if (dto.getId() == null)
            return bankAccountRepository.search(dto.getBank(), dto.getAccountNumber());
        else {
            List<BankAccount> byId = bankAccountRepository.findAllById(dto.getId());
            if(byId.isEmpty())
                throw new NullPointerException("bankAccountNotFound");
            return byId;
        }
    }

    public BankAccount saveOrUpdate(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public void delete(BankAccount bankAccount) {
        bankAccountRepository.delete(bankAccount);
    }
}
