package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.dao.SysCodeDAO;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.model.SysCode;
import com.cngc.pm.service.SysCodeService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class SysCodeServiceImpl implements SysCodeService{

	@Autowired
	private SysCodeDAO syscodeDAO;
	
	@Autowired
	private RecordsDAO recordsDao;
	
	@Override
	@Transactional
	public void save(SysCode code, String name, String ip)
	{
		syscodeDAO.save(code);
		
		Records record = new Records();
		record.setUsername(name);
		record.setType(RecordsType.role);
		record.setIpAddress(ip);
		record.setDesc("新建了数据字典，id：[" + code.getId() +"]，字典名：["+ code.getCodeName()+"]");
		recordsDao.save(record);
	}
	@Override
	@Transactional
	public boolean delById(Long id)
	{
		syscodeDAO.removeById(id);
		return true;
	}
	@Override
	@Transactional
	public SysCode getById(Long id)
	{
		return syscodeDAO.find(id);
	}
	@Override
	@Transactional
	public SearchResult<SysCode> getAllByType(String type)
	{
		return syscodeDAO.getCodeByType(type);
	}
	/* 获取JSON字符串为bootstrap tree
	 * @see com.cngc.pm.service.SysCodeService#getJSON(java.lang.String)
	 */
	@Override
	@Transactional
	public String getJSON(String type)
	{
		String sjson = "";

		List<SysCode> list = syscodeDAO.getCodeByType(type).getResult();

		String lastcode = "";
		String curcode = "";
		for (SysCode code : list) {
			curcode = code.getCode();
			if (lastcode.length() > 0) {
				if (curcode.length() > lastcode.length()) {
					// 子节点
					sjson += ",\"nodes\":[";
				} else if (curcode.length() == lastcode.length()) {
					// 兄弟节点
					sjson += "},";
				} else if (curcode.length() < lastcode.length()) {
					// 父级节点
					sjson += "}";
					for (int i = 0; i < lastcode.length() - curcode.length(); i = i + 2) {
						sjson += "]}";
					}
					sjson += ",";
				}
			}
			sjson += "{\"text\":\"" + code.getCode() + " " + code.getCodeName() + "\"";
			lastcode = curcode;
		}
		sjson += "}";
		for (int i = 2; i < lastcode.length(); i = i + 2) {
			sjson += "]}";
		}
		sjson = "[" + sjson + "]";

		return sjson;
	}
	@Override
	@Transactional
	public SysCode getCode(String code,String type)
	{
		return syscodeDAO.getCode(code, type);
	}
	@Override
	@Transactional(readOnly=true)
	public SearchResult<SysCode> getParentCodeByType(String type)
	{
		return syscodeDAO.getParentCodeByType(type);
	}
}
