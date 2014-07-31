package com.smn.nlp.ner;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
	
	public static Set<String> getContent(String text,String pattern) {

		Set<String> contentList = new HashSet<String>();
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text);
		while (m.find()) {
			contentList.add(m.group(1));
		}
		return contentList;

	}
	
	public static Set<String> getContent(String text,Pattern p) {

		Set<String> contentList = new HashSet<String>();
		Matcher m = p.matcher(text);
		while (m.find()) {
			contentList.add(m.group());
		}
		return contentList;

	}
	


}
