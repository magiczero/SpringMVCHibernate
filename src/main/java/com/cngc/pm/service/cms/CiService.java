package com.cngc.pm.service.cms;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.NoSuchPaddingException;

import org.springframework.web.multipart.MultipartFile;

import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.AuditTask;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.googlecode.genericdao.search.SearchResult;

public interface CiService {

	void save(Ci ci,String ip) throws Exception;

	boolean delById(Long id);

	Ci getById(Long id);

	List<Ci> getAll();

	List<Ci> getByRelation(String relationId, Long primaryId);
	
	SearchResult<Ci> getByCategoryCode(String code);

	boolean deleteRelation(Long primaryId, Long secondaryId, String relationId);

	boolean saveRelation(Long primaryId, Long secondaryId, String relationId);
	
	SearchResult<Ci> getByIds(List<Long> ids);
	
	Map<String,Object> getStats(String column,String row,String status);
	
	SearchResult<Ci> getByUser(String user);

	/**
	 * 导入Excel文件数据（一个或多个Excel文件）
	 * @param setAttach		excel附件集合
	 * @throws IOException 
	 * @throws ParseException 
	 */
	void importData(Set<Attachment> setAttach) throws IOException, ParseException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException;

	/**
	 * 分页获取CI，根据code
	 * @param categoryCode
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	SearchResult<Ci> getAllWithPage(String categoryCode,int iDisplayStart, int iDisplayLength);

	SearchResult<Ci> getListByCondition(List<String> codeList, String type, String department, String status, String securityLevel,int iDisplayStart, int iDisplayLength);

	SearchResult<Ci> getListByUserAndType(SysUser user, String categoryCode,int iDisplayStart,int iDisplayLength);

	/**
	 * 保存审核中的ci
	 * @param ci
	 * @param at 	审核任务信息
	 */
	void saveByAuditTask(Ci ci, AuditTask at) throws Exception;

	void updateByAuditTask(Ci ci, AuditTask at) throws Exception;
	
	/**
	 * 获取对比数据
	 * @param ciid
	 * @param atid
	 * @return
	 * @throws Exception
	 */
	Map<String, String[]> getContrastByCi(long ciid, long atid) throws Exception;
	
	void passCi(Ci ci, AuditTask at, boolean flag) throws Exception;

	void importXlsByTask(MultipartFile file, AuditTask at, SysUser user) throws Exception;

	void exportXls(List<Group> groups , List<Category> categorys, java.io.OutputStream out) throws Exception;

	void delByAuditTask(Ci ci, AuditTask at) throws Exception;

	void recoverByAuditTask(Ci ci, AuditTask at) throws Exception;

	void passCis(AuditTask at, Long[] ids, boolean decide);

}
