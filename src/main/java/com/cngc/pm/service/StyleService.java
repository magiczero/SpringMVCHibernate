package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Style;

public interface StyleService {

	/**
	 * 根据第一层级获取所有
	 * @return
	 */
	List<Style> getAllByLevelFir();
	
	void save(Style style);
}
