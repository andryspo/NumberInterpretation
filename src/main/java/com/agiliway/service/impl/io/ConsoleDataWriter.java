package com.agiliway.service.impl.io;

import com.agiliway.domain.ValidationResult;
import com.agiliway.service.DataWriter;

public class ConsoleDataWriter implements DataWriter {

    @Override
    public void print(String str) {
        System.out.println(str);
    }

    @Override
    public void print(ValidationResult result) {
        System.out.println(result.getPhone() + " " + result.getMessage());
    }
}
