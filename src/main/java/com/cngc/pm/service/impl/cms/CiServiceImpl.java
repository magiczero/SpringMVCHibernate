package com.cngc.pm.service.impl.cms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.StatsDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.dao.cms.CategoryDAO;
import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.service.cms.CiService;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class CiServiceImpl implements CiService{
	
	@Autowired
	private CiDAO ciDao;
	@Autowired
	private StatsDAO statsDao;
	@Autowired
	private CategoryDAO categoryDao;
	@Autowired
	private UserDAO userDao;

	@Override
	@Transactional
	public void save(Ci ci){
		ciDao.save(ci);
	}
	
	@Override
	@Transactional
	public boolean delById(Long id)
	{
		ciDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public Ci getById(Long id){
		return ciDao.find(id);
	}
	
	@Override
	@Transactional
	public List<Ci> getAll()
	{
		return ciDao.findAll();
	}
	
	@Override
	@Transactional
	public List<Ci> getByRelation(String relationId,Long primaryId)
	{
		return ciDao.getByRelation(relationId, primaryId);
	}
	@Override
	@Transactional
	public SearchResult<Ci> getByCategoryCode(String code)
	{
		Search search = new Search();
		search.addFilterLike("categoryCode", code+"%");
		return ciDao.searchAndCount(search);
	}
	@Override
	@Transactional
	public boolean deleteRelation(Long primaryId,Long secondaryId,String relationId)
	{
		return ciDao.deleteRelation(primaryId, secondaryId, relationId);
	}
	@Override
	@Transactional
	public boolean saveRelation(Long primaryId,Long secondaryId,String relationId)
	{
		return ciDao.saveRelation(primaryId, secondaryId, relationId);
	}
	@Override
	@Transactional
	public SearchResult<Ci> getByIds(List<Long> ids)
	{
		return ciDao.getByIds(ids);
	}
	@Override
	@Transactional
	public Map<String,Object> getStats(String column,String row,String status)
	{
		return statsDao.getStats("CMS", column, row, null, null, status);
	}
	@Override
	@Transactional
	public SearchResult<Ci> getByUser(String user)
	{
		return ciDao.getByUser(user);
	}

	@Override
	@Transactional
	public void importData(Set<Attachment> setAttach) throws IOException, ParseException {
		// TODO Auto-generated method stub
		//首先加载properties文件
		//解析excel文件放到CI中并验证，出错则抛出Exception
		
		List<Ci> ciList = new ArrayList<>();
		
		for(Attachment attach : setAttach) {
			String filePath = attach.getPath()+File.separator+attach.getNewFilename();
			
			boolean isE2007 = false;		//判断是否是excel2007格式
			
			if(attach.getNewFilename().endsWith("xlsx"))
				isE2007 = true;
			
			InputStream input = new FileInputStream(filePath);
			Workbook wb  = null;  
	            //根据文件格式(2003或者2007)来初始化  
	        if(isE2007)  
	                wb = new XSSFWorkbook(input);  
	        else  
	                wb = new HSSFWorkbook(input); 
	        
	        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {				//每一行是一个CI
            	Sheet sheet = wb.getSheetAt(numSheet);  
            	
            	//根据sheetname获取分类代码
            	String typeName = sheet.getSheetName();
            	
            	Search search = new Search();
        		search.addFilterEqual("categoryName", typeName);
        		
        		Category category = categoryDao.searchUnique(search);
            	
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
	                	
            			ci.setCategoryCode(category.getCategoryCode());				//设置类型
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
						ci.setSecurityLevel("12");
						break;
					case "变更中":
						ci.setSecurityLevel("05");
						break;
					case "运行中":
						ci.setSecurityLevel("07");
						break;
					case "入库":
						ci.setSecurityLevel("03");
						break;
					case "故障":
						ci.setSecurityLevel("09");
						break;
					case "报废":
						ci.setSecurityLevel("11");
						break;
					case "闲置":
						ci.setSecurityLevel("14");
						break;
					default:
						ci.setSecurityLevel("07");
						break;
				}
				break;
			case "departmentInUse":				//所属部门
				ci.setDepartmentInUse(propertyValue);
				break;
			case "location":						//物理位置
				ci.setLocation(propertyValue);
				break;
			case "userInMaintenance":			//责任人  需要转换 
				ci.setUserInMaintenance(userDao.getUserByName(propertyValue.trim()).getUsername());
				break;
			case "ciManager":				//配置管理员，应该转换
				ci.setCiManager(propertyValue);
				break;
			case "purpose":				//用途
				ci.setPurpose(propertyValue);
				break;
			case "serviceStartTime":										//启用时间
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				ci.setServiceStartTime(format.parse(propertyValue));
				break;
			case "serviceEndTime":										//启用时间
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				ci.setServiceStartTime(format1.parse(propertyValue));
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
}
