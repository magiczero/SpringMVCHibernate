package com.cngc.pm.dao;

import com.cngc.pm.model.Message;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface MessageDAO extends GenericDAO<Message, Long> {

	/**
	 * 阅读消息
	 * 
	 * @param id
	 * @return
	 */
	boolean readMessage(Long id);

	/**
	 * 根据用户ID获取消息
	 * 
	 * @param userId
	 * @return
	 */
	SearchResult<Message> getByUserId(String userId);

	/**
	 * 根据用户ID获取没有阅读的消息
	 * 
	 * @param userId
	 * @return
	 */
	SearchResult<Message> getNotReadMessageByUserId(String userId);

	/**
	 * 根据用户ID获取没有阅读的消息总数
	 * 
	 * @param userId
	 * @return
	 */
	int getNotReadMessageCountByUserId(String userId);

	/**
	 * 发送消息
	 * 
	 * @param fromUser
	 * @param toUser
	 * @param content
	 * @param href
	 * @return
	 */
	boolean sendMessage(String fromUser, String toUser, String content, String href);
}
