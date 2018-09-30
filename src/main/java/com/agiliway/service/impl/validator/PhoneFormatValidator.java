package com.agiliway.service.impl.validator;

import com.agiliway.exception.WrongDataFormatException;

import java.util.Arrays;

/**
 * validate data if it is in a valid format
 * for phone if it contains not more then 3 digits separated by space symbol
 */
public class PhoneFormatValidator {

    public void validate(String phone) throws WrongDataFormatException {
        //to avoid spaces in begin and end
        phone = phone.trim();

        for (String e : phone.split(" ")) {
            if (!e.matches("[0-9]{1,3}")) {
                throw new WrongDataFormatException("wrong phone format: " + phone);
            }
        }
    }
}
