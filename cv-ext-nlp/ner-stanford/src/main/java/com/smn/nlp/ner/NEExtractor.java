package com.smn.nlp.ner;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.smn.nlp.ner.Entity.SearchedEntity;
import com.smn.nlp.ner.rank.Score;

import edu.stanford.nlp.ie.crf.CRFClassifier;

public class NEExtractor {

	private String data = null;
	static final Logger logger = Logger.getLogger(NEExtractor.class);

	public NEExtractor(String text) {
		data = text;
		BasicConfigurator.configure(); // basic log4j configuration
		Logger.getRootLogger().setLevel(Level.INFO);
	}

	public List<SearchedEntity> getNames() {
		String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz";
		// String serializedClassifier
		// ="/home/synerzip/work/nlp/rezoomex/ner-rz.ser.gz";

		logger.info("using " + serializedClassifier);
		CRFClassifier classifier = CRFClassifier
				.getClassifierNoExceptions(serializedClassifier);

		String result = classifier.classifyWithInlineXML(data);
		return RegEx.getContent(result, "<PERSON>(.*?)</PERSON>");

	}

	public NEResult getResult() {
		// System.out.println(text);
		Set<String> emails = new EmailExtractor().getEmailIds(data);
		logger.info(emails);

		List<SearchedEntity> names = getNames();

		calculateScore(names);

		Set<String> mobileNo = new MobileNumberExtractor()
				.getMobileNumber(data);
		logger.info(mobileNo);

		String email = emails.isEmpty() ? "not found" : (String) emails
				.toArray()[0];
		String mobile = mobileNo.isEmpty() ? "not found" : (String) mobileNo
				.toArray()[0];
		String name = names.isEmpty() ? "not found" : ((SearchedEntity) names
				.toArray()[0]).getEntity();
		names.clear();
		mobileNo.clear();
		emails.clear();
		return new NEResult(email, mobile, name);
	}

	public void calculateScore(List<SearchedEntity> entities) {

		for (SearchedEntity se : entities) {
			Score sc = new Score(Collections.frequency(entities, se),
					se.getStart(), se.getEntity().split(" ").length);
			se.setScore(sc.getScore());
		}
		Collections.sort(entities, new Comparator<SearchedEntity>() {

			public int compare(SearchedEntity o1, SearchedEntity o2) {
				return -1 * o1.getScore().compareTo(o2.getScore());
			}
		});
		logger.info(entities);
	}
}
