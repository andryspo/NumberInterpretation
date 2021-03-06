package com.agiliway.service.validator;

import com.agiliway.exception.WrongDataFormatException;

import com.agiliway.service.PhoneFormatValidator;
import com.agiliway.service.impl.validator.PhoneFormatValidatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PhoneFormatValidatorImplTest {

    private PhoneFormatValidator phoneFormatValidator = new PhoneFormatValidatorImpl();

    @Test
    public void validateCorrectPhone() throws WrongDataFormatException {
        String phone = "2 10 69 30 6 60 4 ";
        phoneFormatValidator.validate(phone);
    }

    @Test(expected = WrongDataFormatException.class)
    public void validateBigDigitSequence() throws WrongDataFormatException {
        String phone = "2 10 69111111 30 6 60 4 ";
        phoneFormatValidator.validate(phone);
    }

    @Test(expected = WrongDataFormatException.class)
    public void validateForbiddenSmbols() throws WrongDataFormatException {
        String phone = "2 10 69 a30d 6 .60 4 ";
        phoneFormatValidator.validate(phone);
    }
}
