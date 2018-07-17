package com.cngc.pm.service.impl.cms;

import static com.cngc.utils.Constants._accountMaster;
import static com.cngc.utils.Constants._accountSub;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import static com.cngc.utils.Common._strKey;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cngc.pm.dao.ChangeItemDAO;
import com.cngc.pm.dao.GroupDAO;
import com.cngc.pm.dao.MaintainRecordDAO;
import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.dao.StatsDAO;
import com.cngc.pm.dao.SysCodeDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.dao.cms.AccountDAO;
import com.cngc.pm.dao.cms.AuditTaskDAO;
import com.cngc.pm.dao.cms.CategoryDAO;
import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.dao.cms.PropertyDAO;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.ChangeitemActionType;
import com.cngc.pm.model.ChangeitemType;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.MaintainRecord;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.cngc.pm.model.cms.Account;
import com.cngc.pm.model.cms.AuditTask;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.model.cms.TaskCategoryDepartmentRelation;
import com.cngc.pm.service.cms.CiService;
import com.cngc.utils.Common;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class CiServiceImpl implements CiService{
	
	@Autowired
	private CiDAO ciDao;
	@Autowired
	private StatsDAO statsDao;
	@Autowired
	private CategoryDAO categoryDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private MaintainRecordDAO maintainRecordDao;
	@Autowired
	private RecordsDAO recordsDao;
	@Autowired
	private ChangeItemDAO changeitemDao;
	@Autowired
	private AccountDAO accountDao;
	@Autowired
	private PropertyDAO propertyDao;
	@Autowired
	private AuditTaskDAO auditTaskDao;
	@Autowired
	private SysCodeDAO sysCodeDao;
	@Autowired
	private GroupDAO groupDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void save(Ci ci, String ip) throws Exception{
		ciDao.save(ci);
		//Date date = new Date();
		//同时在变更表中填写记录
		ChangeItem item = new ChangeItem();
		item.setCiId(ci.getId());
		
		//根据类型获得要显示的列
				List<Property> fieldsSet = getParpertiesByCode(ci.getCategoryCode());
				
				String propertiesStr="", propertyNames = "";
				Map<String, Object> mapNewValue = new HashMap<>();
				ObjectMapper mapper = new ObjectMapper();
				@SuppressWarnings("unchecked")
				Map<String,String> newPropertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
				for(Property p:fieldsSet) {
					propertyNames+=p.getPropertyName()+",";
					propertiesStr+=p.getPropertyId()+",";
					//对比值
					if(p.getPropertyId().indexOf("CMS_FIELD_")==0 ) {
						if(p.isNonTransient()) {
							mapNewValue.put(p.getPropertyId(), Common.getFieldValueByName(ci, p.getPropertyConstraint()));
						}
					} else {
						mapNewValue.put(p.getPropertyId(), newPropertymap.get(p.getPropertyId()));
					}
					
				}
		
		item.setNewValue(mapper.writeValueAsString(mapNewValue));
		
		Date now = new Date();
		
		item.setCreatedTime(now);
		item.setPropertiesName(propertyNames.substring(0, propertyNames.length()-1));
		item.setProperties(propertiesStr.substring(0, propertiesStr.length()-1));
		item.setType(ChangeitemType.create);
		//保存
		changeitemDao.save(item);
		//同时写入工作记录
		MaintainRecord mr = new MaintainRecord();
		mr.setEquipId(ci.getId().toString());
		mr.setEquipName(ci.getName());
		mr.setEquipNum(ci.getNum());
		mr.setExecutor(ci.getLastUpdateUser());
		
		mr.setMaintainTime(now);
		mr.setInTime(now);
		mr.setType(1);
		mr.setRole(1);
		
		mr.setCircs("新建了资产，id是："+ci.getId());//执行情况
		
		maintainRecordDao.save(mr);
		//同时写入审计记录
		Records record = new Records();
		record.setUsername(ci.getLastUpdateUser());
		record.setType(RecordsType.user);
		record.setIpAddress(ip);
		record.setDesc("新建了资产，资产id：[" + ci.getId() +"]");
		
		recordsDao.save(record);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public boolean delById(Long id)
	{
		return ciDao.removeById(id);
	}
	
	@Override
	public Ci getById(Long id){
		return ciDao.find(id);
	}
	
	@Override
	public List<Ci> getAll()
	{
		return ciDao.findAll();
	}
	
	@Override
	public List<Ci> getByRelation(String relationId,Long primaryId)
	{
		return ciDao.getByRelation(relationId, primaryId);
	}
	@Override
	public SearchResult<Ci> getByCategoryCode(String code)
	{
		Search search = new Search();
		search.addFilterLike("categoryCode", code+"%");
		return ciDao.searchAndCount(search);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public boolean deleteRelation(Long primaryId,Long secondaryId,String relationId)
	{
		return ciDao.deleteRelation(primaryId, secondaryId, relationId);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public boolean saveRelation(Long primaryId,Long secondaryId,String relationId)
	{
		return ciDao.saveRelation(primaryId, secondaryId, relationId);
	}
	@Override
	public SearchResult<Ci> getByIds(List<Long> ids)
	{
		return ciDao.getByIds(ids);
	}
	@Override
	public Map<String,Object> getStats(String column,String row,String status)
	{
		return statsDao.getStats("CMS", column, row, null, null, status);
	}
	@Override
	public SearchResult<Ci> getByUser(String user)
	{
		return ciDao.getByUser(user);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void importXlsByTask(MultipartFile file, AuditTask at, SysUser user) throws Exception {
		String[] fileNames = file.getOriginalFilename().split("\\.");
        
        if(fileNames[1].equals("xls") || fileNames[1].equals("xlsx")) {
	        
	        Workbook wb  = null;  
            //根据文件格式(2003或者2007)来初始化  
	        if(fileNames[1].equals("xlsx"))  
	                wb = new XSSFWorkbook(file.getInputStream());  
	        else  
	                wb = new HSSFWorkbook(file.getInputStream()); 
	        
	        //可以导入的类别
	        Set<TaskCategoryDepartmentRelation> relationSet = at.getRelationSet();
	        //注意部门和类别是必须项
	        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {				//每一行是一个CI
            	Sheet sheet = wb.getSheetAt(numSheet);  
            	
            	//根据sheet名获取类别
            	Category category = categoryDao.getByName(sheet.getSheetName());
            	
            	if(category == null) throw new NullPointerException("excel中的数据有问题，没有找到这个类别："+sheet.getSheetName());
            	
            	Map<String, Category> categoryMap = new HashMap<>();
            	Map<String, Group> groupMap = new HashMap<>();
            	for(TaskCategoryDepartmentRelation relation : relationSet) {
            		categoryMap.put(relation.getCiCategory().getCategoryCode(),relation.getCiCategory());
            		groupMap.put(relation.getCiDepartment().getGroupName(), relation.getCiDepartment());
            	}
            	//判断类别是否是审核状态
            	if(!categoryMap.containsKey(category.getCategoryCode())) {
            		throw new Exception("表中有的类别不是审核状态");
            	}
            	
            	String categoryCode = category.getCategoryCode();
            	if(sheet.getLastRowNum() > 0) {
	            	// 遍历表格的每一行
	                int rowStart = sheet.getFirstRowNum();
	                // 最小行数为1行
	                int rowEnd = sheet.getLastRowNum();
	                
	                Row headerRow = sheet.getRow(1);
	            	//标题栏
	            	String headers[] = _parseExcelHeader(headerRow);
	            	
	            	//区域内容
	            	String datas[][] = _parseExcelData(sheet, rowStart + 2, rowEnd,Math.max(headerRow.getLastCellNum(), 1));
	            	
	            	ObjectMapper mapper = new ObjectMapper();
	            	Group group = user.getGroup();
	            	for(int i = 0; i < datas.length; i++) {
	                	String datas2[] = datas[i];
	                	Ci ci = new Ci();
	                	
            			ci.setCategoryCode(categoryCode);				//设置类型
	                	ci.setSystem("01"); 			//归属系统
	                	ci.setDeleteStatus("01");
	                	ci.setImportance("低");
	                	ci.setUse("启用");
	                	Map<String,String> propertymap = new HashMap<>();
	                	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	                	for(int j = 0; j<datas2.length; j++) {			//每一行是一个CI
	                		if(StringUtils.isNotBlank(datas2[j])) {
	                			Property p = propertyDao.getByPropertyName(headers[j]);
	                			if(p == null) throw new Exception("excel中的数据有问题，没有找到这个标题对应的类别："+headers[i]);
	                			String propertyId =p.getPropertyId(); 
	                			if(propertyId.indexOf("CMS_FIELD_")==0){
	                				if(StringUtils.contains(propertyId, "CMS_FIELD_REVIEWSTATUS")) continue;
	                				Object value = datas2[j];
	                				if(p.getPropertyType().equals("date")) {
	                					value = format.parse(datas2[j]);
	                				} else if(p.getPropertyType().equals("sqldate")) {
	                					value = new java.sql.Date(format.parse(datas2[j]).getTime());
	                				} else {
	                					switch(propertyId) {
		                					case "CMS_FIELD_DEPARTMENTINUSE"://使用部门
		                						if(group.getGroupName().equals(value)) {
			                						value = String.valueOf(group.getId());
			                					} else
			                						throw new Exception("表格中的部门与操作人员的部门不一致，请查实");
		                						break;
		                					case "CMS_FIELD_USERINMAINTENANCE"://使用人
		                						value=userDao.getUserByName(datas2[j]).getUsername();
		                						break;
		                					case "CMS_FIELD_STATUS":	//使用情况
		                						value = sysCodeDao.getByCodeNameAndType(datas2[j], "CI_STATUS").getCode();
		                						break;
		                					case "CMS_FIELD_SECURITYLEVEL":	//密级
		                						value = sysCodeDao.getByCodeNameAndType(datas2[j], "CI_SECURITY_LEVEL").getCode();
		                						break;
		                				}
	                				}
	            					// 更新字段
	                				Common.setFieldValueByName(ci, p.getPropertyConstraint(), value);
	            				}else{
	            					if(p.isNonTransient())
	            						propertymap.put(propertyId, datas2[j]);
	            				}
	                		}
	                	}
	                	ci.setReviewStatus("02");
	                	
	        			//ci.setLastUpdateUser(user.getUsername());
	                	ci.setPropertiesData(mapper.writeValueAsString(propertymap));
	                	
	                	if(StringUtils.isEmpty(ci.getSecurityNo())){
	                		throw new Exception("没有保密编号");
	                	}
	                	
	                	Ci ci0 = ciDao.getBySecurityNo(ci.getSecurityNo());
	                	
	                	if(ci0==null) {			//新建
	                		saveCiByAuditTask(ci, at);
	                	} else {				//修改
	                		updateCiByAuditTask(ci0 , ci, at);
	                	}
	                }
            	}
            	
	        }
        }
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void importData(Set<Attachment> setAttach) throws IOException, ParseException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		// TODO Auto-generated method stub
		//首先加载properties文件
		//解析excel文件放到CI中并验证，出错则抛出Exception
		
		List<Ci> ciList = new ArrayList<>();
		
		for(Attachment attach : setAttach) {
			String fileStoreName = attach.getNewFilename();
			String filePath = attach.getPath()+File.separator+fileStoreName;
			
			//解密
			String tempFilename = UUID.randomUUID().toString(); // 重命名文件名
			String fileExtension = StringUtils.substring(fileStoreName,  StringUtils.lastIndexOf(fileStoreName, "."));
			
			String tempStoreFileName = tempFilename+fileExtension;
			
			String tempFilePath = attach.getPath()+File.separator + tempStoreFileName;
			File newFile = new File(tempFilePath);
			
			KeyGenerator _generator = KeyGenerator.getInstance("DES"); 
		    _generator.init(new SecureRandom(_strKey.getBytes())); 
		    Key key = _generator.generateKey(); 
		    _generator = null; 
			Cipher cipher = Cipher.getInstance("DES"); 
		    cipher.init(Cipher.DECRYPT_MODE, key);
		    
		    InputStream is = new FileInputStream(filePath);
		    OutputStream out = new FileOutputStream(newFile); 
		    CipherInputStream cis = new CipherInputStream(is, cipher); 
		    
		    byte[] buffer = new byte[1024]; 
		    int r; 
		    while ((r = cis.read(buffer)) > 0) { 
		        out.write(buffer, 0, r); 
		    } 
		    
		    cis.close(); 
		    is.close(); 
		    out.close(); 
		    
			boolean isE2007 = false;		//判断是否是excel2007格式
			
			if(attach.getNewFilename().endsWith("xlsx"))
				isE2007 = true;
			
			InputStream input = new FileInputStream(newFile);
			Workbook wb  = null;  
	            //根据文件格式(2003或者2007)来初始化  
	        if(isE2007)  
	                wb = new XSSFWorkbook(input);  
	        else  
	                wb = new HSSFWorkbook(input); 
	        
	        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {				//每一行是一个CI
            	Sheet sheet = wb.getSheetAt(numSheet);  
            	
            	//根据sheetname获取分类代码
            	String typeCode = sheet.getSheetName();
//            	
//            	Search search = new Search();
//        		search.addFilterEqual("categoryName", typeName);
//        		//System.out.println(typeName);
//        		Category category = categoryDao.searchUnique(search);
            	
            	if(sheet.getLastRowNum() > 0) {
	            	// 遍历表格的每一行
	                int rowStart = sheet.getFirstRowNum();
	                // 最小行数为1行
	                int rowEnd = sheet.getLastRowNum();
	                
	                Row headerRow = sheet.getRow(1);
	            	//标题栏
	            	String headers[] = _parseExcelHeader(headerRow);
	            	//区域内容
	            	String datas[][] = _parseExcelData(sheet, rowStart + 2, rowEnd,Math.max(headerRow.getLastCellNum(), 1));
	            	
	            	for(int i = 0; i < datas.length; i++) {
	                	String datas2[] = datas[i];
	                	Ci ci = new Ci();
	                	
            			ci.setCategoryCode(typeCode);				//设置类型
	                	ci.setSystem("01"); 			//归属系统
	                	ci.setDeleteStatus("01");
	                	
	                	for(int j = 0; j<datas2.length; j++) {			//每一行是一个CI
	                		if(!datas2[j].equals("")) {
	                			
	                			ci = setPropertyValue(ci, PropertyFileUtil.getStringValue( headers[j] ), datas2[j]);

	                			
	                		}
	                	}
	                	if(ci.getReviewStatus()==null)
	                		ci.setReviewStatus("02");
	                	ciList.add(ci);
	                }
            	}
            	
            	
	        }
	        
	        if(newFile.exists()) {
	        	newFile.delete();
	        }
		}
		//保存到数据库中
		if(ciList.size()>0) {
			for(Ci ci : ciList) {
				ciDao.save(ci);
			}
		}
	}
	
	/**
     * 读取每个单元格中的内容
     *
     * @param cell
     * @return
     */
    private String _getCellValue(Cell cell) {
        // 如果单元格为空的，则返回空字符串
        if (cell == null) {
            return "";
        }

        // 根据单元格类型，以不同的方式读取单元格的值
        String value = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
                } else {
                    value = (long) cell.getNumericCellValue() + "";
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue() ? "TRUE" : "FALSE";
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula();
                break;
            default:
        }
        return value;
    }
    
    /**
     * 解析EXCEL标题栏
     *
     * @param row
     */
    private String[] _parseExcelHeader(Row row) {
        int lastColumnIndex = Math.max(row.getLastCellNum(), 1);
        String[] headers = new String[lastColumnIndex];
        // 初始化headers，每一列的标题
        for (int columnIndex = 0; columnIndex < lastColumnIndex; columnIndex++) {
            Cell cell = row.getCell(columnIndex, Row.RETURN_BLANK_AS_NULL);
            headers[columnIndex] = _getCellValue(cell).trim();
        }
        return headers;
    }
    
    /**
     * 解析EXCEL数据区域内容
     *
     * @param sheet
     * @param rowStart
     * @param rowEnd
     */
    private String[][] _parseExcelData(Sheet sheet, int rowStart, int rowEnd, int lastColumnIndex) {
        String [][] datas = new String[rowEnd-1][lastColumnIndex];
        for (int rowIndex = rowStart; rowIndex <= rowEnd; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            int rowNumber = rowIndex - rowStart;
            // 读取遍历每一行中的每一列
            for (int columnIndex = 0; columnIndex < lastColumnIndex; columnIndex++) {
                Cell cell = row.getCell(columnIndex, Row.RETURN_BLANK_AS_NULL);
                String value = _getCellValue(cell).trim();
                datas[rowNumber][columnIndex] = value;
            }
        }
        
        return datas;
    }
    
    /**
	 * 给配置项的属性赋值
	 * @param ci
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 * @throws ParseException 
	 */
	private Ci setPropertyValue(Ci ci, String propertyName, String propertyValue) throws ParseException {
		switch(propertyName){
			case "name":					//配置项名称
				ci.setName(propertyValue);
				break;
			case "serial":					//序列号
				ci.setSerial(propertyValue);
				break;
			case "producer":
				ci.setProducer(propertyValue);
				break;
			case "use" :						//是否启用
				ci.setUse(propertyValue);
				break;
			case "importance":			//重要度
				ci.setImportance(propertyValue);
				break;
			case "securityNo" : 		//保密编号
				ci.setSecurityNo(propertyValue);
				break;
			case "securityLevel" :			//密级
				switch(propertyValue) {
					case "机密":
						ci.setSecurityLevel("01");
						break;
					case "秘密":
						ci.setSecurityLevel("02");
						break;
					case "内部":
						ci.setSecurityLevel("03");
						break;
					case "非涉密":
						ci.setSecurityLevel("04");
						break;
					default:
						ci.setSecurityLevel("04");
				}
				break;
			case "status" :								//状态
				switch(propertyValue) {
					case "丢弃":
						ci.setStatus("12");
						break;
					case "变更中":
						ci.setStatus("05");
						break;
					case "运行正常":
						ci.setStatus("07");
						break;
					case "入库":
						ci.setStatus("03");
						break;
					case "故障":
						ci.setStatus("09");
						break;
					case "报废":
						ci.setStatus("11");
						break;
					case "闲置":
						ci.setStatus("14");
						break;
					default:
						ci.setStatus("07");
						break;
				}
				break;
			case "departmentInUse":				//所属部门
				ci.setDepartmentInUse(propertyValue);
				break;
			case "location":						//物理位置
				ci.setLocation(propertyValue);
				break;
			case "model":
				ci.setModel(propertyValue);
				break;
			case "remark":
				ci.setRemark(propertyValue);
				break;
			case "userInMaintenance":			//责任人  需要转换 
				ci.setUserInMaintenance(userDao.getUserByName(propertyValue.trim()).getUsername());
				break;
			case "num":				//设备编号
				ci.setNum(propertyValue);
				break;
			case "ciManager":				//配置管理员，应该转换
				ci.setCiManager(propertyValue);
				break;
			case "purpose":				//用途
				ci.setPurpose(propertyValue);
				break;
			case "serviceStartTime":										//启用时间
				ci.setServiceStartTime(parse(propertyValue));
				break;
			case "serviceEndTime":										//启用时间
				ci.setServiceStartTime(parse(propertyValue));
				break;
			case "telephone":									//电话
				ci.setServiceProviderContact(propertyValue);
				break;
			case "desc":						//描述
				ci.setDesc(propertyValue);
				break;
			default :												//除了以上基本属性就是扩展属性
				String properData = ci.getPropertiesData();
				if(properData==null || properData.equals("")) {
					ci.setPropertiesData("{\""+propertyName+"\":\""+propertyValue+"\"}");
				}	else {
					
						ci.setPropertiesData( properData.substring(0, properData.length()-1)+",\""+propertyName+"\":\""+propertyValue+"\"}");

				}
				break;
		}
		
		return ci;
	}
	
	
	    public java.sql.Date parse(String text) throws ParseException {  
	        DateFormat df = null;  
	        df = new SimpleDateFormat("yyyy-MM-dd");  
	        return new java.sql.Date(df.parse(text).getTime());  
	    }  
	

	@Override
	public SearchResult<Ci> getAllWithPage(String code,int iDisplayStart, int iDisplayLength) {
		// TODO Auto-generated method stub
		Search search = new Search(Ci.class);
		
		search.addFilterEqual("deleteStatus", "01");
		if(!code.equals("0"))
			search.addFilterLike("categoryCode", code+"%");
		
		search.setFirstResult(iDisplayStart);
		search.setMaxResults(iDisplayLength);
		
		return ciDao.searchAndCount(search);
	}


	@Override
	public SearchResult<Ci> getListByCondition(List<String> codeList, String type, String department, String status,
			String securityLevel,int iDisplayStart, int iDisplayLength) {
		// TODO Auto-generated method stub
		Search search = new Search(Ci.class);
		
		search.addFilterEqual("deleteStatus", "01");
		if(type.equals("0"))
			search.addFilterIn("categoryCode", codeList);
		else {
			
			search.addFilterEqual("categoryCode", type);
		}
		
		if(!department.equals("0")) {
			search.addFilterLike("departmentInUse", department+"%");
		}
		
		if(!status.equals("0")) {
			search.addFilterEqual("status", status);
		}
		
		if(!securityLevel.equals("0")) {
			search.addFilterEqual("securityLevel", securityLevel);
		}
		
		search.setFirstResult(iDisplayStart);
		search.setMaxResults(iDisplayLength);
		
		return ciDao.searchAndCount(search);
	}

	@Override
	public SearchResult<Ci> getListByUserAndType(SysUser user, String categoryCode,int iDisplayStart,int iDisplayLength) {
		// TODO Auto-generated method stub
		Search search = new Search(Ci.class);
		
		Set<UserRole> roles = user.getUserRoles();
		
		boolean isMaster = false, isSub = false;
		for (UserRole role : roles) {
			String roleName = role.getRole().getRoleName();
			if (_accountMaster.equals(roleName) ) {
				isMaster = true;
				break;
			} else if(_accountSub.equals(roleName)) {
				isSub = true;
				break;
			}
		}
		
		String groupIdStr = user.getGroup().getId().toString();
		//权限
		if(isMaster) {		//单位台账负责人
			search.addFilterILike("departmentInUse", groupIdStr.substring(0, 2)+"%");
		} else if(isSub) {	//部门台账负责人
			search.addFilterEqual("departmentInUse", groupIdStr);
		} else  {	
			return null;
		}
		
		if(!categoryCode.equals("0"))
			search.addFilterEqual("categoryCode", categoryCode);
		search.setFirstResult(iDisplayStart);
		search.setMaxResults(iDisplayLength);
		
		return ciDao.searchAndCount(search);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void saveByAuditTask(Ci ci, AuditTask at) throws Exception {
		// TODO Auto-generated method stub
		//是否有这个台账信息
		Search search0 = new Search(Ci.class);
		search0.addFilterEqual("securityNo", ci.getSecurityNo().trim());
		if(!ciDao.search(search0).isEmpty()) throw new Exception("此保密编号下已经有设备了");
		
		saveCiByAuditTask(ci, at);
	}
	
	private void saveCiByAuditTask(Ci ci, AuditTask at) throws Exception {
		//ci.setReviewStatus("05");		//改状态为“审核中”
		ci.setReviewStatus("02");
		ciDao.save(ci);
		//Date date = new Date();
		//同时在变更表中填写记录
		ChangeItem item = new ChangeItem();
		item.setCiId(ci.getId());
		item.setChangeId(at.getId());
		
		//根据类型获得要显示的列
		List<Property> fieldsSet = getParpertiesByCode(ci.getCategoryCode());
		
		String propertiesStr="", propertyNames = "";
		Map<String, Object> mapNewValue = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
		for(Property p:fieldsSet) {
			
			if(p.getPropertyId().indexOf("CMS_FIELD_")==0 )
			{
				if( p.isNonTransient()) {
					mapNewValue.put(p.getPropertyId(), Common.getFieldValueByName(ci, p.getPropertyConstraint()));
					propertyNames+=p.getPropertyName()+",";
					propertiesStr+=p.getPropertyId()+",";
				}
			}
			else {
				mapNewValue.put(p.getPropertyId(), propertymap.get(p.getPropertyId()));
				propertyNames+=p.getPropertyName()+",";
				propertiesStr+=p.getPropertyId()+",";
			}
		}
		
		item.setCreatedTime(new Date());
		item.setNewValue(mapper.writeValueAsString(mapNewValue));
		item.setProperties(propertiesStr.substring(0, propertiesStr.length()-1));
		item.setPropertiesName(propertyNames.substring(0, propertyNames.length()-1));
		item.setType(ChangeitemType.audit);
		item.setActionType(ChangeitemActionType.insert);
		item.setPass(false);
		
		changeitemDao.save(item);
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void updateByAuditTask(Ci ci, AuditTask at) throws Exception {
		// TODO Auto-generated method stub
		Ci originalCi = ciDao.find(ci.getId());
		if(originalCi == null) throw new Exception("没有这个台账信息");
		if(originalCi.getReviewStatus().equals("02") ||originalCi.getReviewStatus().equals("03"))
			updateCiByAuditTask(originalCi , ci, at);
		else
			 throw new Exception("此台账的审核状态不允许修改");
	}
	
	private void updateCiByAuditTask(Ci originalCi,Ci ci, AuditTask at) throws Exception {
		//originalCi.setReviewStatus("05");		//改状态为“审核中” --修改后并不改变状态
		//ciDao.save(originalCi);
		//Date date = new Date();
		//同时在变更表中填写记录

		//根据类型获得要显示的列
		List<Property> fieldsSet = getParpertiesByCode(ci.getCategoryCode());
		
		String propertiesStr="", propertyNames = "";
		Map<String, Object> mapNewValue = new HashMap<>();
		Map<String, Object> mapOldValue = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String,String> newPropertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
		@SuppressWarnings("unchecked")
		Map<String,String> oldPropertymap = mapper.readValue(originalCi.getPropertiesData(), Map.class);
		for(Property p:fieldsSet) {
			String propertyId = p.getPropertyId();
			//对比值
			if(propertyId.indexOf("CMS_FIELD_")==0 ) {
				if(p.isNonTransient()) {
					if(p.getPropertyType().equals("string")) {
						String oldValue = (String)Common.getFieldValueByName(originalCi, p.getPropertyConstraint());
						String newValue = (String)Common.getFieldValueByName(ci, p.getPropertyConstraint());
						//判断为null
						if(oldValue==null && newValue!=null) {
							propertyNames+=p.getPropertyName()+",";
							propertiesStr+=propertyId+",";
							mapNewValue.put(p.getPropertyId(), newValue);
							mapOldValue.put(p.getPropertyId(), "");
						} else if(oldValue!=null && newValue==null) {
							propertyNames+=p.getPropertyName()+",";
							propertiesStr+=propertyId+",";
							mapNewValue.put(p.getPropertyId(), "");
							mapOldValue.put(p.getPropertyId(), oldValue);
						} else if(!oldValue.equals(newValue)) {
							propertyNames+=p.getPropertyName()+",";
							propertiesStr+=propertyId+",";
							mapNewValue.put(p.getPropertyId(), newValue);
							mapOldValue.put(p.getPropertyId(), oldValue);
						}
					} else if(p.getPropertyType().equals("date") || p.getPropertyType().equals("sqldate")) {
						Date oldValue = (Date)Common.getFieldValueByName(originalCi, p.getPropertyConstraint());
						Date newValue = (Date)Common.getFieldValueByName(ci, p.getPropertyConstraint());
						//判断为null
						if(oldValue==null && newValue!=null) {
							propertyNames+=p.getPropertyName()+",";
							propertiesStr+=propertyId+",";
							mapNewValue.put(p.getPropertyId(), newValue);
							mapOldValue.put(p.getPropertyId(), null);
						} else if(oldValue!=null && newValue==null) {
							propertyNames+=p.getPropertyName()+",";
							propertiesStr+=propertyId+",";
							mapNewValue.put(p.getPropertyId(), null);
							mapOldValue.put(p.getPropertyId(), oldValue);
						} else if(oldValue.getTime()!=newValue.getTime()) {
							propertyNames+=p.getPropertyName()+",";
							propertiesStr+=propertyId+",";
							mapNewValue.put(p.getPropertyId(), newValue);
							mapOldValue.put(p.getPropertyId(), oldValue);
						}
					}else {
						Object oldValue = Common.getFieldValueByName(originalCi, p.getPropertyConstraint());
						Object newValue = Common.getFieldValueByName(ci, p.getPropertyConstraint());
						//判断为null
						if(oldValue==null && newValue!=null) {
							propertyNames+=p.getPropertyName()+",";
							propertiesStr+=propertyId+",";
							mapNewValue.put(p.getPropertyId(), newValue);
							mapOldValue.put(p.getPropertyId(), null);
						} else if(oldValue!=null && newValue==null) {
							propertyNames+=p.getPropertyName()+",";
							propertiesStr+=propertyId+",";
							mapNewValue.put(p.getPropertyId(), null);
							mapOldValue.put(p.getPropertyId(), oldValue);
						} else if(oldValue!=newValue) {
							propertyNames+=p.getPropertyName()+",";
							propertiesStr+=propertyId+",";
							mapNewValue.put(propertyId, newValue);
							mapOldValue.put(propertyId, oldValue);
						}
					}
				}
			} else {
					String oldValue = oldPropertymap.get(propertyId);
					String newValue = newPropertymap.get(propertyId);
					//判断为null
					if(oldValue==null && newValue!=null) {
						propertyNames+=p.getPropertyName()+",";
						propertiesStr+=propertyId+",";
						mapNewValue.put(p.getPropertyId(), newValue);
						mapOldValue.put(p.getPropertyId(), "");
					} else if(oldValue!=null && newValue==null) {
						propertyNames+=p.getPropertyName()+",";
						propertiesStr+=propertyId+",";
						mapNewValue.put(p.getPropertyId(), "");
						mapOldValue.put(p.getPropertyId(), oldValue);
					} else if(!oldValue.equals(newValue)) {
						propertyNames+=p.getPropertyName()+",";
						propertiesStr+=propertyId+",";
						mapNewValue.put(propertyId, newValue);
						mapOldValue.put(propertyId, oldValue);
					}
			}
			
		}
		
		ChangeItem item = changeitemDao.getByCiIdAndAuditTask(ci.getId(), at);
		if(item == null) {//证明是修改已存在的
			item = new ChangeItem();
			item.setCiId(originalCi.getId());
			item.setChangeId(at.getId());
			
			
			item.setCreatedTime(ci.getLastUpdateTime());
			item.setNewValue(mapper.writeValueAsString(mapNewValue));
			item.setOldValue(mapper.writeValueAsString(mapOldValue));
			item.setProperties(propertiesStr.substring(0, propertiesStr.length()-1));
			item.setPropertiesName(propertyNames.substring(0, propertyNames.length()-1));
			item.setType(ChangeitemType.audit);
			item.setActionType(ChangeitemActionType.update);
			item.setCreatedTime(new Date());
			item.setPass(false);
			changeitemDao.save(item);
		} else if(item.getActionType() ==null || item.getActionType() == ChangeitemActionType.update) {
			item.setCreatedTime(ci.getLastUpdateTime());
			item.setNewValue(mapper.writeValueAsString(mapNewValue));
			item.setOldValue(mapper.writeValueAsString(mapOldValue));
			item.setProperties(propertiesStr.substring(0, propertiesStr.length()-1));
			item.setPropertiesName(propertyNames.substring(0, propertyNames.length()-1));
			item.setUpdatedTime(new Date());
			item.setActionType(ChangeitemActionType.update);
			item.setPass(false);
		} else if(item.getActionType() == ChangeitemActionType.insert) {
			
			//做替换
			if(!mapOldValue.isEmpty()) {
				String strTemp = item.getNewValue();
				for(String s:mapNewValue.keySet()){
		            String oldStr = "\""+s+"\":\""+mapOldValue.get(s)+"\"";
		            String newStr = "\""+s+"\":\""+mapNewValue.get(s)+"\"";
		            strTemp = strTemp.replace(oldStr, newStr);
				}
				//String oldStr = mapper.writeValueAsString(mapOldValue), newStr=mapper.writeValueAsString(mapNewValue);
				item.setUpdatedTime(new Date());
				item.setPass(false);
				item.setNewValue(strTemp);
				//保存新的ci
				ciDao.merge(ci);
			}
			
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String[]> getContrastByCi(long ciid, long atid) throws Exception {
		// TODO Auto-generated method stub
		Ci ci = ciDao.find(ciid);
		if(ci==null) throw new Exception("没有这个设备");
		
		AuditTask at = auditTaskDao.find(atid);
		if(at==null) throw new Exception("没有这个审核任务");
		
		//查找对比数据
		Search search = new Search(ChangeItem.class);
		search.addFilterEqual("changeId", at.getId());
		search.addFilterEqual("ciId", ci.getId());
		search.addFilterEqual("type", ChangeitemType.audit);
		
		ChangeItem changeItem = changeitemDao.searchUnique(search);
		
		List<Property> fieldsSet = getParpertiesByCode(ci.getCategoryCode());
		
		Map<String, String[]> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String,String> newPropertymap = new HashMap<>();
		Map<String,String> oldPropertiesmap = new HashMap<>();
		
		//新建情况
		if(changeItem.getOldValue()!=null) {
			oldPropertiesmap = mapper.readValue(changeItem.getOldValue(), Map.class);
		}
		//删除情况
		if(changeItem.getNewValue()!=null) {
			newPropertymap = mapper.readValue(changeItem.getNewValue(), Map.class);
		}
		
		//对比当时的记录
		String propertyNames = changeItem.getPropertiesName();
		String[] arrayNames = propertyNames.split(",");
		Arrays.sort(arrayNames);
		ChangeitemActionType type = changeItem.getActionType();
		
		for(Property p:fieldsSet) {
			if(p.isNonTransient()) {
			String[] str = {"",""};
			String propertyId = p.getPropertyId();
			//对比值
			if(Arrays.binarySearch(arrayNames,p.getPropertyName())>=0){
				//如果是状态（使用情况），审核状态，部门，密级,责任人
				switch(propertyId) {
					case "CMS_FIELD_REVIEWSTATUS"://审核状态
						break;
					case "CMS_FIELD_DEPARTMENTINUSE"://使用部门
						switch(type) {
						case insert:
							str[0] = "";
							str[1] = groupDao.find(Long.valueOf(newPropertymap.get(propertyId))).getGroupName();
							break;
						case update:
							str[0] = groupDao.find(Long.valueOf(oldPropertiesmap.get(propertyId))).getGroupName();
							str[1] = groupDao.find(Long.valueOf(newPropertymap.get(propertyId))).getGroupName();
							break;
						case delete:
							str[0] = groupDao.find(Long.valueOf(oldPropertiesmap.get(propertyId))).getGroupName();
							str[1] = "";
							break;
						default:
							break;
						}
						
						map.put(p.getPropertyName(), str);
						break;
					case "CMS_FIELD_USERINMAINTENANCE"://使用人
						switch(type) {
						case insert:
							str[0] = "";
							str[1] = userDao.getUserByUserName(newPropertymap.get(propertyId)).getName();
							break;
						case update:
							str[0] = userDao.getUserByUserName(oldPropertiesmap.get(propertyId)).getName();
							str[1] = userDao.getUserByUserName(newPropertymap.get(propertyId)).getName();
							break;
						case delete:
							str[0] = userDao.getUserByUserName(oldPropertiesmap.get(propertyId)).getName();
							str[1] = "";
							break;
						default:
							break;
						}
						
						map.put(p.getPropertyName(), str);
						break;
					case "CMS_FIELD_STATUS":	//使用情况
						switch(type) {
						case insert:
							str[0] = "";
							str[1] = sysCodeDao.getCode(newPropertymap.get(propertyId), "CI_STATUS").getCodeName();
							break;
						case update:
							str[0] = sysCodeDao.getCode(oldPropertiesmap.get(propertyId), "CI_STATUS").getCodeName();
							str[1] = sysCodeDao.getCode(newPropertymap.get(propertyId), "CI_STATUS").getCodeName();
							break;
						case delete:
							str[0] = sysCodeDao.getCode(oldPropertiesmap.get(propertyId), "CI_STATUS").getCodeName();
							str[1] = "";
							break;
						default:
							break;
						}
						
						map.put(p.getPropertyName(), str);
						break;
					case "CMS_FIELD_SECURITYLEVEL":	//密级
						switch(type) {
						case insert:
							str[0] = "";
							str[1] = sysCodeDao.getCode(newPropertymap.get(propertyId), "CI_SECURITY_LEVEL").getCodeName();
							break;
						case update:
							str[0] = sysCodeDao.getCode(oldPropertiesmap.get(propertyId), "CI_SECURITY_LEVEL").getCodeName();
							str[1] = sysCodeDao.getCode(newPropertymap.get(propertyId), "CI_SECURITY_LEVEL").getCodeName();
							break;
						case delete:
							str[0] = sysCodeDao.getCode(oldPropertiesmap.get(propertyId), "CI_SECURITY_LEVEL").getCodeName();
							str[1] = "";
							break;
						default:
							break;
						}
						
						map.put(p.getPropertyName(), str);
						break;
					default:
						switch(type) {
						case insert:
							str[0] = "";
							str[1] = newPropertymap.get(propertyId);
							break;
						case update:
							str[0] = oldPropertiesmap.get(propertyId);
							str[1] = newPropertymap.get(propertyId);
							break;
						case delete:
							str[0] = oldPropertiesmap.get(propertyId);
							str[1] = "";
							break;
						default:
							break;
						}
						
						map.put(p.getPropertyName(), str);
						break;
				}
				
			} else {
				map.put(p.getPropertyName(), str);
			}
			
//			
//			if(propertyId.indexOf("CMS_FIELD_")==0) {
//				if(p.isNonTransient()){
//					String fieldName = p.getPropertyConstraint();
//					if(p.getPropertyType().equals("string")) {
//						//如果是状态（使用情况），审核状态，部门，密级,责任人
//						switch(propertyId) {
//							case "CMS_FIELD_REVIEWSTATUS"://审核状态
//								str[0] = (String)Common.getFieldValueByName(ci, fieldName+="Name");
//								str[1] = sysCodeDao.getCode(newPropertymap.get(propertyId), "CI_REVIEW_STATUS").getCodeName();
//								break;
//	    					case "CMS_FIELD_DEPARTMENTINUSE"://使用部门
//	    						str[0] = (String)Common.getFieldValueByName(ci, fieldName+="Name");
//	    						str[1] = groupDao.find(Long.valueOf(newPropertymap.get(propertyId))).getGroupName();
//	    						break;
//	    					case "CMS_FIELD_USERINMAINTENANCE"://使用人
//	    						str[0] = (String)Common.getFieldValueByName(ci, fieldName+="Name");
//	    						str[1]=userDao.getUserByUserName(newPropertymap.get(propertyId)).getName();
//	    						break;
//	    					case "CMS_FIELD_STATUS":	//使用情况
//	    						str[0] = (String)Common.getFieldValueByName(ci, fieldName+="Name");
//	    						str[1] = sysCodeDao.getCode(newPropertymap.get(propertyId), "CI_STATUS").getCodeName();
//	    						break;
//	    					case "CMS_FIELD_SECURITYLEVEL":	//密级
//	    						str[0] = (String)Common.getFieldValueByName(ci, fieldName+="Name");
//	    						str[1] = sysCodeDao.getCode(newPropertymap.get(propertyId), "CI_SECURITY_LEVEL").getCodeName();
//	    						break;
//	    					default:
//	    						str[0] = (String)Common.getFieldValueByName(ci, fieldName);
//	    						str[1] = newPropertymap.get(propertyId);
//	    						break;
//	    				}
//						
//					} else if(p.getPropertyType().equals("date")) {
//						Date date = (Date)Common.getFieldValueByName(ci, fieldName);
//						str[0] = date.toString();
//						str[1] = newPropertymap.get(propertyId);
//					} else {
//						Object obj = Common.getFieldValueByName(ci, fieldName);
//						str[0] = obj.toString();
//						str[1] = newPropertymap.get(propertyId);
//					}
//					map.put(p.getPropertyName(), str);
//				}
//			} else {
//				str[0] = oldPropertiesmap.get(propertyId);
//				str[1] = newPropertymap.get(propertyId);
//				map.put(p.getPropertyName(), str);
//			}
//			
			}
		}
		
		return map;
	}
	
	//根据类型获得要显示的列
	private List<Property> getParpertiesByCode(String code) {
		Search search = new Search(Account.class);
		search.addFilterEqual("category", code);
		
		Account account = accountDao.searchUnique(search);
		
		String fields="", properties = "";
		//获取属性
		switch(account.getType()) {
			case infoSys:
				fields = PropertyFileUtil.getStringValue("account.type.infosys.filed");
				properties = PropertyFileUtil.getStringValue("account.type.infosys.property");
				break;
			case infoEquipment:
				fields = PropertyFileUtil.getStringValue("account.type.infoequipment.filed");
				properties = PropertyFileUtil.getStringValue("account.type.infoequipment.property");
				break;
			case storage:
				fields = PropertyFileUtil.getStringValue("account.type.storage.filed");
				properties = PropertyFileUtil.getStringValue("account.type.storage.property");
				break;
			case security:
				fields = PropertyFileUtil.getStringValue("account.type.security.filed");
				properties = PropertyFileUtil.getStringValue("account.type.security.property");
				break;
			case appSys:
				fields = PropertyFileUtil.getStringValue("account.type.appsys.filed");
				properties = PropertyFileUtil.getStringValue("account.type.appsys.property");
				break;
		}
		
		return propertyDao.getByPropertyIds(fields+","+properties+","+account.getProperties()).getResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void passCi(Ci ci ,AuditTask at, boolean flag) throws Exception {
		// TODO Auto-generated method stub
		//查找对比数据
		Search search = new Search(ChangeItem.class);
		search.addFilterEqual("changeId", at.getId());
		search.addFilterEqual("ciId", ci.getId());
		search.addFilterEqual("type", ChangeitemType.audit);
		
		ChangeItem changeItem = changeitemDao.searchUnique(search);
		
		if(flag) {			//审核通过
			ObjectMapper mapper = new ObjectMapper();
			List<Property> propertylist = propertyDao.getFields();
			@SuppressWarnings("unchecked")
			Map<String,String> newPropertymap = mapper.readValue(changeItem.getNewValue(), Map.class);
			@SuppressWarnings("unchecked")
			Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
			
			String ps[] = changeItem.getProperties().split(",");
			Map<String,Property> fieldmap = new HashMap<String,Property>();
			for(Property p:propertylist)
				fieldmap.put(p.getPropertyId(), p);
			for(String s:ps)
			{
				if(s.indexOf("CMS_FIELD_")==0)
				{
					// 更新字段
					Common.setFieldValueByName(ci, fieldmap.get(s).getPropertyConstraint(), newPropertymap.get(s));
				}
				else
				{
					// 更新参数
					propertymap.put(s, newPropertymap.get(s));
				}
			}
			ci.setPropertiesData( mapper.writeValueAsString(propertymap) );
			ci.setReviewStatus("04");
			
			ciDao.save(ci);
		} else {
			//删除修改
			if(changeItem.getOldValue()==null) {		//新建的，需要删除ci
				ciDao.remove(ci);
			} else {
				ci.setReviewStatus("03");
				ciDao.save(ci);
			}
			
			changeitemDao.remove(changeItem);
			
		}
	}

	@Override
	public void exportXls(List<Group> groups, List<Category> categorys, java.io.OutputStream out) throws Exception {
		// TODO Auto-generated method stub
		//查找数据
		if(categorys.size()==0)
			throw new Exception("没有选择任何类别，至少选择一项类别");
		
		if(groups.size() == 0) throw new Exception("没有选择任何部门，至少选择一个部门");
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String[]> headerMap = new HashMap<>(); 
		Map<String, List<Object[]>> datasetMap = new HashMap<>();
		for(Category category : categorys) {
			//获取表头
			List<Property> propertyList =  getParpertiesByCode(category.getCategoryCode());
			int headerNum = propertyList.size();
			String[] headers = new String[headerNum];
			
			for(int i=0 ; i<headerNum; i++) {
				headers[i] = propertyList.get(i).getPropertyName();
			}
			List<Object[]> dataset = new ArrayList<Object[]>();	
			for(Group group : groups) {
				List<Ci> list = ciDao.getListByCodeAndGroup(category, group, "01");		//已审核的
				for(Ci ci : list) {
					@SuppressWarnings("unchecked")
					Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
					Object[] objs = new Object[headerNum];
					//获取要导出的列
					for(int i=0; i<headerNum; i++) {
						Property p = propertyList.get(i);
						
						if(p.getPropertyId().indexOf("CMS_FIELD_")==0) {
							if(StringUtils.contains(p.getPropertyId(),"CMS_FIELD_DEPARTMENTINUSE"))			//使用部门
								objs[i] = ci.getDepartmentInUseName();
							else if(StringUtils.contains(p.getPropertyId(),"CMS_FIELD_USERINMAINTENANCE"))		//使用人
								objs[i] = ci.getUserInMaintenanceName();
							else if(StringUtils.contains(p.getPropertyId(),"CMS_FIELD_SERVICESTARTTIME")){
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");    
								objs[i] = format.format(ci.getServiceStartTime());
							}else if(StringUtils.contains(p.getPropertyId(),"CMS_FIELD_STATUS"))			//使用情况
								objs[i] = ci.getStatusName();
							else if(StringUtils.contains(p.getPropertyId(),"CMS_FIELD_SECURITYLEVEL"))			//密级
								objs[i] = ci.getSecurityLevelName();
							else
								objs[i] = Common.getFieldValueByName(ci, p.getPropertyConstraint());
						} else {
							objs[i] = propertymap.get(p.getPropertyId());
						}
					}
					dataset.add(objs);
				}
			}
			
			headerMap.put(category.getCategoryName(), headers);
			datasetMap.put(category.getCategoryName(), dataset);
		}
		
		//Common.exportExcel( headerMap, datasetMap, new FileOutputStream("E://a.xls"));
		Common.exportExcel( headerMap, datasetMap, out);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void delByAuditTask(Ci ci, AuditTask at) throws Exception {
		// TODO Auto-generated method stub
		ChangeItem item = changeitemDao.getByCiIdAndAuditTask(ci.getId(), at);
		if(item == null) {
			//添加删除标记
			item = new ChangeItem();
			
			List<Property> fieldsSet = getParpertiesByCode(ci.getCategoryCode());
			String propertiesStr="", propertyNames = "";
			Map<String, Object> mapOldValue = new HashMap<>();
			ObjectMapper mapper = new ObjectMapper();
			Ci originalCi = ciDao.find(ci.getId());
			@SuppressWarnings("unchecked")
			Map<String,String> oldPropertymap = mapper.readValue(originalCi.getPropertiesData(), Map.class);
			for(Property p:fieldsSet) {
				propertyNames+=p.getPropertyName()+",";
				propertiesStr+=p.getPropertyId()+",";
				//对比值
				if(p.getPropertyId().indexOf("CMS_FIELD_")==0 ) {
					if(p.getPropertyType().equals("string")) {
						mapOldValue.put(p.getPropertyId(), (String)Common.getFieldValueByName(originalCi, p.getPropertyConstraint()));
					} else if(p.getPropertyType().equals("date")) {
						mapOldValue.put(p.getPropertyId(), (Date)Common.getFieldValueByName(originalCi, p.getPropertyConstraint()));
					}else {
						Object oldValue = Common.getFieldValueByName(originalCi, p.getPropertyConstraint());
						mapOldValue.put(p.getPropertyId(), oldValue);
					}
				} else {
					String oldValue = oldPropertymap.get(p.getPropertyId());
					mapOldValue.put(p.getPropertyId(), oldValue);
				}
				
			}
			
			item.setNewValue(null);
			item.setOldValue(mapper.writeValueAsString(mapOldValue));
			item.setActionType(ChangeitemActionType.delete);
			item.setPropertiesName(propertyNames.substring(0, propertyNames.length()-1));
			item.setProperties(propertiesStr.substring(0, propertiesStr.length()-1));
			item.setChangeId(at.getId());
			item.setCiId(ci.getId());
			item.setType(ChangeitemType.audit);
			item.setCreatedTime(new Date());
			item.setPass(false);
			
			changeitemDao.save(item);
		} else {
			if(item.getActionType()==null) {

				List<Property> fieldsSet = getParpertiesByCode(ci.getCategoryCode());
				String propertiesStr="", propertyNames = "";
				Map<String, Object> mapOldValue = new HashMap<>();
				ObjectMapper mapper = new ObjectMapper();
				Ci originalCi = ciDao.find(ci.getId());
				@SuppressWarnings("unchecked")
				Map<String,String> oldPropertymap = mapper.readValue(originalCi.getPropertiesData(), Map.class);
				for(Property p:fieldsSet) {
					propertyNames+=p.getPropertyName()+",";
					propertiesStr+=p.getPropertyId()+",";
					//对比值
					if(p.getPropertyId().indexOf("CMS_FIELD_")==0 ) {
						if(p.getPropertyType().equals("string")) {
							mapOldValue.put(p.getPropertyId(), (String)Common.getFieldValueByName(originalCi, p.getPropertyConstraint()));
						} else if(p.getPropertyType().equals("date")) {
							mapOldValue.put(p.getPropertyId(), (Date)Common.getFieldValueByName(originalCi, p.getPropertyConstraint()));
						}else {
							Object oldValue = Common.getFieldValueByName(originalCi, p.getPropertyConstraint());
							mapOldValue.put(p.getPropertyId(), oldValue);
						}
					} else {
						String oldValue = oldPropertymap.get(p.getPropertyId());
						mapOldValue.put(p.getPropertyId(), oldValue);
					}
					
				}
				
				item.setNewValue(null);
				item.setOldValue(mapper.writeValueAsString(mapOldValue));
				item.setActionType(ChangeitemActionType.delete);
				item.setPropertiesName(propertyNames.substring(0, propertyNames.length()-1));
				item.setProperties(propertiesStr.substring(0, propertiesStr.length()-1));
				item.setUpdatedTime(new Date());
				item.setPass(false);
			} else {
				switch(item.getActionType() ) {
					//直接删
					case insert:
						ciDao.removeById(ci.getId());
						changeitemDao.removeById(item.getId());
						break;
					case update:
						//把数据保存到oldvalue中
						List<Property> fieldsSet = getParpertiesByCode(ci.getCategoryCode());
						String propertiesStr="", propertyNames = "";
						Map<String, Object> mapOldValue = new HashMap<>();
						ObjectMapper mapper = new ObjectMapper();
						Ci originalCi = ciDao.find(ci.getId());
						@SuppressWarnings("unchecked")
						Map<String,String> oldPropertymap = mapper.readValue(originalCi.getPropertiesData(), Map.class);
						for(Property p:fieldsSet) {
							propertyNames+=p.getPropertyName()+",";
							propertiesStr+=p.getPropertyId()+",";
							//对比值
							if(p.getPropertyId().indexOf("CMS_FIELD_")==0 ) {
								if(p.getPropertyType().equals("string")) {
									mapOldValue.put(p.getPropertyId(), (String)Common.getFieldValueByName(originalCi, p.getPropertyConstraint()));
								} else if(p.getPropertyType().equals("date")) {
									mapOldValue.put(p.getPropertyId(), (Date)Common.getFieldValueByName(originalCi, p.getPropertyConstraint()));
								}else {
									Object oldValue = Common.getFieldValueByName(originalCi, p.getPropertyConstraint());
									mapOldValue.put(p.getPropertyId(), oldValue);
								}
							} else {
								String oldValue = oldPropertymap.get(p.getPropertyId());
								mapOldValue.put(p.getPropertyId(), oldValue);
							}
							
						}
						
						item.setNewValue(null);
						item.setOldValue(mapper.writeValueAsString(mapOldValue));
						item.setPropertiesName(propertyNames.substring(0, propertyNames.length()-1));
						item.setProperties(propertiesStr.substring(0, propertiesStr.length()-1));
						item.setActionType(ChangeitemActionType.delete);
						item.setUpdatedTime(new Date());
						item.setPass(false);
						break;
					default:
						break;
				}
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void recoverByAuditTask(Ci ci, AuditTask at) throws Exception {
		// TODO Auto-generated method stub
		ChangeItem item = changeitemDao.getByCiIdAndAuditTask(ci.getId(), at);
		if(item == null) {
			throw new Exception("无法恢复");
		} else {
			if(item.getActionType()== ChangeitemActionType.delete) {
				//changeitemDao.removeById(item.getId());
				item.setActionType(null);
				item.setUpdatedTime(new Date());
				item.setNewValue(null);
				item.setOldValue(null);
				item.setPass(false);
				item.setProperties(null);
				item.setPropertiesName(null);
			} else
				throw new Exception("此状态下无法执行恢复操作");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void passCis(AuditTask at, Long[] ids, boolean decide) {
		// TODO Auto-generated method stub
		for(Ci ci : ciDao.find(ids)){
			if(ci.getReviewStatus().equals("05")) {
				ChangeItem item = changeitemDao.getByCiIdAndAuditTask(ci.getId(), at);
				if(decide)
					ci.setReviewStatus("04");//审核已通过
				else
					ci.setReviewStatus("03");
				
				if(item!=null) {
					item.setPass(true);
				}
			}
		}
	}
}
