package com.agiliway.service.impl.ambiguities;


import com.agiliway.domain.CombinationType;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * class that is responsible for collecting string and forming ambiguities number
 */
public class AmbiguitiesStringCollector {

    private static final Logger LOGGER = Logger.getLogger(AmbiguitiesStringCollector.class);

    public String collectString(List<String> numberParts, int combinationIndex, CombinationType combinationType) {
        LOGGER.info("start string collecting");

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numberParts.size(); i++) {
            //find index of combination
            if (i != combinationIndex) {
                stringBuilder.append(numberParts.get(i));
            } else {
                switch (combinationType) {
                    case DIVIDED_BY_TEN:
                        collectIfDividedByTen(numberParts, stringBuilder, i);
                        i++;
                        break;
                    case NOT_DIVIDED_BY_TEN:
                        collectIfNotDividedByTen(numberParts, stringBuilder, i);
                        break;
                }
            }
        }
        LOGGER.info("finish collecting");

        return stringBuilder.toString();
    }

    private void collectIfNotDividedByTen(List<String> symbols, StringBuilder stringBuilder, int i) {
        stringBuilder
                .append(symbols.get(i).charAt(0))
                .append("0")
                .append(symbols.get(i).charAt(1));
    }

    private void collectIfDividedByTen(List<String> symbols, StringBuilder stringBuilder, int i) {
        stringBuilder
                .append(symbols.get(i).charAt(0))
                .append(symbols.get(i + 1));
    }

}
