package com.libCount.domain.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.libCount.domain.model.Library;
import com.libCount.domain.model.WebPageReference;

public class LibParserWrapper {
	
	private HtmlReader reader;
	
	private LibParser parser;
	
	public LibParserWrapper(HtmlReader reader, LibParser parser) {
		this.reader = reader;
		this.parser = parser;
	}
	
	public Optional<List<Library>> parseWebPage(WebPageReference reference) {
		try {
			String html = this.reader.readHtml(reference);
			List<Library> libraries = this.parser.parseHtml(html);
			return Optional.of(libraries);
		} catch(IOException ex) {
			return Optional.empty();
		}
	}

}
