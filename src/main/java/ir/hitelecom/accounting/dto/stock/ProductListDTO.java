package ir.hitelecom.accounting.dto.stock;

import ir.hitelecom.accounting.dto.PageableDTO;

public class ProductListDTO {

    private Long id;
    private String name;
    private String type;
    private boolean order;
    private PageableDTO pageableDTO;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PageableDTO getPageableDTO() {
        return pageableDTO;
    }

    public void setPageableDTO(PageableDTO pageableDTO) {
        this.pageableDTO = pageableDTO;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }
}
