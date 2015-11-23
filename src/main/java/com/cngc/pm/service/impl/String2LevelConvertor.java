package com.cngc.pm.service.impl;

import org.springframework.core.convert.converter.Converter;

import com.cngc.pm.model.SecretLevel;

public class String2LevelConvertor implements Converter<String, SecretLevel> {

	@Override
	public SecretLevel convert(String enumValueStr) {
		String value = enumValueStr.trim();
		if(value.isEmpty()) return null;
		
		return SecretLevel.get(value);
	}


}
