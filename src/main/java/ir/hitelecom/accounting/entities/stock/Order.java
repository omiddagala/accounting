package ir.hitelecom.accounting.entities.stock;

import ir.hitelecom.accounting.entities.User;
import ir.hitelecom.accounting.entities.stock.Reservoir;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Reservoir source;
    @ManyToOne
    private Reservoir destination;
    @ManyToOne
    private User submitter;
    @ManyToOne
    private Product product;
    private Long count;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
