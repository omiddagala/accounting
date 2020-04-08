package ir.hitelecom.accounting.entities.sales;

import ir.hitelecom.accounting.entities.stock.ProductSize;
import ir.hitelecom.accounting.entities.stock.Reservoir;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Reservoir reservoir;
//    @ManyToOne
//    private ProductSize productSize;
    private int amount;

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

    public Reservoir getReservoir() {
        return reservoir;
    }

    public void setReservoir(Reservoir reservoir) {
        this.reservoir = reservoir;
    }

//    public ProductSize getProductSize() {
//        return productSize;
//    }
//
//    public void setProductSize(ProductSize productSize) {
//        this.productSize = productSize;
//    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
