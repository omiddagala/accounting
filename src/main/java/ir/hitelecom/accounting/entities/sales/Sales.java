package ir.hitelecom.accounting.entities.sales;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.ProductSize;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private User user;
    @ManyToOne
    private ProductSize productSize;
    private int amount;
    @Enumerated
    private Status status;
    @Transient
    private Long productCode;
    private LocalDateTime addDateTime;
    private LocalDate addDate;
    private LocalDateTime paidDateTime;
    private LocalDate paidDate;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public LocalDateTime getAddDateTime() {
        return addDateTime;
    }

    public void setAddDateTime(LocalDateTime addDateTime) {
        this.addDateTime = addDateTime;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public LocalDateTime getPaidDateTime() {
        return paidDateTime;
    }

    public void setPaidDateTime(LocalDateTime paidDateTime) {
        this.paidDateTime = paidDateTime;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }
}
