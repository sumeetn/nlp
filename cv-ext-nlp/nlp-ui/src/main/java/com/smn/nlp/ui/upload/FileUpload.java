package com.smn.nlp.ui.upload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.smn.nlp.ner.DocExtractor;
import com.smn.nlp.ner.NEExtractor;
import com.smn.nlp.ner.NEResult;
import com.smn.nlp.preprocess.Sanitize;

public class FileUpload extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		try {
			StringBuilder sb = new StringBuilder("{\"result\": [");

			if (req.getHeader("Content-Type") != null
					&& req.getHeader("Content-Type").startsWith(
							"multipart/form-data")) {
				ServletFileUpload upload = new ServletFileUpload();

				FileItemIterator iterator = upload.getItemIterator(req);

				while (iterator.hasNext()) {

					FileItemStream item = iterator.next();

					if (item.getName() != null) {
						sb.append("{");
						NEResult nerResult = textAnalysis(item.openStream());

						sb.append("\"name\":\"").append(nerResult.getName())
								.append("\",");

						sb.append("\"email\":\"")
								.append(nerResult.getEmailId()).append("\",");

						sb.append("\"mobile\":\"")
								.append(nerResult.getMobileNo()).append("\"");

						sb.append("}");

					}
				}
			} else {
				sb.append("{\"error\":\"Check header Content-Type, should start with multipart/form-data\"}");
			}

			sb.append("]");
			sb.append("}");
			res.getWriter().write(sb.toString());
			System.out.println("response " + sb.toString());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	protected int size(InputStream stream) {
		int length = 0;
		try {
			byte[] buffer = new byte[2048];
			int size;
			while ((size = stream.read(buffer)) != -1) {
				length += size;
				// for (int i = 0; i < size; i++) {
				// System.out.print((char) buffer[i]);
				// }
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return length;

	}

	public NEResult textAnalysis(InputStream stream) {

		/*
		 * try { BufferedReader rd = new BufferedReader( new
		 * InputStreamReader(stream)); String line = null; while ((line =
		 * rd.readLine()) != null) { System.out.println("->" + line); }
		 * System.out.println(" ** read complete **"); } catch (Exception ex) {
		 * ex.printStackTrace(); }
		 */

		DocExtractor ext = new DocExtractor();

		/** Method call to read the document (demonstrate some useage of POI) **/
		String text = ext.readDocument(stream);
		text = text.replaceAll("\\p{C}", "?");
		NEExtractor ne = new NEExtractor(text);
		return ne.getResult();

	}

	protected String read(InputStream stream) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}
		return sb.toString();

	}
}