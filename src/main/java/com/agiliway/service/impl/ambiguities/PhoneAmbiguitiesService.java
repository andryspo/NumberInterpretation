package com.agiliway.service.impl.ambiguities;

import com.agiliway.domain.Combination;
import com.agiliway.domain.CombinationType;

import java.util.*;

/**
 * class that process all possible ambiguities from phone
 */
public class PhoneAmbiguitiesService {

    private AmbiguitiesStringCollector stringCollector = new AmbiguitiesStringCollector();

    public Set<String> processAmbiguities(String phone) {

        //set of phones that will be validated
        Set<String> phones = new LinkedHashSet<>();
        //list of symbols inputed by user
        List<String> symbols = pronouncedSymbols(phone);
        //list of symbols that could be Ambiguities for example 25 or sequence 20 5
        List<Combination> combinations = new ArrayList<>();
        //add phone
        phones.add(getAllDigits(phone));
        //find all symbols that could be Ambiguities
        fillListWithAmbiguitiesSymbols(symbols, combinations);
        //find all phone number combinations
        findAllPossibleAmbiguitiesCombination(symbols, combinations, 0, phones);

        return phones;
    }

    //recursively find all possible ambiguities
    private void findAllPossibleAmbiguitiesCombination(List<String> numberParts, List<Combination> combinations, int combinationIndex, Set<String> phones) {

        if (combinationIndex >= combinations.size()) {
            return;
        }
        Combination combination = combinations.get(combinationIndex);
        findAmbiguitiesPhonesForSymbols(numberParts, phones);

        addNextCombinationToSymbols(numberParts, combination);

        //updateIndexes
        //fillListWithAmbiguitiesSymbols(numberParts, combinations);
        findAllPossibleAmbiguitiesCombination(numberParts, combinations, ++combinationIndex, phones);
    }

    private void addNextCombinationToSymbols(List<String> numberParts, Combination combination) {
        int index = numberParts.indexOf(combination.getValue());

        switch (combination.getCombinationType()) {
            case DIVIDED_BY_TEN:
                numberParts.set(index, numberParts.get(index).charAt(0) + numberParts.get(index + 1));
                numberParts.remove(index + 1);
                break;
            case NOT_DIVIDED_BY_TEN:
                numberParts.set(index, numberParts.get(index).charAt(0) + "0" + numberParts.get(index).charAt(1));
        }
    }

    private void findAmbiguitiesPhonesForSymbols(List<String> symbols, Set<String> phones) {
        for (int i = 0; i < symbols.size(); i++) {
            String s = symbols.get(i);

            if (isTwoDigitAndDividedByTen(s) && i + 1 < symbols.size()) {
                if (isOneDigitNoneZero(symbols.get(i + 1))) {
                    phones.add(stringCollector.collectString(symbols, i, CombinationType.DIVIDED_BY_TEN));
                }
            }
            if (isTwoDigitAndNotDividedByTen(s)) {
                phones.add(stringCollector.collectString(symbols, i, CombinationType.NOT_DIVIDED_BY_TEN));
            }
        }
    }

    private void fillListWithAmbiguitiesSymbols(List<String> symbols, List<Combination> combinations) {
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

    private boolean isOneDigitNoneZero(String s) {
        return s.length() == 1 && Integer.valueOf(s) != 0;
    }

    private boolean isTwoDigitAndNotDividedByTen(String s) {
        return isTwoDigitOrTen(s) && Integer.valueOf(s) % 10 != 0;

    }

    private boolean isTwoDigitAndDividedByTen(String s) {

        return isTwoDigitOrTen(s) && Integer.valueOf(s) % 10 == 0;

    }

    private boolean isTwoDigitOrTen(String s) {
        if (s.length() != 2) {
            return false;
        }

        //10 cannot provide ambiguities
        return !s.equals("10");
    }

    private List<String> pronouncedSymbols(String phone) {

        return new ArrayList<>(Arrays.asList(phone.split("\\s+")));
    }

    private String getAllDigits(String phone) {
        return phone.replaceAll("\\D+", "");
    }

}
