package ir.hitelecom.accounting.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RecoveryResponseDTO {
    private String username;
    private String token;
    @JsonIgnore
    private Exception exception;
    public RecoveryResponseDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
