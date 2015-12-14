package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.MessageDAO;
import com.cngc.pm.model.Message;
import com.cngc.pm.service.MessageService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDAO messageDAO;
	
	@Override
	@Transactional
	public void save(Message message){
		messageDAO.save(message);
	}
	
	@Override
	@Transactional
	public boolean delByIds(String ids)
	{
		String id[] = ids.split(",");
		int j = id.length;
		Long[] idss = new Long[j];
		for(int i=0; i<id.length; i++) {
			String str = id[i];
			 if (!isNumeric(str)) {
				 return false;
			 }
			 idss[i] = Long.valueOf(str);
		}
		try {
			for(Long k : idss) {
				messageDAO.removeByIds(k);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional
	public boolean delById(Long id)
	{
		messageDAO.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public Message getById(Long id){
		return messageDAO.find(id);
	}
	
	@Override
	@Transactional
	public List<Message> getAll()
	{
		return messageDAO.findAll();
	}
	@Override
	@Transactional
	public boolean readMessage(Long id)
	{
		return messageDAO.readMessage(id);
	}
	@Override
	@Transactional
	public SearchResult<Message> getByUserId(String userId){
		return messageDAO.getByUserId(userId);
	}
	@Override
	@Transactional
	public SearchResult<Message> getNotReadMessageByUserId(String userId){
		return messageDAO.getNotReadMessageByUserId(userId);
	}
	@Override
	@Transactional
	public int getNotReadMessageCountByUserId(String userId){
		return messageDAO.getNotReadMessageCountByUserId(userId);
	}
	@Override
	@Transactional
	public boolean sendMessage(String fromUser, String toUser, String content, String href)
	{
		return messageDAO.sendMessage(fromUser, toUser, content, href);
	}
}
