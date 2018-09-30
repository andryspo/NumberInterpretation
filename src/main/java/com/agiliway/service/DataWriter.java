package com.agiliway.service;

import com.agiliway.domain.ValidationResult;

public interface DataWriter {

    void print(String str);

    void print(ValidationResult result);

}
