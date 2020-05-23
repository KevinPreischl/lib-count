package com.libCount.domain.service;

import java.io.IOException;

import org.jsoup.Jsoup;

import com.libCount.domain.model.WebPageReference;

public class HtmlReader {
	
	public String readHtml(WebPageReference reference) throws IOException {
		return Jsoup.connect(reference.getHyperLink()).get().html();
	}
	
}
