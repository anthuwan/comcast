package com.comcast.stringinator.service;

import com.comcast.stringinator.model.StatsResult;
import com.comcast.stringinator.model.StringinatorInput;
import com.comcast.stringinator.model.StringinatorResult;
import com.comcast.stringinator.util.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class StringinatorServiceImpl implements StringinatorService {

  private Map<String, Integer> seenStrings = new HashMap<>();
  String mostPopular = null;
  String longestInput = null;

  @Override
  public StringinatorResult stringinate(StringinatorInput input) {
    seenStrings.compute(input.getInput(), (k, v) -> (v == null) ? 1 : v + 1);

    Map<Character, Integer> charMap = Utils.getCharacterIntegerMap(input);

    return new StringinatorResult(input.getInput(), charMap, input.getInput().length());
  }

  @Override
  public StatsResult stats() {
    Optional<Entry<String, Integer>> optionalInput1 =
        seenStrings.entrySet().stream()
            .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1);
    Optional<Entry<String, Integer>> optionalInput2 =
        seenStrings.entrySet().stream()
            .max((entry1, entry2) -> entry1.getKey().length() > entry2.getKey().length() ? 1 : -1);
    optionalInput1.ifPresent(stringIntegerEntry -> mostPopular = stringIntegerEntry.getKey());
    optionalInput2.ifPresent(stringIntegerEntry -> longestInput = stringIntegerEntry.getKey());
    return new StatsResult(seenStrings, mostPopular, longestInput);
  }
}
