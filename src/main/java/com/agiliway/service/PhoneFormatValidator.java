package com.agiliway.service;

import com.agiliway.exception.WrongDataFormatException;

public interface PhoneFormatValidator {

    void validate(String phone) throws WrongDataFormatException;

}
