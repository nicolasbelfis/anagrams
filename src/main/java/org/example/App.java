package org.example;

public class App {


	public static boolean areAnagrams(String sentence1, String sentence2) {
		if(sentence1 == null || sentence2 == null) return false;
		if(sentence1.equals("") || sentence2.equals("")) return false;
		return removePunctuation(sentence1)
				.equalsIgnoreCase(removePunctuation(sentence2));
	}

	private static String removePunctuation(String sentence1) {
		return sentence1.replaceAll("[^a-zA-Z]", "");
	}
}
