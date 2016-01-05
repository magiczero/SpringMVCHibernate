package com.cngc.pm.controller;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.CheckItems;
import com.cngc.pm.model.Document;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.CheckItemsService;
import com.cngc.pm.service.StyleService;
import com.googlecode.genericdao.search.SearchResult;

@Controller
@RequestMapping("/checkitems")
public class CheckItemsController {

	@Resource
	private CheckItemsService itemsService;
	
	@Resource
	private StyleService styleService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "docSet", new CustomCollectionEditor(Set.class)
          {
            @Override
            protected Document convertElement(Object element)
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

                return id != null ? itemsService.loadDocById(id) : null;
            }
          });
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
	
	@RequestMapping(value = "/bmb-list/{bmb}", method = RequestMethod.GET)
	public String listAll(@PathVariable() String bmb, Model model, Integer offset, Integer maxResults) throws Exception {
		String returnStr = "";
		//验证
		switch(bmb) {
			case	 "BMB22" :
				returnStr = "bmb22-list";
				break;
			case "BMB20" :
				returnStr = "bmb20-list";
				break;
			case "BMB17" : 
				returnStr = "bmb17-list";
				break;
			default:
				throw new Exception("请输入指定的参数");
		}
		
		//SearchResult<CheckItems> result = itemsService.getAll(bmb, offset, maxResults);
		List<Style> styleList = itemsService.getAllItemsByCode(bmb);
		model.addAttribute("checkitemsList", styleList);
		model.addAttribute("item", itemsService.getStyleByCode(bmb));
//		model.addAttribute("count", result.getTotalCount());
		model.addAttribute("offset", offset);
//		model.addAttribute("styleid", new Long(0));
//		model.addAttribute("style", new Style());
		
		model.addAttribute("checkitems", new CheckItems());
		model.addAttribute("docList", itemsService.getAllDoc());
		
		return "/checkitems/"+returnStr;
	}
	
	@RequestMapping(value = "/list/items/{itemid}", method = RequestMethod.GET)
	public String listByItems(@PathVariable() long itemid, Model model) throws Exception {
		//验证参数
//		String[] itemlevel = itemid.split("-");
//		int level = Integer.valueOf(itemlevel[0]);
//		long itemsid = Long.valueOf(itemlevel[1]);
		
		Style style = styleService.getById(itemid);
		
		String code = itemsService.getCodeByTypeid(itemid);
		
		int level = getLevel(style.getStyle(), code, 1);
		
		String returnStr = "";
		switch(code) {
			case	 "BMB22" :
				returnStr = "bmb22-list-by-item";
				break;
			case "BMB20" :
				returnStr = "bmb20-list-by-item";
				break;
			case "BMB17" : 
				returnStr = "bmb17-list-by-item";
				break;
			default:
				throw new Exception("请输入指定的参数");
		}
		
		SearchResult<CheckItems> result = itemsService.getAllByLevelItemid(level, itemid);
				
		model.addAttribute("checkitemsList", result.getResult());
		model.addAttribute("itemAll", itemsService.getStyleByCode(code));
		model.addAttribute("item", style);
		model.addAttribute("count", result.getTotalCount());
		model.addAttribute("offset", null);
		model.addAttribute("styleid", itemid);
		model.addAttribute("level", level);
		
		model.addAttribute("style", new Style());
		model.addAttribute("checkitems", new CheckItems());
		model.addAttribute("docList", itemsService.getAllDoc());
		
		return "/checkitems/"+returnStr;
	}
	
	@RequestMapping(value = "/additems", method = RequestMethod.GET)
	public String addItems(Model model) {
		
		model.addAttribute("itemsAll", itemsService.getAllByCode());
		model.addAttribute("style", new Style());
		
		return "checkitems/additems";
	}
	
	@RequestMapping(value = "/saveitems", method = RequestMethod.POST)
	public String saveItems(@Valid @ModelAttribute() Style style) {
		itemsService.save(style);
		String code = itemsService.getCodeByTypeid(style.getId());
		
		return "redirect:/checkitems/bmb-list/"+code;
//		return "redirect:/checkitems/additems";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String add(@PathVariable() Long id,Model model) {
		CheckItems ci = itemsService.find(id);
		
		model.addAttribute("checkitems", ci);
		//model.addAttribute("styleList", itemsService.getSytleListByCode("CI"));
		model.addAttribute("docList", itemsService.getAllDoc());
		
		return "checkitems/add";
	}
	
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable() Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isDelete = itemsService.delete(id);
		
		map.put("flag", isDelete);
		
		return map;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute() CheckItems checkitems) {
		itemsService.save(checkitems);
		String code = itemsService.getCodeByTypeid(checkitems.getItem().getId());
		
		return "redirect:/checkitems/bmb-list/"+code;
	}
	
	private int getLevel(Style style, String code, int grade) {
		//Style parent = style.getStyle();
		if(style != null && code.equals(style.getCode())) {
			return grade;
		} else
			return getLevel(style.getStyle(), code, ++grade);
	}
}
