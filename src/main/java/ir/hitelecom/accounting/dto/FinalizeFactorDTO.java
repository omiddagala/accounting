package ir.hitelecom.accounting.dto;

import ir.hitelecom.accounting.entities.sales.BankAccount;

import java.util.List;

public class FinalizeFactorDTO {
    private List<Long> ids;
    private BankAccount bankAccount;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
