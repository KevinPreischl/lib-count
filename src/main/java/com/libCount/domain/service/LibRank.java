package com.libCount.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.libCount.domain.model.Library;

public class LibRank {
	
	public List<Library> rank(Map<Library, Long> libFrequencyMap, int limit) {
		List<Entry<Library, Long>> libFrequencyList = this.maptoList(libFrequencyMap);
		List<Entry<Library, Long>> sortedLibFrequencyList = this.sortDescByFrequency(libFrequencyList);
		return this.getMostFrequentLibs(sortedLibFrequencyList, limit);
	}
	
	private List<Entry<Library, Long>> maptoList(Map<Library, Long> libFrequencyMap) {
		return new ArrayList<Entry<Library, Long>>(libFrequencyMap.entrySet());
	}
	
	private List<Entry<Library, Long>> sortDescByFrequency(List<Entry<Library, Long>> libFrequencyList) {
		libFrequencyList.sort(Comparator.comparing(Entry::getValue));
		Collections.reverse(libFrequencyList);
		return libFrequencyList;
	}
	
	private List<Library> getMostFrequentLibs(List<Entry<Library, Long>> libFrequencyList, int limit) {
		return libFrequencyList.stream()
				.limit(limit)
				.map(entry -> entry.getKey())
				.collect(Collectors.toList());
	}

}
