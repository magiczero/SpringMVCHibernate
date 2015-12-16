package com.cngc.pm.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.MessageDAO;
import com.cngc.pm.model.Message;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class MessageDAOImpl extends BaseDAOImpl<Message, Long> implements MessageDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.MessageDAO#readMessage(java.lang.Long)
	 */
	@Override
	public boolean readMessage(Long id) {
		Message message = this.find(id);
		message.setIsRead(true);
		message.setReadTime(new Date());
		this.save(message);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.MessageDAO#getByUserId(java.lang.String)
	 */
	public SearchResult<Message> getByUserId(String userId) {
		Search search = new Search();
		search.addFilterEqual("toUser", userId);

		return this.searchAndCount(search);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cngc.pm.dao.MessageDAO#getNotReadMessageByUserId(java.lang.String)
	 */
	public SearchResult<Message> getNotReadMessageByUserId(String userId) {
		Search search = new Search();
		search.addFilterEqual("toUser", userId);
		search.addFilterEqual("isRead", false);
		search.setMaxResults(5);
		search.addSort("createdTime", true);

		return this.searchAndCount(search);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cngc.pm.dao.MessageDAO#getNotReadMessageCountByUserId(java.lang.String
	 * )
	 */
	public int getNotReadMessageCountByUserId(String userId) {
		Search search = new Search();
		search.addFilterEqual("toUser", userId);
		search.addFilterEqual("isRead", false);

		return this.count(search);
	}
	/* (non-Javadoc)
	 * @see com.cngc.pm.dao.MessageDAO#sendMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean sendMessage(String fromUser, String toUser, String content, String href){
		Message message = new Message();
		message.setFromUser(fromUser);
		message.setToUser(toUser);
		message.setContent(content);
		message.setHref(href);
		message.setCreatedTime(new Date());
		message.setIsRead(false);
		
		this.save(message);
		
		return true;
	}
}
