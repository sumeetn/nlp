package com.smn.nlp.ner;

import java.util.Set;
import java.util.regex.Pattern;

public class MobileNumberExtractor {

	public Set<String> getMobileNumber(String text) {
		Pattern p = Pattern.compile("(\\+?(91)?( )?\\d+){10}");
		return RegEx.getContent(text, p);

	}

}
