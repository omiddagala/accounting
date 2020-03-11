package ir.hitelecom.accounting.entities.stock;

import ir.hitelecom.accounting.entities.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "timeline")
public class Timeline {

    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    @ManyToOne
    private ProductSize productSize;
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
