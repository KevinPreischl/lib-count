package com.libCount.main;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.libCount.domain.model.Library;
import com.libCount.domain.model.WebPageReference;
import com.libCount.domain.service.GoogleSERPParser;
import com.libCount.domain.service.GoogleWebSearch;
import com.libCount.domain.service.HtmlReader;
import com.libCount.domain.service.LibCounter;
import com.libCount.domain.service.LibParser;
import com.libCount.domain.service.LibParserWrapper;
import com.libCount.domain.service.LibRank;
import com.libCount.domain.service.WebSearch;

public class Main {
	
	public static void main(String[] args) {
		try {
			String query = args[0];
			List<WebPageReference> references = searchWeb(query);
			List<Library> topLibs = determineTopNLibs(references, 5);
			printLibs(topLibs);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static List<WebPageReference> searchWeb(String query) throws IOException {
		WebSearch search = new GoogleWebSearch(new HtmlReader(), new GoogleSERPParser());
		return search.search(query, 10);
	}
	
	private static List<Library> determineTopNLibs(List<WebPageReference> references, int n) {
		LibParserWrapper parser = new LibParserWrapper(new HtmlReader(), new LibParser());
		LibCounter counter = new LibCounter(parser);
		Map<Library, Long> libFrequencyMap = counter.countLibs(references);
		List<Library> topLibs = new LibRank().rank(libFrequencyMap, n);
		return topLibs;
	}
	
	private static void printLibs(List<Library> libs) {
		libs.forEach(lib -> System.out.println(lib.getName()));
	}

}
