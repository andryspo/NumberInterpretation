package com.agiliway.service.impl.validator;

import com.agiliway.constant.Messages;
import com.agiliway.domain.ValidationResult;
import com.agiliway.exception.WrongNumberException;
import com.agiliway.service.AdvanceLevelNumberValidator;
import com.agiliway.service.BaseLevelNumberValidator;
import com.agiliway.service.impl.ambiguities.PhoneAmbiguitiesService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * class responsible for validating phone numbers by base rules and rules of ambiguities
 */
public class AdvanceLevelValidatorImpl implements AdvanceLevelNumberValidator {

    private static final Logger LOGGER = LogManager.getLogger(AdvanceLevelValidatorImpl.class);

    private BaseLevelNumberValidator baseLevelNumberValidator;
    private PhoneAmbiguitiesService phoneAmbiguitiesProcessor;

    public AdvanceLevelValidatorImpl() {
        baseLevelNumberValidator = new BaseLevelValidatorImpl();
        phoneAmbiguitiesProcessor = new PhoneAmbiguitiesService();
    }

    @Override
    public List<ValidationResult> validate(String phoneNumber) {
        //get all possible phones
        LOGGER.info("start advance phone number validation");

        Set<String> phones = phoneAmbiguitiesProcessor.processAmbiguities(phoneNumber);
        List<ValidationResult> results = new ArrayList<>();
        final int[] interpretationCounter = {0};
        phones.forEach(number -> {
            ValidationResult validationResult;
            interpretationCounter[0]++;
            try {
                //validate each phone
                baseLevelNumberValidator.validate(number);
                validationResult = new ValidationResult(true, Messages.VALID_PHONE, number, interpretationCounter[0]);
                LOGGER.info("phone" + number + "is valid");
            } catch (WrongNumberException e) {
                validationResult = new ValidationResult(false, Messages.INVALID_PHONE, number, interpretationCounter[0]);
                LOGGER.error("phone" + number + "is invalidvalid");
                LOGGER.warn(e.getMessage());
            }
            results.add(validationResult);
        });

        LOGGER.info("finish advance phone number validation");

        return results;
    }
}
