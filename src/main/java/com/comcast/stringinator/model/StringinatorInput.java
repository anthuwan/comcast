package com.comcast.stringinator.model;

import java.util.Map;

public class StringinatorInput {
    private String input;
    private Map<Character, Integer> repeatedChar;

    public StringinatorInput() {
    }

    public Map<Character, Integer> getRepeatedChar() {
        return repeatedChar;
    }

    public void setRepeatedChar(Map<Character, Integer> repeatedChar) {
        this.repeatedChar = repeatedChar;
    }

    public StringinatorInput(String input) {
        this.input = input;
    }


    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
