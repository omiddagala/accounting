package ir.hitelecom.accounting.entities.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Reservoir;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Date submitDate;
    @ManyToOne
    private Reservoir source;
    @ManyToOne
    private Reservoir destination;
    @ManyToOne
    private User submitter;
    @ManyToOne
    private Product product;
    private String orders;
    @Transient
    private List<Size> listOrders;
    private boolean closed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservoir getSource() {
        return source;
    }

    public void setSource(Reservoir source) {
        this.source = source;
    }

    public Reservoir getDestination() {
        return destination;
    }

    public void setDestination(Reservoir destination) {
        this.destination = destination;
    }

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public List<Size> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Size> listOrders) {
        this.listOrders = listOrders;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }
}
