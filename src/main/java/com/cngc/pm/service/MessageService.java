package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Message;
import com.googlecode.genericdao.search.SearchResult;

public interface MessageService {

	void save(Message message);
	boolean delByIds(String ids);
	boolean delById(Long id);
	Message getById(Long id);
	List<Message> getAll();
	boolean readMessage(Long id);
	SearchResult<Message> getByUserId(String userId);
	SearchResult<Message> getNotReadMessageByUserId(String userId);
	int getNotReadMessageCountByUserId(String userId);
	boolean sendMessage(String fromUser, String fromUsername, String toUser, String toUsername, String content, String href);
	
	SearchResult<Message> getListByUserIdAndPage(String userId, int pageStart, int pageLength);
	SearchResult<Message> getListByMineAndPage(String userId,int pageStart, int pageLength);
}
