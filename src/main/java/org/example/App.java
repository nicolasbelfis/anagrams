package org.example;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

  public static boolean areAnagrams(String sentence1, String sentence2) {
    if (sentence1 == null || sentence2 == null) {
      return false;
    }
    if (sentence1.equals("") || sentence2.equals("")) {
      return false;
    }

    Stream<Character> sentence1Stream = sentence1.chars()
        .filter(Character::isAlphabetic)
        .map(Character::toLowerCase)
        .mapToObj(operand -> (char) operand);

    Stream<Character> sentence2Stream = sentence2.chars()
        .filter(Character::isAlphabetic)
        .map(Character::toLowerCase)
        .mapToObj(operand -> (char) operand);


    Map<Character, Long> sentence1CharCount =
        sentence1Stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    boolean charAllMatchedAtLeastOnce = sentence2Stream
        .peek(charSentence2 -> sentence1CharCount.computeIfPresent(charSentence2, (unused, aLong) -> --aLong))
        .allMatch(charSentence2 -> sentence1CharCount.get(charSentence2) != null);

    return sentence1CharCount.values().stream().allMatch(aLong -> aLong == 0 && charAllMatchedAtLeastOnce);
  }

}
