package org.example;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

  public static boolean areAnagrams(String sentence1, String sentence2) {
    if (isNull(sentence1) || isNull(sentence2)) {
      return false;
    }
    if (sentence1.isEmpty() || sentence2.isEmpty()) {
      return false;
    }

    Stream<Character> sentence1Stream = getLettersLowerCaseStream(sentence1);

    Stream<Character> sentence2Stream = getLettersLowerCaseStream(sentence2);


    Map<Character, Long> sentence1CharCount =
        sentence1Stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    boolean charsAllMatchedAtLeastOnce = sentence2Stream
        .peek(charSentence2 -> decreaseCountOfCharInMap(sentence1CharCount, charSentence2))
        .allMatch(charSentence2 -> sentence1CharCount.get(charSentence2) != null);

    return sentence1CharCount.values().stream().allMatch(aLong -> aLong == 0 && charsAllMatchedAtLeastOnce);
  }

  private static Long decreaseCountOfCharInMap(Map<Character, Long> sentence1CharCount, Character charSentence2) {
    return sentence1CharCount.computeIfPresent(charSentence2, (unused, aLong) -> --aLong);
  }

  private static Stream<Character> getLettersLowerCaseStream(String sentence1) {
    return sentence1.chars()
        .filter(Character::isAlphabetic)
        .map(Character::toLowerCase)
        .mapToObj(operand -> (char) operand);
  }

  private static boolean isNull(String sentence1) {
    return sentence1 == null;
  }

}
