package com.cngc.pm.controller.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.cms.Account;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.cms.AccountService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.pm.service.cms.PropertyService;
import com.cngc.utils.Common;

@Controller
@RequestMapping(value="/stats/account")
public class AccountController {
	@Resource
	private CiService ciService;
	@Resource
	private PropertyService propertyService;
	@Resource
	private AccountService accountService;
	@Resource
	private UserUtil userUtil;

	/**添加台账信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String account(Model model,HttpServletRequest request){

		List<Account> accounts = accountService.getAll();
		
		model.addAttribute("accounts", accounts);
		model.addAttribute("fields", propertyService.getFields());
		
		return "stats/account-add";
	}
	/**保存台账信息
	 * @param category
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(HttpServletRequest request,Authentication authentication) throws Exception{
		
		String code = request.getParameter("category");
		String fields = request.getParameter("fm_fields");
		String properties = request.getParameter("fm_properties");
		String name = request.getParameter("fm_name");
		
		Account account = new Account();
		account.setName(name);
		account.setCategory(code);
		account.setFields(fields);
		account.setProperties(properties);
		account.setCreatedTime(new Date());
		account.setCreatedUser(userUtil.getUsernameByAuth(authentication));
		
		accountService.save(account);
		
		return "redirect:list";
	}
	/**列表信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", accountService.getAll());
		return "stats/account-list";
	}
	/**删除台账
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,Model model){
			
		accountService.delById(id);
		
		return "redirect:/stats/account/list";
	}
	/**定义台账信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setting",method = RequestMethod.GET)
	public String setting(Model model,HttpServletRequest request){

		model.addAttribute("fields", propertyService.getFields());
		
		return "stats/account-setting";
	}
	/**生成自定义台账
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/generate",method = RequestMethod.POST)
	public String accountList(Model model,HttpServletRequest request){

		String code = request.getParameter("category");
		String fields = request.getParameter("fm_fields");
		String properties = request.getParameter("fm_properties");
		
		List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
		List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
		
		//生成column
		List<String> columns = new LinkedList<String>();
		for(Property p:fieldsSet)
			columns.add(p.getPropertyName());
		for(Property p:propertiesSet)
			columns.add(p.getPropertyName());
		model.addAttribute("columns", columns);
		//生成row
		ObjectMapper mapper = new ObjectMapper();
		List<Object> rows = new LinkedList<Object>();
		List<Ci> cilist = ciService.getByCategoryCode(code).getResult();
		for(Ci ci:cilist)
		{
			List<String> row = new LinkedList<String>();
			try {
				Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
				Object obj = null;
				for(Property p:fieldsSet)
				{
					obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
					if(obj==null)
						row.add("-");
					else
						row.add(obj.toString());
				}
				for(Property p:propertiesSet)
					row.add(propertymap.get(p.getPropertyId()));
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rows.add(row);
		}
		model.addAttribute("rows", rows);
		
		
		return "stats/account-generate";
	}
	
	@RequestMapping(value="/view/{ciid}/{accountid}",method = RequestMethod.GET)
	public String viewAccount(@PathVariable("ciid") Long ciId,@PathVariable("accountid") Long accountId,Model model) throws JsonParseException, JsonMappingException, IOException {
		//根据id获取ci
		Ci ci = ciService.getById(ciId);
		Account account = accountService.getById(accountId);
		//获取要显示的列
		String fields = account.getFields();
		String properties = account.getProperties();
		
		Map<String, String> ciMap = new LinkedHashMap<String, String>();
		
		List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
		List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
		
		for(Property p:fieldsSet) {
			Object obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
			ciMap.put(p.getPropertyName(), obj.toString());
		}
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
		for(Property p:propertiesSet) {
			ciMap.put(p.getPropertyName(), propertymap.get(p.getPropertyId()));
		}
		
//		int mapSize = ciMap.size();
//		
//		String body = "";
//
//		Object s[] = ciMap.keySet().toArray();
//		for(int i=0; i<mapSize; i=i+2) {
//			body += "<div class=\"row-form clearfix\">";
//			body += "<div class=\"col-md-2\">"+s[i]+"：</div><div class=\"col-md-4\">"+ciMap.get(s[i])+"</div>";
//			body += "<div class=\"col-md-2\">"+s[i+1]+"：</div><div class=\"col-md-4\">"+ciMap.get(s[i+1])+"</div>";
//			body += "</div>";
//		}
//		
//		if(mapSize%2 !=0) {
//			body += "<div class=\"row-form clearfix\">";
//			body += "<div class=\"col-md-2\">"+s[mapSize-1]+":</div><div class=\"col-md-4\">"+ciMap.get(s[mapSize-1])+"</div>";
//			body += "<div class=\"col-md-2\"></div><div class=\"col-md-4\"></div>";
//			body += "</div>";
//		}
		
		List<Account> accounts = accountService.getAll();
		
		model.addAttribute("accounts", accounts);
//		model.addAttribute("body", body);
		model.addAttribute("ciMap", ciMap);
		model.addAttribute("accountName", account.getName());
		
		return "stats/account-view";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/get/{id}",method = RequestMethod.GET)
	public String getAccount(@PathVariable("id") Long id,HttpServletRequest request,Model model){

		List<Account> accounts = accountService.getAll();
		Account account = accountService.getById(id);
		String code = account.getCategory();
//		String fields = account.getFields();
//		String properties = account.getProperties();
		String fields = "", properties = "", comma=",";
		String views = account.getViews();
		String[] viewproperties = views.split(comma);
		
		for(String vp : viewproperties) {
			if(StringUtils.contains(vp, "FIELD")) {
				fields += vp+comma;
			} else if(StringUtils.contains(vp, "PROPERTY")) {
				properties += vp+comma;
			}
		}
		
		fields = fields.substring(0, fields.length()-1);
		properties = properties.substring(0, properties.length()-1);
		
		List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
		List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
		
		//生成column
		List<String> columns = new LinkedList<String>();
		for(Property p:fieldsSet)
			columns.add(p.getPropertyName());
		for(Property p:propertiesSet)
			columns.add(p.getPropertyName());
		model.addAttribute("columns", columns);
		//生成row
		ObjectMapper mapper = new ObjectMapper();
		List<Object> rows = new LinkedList<Object>();
		List<Ci> cilist = ciService.getByCategoryCode(code).getResult();
		for(Ci ci:cilist)
		{
			List<String> row = new LinkedList<String>();
			try {
				//需要判断是否为空ci.getPropertiesData()
				Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
				Object obj;
				for(Property p:fieldsSet)
				{
					obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
					if(obj==null)
						row.add("-");
					else
						row.add(obj.toString());
				}
				for(Property p:propertiesSet)
					row.add(propertymap.get(p.getPropertyId()));
				
				row.add("<a href=\""+request.getContextPath()+"/stats/account/view/"+ci.getId()+"/"+account.getId()+"\">查看</a>");
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rows.add(row);
		}
		model.addAttribute("rows", rows);
		model.addAttribute("accounts", accounts);
		model.addAttribute("id", id);
		
		return "stats/account-get";
	}
	

    /** 
        * 导出Excel 
        * @param model 
        * @param projectId 
        * @param request 
        * @return 
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
        */  
    @RequestMapping(value="/dcExcel",method=RequestMethod.GET)  
    public ModelAndView toDcExcel(ModelMap model, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{  
    	   String accountid = request.getParameter("id");
    	   
    	   Long id = Long.valueOf(accountid);
    	   Account account = accountService.getById(id);
    	   
    	   List<Ci> ciList = ciService.getByCategoryCode(account.getCategory()).getResult();
    	   int rows = ciList.size();
    	   
    	   
    	   //设置表头
    	   int columnsNum = 0;
    	   String fields = account.getFields();
    	   String properties = account.getProperties();
    	   
    	   List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
    	   List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
    	   
    	   String[][] datas = new String[rows+1][fieldsSet.size() + propertiesSet.size()];
    	   
    	   for(Property p:fieldsSet) {
   				datas[0][columnsNum] = p.getPropertyName();
   				columnsNum++;
    	   }
    	   for(Property p:propertiesSet) {
    		   datas[0][columnsNum] = p.getPropertyName();
    		   columnsNum++;
    	   }
    	   
    	   int rowsNum = 1;
    	   ObjectMapper mapper = new ObjectMapper();
    	   //数值行
    	   for(Ci ci : ciList) {
    		   columnsNum = 0;
    		   @SuppressWarnings("unchecked")
			Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
    		   for(Property p:fieldsSet)
				{
					Object obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
					if(obj==null)
						datas[rowsNum][columnsNum] = "-";
					else
						datas[rowsNum][columnsNum] = obj.toString();
					columnsNum++;
				}
				for(Property p:propertiesSet) {
					datas[rowsNum][columnsNum] = propertymap.get(p.getPropertyId());
					columnsNum++;
				}
				rowsNum++;
    	   }
    	   
           ViewExcel viewExcel = new ViewExcel();    
           model.addAttribute("datas", datas);
           model.addAttribute("accountName", account.getName());
           return new ModelAndView(viewExcel, model);   
       }  
	
	class ViewExcel extends AbstractExcelView {

		@Override
		protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			HSSFSheet sheet = workbook.createSheet((String)model.get("accountName"));    
			
			String datas[][] = (String[][]) model.get("datas");
			
			for(int i = 0; i<datas.length; i++) {
				HSSFRow row = sheet.createRow(i);
				
				String datas1[] = datas[i];
				
				for(int j = 0; j < datas1.length; j++) {
					HSSFCell cell = row.createCell(j);
					
					cell.setCellValue(datas1[j]);
				}
			}
			
	        String filename = "台账.xls";//设置下载时客户端Excel的名称     
	        filename = encodeFilename(filename, request);//处理中文文件名  
	        response.setContentType("application/vnd.ms-excel");     
	        response.setHeader("Content-disposition", "attachment;filename=" + filename);     
	        OutputStream ouputStream = response.getOutputStream();     
	        workbook.write(ouputStream);     
	        ouputStream.flush();     
	        ouputStream.close();    
		}
		

	    /**  
	         * 设置下载文件中文件的名称  
	         *   
	         * @param filename  
	         * @param request  
	         * @return  
	         */    
	        public  String encodeFilename(String filename, HttpServletRequest request) {    
	          /**  
	           * 获取客户端浏览器和操作系统信息  
	           * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
	           * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
	           */    
	          String agent = request.getHeader("USER-AGENT");    
	          try {    
	            if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
	              String newFileName = URLEncoder.encode(filename, "UTF-8");    
	              newFileName = StringUtils.replace(newFileName, "+", "%20");    
	              if (newFileName.length() > 150) {    
	                newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
	                newFileName = StringUtils.replace(newFileName, " ", "%20");    
	              }    
	              return newFileName;    
	            }    
	            if ((agent != null) && (-1 != agent.indexOf("Mozilla")))    
	              return MimeUtility.encodeText(filename, "UTF-8", "B");    
	          
	            return filename;    
	          } catch (Exception ex) {    
	            return filename;    
	          }    
	        }   
		
	}
}
