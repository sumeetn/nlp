package com.smn.nlp.ner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.smn.nlp.ner.Entity.SearchedEntity;

public class RegEx {
	
	public static List<SearchedEntity> getContent(String text,String pattern) {

		List<SearchedEntity> contentList = new ArrayList<SearchedEntity>();
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text);
		while (m.find()) {
			SearchedEntity se = new SearchedEntity(m.group(1), m.start(1), m.end(1));
			contentList.add(se);
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
