package com.agiliway.service.impl.validator;


import com.agiliway.constant.ValidationRules;
import com.agiliway.exception.WrongNumberException;
import com.agiliway.service.BaseLevelNumberValidator;

public class BaseLevelValidatorImpl implements BaseLevelNumberValidator {

    @Override
    public void validate(String phone) throws WrongNumberException {
        validateOnNull(phone);
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
                    throw new WrongNumberException("invalid phone number beginning: " + phone);
                }
                break;
            case ValidationRules.MAX_NUMBERS:
                if (!phone.startsWith(ValidationRules.FOURTEEN_DIGITS_FIRST_BEGINNING) &&
                        !phone.startsWith(ValidationRules.FOURTEEN_DIGITS_SECOND_BEGINNING)) {
                    throw new WrongNumberException("invalid phone number beginning: " + phone);
                }
                break;
            default:
                //so if length is not equal to 10 or 14 validation fail
                throw new WrongNumberException("invalid phone number length: " + phone.length());
        }
    }
}
