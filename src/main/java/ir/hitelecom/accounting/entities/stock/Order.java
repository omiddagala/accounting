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
    private Long count;
    private boolean seen;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
