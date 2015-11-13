package com.cngc.pm.controller;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.DocAuth;
import com.cngc.pm.model.Document;
import com.cngc.pm.model.SecretLevel;
import com.cngc.pm.model.Style;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.DocumentService;
import com.cngc.utils.Common;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Resource
	private DocumentService docService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "checkItems", new CustomCollectionEditor(Set.class)
          {
            @Override
            protected Style convertElement(Object element)
            {
                Long id = null;

                if(element instanceof String && !((String)element).equals("")){
                    //From the JSP 'element' will be a String
                    try{
                        id = Long.parseLong((String) element);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Style was " + ((String) element));
                        e.printStackTrace();
                    }
                }
                else if(element instanceof Long) {
                    //From the database 'element' will be a Long
                    id = (Long) element;
                }

                return id != null ? docService.loadStyleById(id) : null;
            }
          });
//        binder.registerCustomEditor(DocAuth.class, new AuthEnumEditor());
     // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
     		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
     			@Override
     			public void setAsText(String text) {
     				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
     			}
     			@Override
     			public String getAsText() {
     				Object value = getValue();
     				return value != null ? value.toString() : "";
     			}
     		});
    }
	
	@RequestMapping
	public String root(Model model) {
		model.addAttribute("styles", docService.getListStyle());
		
		return "document/list";
	}
	
	@RequestMapping(value = "/list3", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listAjax(HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		
		List<Document> list = docService.getAll(user.getId());
		int rows = list.size();
		String[][] str = new String[rows][10];
		
		for(int i=0; i<rows; i++) {
			Document d = list.get(i);
			
			str[i][0] = "";
			str[i][1] = d.getName();
			str[i][2] = d.getKeywords();
			str[i][3] = d.getUser().getUsername();
			String  attach = "";
//			for(Style style :d.getStyles()) {
//				s += style.getName()+"<br/>";
//			}
			str[i][4] = d.getStyle().getName();
			String auth = "";
			if(d.getAuth().getNum()==1) {  //公开
				auth = "<span class=\"label label-warning\">";
			} else {
				auth = "<span class=\"label label-success\">";
			}
			str[i][5] = auth+d.getAuth().getName()+"</span>";
			str[i][6] = d.getCreateDate().toString();
			str[i][7] = d.getVersions().toString();
			for(Attachment att : d.getAttachs()) {
				attach += "<a href=\"attachment/download/"+att.getId()+"\" target=\"_blank\">"+att.getName()+"</a><br/>";
			}
			str[i][8] = attach;
			if(d.getUser().getId() == user.getId()) {
				str[i][9] = "<button class=\"btn btn-danger\" type=\"button\" onclick=\"del("+d.getId()+", "+i+", this)\">删 除</button>";
			} else {
				str[i][9] = "";
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("aaData", str);
		
		return map;
	}
	
	@RequestMapping(value = "/update_version/{docid}", method = RequestMethod.GET)
	public String initUpdateVersion(Model model, @PathVariable("docid") long docid) {
		Document doc = docService.getById(docid);
		if(doc == null) {
			return "redirect:/document/list";
		} else {
			model.addAttribute("document", doc);
			
			return "document/update_version";
		}
	}
	@RequestMapping(value = "/update_version_commit", method = RequestMethod.POST)
	public String updateVersion(@ModelAttribute("document") Document document, HttpServletRequest request) {
		long docid = document.getId();
		Document doc = docService.getById(docid);
		if(doc == null) {
			
			return "500";				//内部错误
		} else {
			int count = Integer.parseInt(request.getParameter("uploader_count"));
			
			String[] names = new String[count];
			for (int i = 0; i < count; i++) {
				//uploadFileName = request.getParameter("uploader_" + i + "_name");
				names[i] = request.getParameter("uploader_" + i + "_tmpname");
			}
	
			Set<Attachment> setAttach = docService.getSetAttach(names);
			
			//获取用户
			SysUser user = (SysUser)request.getSession().getAttribute("user");
			
			Document newDoc = new Document();
			
			newDoc.setAttachs(setAttach);				
			newDoc.setDeposit(doc.getDeposit());
			
			
			return "redirect:/document/list";
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		//密级
		model.addAttribute("levels", SecretLevel.values());
		//权限
		//model.addAttribute("auths", DocAuth.values());
		//读取所有类型
		model.addAttribute("styleList", docService.getListStyle());
		model.addAttribute("document", new Document());
		
		return "document/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("document") Document document, BindingResult result, HttpServletRequest request) {
		int count = Integer.parseInt(request.getParameter("uploader_count"));
		
		String[] names = new String[count];
		for (int i = 0; i < count; i++) {
			//uploadFileName = request.getParameter("uploader_" + i + "_name");
			names[i] = request.getParameter("uploader_" + i + "_tmpname");
		}

		Set<Attachment> setAttach = docService.getSetAttach(names);
		
		//获取用户
		SysUser user = (SysUser)request.getSession().getAttribute("user");
		
		document.setDocId(Common.getUUID());
		document.setLast(true);
		document.setVersions(1);
		document.setUser(user);		
		document.setAttachs(setAttach);
//		document.setStyles(setStyle);
		docService.save(document);
		
		return "redirect:/document";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		model.addAttribute("styles", docService.getListStyle());
		model.addAttribute("listCheckItems", docService.getAllCheckItems());
		//model.addAttribute("listDocs", docService.getAll(user.getId()));
		model.addAttribute("listDocs", docService.getAllLastVersion());
		//model.addAttribute("flag", "all");
		model.addAttribute("styleid", new Long(0));
		model.addAttribute("document", new Document());
		
		return "document/list2";
	}
	
	/**
	 * 按照类别查询文档
	 * @param styleid
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/list/style/{styleid}", method = RequestMethod.GET)
	public String listBySytle(@PathVariable("styleid") long styleid, Model model) {
		//SysUser user = (SysUser)session.getAttribute("user");
		model.addAttribute("styles", docService.getListStyle());
		model.addAttribute("listCheckItems", docService.getAllCheckItems());
		//model.addAttribute("listDocs", docService.getAllByStyle(user.getId(), Long.valueOf(styleid)));
		model.addAttribute("listDocs", docService.getByStyle(styleid));
		model.addAttribute("styleid", styleid);
		model.addAttribute("document", new Document());
		
		return "document/list2";
	}
	
	@RequestMapping(value = "/list/item/{itemid}", method = RequestMethod.GET)
	public String listByItem(@PathVariable("itemid") long itemid, Model model) {
		model.addAttribute("styles", docService.getListStyle());
		model.addAttribute("listCheckItems", docService.getAllCheckItems());
		model.addAttribute("listDocs", docService.getByItem(itemid));
		model.addAttribute("styleid", itemid);
		model.addAttribute("document", new Document());
		
		return "document/list2";
	}
	
	@RequestMapping(value="/list/private", method = RequestMethod.GET)
	public String listByPrivate(Model model, HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		
		model.addAttribute("styles", docService.getListStyle());
		model.addAttribute("listDocs", docService.getAllByPrivate(user.getId()));
		model.addAttribute("flag", "private");
		
		return "document/list2";
	}
	
	//关联检查项
	@RequestMapping(value="/relation-item", method = RequestMethod.POST)
	public String relationItem( @ModelAttribute("document") Document document) {
		if(document.getId() != null && document.getCheckItems() != null) {
			Document doc = docService.getById(document.getId());
			doc.setCheckItems(document.getCheckItems());
			docService.update(doc);
		}
		
		return "redirect:/document/list";
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody  
	public Map<String,Object> delete(@RequestParam String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(docService.delByIds(ids)) {
			map.put("flag", "true");
		} else {
			map.put("flag", "false");
		}
		return map;
	}
	
	@RequestMapping(value = "/remove/{docid}")
	@ResponseBody  
	public Map<String,Object> remove(@PathVariable("docid") long docid, HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = false;
		
		if(docService.delById(docid, user.getId())) 
			flag = true;
		
		map.put("flag", flag);
		
		return map;
	}
	
	class AuthEnumEditor extends PropertyEditorSupport {
		@Override
		public void setAsText(String text) throws java.lang.IllegalArgumentException {
			int val = Integer.parseInt(text);
			for(DocAuth da : DocAuth.values()) {
				if(da.getNum() == val) {
					this.setValue(da);
					break;
				}
			}
		}
	}
}
