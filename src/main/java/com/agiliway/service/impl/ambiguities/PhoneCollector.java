package com.agiliway.service.impl.ambiguities;

//class responsible for collecting data writen by user to phone number
public class PhoneCollector {

    public static final String collectToPhone(String data) {
        return data.replaceAll("\\D+", "");
    }

}
