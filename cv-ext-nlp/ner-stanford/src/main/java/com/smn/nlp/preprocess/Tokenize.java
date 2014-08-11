package com.smn.nlp.preprocess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.process.PTBTokenizer;

public class Tokenize {

	private List<String> wordList = null;
	private String tokenList = null;

	public Tokenize() {
		wordList = new ArrayList<String>();

	}

	public void tokenize(String text) {
		PTBTokenizer<Word> tokenized = PTBTokenizer
				.newPTBTokenizer(new StringReader(text));

		StringBuilder sb = new StringBuilder();
		List<String> rawWords = new ArrayList<String>();
		while (tokenized.hasNext()) {
			String word = tokenized.next().toString();
			// word = word.matches("^.*[^a-zA-Z0-9].*$") ? null : word;
			// if (word != null)
			rawWords.add(word);
			sb.append(word);
			sb.append("\tO\n");
		}
		tokenList = sb.toString();
	}

	public void save(String path) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				new File(path)));
		writer.write(tokenList);
		System.out.println(" saved to "+path);
		writer.flush();
		writer.close();
	}

	public List<String> getWordList() {
		return wordList;
	}

	public String getTokenList() {
		return tokenList;
	}

}
