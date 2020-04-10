package ir.hitelecom.accounting.dto;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.sales.Customer;
import ir.hitelecom.accounting.entities.sales.Status;
import ir.hitelecom.accounting.entities.stock.ProductSize;

import java.time.LocalDate;

public class SalesListDTO {
    private Long id;
    private Customer customer;
    private User user;
    private ProductSize productSize;
    private Status status;
    private LocalDate addDate;
    private LocalDate paidDate;
    private Long factorNumber;
    private PageableDTO pageableDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PageableDTO getPageableDTO() {
        return pageableDTO;
    }

    public void setPageableDTO(PageableDTO pageableDTO) {
        this.pageableDTO = pageableDTO;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public Long getFactorNumber() {
        return factorNumber;
    }

    public void setFactorNumber(Long factorNumber) {
        this.factorNumber = factorNumber;
    }
}
