package com.smn.nlp.ner;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.HeaderStories;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.smn.nlp.preprocess.Sanitize;

public class DocExtractor {

	public String readDocument(String fileName) {
		POIFSFileSystem fs = null;
		String extractedText = null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(fileName));
			HWPFDocument doc = new HWPFDocument(fs);

			/** Read the content **/
			extractedText = readParagraphs(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return extractedText;
	}

	public String readDocument(InputStream is) {
		POIFSFileSystem fs = null;
		String extractedText = null;
		try {
			fs = new POIFSFileSystem(is);
			HWPFDocument doc = new HWPFDocument(fs);

			/** Read the content **/
			extractedText = readParagraphs(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return extractedText;
	}

	public String readParagraphs(HWPFDocument doc) throws Exception {
		StringBuilder sb = new StringBuilder();
		WordExtractor we = new WordExtractor(doc);
		/** Get the total number of paragraphs **/
		String[] paragraphs = we.getParagraphText();
		for (int i = 0; i < paragraphs.length; i++) {
			String paragraph = paragraphs[i].toString();
			
			//String santitizedText = Sanitize.removeNonPrintable(paragraph);
			sb.append(paragraph);
		}
		return sb.toString();
	}

	public String readHeader(HWPFDocument doc, int pageNumber) {

		HeaderStories headerStore = new HeaderStories(doc);
		String header = headerStore.getHeader(pageNumber);
		return header;

	}

	public String readFooter(HWPFDocument doc, int pageNumber) {
		HeaderStories headerStore = new HeaderStories(doc);
		String footer = headerStore.getFooter(pageNumber);
		return footer;

	}

	public static void readDocumentSummary(HWPFDocument doc) {
		DocumentSummaryInformation summaryInfo = doc
				.getDocumentSummaryInformation();
		String category = summaryInfo.getCategory();
		String company = summaryInfo.getCompany();
		int lineCount = summaryInfo.getLineCount();
		int sectionCount = summaryInfo.getSectionCount();
		int slideCount = summaryInfo.getSlideCount();

		System.out.println("---------------------------");
		System.out.println("Category: " + category);
		System.out.println("Company: " + company);
		System.out.println("Line Count: " + lineCount);
		System.out.println("Section Count: " + sectionCount);
		System.out.println("Slide Count: " + slideCount);

	}
}
