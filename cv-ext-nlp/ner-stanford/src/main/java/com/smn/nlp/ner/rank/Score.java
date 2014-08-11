package com.smn.nlp.ner.rank;

public class Score {

	private int frequency = 0;
	private int startPosition = 100;
	private int wordCount = 0;

	public Score(int freq, int startPos, int wordCountInName) {
		frequency = freq;
		startPosition = startPos;
		wordCount = wordCountInName;

	}

	public float getScore() {

		double startWeight = (startPosition > 50) ? 0 : .9;

		double freqWt = (frequency == 2) ? 0.9 : .25;

		double wordWt = (wordCount > 1 && wordCount <= 3) ? 0.9 : 0.25;

		return (float) ((freqWt * wordWt) + startWeight);
	}

}
