package com.libCount.domain.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.libCount.domain.model.Library;
import com.libCount.domain.model.WebPageReference;

public class LibCounter {

	private LibParserWrapper parser;

	public LibCounter(LibParserWrapper parser) {
		this.parser = parser;
	}

	public Map<Library, Long> countLibs(List<WebPageReference> references) {
		List<Library> libs = this.parseWebPages(references);
		return this.countLibraries(libs);
	}

	private List<Library> parseWebPages(List<WebPageReference> references) {
		List<Library> libraries = new LinkedList<Library>();
		for(WebPageReference reference : references) {
			Optional<List<Library>> optionalLibraries = this.parser.parseWebPage(reference);
			if(optionalLibraries.isPresent()) {
				libraries.addAll(optionalLibraries.get());
			}
		}
		return libraries;
	}
	
	private Map<Library, Long> countLibraries(List<Library> libs) {
		return libs.stream()
	            .collect(Collectors.groupingBy(lib -> lib, Collectors.counting()));
	}

}
