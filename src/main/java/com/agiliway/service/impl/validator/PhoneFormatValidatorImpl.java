package com.agiliway.service.impl.validator;

import com.agiliway.exception.WrongDataFormatException;
import com.agiliway.service.PhoneFormatValidator;
import com.agiliway.service.impl.ambiguities.PhoneAmbiguitiesService;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * validate data if it is in a valid format
 * for phone if it contains not more then 3 digits separated by space symbol
 */
public class PhoneFormatValidatorImpl implements PhoneFormatValidator {

    private static final Logger LOGGER = Logger.getLogger(PhoneFormatValidator.class);

    @Override
    public void validate(String phone) throws WrongDataFormatException {
        //to avoid spaces in begin and end
        LOGGER.info("start validating phone format");

        phone = phone.trim();

        for (String e : phone.split(" ")) {
            if (!e.matches("[0-9]{1,3}")) {
                LOGGER.error("phone format is invalid: " + e);
                throw new WrongDataFormatException("wrong phone format: " + phone);
            }
        }

        LOGGER.info("finish validating phone format");
    }
}
