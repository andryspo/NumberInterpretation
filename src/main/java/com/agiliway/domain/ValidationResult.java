package com.agiliway.domain;

public class ValidationResult {

    private Boolean isValid;

    private String message;

    private String phone;

    public ValidationResult(Boolean isValid, String message, String phone) {
        this.isValid = isValid;
        this.message = message;
        this.phone = phone;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
