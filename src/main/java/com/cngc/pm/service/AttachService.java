package com.cngc.pm.service;

import java.util.Set;

import com.cngc.pm.model.Attachment;

public interface AttachService {

	Attachment create(Attachment attach, String username);

	Attachment get(long id);

	/**
	 * 根据id列表获取附件集合
	 * @param attachIds id列表，格式"1,2,3,"
	 * @return
	 */
	Set<Attachment> getSetByIds(String attachIds);

	boolean delById(long attachid);

}
