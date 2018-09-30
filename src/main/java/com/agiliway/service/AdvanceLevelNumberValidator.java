package com.agiliway.service;

import com.agiliway.domain.ValidationResult;

import java.util.List;

public interface AdvanceLevelNumberValidator {

    List<ValidationResult> validate(String phone);

}
