package com.agiliway.domain;

/**
 * class contains information about ambiguity
 * value - ambiguity string for example (20 or 25)
 * combinationType - type of ambiguity
 */
public class Combination {

    private String value;

    private Integer index;

    private CombinationType combinationType;

    public Combination(String value, CombinationType combinationType, int index) {
        this.value = value;
        this.combinationType = combinationType;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CombinationType getCombinationType() {
        return combinationType;
    }

    public void setCombinationType(CombinationType combinationType) {
        this.combinationType = combinationType;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
