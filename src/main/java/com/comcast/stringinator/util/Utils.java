package com.comcast.stringinator.util;

import com.comcast.stringinator.model.StringinatorInput;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
  private Utils() {}
  public static final String ALPHANUMERIC_REGEX_PATTERN = "[^a-zA-Z]+";
  public static final String INPUT = "anthuwan";

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

  public static String saveFile(Map<String, Integer> bytes, String filename)
      throws IOException {

    // writing new file

    String str = new Gson().toJson(bytes);
    File file = new File(filename);
    try (FileOutputStream out = new FileOutputStream(file)) {
      boolean created = file.createNewFile();
      log.info("created file {}", created);
      try {

        out.write(str.getBytes());

      } catch (Exception e) {
        e.printStackTrace();
      }
    } // FileNotFoundException
    return file.getName();
  }
}
