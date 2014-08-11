package com.smn.nlp.ner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.smn.nlp.ner.Entity.SearchedEntity;

public class EmailExtractor {

	private static final String RE_MAIL = "([\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4})";

	public Set<String> getEmailIds(String text) {
		List<SearchedEntity> list = RegEx.getContent(text, RE_MAIL);
		Set<String> emails = new HashSet<String>();
		for (SearchedEntity se : list) {
			emails.add(se.getEntity());
		}
		return emails;

	}

}
