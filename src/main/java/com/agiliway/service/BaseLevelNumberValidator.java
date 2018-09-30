package com.agiliway.service;

import com.agiliway.exception.WrongNumberException;

public interface BaseLevelNumberValidator {

    void validate(String phone) throws WrongNumberException;

}
