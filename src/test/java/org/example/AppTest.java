package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

  @Test
  public void empty_string_are_not_anagrams() {

    assertFalse(App.areAnagrams("", ""));
  }

  @Test
  public void null_are_not_anagrams() {

    assertFalse(App.areAnagrams(null, null));
  }

  @Test
  public void single_null_means_no_anagrams() {

    assertFalse(App.areAnagrams(null, "a"));
    assertFalse(App.areAnagrams("a", null));
  }

  @Test
  public void single_letter_identical_means_anagram() {

    assertTrue(App.areAnagrams("a", "a"));
  }

  @Test
  public void single_letter_identical_but_case_different_means_anagram() {

    assertTrue(App.areAnagrams("a", "A"));
  }


}
