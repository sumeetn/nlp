package com.smn.nlp.ner;

import java.util.Set;

import edu.stanford.nlp.ie.crf.CRFClassifier;

public class NEExtractor {

	private String data = null;

	public NEExtractor(String text) {
		data = text;

	}

	public Set<String> getNames() {
		String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz";

		CRFClassifier classifier = CRFClassifier
				.getClassifierNoExceptions(serializedClassifier);

		String result = classifier.classifyWithInlineXML(data);
		return RegEx.getContent(result, "<PERSON>(.*?)</PERSON>");

	}

	
	public NEResult getResult() {
		// System.out.println(text);
		Set<String> emails = new EmailExtractor().getEmailIds(data);
		System.out.println(emails);
		Set<String> names = getNames();
		System.out.println(names);
		Set<String> mobileNo = new MobileNumberExtractor()
				.getMobileNumber(data);
		System.out.println(mobileNo);
		return new NEResult((String) emails.toArray()[0],
				(String) mobileNo.toArray()[0], (String) names.toArray()[0]);
	}

}
