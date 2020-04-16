package ir.hitelecom.accounting.controllers.sales;

import ir.hitelecom.accounting.dto.sales.BankAccountListDTO;
import ir.hitelecom.accounting.entities.sales.BankAccount;
import ir.hitelecom.accounting.services.sales.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shop/bank")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/list")
    @ResponseBody
    public List<BankAccount> fetchAll(@RequestBody BankAccountListDTO dto) {
        return bankAccountService.fetchAll(dto);
    }

    @PostMapping("/save")
    @ResponseBody
    public BankAccount saveOrUpdate(@RequestBody BankAccount bankAccount) {
        return bankAccountService.saveOrUpdate(bankAccount);
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody BankAccount bankAccount) {
        bankAccountService.delete(bankAccount);
    }
}
