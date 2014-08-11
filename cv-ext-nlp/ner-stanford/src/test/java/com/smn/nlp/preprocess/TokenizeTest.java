package com.smn.nlp.preprocess;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.smn.nlp.ner.DocExtractor;

public class TokenizeTest {

	@Test
	public void test() throws IOException {
		
		InputStream is = Test.class.getResourceAsStream("/SumeetNikam.doc");
		DocExtractor ext = new DocExtractor();
		String text = ext.readDocument(is);
		String santitizedText = Sanitize.removeNonPrintable(text);
		Tokenize tokenizer =new Tokenize();
		tokenizer.tokenize(santitizedText);
		
		assertTrue(true);
	}
	
	@Test
	public void testSaveTokens() throws IOException{
		String directory ="/home/synerzip/work/project/rezoomex/data/profiles2";
		File folder = new File(directory);
		
		String[] files=folder.list();
		
		for(String file : files){
			try {
				String absPath = directory.concat("/").concat(file);
				
				//InputStream is = Test.class.getResourceAsStream(absPath);
				InputStream is = new FileInputStream(absPath);
				DocExtractor ext = new DocExtractor();
				String text = ext.readDocument(is);
				String santitizedText = Sanitize.removeNonPrintable(text);
				Tokenize tokenizer =new Tokenize();
				tokenizer.tokenize(santitizedText);
				String newName = absPath.concat(".tsv");
				
				tokenizer.save(newName);
				is.close();
			} catch (Exception e) {
				
			}
		}
		assertTrue(true);
	}

}
