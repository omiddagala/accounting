package ir.hitelecom.accounting.dto;

import ir.hitelecom.accounting.entities.sales.Status;

import java.time.LocalDate;

public class DateStatusDTO {
    private LocalDate date;
    private Status status;
    private PageableDTO pageableDTO;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PageableDTO getPageableDTO() {
        return pageableDTO;
    }

    public void setPageableDTO(PageableDTO pageableDTO) {
        this.pageableDTO = pageableDTO;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
