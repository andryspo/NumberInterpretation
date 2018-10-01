package com.agiliway.domain;

/**
 * class contains all information about validation result.
 */
public class ValidationResult {

    private Boolean isValid;

    private String message;

    private String phone;

    private int interpretationNumber;

    public ValidationResult(Boolean isValid, String message, String phone, int interpretationNumber) {
        this.isValid = isValid;
        this.message = message;
        this.phone = phone;
        this.interpretationNumber = interpretationNumber;
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

    public int getInterpretationNumber() {
        return interpretationNumber;
    }

    public void setInterpretationNumber(int interpretationNumber) {
        this.interpretationNumber = interpretationNumber;
    }
}
