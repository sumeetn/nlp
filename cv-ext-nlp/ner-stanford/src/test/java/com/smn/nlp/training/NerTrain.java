package com.smn.nlp.training;

import java.io.IOException;
import java.util.Properties;

import edu.stanford.nlp.ie.crf.CRFClassifier;


public class NerTrain {
	
	
	public void train() throws IOException{
		
		Properties prop = new Properties();
		prop.load(NerTrain.class.getResourceAsStream("training/ner.properties"));
		CRFClassifier classfier = new CRFClassifier(prop);
		
	}

}
