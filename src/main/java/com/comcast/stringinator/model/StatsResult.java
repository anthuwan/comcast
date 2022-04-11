package com.comcast.stringinator.model;

import java.util.Map;

public class StatsResult {
    private final Map<String, Integer> inputs;
    private final String mostPopular;
    private final String longestInput;
    public String getMostPopular() {
        return mostPopular;
    }

    public String getLongestInput() {
        return longestInput;
    }

    public StatsResult(Map<String, Integer> inputs,String mostPopular,String  longestInput) {
        this.inputs = inputs;
        this.mostPopular=mostPopular;
        this.longestInput=longestInput;
    }

    public Map<String, Integer> getInputs() {
        return inputs;
    }
}
