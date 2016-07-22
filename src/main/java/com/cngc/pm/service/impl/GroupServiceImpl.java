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
		String groupIdStr = "{\"groupId\":\"";
		String groupNameStr = "\",\"groupName\":\"";
		String groupEndStr = "\"";
		String users = ",\"users\":[";
		String endStr = "]";
		String userIdStr = "{\"userId\":\"";
		String userNameStr = "\",\"userName\":\"";
		String userTelStr = "\",\"userTel\":\"";
		String userRoomStr = "\",\"userRoom\":\"";
		String userEndStr = "\"},";
		String childStr = ",\"child\":[";
		String endStr2 = "},";
		StringBuffer jsonStr = new StringBuffer("[");
		
		/*
		 * 此方法可用此类org.activiti.engine.impl.util.json.JSONObject，但可能效率低
		 */
		for(Group group : groupDao.getAllTopGroup()) {
			jsonStr.append(groupIdStr).append(group.getId()).append(groupNameStr).append(group.getGroupName()).append(groupEndStr);
			if(!group.getUsers().isEmpty()) {
				jsonStr.append(users);
				for(SysUser user : group.getUsers()) {
					jsonStr.append(userIdStr).append(user.getUsername()).append(userNameStr).append(user.getName()).append(userTelStr).append(user.getDepName()).append(userRoomStr).append(user.getMechName()).append(userEndStr);
				}
				jsonStr.deleteCharAt(jsonStr.length()-1);
				jsonStr.append(endStr);
			}
			if(!group.getChild().isEmpty()) {
				jsonStr.append(childStr);
				for(Group child1 : group.getChild()) {
					jsonStr.append(groupIdStr).append(child1.getId()).append(groupNameStr).append(child1.getGroupName()).append(groupEndStr);
					if(!child1.getUsers().isEmpty()) {
						jsonStr.append(users);
						for(SysUser user : child1.getUsers()) {
							jsonStr.append(userIdStr).append(user.getUsername()).append(userNameStr).append(user.getName()).append(userTelStr).append(user.getDepName()).append(userRoomStr).append(user.getMechName()).append(userEndStr);
						}
						jsonStr.deleteCharAt(jsonStr.length()-1);
						jsonStr.append(endStr);
					}
					if(!child1.getChild().isEmpty()) {
						jsonStr.append(childStr);
						for(Group child2 : child1.getChild()) {
							jsonStr.append(groupIdStr).append(child2.getId()).append(groupNameStr).append(child2.getGroupName()).append(groupEndStr);
							if(!child2.getUsers().isEmpty()) {
								jsonStr.append(users);
								for(SysUser user : child2.getUsers()) {
									jsonStr.append(userIdStr).append(user.getUsername()).append(userNameStr).append(user.getName()).append(userTelStr).append(user.getDepName()).append(userRoomStr).append(user.getMechName()).append(userEndStr);
								}
								jsonStr.deleteCharAt(jsonStr.length()-1);
								jsonStr.append(endStr);
							}
							jsonStr.append(endStr2);
						}
						jsonStr.deleteCharAt(jsonStr.length()-1);
						jsonStr.append(endStr);
					}
					jsonStr.append(endStr2);
				}
				jsonStr.deleteCharAt(jsonStr.length()-1);
				jsonStr.append(endStr);
			}
			jsonStr.append(endStr2);
		}
		jsonStr.deleteCharAt(jsonStr.length()-1);
		jsonStr.append(endStr);
		//原始代码，不能删
		/*
		String jsonStr = "[";
		for(Group group : groupDao.getAllTopGroup()) {
			jsonStr += "{\"groupId\":\""+group.getId()+"\",\"groupName\":\""+group.getGroupName()+"\"";
			if(!group.getUsers().isEmpty()) {
				jsonStr += ",\"users\":[";
				for(SysUser user : group.getUsers()) {
					jsonStr += "{\"userId\":\""+user.getUsername()+"\",\"userName\":\""+user.getName()+"\",\"userTel\":\""+user.getDepName()+"\",\"userRoom\":\""+user.getMechName()+"\"},";
				}
				jsonStr = jsonStr.substring(0, jsonStr.length()-1);
				jsonStr += "]";
			}
			if(!group.getChild().isEmpty()) {
				jsonStr += ",\"child\":[";
				for(Group child1 : group.getChild()) {
					jsonStr +="{\"groupId\":\""+child1.getId()+"\",\"groupName\":\""+child1.getGroupName()+"\"";
					if(!child1.getUsers().isEmpty()) {
						jsonStr += ",\"users\":[";
						for(SysUser user : child1.getUsers()) {
							jsonStr += "{\"userId\":\""+user.getUsername()+"\",\"userName\":\""+user.getName()+"\",\"userTel\":\""+user.getDepName()+"\",\"userRoom\":\""+user.getMechName()+"\"},";
						}
						jsonStr = jsonStr.substring(0, jsonStr.length()-1);
						jsonStr += "]";
					}
					if(!child1.getChild().isEmpty()) {
						jsonStr += ",\"child\":[";
						for(Group child2 : child1.getChild()) {
							jsonStr += "{\"groupId\":\""+child2.getId()+"\",\"groupName\":\""+child2.getGroupName()+"\"";
							if(!child2.getUsers().isEmpty()) {
								jsonStr += ",\"users\":[";
								for(SysUser user : child2.getUsers()) {
									jsonStr += "{\"userId\":\""+user.getUsername()+"\",\"userName\":\""+user.getName()+"\",\"userTel\":\""+user.getDepName()+"\",\"userRoom\":\""+user.getMechName()+"\"},";
								}
								jsonStr = jsonStr.substring(0, jsonStr.length()-1);
								jsonStr += "]";
							}
							jsonStr+= "},";
						}
						jsonStr = jsonStr.substring(0, jsonStr.length()-1);
						jsonStr += "]";
					}
					jsonStr+= "},";
				}
				jsonStr = jsonStr.substring(0, jsonStr.length()-1);
				jsonStr += "]";
			}
			jsonStr += "},";
		}
		jsonStr = jsonStr.substring(0, jsonStr.length()-1);
		jsonStr += "]";
		*/
		return jsonStr.toString();
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

	@Override
	@Transactional(readOnly=true)
	public String getChildByGroup(String groupid) {
		// TODO Auto-generated method stub
		StringBuffer jsonStr = new StringBuffer("[");
		if(("source").equals(groupid)) {
			for(Group group : groupDao.getAllTopGroup()) {
				jsonStr.append("{\"text\":\"").append(group.getGroupName()).append("\"");
				if(group.getChild().size()>0 || group.getUsers().size()>0) {
					jsonStr.append(",\"id\":\"").append(group.getId()).append("\",");
					jsonStr.append("\"hasChildren\":\"true\"");
				}
				jsonStr.append("},");
			}
			jsonStr.deleteCharAt(jsonStr.length()-1);
		} else {
			Group group =  groupDao.find(Long.valueOf(groupid));
			if(group.getUsers().size()>0) {
				for(SysUser user : group.getUsers()) {
					jsonStr.append("{\"text\":\"<a href='javascript:void(0);' onclick='inputUserinfo(\\\""+user.getUsername()+"\\\",\\\""+user.getName()+"\\\",\\\""+user.getDepName()+"\\\",\\\""+user.getMechName()+"\\\");'>").append(user.getName()).append("</a>\"},");
				}
				jsonStr.deleteCharAt(jsonStr.length()-1);
			}
			if(group.getChild().size()>0) {
				if(group.getUsers().size()>0)
					jsonStr.append(",");
				for(Group child : group.getChild()) {
					jsonStr.append("{\"text\":\"").append(child.getGroupName()).append("\"");
					if(group.getChild().size()>0 || child.getUsers().size()>0) {
						jsonStr.append(",\"id\":\"").append(child.getId()).append("\",");
						jsonStr.append("\"hasChildren\":\"true\"");
					}
					jsonStr.append("},");
				}
				jsonStr.deleteCharAt(jsonStr.length()-1);
			}
		}
		
		jsonStr.append("]");
		return jsonStr.toString();
	}
}
