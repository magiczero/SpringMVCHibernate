package com.cngc.pm.controller;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.DocAuth;
import com.cngc.pm.model.Document;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.DocumentService;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Resource
	private DocumentService docService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "styles", new CustomCollectionEditor(Set.class)
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
        binder.registerCustomEditor(DocAuth.class, new AuthEnumEditor());
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		//权限
		model.addAttribute("auths", DocAuth.values());
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
//		Long[] ids = {11l,12l};
//		Set<Style> setStyle = docService.getSetStyle(ids);
		Set<Attachment> setAttach = docService.getSetAttach(names);
		
		document.setAttachs(setAttach);
//		document.setStyles(setStyle);
		docService.save(document);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String save(Model model) {
		model.addAttribute("listDocs", docService.getAll());
		
		return "document/list";
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
