package com.cngc.pm.dao;

import java.util.Set;

import com.cngc.pm.model.Attachment;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface AttachmentDAO extends GenericDAO<Attachment, Long> {

	Attachment create(Attachment attach);

	/**
	 * 根据文件名获取附件列表
	 * @param filenames
	 * @return
	 */
	Set<Attachment> getSet(String... filenames);

	/**删除附件，包括文件
	 * @param attach
	 */
	void del(Attachment attach);
}
