package com.cngc.pm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.RecordsService;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class RecordsServiceImpl implements RecordsService {

	@Autowired
	private RecordsDAO recordsDao;
	@Autowired
	private UserDAO userDao;

	@Override
	@Transactional
	public void save(Records record) {
		// TODO Auto-generated method stub
		recordsDao.save(record);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Records> getListAll() {
		// TODO Auto-generated method stub
		return recordsDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public SearchResult<Records> getAllWithPage(String roleName, Integer offset,
			Integer maxResults) {
		// TODO Auto-generated method stub
		Search search = new Search();
		if(roleName.equals("security_secrecy_admin")) {
			search.addFilterIn("type", RecordsType.user,RecordsType.login);
		} else if(roleName.equals("security_auditor")) {
			search.addFilterIn("type", RecordsType.role,RecordsType.memberlogin);
		}
		search.setFirstResult(offset == null?0:offset);
		search.setMaxResults(maxResults==null?10:maxResults);
		search.addSort("id", true);
		
		return recordsDao.searchAndCount(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SysUser> getUsersByRole(String...names) {
		// TODO Auto-generated method stub
		Search search = new Search();
		
		search.addFilterIn("userRoles.role.roleName", Arrays.asList(names));
		
		return userDao.search(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Records> searchList(String username, List<Integer> typeList, String start,
			String end) throws ParseException {
		Search search = new Search();
		if(!StringUtils.isEmpty(username)) {
			search.addFilterEqual("username", username);
		}
		
		List<RecordsType> rts = new ArrayList<>();
		
		for(int i : typeList) {
			switch(i) 
			{
				case 1:
					rts.add(RecordsType.auth);
					break;
				case 2:
					rts.add(RecordsType.login);
					break;
				case 3:
					rts.add(RecordsType.user);
					break;
				case 4:
					rts.add(RecordsType.rescourcs);
					break;
				case 5:
					rts.add(RecordsType.menu);
					break;
				case 6:
					rts.add(RecordsType.role);
					break;
				case 7:
					rts.add(RecordsType.memberlogin);
					break;
			}
		}
		
		if(rts.size()>0)
			search.addFilterIn("type", rts);
		
		if(!StringUtils.isEmpty(start)) {
			start = start + " 00:00:00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = sdf.parse(start);
			
			search.addFilterGreaterOrEqual("inTime", startDate);
		}
		
		if(!StringUtils.isEmpty(end)) {
			end = end + " 23:59:59";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endDate = sdf.parse(end);
			
			search.addFilterLessOrEqual("inTime", endDate);
		}
		
		search.addSortDesc("id");
		
		return recordsDao.search(search);
	}
}
