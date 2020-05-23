package com.libCount.domain.service;

import java.io.IOException;
import java.util.List;

import com.libCount.domain.model.WebPageReference;

public abstract class WebSearch {
	
	private HtmlReader reader;
	
	private SERPParser parser;
	
	public WebSearch(HtmlReader reader, SERPParser parser) {
		this.reader = reader;
		this.parser = parser;
	}
	
	public List<WebPageReference> search(String query, int numReferences) throws IOException {
		String searchURL = this.buildSearchURL(query, numReferences);
		String serp = this.reader.readHtml(new WebPageReference(searchURL));
		List<WebPageReference> searchResults = this.parser.parseSERP(serp);
		return searchResults;
	}
	
	protected abstract String buildSearchURL(String query, int numReferences);

}
