package com.agiliway.service.impl.validator;

import com.agiliway.domain.Combination;
import com.agiliway.domain.CombinationType;

import java.util.*;

/**
 * class that process all possible ambiguities from phone
 */
public class PhoneAmbiguitiesProcessor {

    public Set<String> processAmbiguities(String phone) {
        HashSet<String> phones = new HashSet<>();
        List<String> symbols = pronouncedSymbols(phone);
        List<Combination> combinations = new ArrayList<>();
        phones.add(getAllDigits(phone));
        findAllAmbiguities(symbols, combinations);
        findAllCombinations(symbols, combinations, 0, phones);

        return phones;
    }

    private void findAllCombinations(List<String> symbols, List<Combination> combinations, int combinationIndex, HashSet<String> phones) {

        if (combinationIndex >= combinations.size()) {
            return;
        }
        Combination combination = combinations.get(combinationIndex);

        for (int i = 0; i < symbols.size(); i++) {
            String s = symbols.get(i);

            if (isTwoDigitAndDividedByTen(s) && i + 1 < symbols.size()) {
                if (isOneDigitNoneZero(symbols.get(i + 1))) {
                    phones.add(collectString(symbols, i, true));
                }
            }
            if (isTwoDigitAndNotDividedByTen(s)) {
                phones.add(collectString(symbols, i, false));
            }
        }

        switch (combination.getCombinationType()) {
            case DIVIDED_BY_TEN:
                symbols.set(combination.getIndex(),
                        symbols.get(combination.getIndex()).charAt(0) + symbols.get(combination.getIndex() + 1));
                symbols.remove(combination.getIndex() + 1);
                break;
            case NOT_DIVIDED_BY_TEN:
                symbols.set(combination.getIndex(),
                        symbols.get(combination.getIndex()).charAt(0) +"0"+ symbols.get(combination.getIndex()).charAt(1));
        }

        findAllCombinations(symbols, combinations, ++combinationIndex, phones);
    }

    private void findAllAmbiguities(List<String> symbols, List<Combination> combinations) {
        for (int i = 0; i < symbols.size(); i++) {
            String s = symbols.get(i);
            if (isTwoDigitAndDividedByTen(s) && i + 1 < symbols.size()) {
                if (isOneDigitNoneZero(symbols.get(i + 1))) {
                    combinations.add(new Combination(symbols.get(i), CombinationType.DIVIDED_BY_TEN, i));
                }
            }

            if (isTwoDigitAndNotDividedByTen(s)) {
                combinations.add(new Combination(symbols.get(i), CombinationType.NOT_DIVIDED_BY_TEN, i));
            }
        }
    }

    private String collectString(List<String> symbols, int index, boolean isDividedByTen) {
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

    private boolean isOneDigitNoneZero(String s) {
        if (s.length() != 1) {
            return false;
        }
        return Integer.valueOf(s) != 0;
    }

    private boolean isTwoDigitAndNotDividedByTen(String s) {
        if (isTwoDigitOrTen(s)) {
            return false;
        }

        return Integer.valueOf(s) % 10 != 0;
    }

    private boolean isTwoDigitAndDividedByTen(String s) {

        if (isTwoDigitOrTen(s)) {
            return false;
        }

        return Integer.valueOf(s) % 10 == 0;
    }

    private boolean isTwoDigitOrTen(String s) {
        if (s.length() != 2) {
            return true;
        }

        //10 cannot provide ambiguities
        return s.equals("10");
    }

    private List<String> pronouncedSymbols(String phone) {

        return new ArrayList<>(Arrays.asList(phone.split("\\s+")));
    }

    private String getAllDigits(String phone) {
        return phone.replaceAll("\\D+", "");
    }

}
