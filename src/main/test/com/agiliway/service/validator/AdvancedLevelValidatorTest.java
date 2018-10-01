package com.agiliway.service.validator;

import com.agiliway.domain.ValidationResult;
import com.agiliway.exception.WrongNumberException;
import com.agiliway.service.AdvanceLevelNumberValidator;
import com.agiliway.service.BaseLevelNumberValidator;
import com.agiliway.service.impl.ambiguities.PhoneAmbiguitiesService;
import com.agiliway.service.impl.validator.AdvanceLevelValidatorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdvancedLevelValidatorTest {

    @Mock
    private BaseLevelNumberValidator baseLevelNumberValidator;

    @Mock
    private PhoneAmbiguitiesService phoneAmbiguitiesProcessor;

    @InjectMocks
    private AdvanceLevelNumberValidator advanceLevelNumberValidator = new AdvanceLevelValidatorImpl();

    private String phone;

    @Before
    public void setUp() throws Exception {
        phone =  "2 10 6 9 30 6 6 4";

        Set<String> phones = new LinkedHashSet<>();
        phones.add("2106930664");
        phones.add("210693664");

        doNothing().when(baseLevelNumberValidator).validate(anyString());
        when(phoneAmbiguitiesProcessor.processAmbiguities(any())).thenReturn(phones);
    }

    @Test
    public void validationTest() throws WrongNumberException {
        List<ValidationResult> validationResult = advanceLevelNumberValidator.validate(phone);

        Assert.assertNotNull(validationResult);
        Assert.assertEquals(validationResult.size(), 2);

        validationResult.forEach(e -> {Assert.assertTrue(e.getValid());});
        verify(baseLevelNumberValidator, times(2)).validate(anyString());
        verify(phoneAmbiguitiesProcessor, times(1)).processAmbiguities(anyString());
    }

    @Test
    public void baseValidationFail() throws WrongNumberException {
        doThrow(WrongNumberException.class).when(baseLevelNumberValidator).validate(anyString());

        List<ValidationResult> validationResult = advanceLevelNumberValidator.validate(phone);

        Assert.assertNotNull(validationResult);
        Assert.assertEquals(validationResult.size(), 2);

        validationResult.forEach(e -> {Assert.assertFalse(e.getValid());});
        verify(baseLevelNumberValidator, times(2)).validate(anyString());
        verify(phoneAmbiguitiesProcessor, times(1)).processAmbiguities(anyString());
    }
}
