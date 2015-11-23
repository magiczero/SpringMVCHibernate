package com.cngc.pm.controller;

import java.beans.PropertyEditorSupport;
import java.util.Set;

import javax.annotation.Resource;
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

import com.cngc.pm.model.Authority;
import com.cngc.pm.model.Resources;
import com.cngc.pm.service.AuthorityService;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

	@Resource
	private AuthorityService authService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "setResources", new CustomCollectionEditor(Set.class)
          {
            @Override
            protected Resources convertElement(Object element)
            {
                Long id = null;

                if(element instanceof String && !((String)element).equals("")){
                    //From the JSP 'element' will be a String
                    try{
                        id = Long.parseLong((String) element);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Resources was " + ((String) element));
                        e.printStackTrace();
                    }
                }
                else if(element instanceof Long) {
                    //From the database 'element' will be a Long
                    id = (Long) element;
                }

                return id != null ? authService.loadResourcesById(id) : null;
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
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("listAuths", authService.getAll());
		model.addAttribute("listResources", authService.getListResources());
		model.addAttribute("authority", new Authority());
		
		return "authority/list";
	}
	
	@RequestMapping(value = "/init-update/{id}", method = RequestMethod.GET)
	public String initUpdate(@PathVariable long id,Model model) {
		model.addAttribute("authority", authService.getById(id));
		model.addAttribute("listResources", authService.getListResources());
		return "authority/update";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("authority") Authority authority, BindingResult result) {
		if(result.hasErrors()) {
			return "500";
		}
		
		if(authority.getId() == null)
			authService.save(authority);
		else
			authService.update(authority);
		
		return "redirect:/authority/list";
	}
}