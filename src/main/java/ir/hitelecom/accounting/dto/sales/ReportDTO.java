package ir.hitelecom.accounting.dto.sales;

import ir.hitelecom.accounting.dto.PageableDTO;
import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.sales.BankAccount;
import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.entities.stock.ProductSize;

import java.time.LocalDate;

public class ReportDTO {
    private User user;
    private Customer customer;
    private ProductSize productSize;
    private LocalDate from;
    private LocalDate to;
    private BankAccount bankAccount;
    private Long factorNumber;
    private PageableDTO pageableDTO;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public PageableDTO getPageableDTO() {
        return pageableDTO;
    }

    public void setPageableDTO(PageableDTO pageableDTO) {
        this.pageableDTO = pageableDTO;
    }

    public Long getFactorNumber() {
        return factorNumber;
    }

    public void setFactorNumber(Long factorNumber) {
        this.factorNumber = factorNumber;
    }
}
