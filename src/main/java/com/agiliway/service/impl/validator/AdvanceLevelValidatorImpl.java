package com.agiliway.service.impl.validator;

import com.agiliway.constant.Messages;
import com.agiliway.domain.ValidationResult;
import com.agiliway.exception.WrongNumberException;
import com.agiliway.service.AdvanceLevelNumberValidator;
import com.agiliway.service.BaseLevelNumberValidator;
import com.agiliway.service.impl.ambiguities.PhoneAmbiguitiesProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdvanceLevelValidatorImpl implements AdvanceLevelNumberValidator {

    private BaseLevelNumberValidator baseLevelNumberValidator;
    private PhoneAmbiguitiesProcessor phoneAmbiguitiesProcessor;

    public AdvanceLevelValidatorImpl() {
        baseLevelNumberValidator = new BaseLevelValidatorImpl();
        phoneAmbiguitiesProcessor = new PhoneAmbiguitiesProcessor();
    }

    @Override
    public List<ValidationResult> validate(String phoneNumber) {
        //get all possible phones
        Set<String> phones = phoneAmbiguitiesProcessor.processAmbiguities(phoneNumber);
        List<ValidationResult> results = new ArrayList<>();

        phones.forEach(number -> {
            ValidationResult validationResult;
            try {
                baseLevelNumberValidator.validate(number);
                validationResult = new ValidationResult(true, Messages.VALID_PHONE, number);
            } catch (WrongNumberException e) {
                e.printStackTrace();
                validationResult = new ValidationResult(false, Messages.INVALID_PHONE, number);
            }
            results.add(validationResult);
        });

        return results;
    }
}
