package ir.hitelecom.accounting.dto;

public class BankAccountListDTO {
    private Long id;
    private String Bank;
    private String accountNumber;
    private PageableDTO pageableDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public PageableDTO getPageableDTO() {
        return pageableDTO;
    }

    public void setPageableDTO(PageableDTO pageableDTO) {
        this.pageableDTO = pageableDTO;
    }
}
