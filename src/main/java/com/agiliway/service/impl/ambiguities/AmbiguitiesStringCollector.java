package com.agiliway.service.impl.ambiguities;


import java.util.List;

/**
 * class that is responsible for collecting string and forming ambiguities number
 */
public class AmbiguitiesStringCollector {

    public String collectString(List<String> symbols, int index, boolean isDividedByTen) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int j = 0; j < symbols.size(); j++) {
            if (j != index) {
                stringBuilder.append(symbols.get(j));
            } else {
                if (isDividedByTen) {
                    stringBuilder
                            .append(symbols.get(j).charAt(0))
                            .append(symbols.get(j + 1));
                    j++;
                } else {
                    stringBuilder
                            .append(symbols.get(j).charAt(0))
                            .append("0")
                            .append(symbols.get(j).charAt(1));
                }

            }
        }

        return stringBuilder.toString();
    }

}
