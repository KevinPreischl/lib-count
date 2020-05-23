package com.libCount.domain.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleWebSearch extends WebSearch {
	
	private static final String BASE_SEARCH_URL = "https://www.google.com/search?q=";
	
	public GoogleWebSearch(HtmlReader reader, SERPParser parser) {
		super(reader, parser);
	}
	
	@Override
	protected String buildSearchURL(String query, int numReferences) {
		return BASE_SEARCH_URL + this.encode(query) + "&num=" + numReferences;
	}
	
	private String encode(String query) {
		try {
			return URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
		} catch(UnsupportedEncodingException ex) {
			return query;
		}
	}
	
}
