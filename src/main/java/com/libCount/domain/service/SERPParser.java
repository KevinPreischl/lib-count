package com.libCount.domain.service;

import java.util.List;

import com.libCount.domain.model.WebPageReference;

public interface SERPParser {
	
	public List<WebPageReference> parseSERP(String html);

}
