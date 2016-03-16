package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.GroupDAO;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO groupDao;

	@Override
	@Transactional(readOnly=true)
	public List<Group> getAll() {
		// TODO Auto-generated method stub
		return groupDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public String getAllWithJson() {
		// TODO Auto-generated method stub
		String jsonStr = "[";
		for(Group group : groupDao.getAllTopGroup()) {
			jsonStr += "{\"groupId\":\""+group.getId()+"\",\"groupName\":\""+group.getGroupName()+"\"";
			if(!group.getUsers().isEmpty()) {
				jsonStr += ",\"users\":[";
				for(SysUser user : group.getUsers()) {
					jsonStr += "{\"userId\":\""+user.getUsername()+"\",\"userName\":\""+user.getName()+"\",\"userTel\":\""+user.getDepName()+"\"},";
				}
				jsonStr = jsonStr.substring(0, jsonStr.length()-1);
				jsonStr += "]";
			}
			//jsonStr = jsonStr.substring(0, jsonStr.length()-1);
			if(!group.getChild().isEmpty()) {
				jsonStr += ",\"child\":[";
				for(Group child1 : group.getChild()) {
					jsonStr +="{\"groupId\":\""+child1.getId()+"\",\"groupName\":\""+child1.getGroupName()+"\"";
					if(!child1.getUsers().isEmpty()) {
						jsonStr += ",\"users\":[";
						for(SysUser user : child1.getUsers()) {
							jsonStr += "{\"userId\":\""+user.getUsername()+"\",\"userName\":\""+user.getName()+"\",\"userTel\":\""+user.getDepName()+"\"},";
						}
						jsonStr = jsonStr.substring(0, jsonStr.length()-1);
						jsonStr += "]";
					}
					//jsonStr = jsonStr.substring(0, jsonStr.length()-1);
					if(!child1.getChild().isEmpty()) {
						jsonStr += ",\"child\":[";
						for(Group child2 : child1.getChild()) {
							jsonStr += "{\"groupId\":\""+child2.getId()+"\",\"groupName\":\""+child2.getGroupName()+"\"";
							if(!child2.getUsers().isEmpty()) {
								jsonStr += ",\"users\":[";
								for(SysUser user : child2.getUsers()) {
									jsonStr += "{\"userId\":\""+user.getUsername()+"\",\"userName\":\""+user.getName()+"\",\"userTel\":\""+user.getDepName()+"\"},";
								}
								jsonStr = jsonStr.substring(0, jsonStr.length()-1);
								jsonStr += "]";
							}
							jsonStr+= "},";
						}
						jsonStr = jsonStr.substring(0, jsonStr.length()-1);
						jsonStr += "]";
					}
					//jsonStr = jsonStr.substring(0, jsonStr.length()-1);
					jsonStr+= "},";
				}
				jsonStr = jsonStr.substring(0, jsonStr.length()-1);
				jsonStr += "]";
			}
			//jsonStr = jsonStr.substring(0, jsonStr.length()-1);
			jsonStr += "},";
		}
		jsonStr = jsonStr.substring(0, jsonStr.length()-1);
		jsonStr += "]";
		return jsonStr;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Group> getAllTop() {
		// TODO Auto-generated method stub
		return groupDao.getAllTopGroup();
	}

	@Override
	@Transactional(readOnly=true)
	public Group getById(Long id) {
		// TODO Auto-generated method stub
		return groupDao.find(id);
	}

	@Override
	@Transactional
	public boolean saveGroup(Group group) {
		// TODO Auto-generated method stub
		return groupDao.save(group);
	}
}
