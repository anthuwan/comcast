package com.comcast.stringinator.model;

import java.util.Map;

public class StringinatorResult {
  private final String input;
  private final Map<Character, Integer> repeatedChar;
  private final Integer length;

  public StringinatorResult(String input,Map<Character, Integer> repeatedChar, Integer length) {
    this.input = input;
    this.repeatedChar = repeatedChar;
    this.length = length;
  }

  public Integer getLength() {
    return length;
  }
  public Map<Character, Integer> getRepeatedChar() {
    return repeatedChar;
  }


  public String getInput() {
    return this.input;
  }
    }
