package ir.hitelecom.accounting.dto.sales;

import ir.hitelecom.accounting.entities.sales.Status;

import java.time.LocalDate;

public class DailyCustomerListDTO extends CustomerListDTO {
    private Status status;
    private LocalDate addDate;
    private LocalDate paidDate;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }
}
