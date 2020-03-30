package ir.hitelecom.accounting.entities.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.hitelecom.accounting.entities.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String type;
    private String image;
    private Date expireDate;
    private Long price;
    private Long buyPrice;
    @ManyToOne
    @JsonIgnore
    private User owner;
    @ManyToOne
    @JsonIgnore
    private User user;
    @ManyToOne
    private Reservoir reservoir;
    @ManyToOne
    private Group group;
    @Transient
    private List<ProductSize> productSizes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Reservoir getReservoir() {
        return reservoir;
    }

    public void setReservoir(Reservoir reservoir) {
        this.reservoir = reservoir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<ProductSize> getProductSizes() {
        return productSizes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setProductSizes(List<ProductSize> productSizes) {
        this.productSizes = productSizes;
    }

    public Product clone() {
        Product product = new Product();
        product.setName(this.getName());
        product.setOwner(this.getOwner());
        product.setType(this.getType());
        product.setDescription(this.getDescription());
        product.setExpireDate(this.getExpireDate());
        product.setGroup(this.getGroup());
        product.setImage(this.image);
        product.setPrice(this.getPrice());
        product.setBuyPrice(this.getBuyPrice());
        product.setReservoir(this.getReservoir());
        return product;
    }
}
