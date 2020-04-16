package ir.hitelecom.accounting.dto.sales;

import ir.hitelecom.accounting.dto.PageableDTO;
import ir.hitelecom.accounting.entities.sales.Customer;

public class CustomerDTO {
    private PageableDTO pageableDTO;
    private Customer customer;

    public PageableDTO getPageableDTO() {
        return pageableDTO;
    }

    public void setPageableDTO(PageableDTO pageableDTO) {
        this.pageableDTO = pageableDTO;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
