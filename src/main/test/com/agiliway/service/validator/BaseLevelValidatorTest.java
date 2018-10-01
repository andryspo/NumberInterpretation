package com.agiliway.service.validator;

import com.agiliway.exception.WrongNumberException;
import com.agiliway.service.BaseLevelNumberValidator;
import com.agiliway.service.impl.validator.BaseLevelValidatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BaseLevelValidatorTest {

    private BaseLevelNumberValidator baseLevelNumberValidator = new BaseLevelValidatorImpl();

    @Test
    public void testAllPossibleBeginning() throws WrongNumberException {
        baseLevelNumberValidator.validate("2106930664");
        baseLevelNumberValidator.validate("6906930664");
        baseLevelNumberValidator.validate("00306972413502");
        baseLevelNumberValidator.validate("00302172413502");
    }

    @Test(expected = WrongNumberException.class)
    public void invalidBeginning10() throws WrongNumberException{
        baseLevelNumberValidator.validate("1006930664");
    }

    @Test(expected = WrongNumberException.class)
    public void invalidBeginning14() throws WrongNumberException{
        baseLevelNumberValidator.validate("00312172413502");
    }

    @Test(expected = WrongNumberException.class)
    public void invalidLengthFor10Digits() throws WrongNumberException{
        baseLevelNumberValidator.validate("21069306641");
    }

    @Test(expected = WrongNumberException.class)
    public void invalidLengthFor14Digits() throws WrongNumberException {
        baseLevelNumberValidator.validate("00302172413");
    }

    @Test(expected = WrongNumberException.class)
    public void validateOnNull() throws WrongNumberException {
        baseLevelNumberValidator.validate(null);
    }


}
