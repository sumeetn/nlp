package com.smn.nlp.ner;

import com.smn.nlp.preprocess.Sanitize;

public class Main {
	public static void main(String[] args) throws Exception {
		/** This is the document that you want to read using Java. **/
		String fileName = "/home/synerzip/work/project/rezoomex/data/profiles2/Miheer Malhotra.doc";

		DocExtractor ext = new DocExtractor();

		/** Method call to read the document (demonstrate some useage of POI) **/
		String text = ext.readDocument(fileName);
		//System.out.println(text);
		//String santitizedText = Sanitize.removeNonPrintable(text);
		
		//System.out.println(santitizedText);
		text = text.replaceAll("\\p{C}", "?");
		
		
		NEExtractor ne = new NEExtractor(text);
		
		System.out.println(ne.getResult().toString());

	}

}
