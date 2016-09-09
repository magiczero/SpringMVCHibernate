package com.cngc.pm.controller.cms;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.SysCode;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.SecretLevel;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.cms.CategoryService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.SearchResult;

@Controller
@RequestMapping(value="/cms/ci/document")
public class Document1Controller {

	@Resource
	private CiService ciService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private SysCodeService syscodeService;
	@Resource
	private AttachService attachService;
	@Resource
	private UserUtil userUtil;
	
	@RequestMapping
	public String root(Model model) {
		//model.addAttribute("styles", docService.getListStyle());
		//去数据库读取系统
		model.addAttribute("syscodeList", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.system")).getResult());
		
		return "cms/ci-document-sys-list";
	}
	
	@RequestMapping(value="/sys-code-list/{codeId}", method = RequestMethod.GET)
	public String sysCodeList(Model model, @PathVariable() long codeId) {
		//去数据库读取系统
		SysCode code = syscodeService.getById(codeId);
		
		Category category = categoryService.getByCodeName(code.getCodeName());
		model.addAttribute("category", category);
		
		return "cms/document-list-by-code";
	}
	
	@RequestMapping(value="/getsubmenu/{categoryCode}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delAttach(@PathVariable("categoryCode") String categoryCode){
		//SysCode code = syscodeService.getById(codeId);
		
		Category category = categoryService.getByCode(categoryCode);
		
		List<Category> list = categoryService.getListByParent(category,false);
		
		String returnStr = "";
		
		for(Category c : list) {
			List<Category> list1 = categoryService.getListByParent(c,false);
			
			if(list1.size() >0) {
				returnStr += "<li class=\"dropdown\"><a tabindex=\"0\" data-toggle=\"dropdown\" data-submenu>"+c.getCategoryName()+"<span class=\"caret\"></span></a><ul class=\"dropdown-menu\">";
				for(Category c2 : list1) {
					List<Category> list2 = categoryService.getListByParent(c2,false);
					if(list2.size() > 0) {
						returnStr += "<li class=\"dropdown-submenu\"><a tabindex=\"0\" onclick=\"searchByCode("+c2.getCategoryCode()+")\">"+c2.getCategoryName()+"</a><ul class=\"dropdown-menu\">";
						
						for(Category c3 : list2) {
							returnStr += "<li><a tabindex=\"0\" onclick=\"searchByCode("+c3.getCategoryCode()+")\">"+c3.getCategoryName()+"</a></li>";
						}
						
						returnStr += "</ul>";
					} else {
						returnStr += "<li><a tabindex=\"0\" onclick=\"searchByCode("+c2.getCategoryCode()+")\">"+c2.getCategoryName()+"</a>";
					}
					returnStr += "</li>";
				}
				returnStr += "</ul>";
			} else {
				returnStr += "<li><a tabindex=\"0\">"+c.getCategoryName()+"</a>";
			}
			
			returnStr += "</li>";
			
		}
		
		return returnStr;
	}

	/**
	 * 获取table的数据
	 * @param aoData
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/table-page-ajax-list",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String tablePageAjax(@RequestParam String aoData) throws ParseException {
		JSONArray jsonarray = new JSONArray(aoData);
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
		//搜索条件
		String categoryCode = "";
		
		for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  
	            sEcho = obj.get("value").toString();  
	   
	        if (obj.get("name").equals("iDisplayStart"))  
	            iDisplayStart = obj.getInt("value");  
	   
	        if (obj.get("name").equals("iDisplayLength"))  
	            iDisplayLength = obj.getInt("value");  
	        
	        if (obj.get("name").equals("categoryCode"))  
	        	categoryCode = obj.getString("value");  
	    } 
		SearchResult<Ci> ciSr = ciService.getDocumentAllByCategory(categoryService.getByCode(categoryCode), iDisplayStart, iDisplayLength);
		
		JSONObject getObj = new JSONObject();
	    
	    getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", ciSr.getTotalCount());//实际的行数
	    getObj.put("iTotalDisplayRecords",  ciSr.getTotalCount());//显示的行数,这个要和上面写的一样
	    getObj.put("aaData", ciSr.getResult());
		
		return getObj.toString();
	}
	
	@RequestMapping(value="/init-add", method = RequestMethod.GET)
	public String initAdd(Model model) {
		Ci ci = new Ci();
		model.addAttribute("document", ci);
		//密级
		model.addAttribute("levels", SecretLevel.values());
		
		return "cms/doc-add";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("document") Ci document, BindingResult result, HttpServletRequest request,Authentication authentication) {
		if(!StringUtils.isEmpty(request.getParameter("fileids"))) {
			String attachIds = request.getParameter("fileids");
			Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
			document.setAttachs(attachSet);
		}
		
		document.setReviewStatus("02");
		document.setDeleteStatus("01");
		document.setCreatedTime(new Date());
		document.setLastUpdateTime(new Date());
		document.setLastUpdateUser(userUtil.getUserId(authentication));
		
		ciService.save(document);
		
		return "redirect:/cms/ci/document/sys-code-list/223";
	}
	
	@RequestMapping(value="/document-category-tree",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String documentTree()  {
		Category categoryRoot = categoryService.getByCodeName("文档");
		
		List<Category> list = categoryService.getListByParent(categoryRoot, true);
		
		String sjson = "";
		String lastcode = "";
		String curcode = "";
		for (Category category : list) {
			curcode = category.getCategoryCode();
			if (lastcode.length() > 0) {
				if (curcode.length() > lastcode.length()) {
					// 子节点
					sjson += ",\"collapsed\":true,\"children\":[";
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
			sjson += "{\"text\":\"<a href='javascript:void(0);' onclick='initTable(\\\""+category.getCategoryCode()+"\\\");'>" + category.getCategoryCode() + " " + category.getCategoryName() + "\"";
			lastcode = curcode;
		}
		sjson += "}";
		for (int i = 2; i < lastcode.length(); i = i + 2) {
			sjson += "]}";
		}
		sjson = "[" + sjson + "]";

		return sjson;
	}
}
