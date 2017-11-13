package com.cngc.pm.service.cms;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.NoSuchPaddingException;

import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Ci;
import com.googlecode.genericdao.search.SearchResult;

public interface CiService {

	void save(Ci ci,String ip);

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

	/**
	 * 根据用户权限和分类代码确定Ci集合
	 * @param secretlevel  0=全部，1=涉密，2=非密
	 * @param user
	 * @param codeList
	 * @return
	 */
	List<Ci> getByAuthAndCategory(int secretlevel,SysUser user, List<String> codeList);

	SearchResult<Ci> getListByCondition(List<String> codeList, String type, String department, String status, String securityLevel,int iDisplayStart, int iDisplayLength);
}
