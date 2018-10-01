package com.agiliway.service.impl.validator;


import com.agiliway.constant.ValidationRules;
import com.agiliway.exception.WrongNumberException;
import com.agiliway.service.BaseLevelNumberValidator;
import org.apache.log4j.Logger;

public class BaseLevelValidatorImpl implements BaseLevelNumberValidator {

    private static final Logger LOGGER = Logger.getLogger(BaseLevelNumberValidator.class);

    @Override
    public void validate(String phone) throws WrongNumberException {
        LOGGER.info("validating on null");
        validateOnNull(phone);
        LOGGER.info("validate phone beginning");
        validatePhoneNumberBeginning(phone);
    }

    private void validateOnNull(String phone) throws WrongNumberException {
        if (phone == null || phone.isEmpty()) {
            throw new WrongNumberException("number is empty");
        }
    }

    private void validatePhoneNumberBeginning(String phone) throws WrongNumberException {
        switch (phone.length()) {
            case ValidationRules.MIN_NUMBERS:
                if (!phone.startsWith(ValidationRules.TEN_DIGITS_FIRST_BEGINNING) &&
                        !phone.startsWith(ValidationRules.TEN_DIGITS_SECOND_BEGINNING)) {
                    LOGGER.error("invalid beginning: " + phone);
                    throw new WrongNumberException("invalid phone number beginning: " + phone);
                }
                break;
            case ValidationRules.MAX_NUMBERS:
                if (!phone.startsWith(ValidationRules.FOURTEEN_DIGITS_FIRST_BEGINNING) &&
                        !phone.startsWith(ValidationRules.FOURTEEN_DIGITS_SECOND_BEGINNING)) {
                    LOGGER.error("invalid beginning: " + phone);
                    throw new WrongNumberException("invalid phone number beginning: " + phone);
                }
                break;
            default:
                //so if length is not equal to 10 or 14 validation fail
                LOGGER.error("invalid length: " + phone.length());
                throw new WrongNumberException("invalid phone number length: " + phone.length());
        }
    }
}
