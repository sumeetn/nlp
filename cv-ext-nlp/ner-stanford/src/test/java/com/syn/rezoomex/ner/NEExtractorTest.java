package com.syn.rezoomex.ner;

import java.io.InputStream;

import junit.framework.TestCase;

import org.junit.Test;

import com.smn.nlp.ner.DocExtractor;
import com.smn.nlp.ner.NEExtractor;
import com.smn.nlp.ner.NEResult;

public class NEExtractorTest extends TestCase {

	@Test
	public void testNER() {
		InputStream is = Test.class.getResourceAsStream("/SumeetNikam.doc");
		DocExtractor ext = new DocExtractor();
		/** Method call to read the document (demonstrate some useage of POI) **/
		String text = ext.readDocument(is);
		NEExtractor ne = new NEExtractor(text);

		NEResult result = new NEResult("sumeet.nikam@yahoo.com",
				"+91 7276099903", "Sumeet Nikam");

		assertEquals(result, ne.getResult());
	}

}
