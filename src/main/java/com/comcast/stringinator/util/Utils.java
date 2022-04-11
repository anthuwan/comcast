package com.comcast.stringinator.util;

import com.comcast.stringinator.model.StringinatorInput;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static final String ALPHANUMERIC_REGEX_PATTERN = "[^a-zA-Z]+";
    public static final String INPUT="anthuwan";
    public static Map<Character, Integer> getCharacterIntegerMap(StringinatorInput input) {
        char[] ch = input.getInput().toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();

        for (char value : ch) {
            if (charMap.containsKey(value)) {
                charMap.put(value, charMap.get(value) + 1);
            } else {
                charMap.put(value, 1);
            }
        }
        return charMap;
    }
}
