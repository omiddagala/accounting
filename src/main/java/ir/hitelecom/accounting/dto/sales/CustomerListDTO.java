package ir.hitelecom.accounting.dto.sales;

import ir.hitelecom.accounting.dto.PageableDTO;

public class CustomerListDTO {
    private Long id;
    private String name;
    private String family;
    private String nationalCode;
    private String mobile;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public PageableDTO getPageableDTO() {
        return pageableDTO;
    }

    public void setPageableDTO(PageableDTO pageableDTO) {
        this.pageableDTO = pageableDTO;
    }
}
