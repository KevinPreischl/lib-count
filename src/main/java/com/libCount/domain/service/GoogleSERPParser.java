package com.libCount.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.libCount.domain.model.WebPageReference;

public class GoogleSERPParser implements SERPParser {

	@Override
	public List<WebPageReference> parseSERP(String html) {
		Document serp = this.toDocument(html);
		return this.getReferences(serp);
	}
	
	private Document toDocument(String html) {
		return Jsoup.parse(html);
	}
	
	private List<WebPageReference> getReferences(Document serp) {
		List<WebPageReference> references = new ArrayList<WebPageReference>();
		for(Element result : serp.select("#search .g .r > a")) {
			references.add(new WebPageReference(result.attr("href")));
		}
		return references;
	}

}
