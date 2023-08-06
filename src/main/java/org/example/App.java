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
    if (sentence1.isEmpty() || sentence2.isEmpty()) {
      return false;
    }

    Stream<Character> sentence1Stream = getLettersLowerCaseStream(sentence1);

    Stream<Character> sentence2Stream = getLettersLowerCaseStream(sentence2);

    Map<Character, Long> sentence1CharCount =
        sentence1Stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    boolean charsAllMatchedAtLeastOnce = decreaseCharCount(sentence1CharCount, sentence2Stream)
        .allMatch(charSentence2 -> sentence1CharCount.get(charSentence2) != null);

    return charsAllMatchedAtLeastOnce && isCharCountZero(sentence1CharCount);
  }

  private static boolean isCharCountZero(Map<Character, Long> charCount) {
    return charCount.values().stream().allMatch(count -> count == 0);
  }

  private static Stream<Character> decreaseCharCount(
      Map<Character, Long> charCount,
      Stream<Character> OtherCharStream
  ) {
    return OtherCharStream
        .peek(character -> decreaseCharCountInMap(charCount, character));
  }

  private static void decreaseCharCountInMap(Map<Character, Long> charCount, Character character) {
    charCount.computeIfPresent(character, (unused, l) -> --l);
  }

  private static Stream<Character> getLettersLowerCaseStream(String sentence) {
    return sentence.chars()
        .filter(Character::isAlphabetic)
        .map(Character::toLowerCase)
        .mapToObj(i -> (char) i);
  }

}
