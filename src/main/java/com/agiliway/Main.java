package com.agiliway;

import com.agiliway.exception.WrongDataFormatException;
import com.agiliway.service.AdvanceLevelNumberValidator;
import com.agiliway.service.impl.io.ConsoleDataReader;
import com.agiliway.service.impl.io.ConsoleDataWriter;
import com.agiliway.service.impl.validator.AdvanceLevelValidatorImpl;
import com.agiliway.service.impl.validator.PhoneFormatValidator;

import java.io.IOException;

public class Main {

    private static final String FINISH_FLAG = "end";

    public static void main(String[] args) throws IOException {
        ConsoleDataReader reader = new ConsoleDataReader();
        ConsoleDataWriter writer = new ConsoleDataWriter();
        AdvanceLevelNumberValidator advanceLevelNumberValidator = new AdvanceLevelValidatorImpl();
        PhoneFormatValidator formatValidator = new PhoneFormatValidator();
        String currentLine;

        writer.print("Enter phone number: \n");


        while (!(currentLine = reader.readLine()).equals(FINISH_FLAG)) {
            try {
                formatValidator.validate(currentLine);
                advanceLevelNumberValidator.validate(currentLine).forEach(writer::print);
            } catch (WrongDataFormatException e) {
                writer.print("bad phone format: " + currentLine);
            }
        }

    }

}
