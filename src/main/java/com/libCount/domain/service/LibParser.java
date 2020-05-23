package com.libCount.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.libCount.domain.model.Library;

public class LibParser {
	
	public List<Library> parseHtml(String html) {
		Document webPage = this.toDocument(html);
		return this.getLibraries(webPage);
	}
	
	private Document toDocument(String html) {
		return Jsoup.parse(html);
	}
	
	private List<Library> getLibraries(Document webPage) {
		Elements elements = webPage.select("script");
		return elements.stream()
				.map(element -> element.attr("src"))
				.filter(src -> this.isExternalScript(src))
				.map(src -> new Library(src))
				.collect(Collectors.toList());
	}
	
	private boolean isExternalScript(String src) {
		return !StringUtil.isBlank(src);
	}

}
