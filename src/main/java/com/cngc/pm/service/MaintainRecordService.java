package com.cngc.pm.service;

import java.text.ParseException;
import java.util.List;

import com.cngc.pm.model.MaintainRecord;
import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.search.SearchResult;

public interface MaintainRecordService {

	List<MaintainRecord> getAllRecords();

	List<MaintainRecord> getRecordsByUsername(String username);

	void save(MaintainRecord mr, String ip);

	List<MaintainRecord> getRecordListByusernames(String... usernames);

	List<MaintainRecord> search(String equipName, String maintainTimeStart, String maintainTimeEnd, String type) throws ParseException ;

	void delById(int id, String username, String ip);

	SearchResult<MaintainRecord> search(SysUser user, String equipName, String startTime, String endTime,
			String type_, int iDisplayStart, int iDisplayLength) throws ParseException;
}
