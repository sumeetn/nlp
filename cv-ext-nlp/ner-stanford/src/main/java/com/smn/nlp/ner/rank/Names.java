package com.smn.nlp.ner.rank;

import java.util.Collections;
import java.util.List;

import com.smn.nlp.ner.Entity.SearchedEntity;

public class Names {
	
	public String positionBased(List<SearchedEntity> names){
		
		for(SearchedEntity se:names){
			
			int freqCount = Collections.frequency(names, se);
			int size = se.getEntity().split(" ").length;
			int position = se.getStart();
			
			
			
			
		}
		return null;
		
	}

}
