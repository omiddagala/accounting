package ir.hitelecom.accounting.entities.stock;

import ir.hitelecom.accounting.entities.User;

import javax.persistence.*;

@Entity
@Table(name = "product_group")
public class Group {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private Long fromCode;
    private Long toCode;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFromCode() {
        return fromCode;
    }

    public void setFromCode(Long fromCode) {
        this.fromCode = fromCode;
    }

    public Long getToCode() {
        return toCode;
    }

    public void setToCode(Long toCode) {
        this.toCode = toCode;
    }
}
