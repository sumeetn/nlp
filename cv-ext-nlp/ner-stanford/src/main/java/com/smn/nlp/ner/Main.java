package com.smn.nlp.ner;

import java.util.Set;

public class Main {
	public static void main(String[] args) throws Exception {
		/** This is the document that you want to read using Java. **/
		String fileName = "/home/synerzip/work/workspaces/luna/RzFastUpload/src/test/resources/Deepak Gupta.doc";

		DocExtractor ext = new DocExtractor();

		/** Method call to read the document (demonstrate some useage of POI) **/
		String text = ext.readDocument(fileName);

		NEExtractor ne = new NEExtractor(text);
		
		System.out.println(ne.getResult().toString());

	}

}
