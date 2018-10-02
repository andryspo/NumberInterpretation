package com.agiliway;

import com.agiliway.constant.Messages;
import com.agiliway.exception.WrongDataFormatException;
import com.agiliway.exception.WrongNumberException;
import com.agiliway.service.AdvanceLevelNumberValidator;
import com.agiliway.service.BaseLevelNumberValidator;
import com.agiliway.service.PhoneFormatValidator;
import com.agiliway.service.impl.ambiguities.PhoneCollector;
import com.agiliway.service.impl.io.ConsoleDataReader;
import com.agiliway.service.impl.io.ConsoleDataWriter;
import com.agiliway.service.impl.validator.AdvanceLevelValidatorImpl;
import com.agiliway.service.impl.validator.BaseLevelValidatorImpl;
import com.agiliway.service.impl.validator.PhoneFormatValidatorImpl;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Main {

    //print it to finish an application
    private static final String FINISH_FLAG = "end";

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting application: ");

        ConsoleDataReader reader = new ConsoleDataReader();
        ConsoleDataWriter writer = new ConsoleDataWriter();

        AdvanceLevelNumberValidator advanceLevelNumberValidator = new AdvanceLevelValidatorImpl();
        PhoneFormatValidator formatValidator = new PhoneFormatValidatorImpl();
        BaseLevelNumberValidator baseLevelNumberValidator = new BaseLevelValidatorImpl();

        String currentLine;
        writer.print("Enter phone number: \n");

        while (!(currentLine = reader.readLine()).equals(FINISH_FLAG)) {
            try {
                formatValidator.validate(currentLine);

                String phoneNumber = PhoneCollector.collectToPhone(currentLine);

                writer.print(phoneNumber);

                try {
                    baseLevelNumberValidator.validate(phoneNumber);
                    writer.print(phoneNumber + " " + Messages.VALID_PHONE);
                } catch (WrongNumberException e) {
                    writer.print(phoneNumber + " " + Messages.INVALID_PHONE + "\n");
                }

                advanceLevelNumberValidator.validate(currentLine).forEach(writer::print);
            } catch (WrongDataFormatException e) {
                writer.print("bad phone format: " + currentLine);
            }
            writer.print("\nEnter phone number: \n");
        }

        LOGGER.info("Finishing application!!!");

    }

}
