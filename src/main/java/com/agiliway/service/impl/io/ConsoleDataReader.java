package com.agiliway.service.impl.io;

import com.agiliway.service.DataReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleDataReader implements DataReader {

    private BufferedReader reader;

    public ConsoleDataReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }
}
