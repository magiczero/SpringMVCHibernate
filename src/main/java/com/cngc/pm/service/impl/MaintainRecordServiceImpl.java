package com.cngc.pm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.MaintainRecordDAO;
import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.dao.RoleDAO;
import com.cngc.pm.model.MaintainRecord;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.cngc.pm.service.MaintainRecordService;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class MaintainRecordServiceImpl implements MaintainRecordService {

	@Autowired
	private MaintainRecordDAO mrDao;
	@Autowired
	private RecordsDAO recordsDao;
	@Autowired
	private RoleDAO roleDao;
	
	@Override
	public List<MaintainRecord> getAllRecords() {
		// TODO Auto-generated method stub
		Search search = new Search(MaintainRecord.class);
		search.addSortDesc("maintainTime");
		
		return mrDao.search(search);
	}

	@Override
	public List<MaintainRecord> getRecordsByUsername(String username) {
		// TODO Auto-generated method stub
		Search search = new Search(MaintainRecord.class);
		search.addFilterEqual("executor", username);
		
		search.addSortDesc("maintainTime");
		
		return mrDao.search(search);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void save(MaintainRecord mr, String ip) {
		// TODO Auto-generated method stub
		
		mrDao.save(mr);
		
		Records record = new Records();
		record.setUsername(mr.getExecutor());
		record.setType(RecordsType.user);
		record.setIpAddress(ip);
		record.setDesc("录入了三员工作记录，用户id：[" + mr.getExecutor() +"]，记录id：["+ mr.getId()+"]");
		recordsDao.save(record);
	}

	@Override
	public List<MaintainRecord> getRecordListByusernames(String... usernames) {
		// TODO Auto-generated method stub
		Search search = new Search(MaintainRecord.class);
		//search.addFilterEqual("executor", username);
		search.addFilterIn("executor", usernames);
		
		search.addSortDesc("maintainTime");
		
		return mrDao.search(search);
	}

	@Override
	public List<MaintainRecord> search(String equipName, String maintainTimeStart, String maintainTimeEnd,
			String type) throws ParseException {
		// TODO Auto-generated method stub
		Search search = new Search(MaintainRecord.class);
		
		if(!StringUtils.isEmpty(equipName))
			search.addFilterEqual("equipName", equipName);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		
		
		if(!StringUtils.isEmpty(maintainTimeStart))
			search.addFilterGreaterOrEqual("maintainTime", sdf.parse(maintainTimeStart));
		
		if(!StringUtils.isEmpty(maintainTimeStart))
			search.addFilterLessOrEqual("maintainTime", sdf.parse(maintainTimeEnd));
		
		if(!StringUtils.isEmpty(type) && Integer.valueOf(type)>0)
			search.addFilterEqual("type", type);
		
		search.addSortDesc("maintainTime");
		return mrDao.search(search);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void delById(int id, String username, String ip) {
		// TODO Auto-generated method stub
		mrDao.removeById((long) id);
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.user);
		record.setIpAddress(ip);
		record.setDesc("删除了三员工作记录，用户id：[" + username +"]，记录id：["+ id +"]");
		recordsDao.save(record);
	}

	@Override
	public SearchResult<MaintainRecord> search(SysUser user, String equipName, String startTime,
			String endTime, String type_, int iDisplayStart, int iDisplayLength) throws ParseException {
		// TODO Auto-generated method stub
		boolean isLeader = false, isSysop = false, isSecurity=false, isAuditor = false;
		for (UserRole ur : user.getUserRoles()) {
			if ("WK_LEADER".indexOf(ur.getRole().getRoleName())>=0 ) {	//领导
				isLeader = true;
				break;
			} else if("comptoller".indexOf(ur.getRole().getRoleName())>=0 ) {	//审计员
				isAuditor = true;
				break;
			} else if("safety_manager".indexOf(ur.getRole().getRoleName())>=0 ) {	//安全员
				isSecurity = true;
				break;
			} 
//			else if("sys_admin".indexOf(ur.getRole().getRoleName())>=0 ) {	//安全员
//				isSysop = true;
//				break;
//			} 
		}
		Search search = new Search(MaintainRecord.class);
		
		if(!StringUtils.isEmpty(equipName))
			search.addFilterEqual("equipName", equipName);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		
		if(!StringUtils.isEmpty(startTime))
			search.addFilterGreaterOrEqual("maintainTime", sdf.parse(startTime));
		
		if(!StringUtils.isEmpty(endTime))
			search.addFilterLessOrEqual("maintainTime", sdf.parse(endTime));
		
		if(!StringUtils.isEmpty(type_) && Integer.valueOf(type_)>0)
			search.addFilterEqual("type", type_);
		
		List<Role> roleList = new ArrayList<>();
		List<String> usernameList = new ArrayList<>();
		if(isLeader) {
			//看所有三员的
			roleList = roleDao.getRoleByNames("sysop,safety_manager,comptoller");
		} else if(isAuditor) {
			roleList = roleDao.getRoleByNames("sysop,safety_manager");
		} else if(isSecurity) {
			roleList = roleDao.getRoleByNames("comptoller");
		} 
//		else if(isSysop) {
//			roleList = roleDao.getRoleByNames("security_auditor");
//		}
		
		for(Role role : roleList) {
			for(UserRole ur :role.getUserRoles()) {
				usernameList.add(ur.getUser().getUsername());
			}
		}
		
//		System.out.println(usernameList);
		search.addFilterIn("executor", usernameList);
		
		search.setFirstResult(iDisplayStart);
		search.setMaxResults(iDisplayLength);
		
		search.addSortDesc("maintainTime");
		
		return mrDao.searchAndCount(search);
	}

}
