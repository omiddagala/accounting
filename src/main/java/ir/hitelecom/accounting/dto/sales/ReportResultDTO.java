package ir.hitelecom.accounting.dto.sales;

import ir.hitelecom.accounting.entities.sales.Sales;

import java.util.List;

public class ReportResultDTO {
    private List<Sales> sales;
    private Long total;

    public List<Sales> getSales() {
        return sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
