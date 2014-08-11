package com.smn.nlp.preprocess;

public class Sanitize {

	public static String removeNonPrintable(String paragraph) {
		return paragraph.replaceAll("\\p{C}", " ");
	}

}
