package com.smn.nlp.ner;

import java.util.Set;

public class EmailExtractor {

	private static final String RE_MAIL = "([\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4})";

	public Set<String> getEmailIds(String text) {
		return RegEx.getContent(text, RE_MAIL);

	}

}
