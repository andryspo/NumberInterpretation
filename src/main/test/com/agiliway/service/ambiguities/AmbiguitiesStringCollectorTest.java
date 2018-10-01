package com.agiliway.service.ambiguities;

import com.agiliway.domain.CombinationType;
import com.agiliway.service.impl.ambiguities.AmbiguitiesStringCollector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AmbiguitiesStringCollectorTest {

    private AmbiguitiesStringCollector stringCollector;
    private List<String> numbers;
    private int combinationIndex;

    @Before
    public void setUp() throws Exception {
        stringCollector = new AmbiguitiesStringCollector();
        numbers = new ArrayList<>();

        numbers.add("2");
        numbers.add("23");
        numbers.add("3");
        numbers.add("20");
        numbers.add("1");
        numbers.add("100");

        combinationIndex = 1;
    }

    @Test
    public void testNotDividedByTen() {
        String actual = stringCollector.collectString(numbers, combinationIndex, CombinationType.NOT_DIVIDED_BY_TEN);
        String expected = "22033201100";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDividedByTen() {
        combinationIndex = 3;
        String actual = stringCollector.collectString(numbers, combinationIndex, CombinationType.DIVIDED_BY_TEN);
        String expected = "223321100";
        Assert.assertEquals(expected, actual);
    }
}
