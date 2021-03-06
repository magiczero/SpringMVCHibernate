package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cngc.pm.annotation.ControllerLog;
import com.cngc.pm.common.web.BaseController;
import com.cngc.pm.model.Authority;
import com.cngc.pm.service.AuthorityService;

@Controller
@RequestMapping("/authority")
public class AuthorityController extends BaseController {

	@Resource
	private AuthorityService authService;
	
//	@InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Set.class, "setResources", new CustomCollectionEditor(Set.class)
//          {
//            @Override
//            protected Resources convertElement(Object element)
//            {
//                Long id = null;
//
//                if(element instanceof String && !((String)element).equals("")){
//                    //From the JSP 'element' will be a String
//                    try{
//                        id = Long.parseLong((String) element);
//                    }
//                    catch (NumberFormatException e) {
//                        System.out.println("Resources was " + ((String) element));
//                        e.printStackTrace();
//                    }
//                }
//                else if(element instanceof Long) {
//                    //From the database 'element' will be a Long
//                    id = (Long) element;
//                }
//
//                return id != null ? authService.loadResourcesById(id) : null;
//            }
//          });
//     // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
//     		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
//     			@Override
//     			public void setAsText(String text) {
//     				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
//     			}
//     			@Override
//     			public String getAsText() {
//     				Object value = getValue();
//     				return value != null ? value.toString() : "";
//     			}
//     		});
//	}
//	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ControllerLog(description="查看所有的权限")
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
	@ControllerLog(description="添加权限")
	public String save(@Valid @ModelAttribute("authority") Authority authority, BindingResult result, HttpServletRequest request) throws BindException {
		if(result.hasErrors()) {
			throw new BindException(result);
		}
		
		String[] resours = request.getParameterValues("resos");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		for(String s : resours) {
//		System.out.println(s);
//		}
		//if(authority.getId() == null)	//添加
			authService.save(true, authority, username, resours );
//		else											//修改
//			authService.update(authority, resours);
		
//		if(authority.getId() == null)
//			authService.save(authority);
//		else
//			authService.update(authority);
		
		return "redirect:/authority/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String update(@Valid @ModelAttribute("authority") Authority authority, BindingResult result, HttpServletRequest request) throws BindException {
		if(result.hasErrors()) {
			throw new BindException(result);
		}
		
		String[] resours = request.getParameterValues("resos");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		authService.save(false, authority, username, resours );
		
		return "redirect:/authority/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String,Object> delete(@PathVariable("id") long id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Map<String, Object> map = new HashMap<>();  
		if(authService.delete(authService.getById(id), username)) {
			map.put("msg", true);
		} else {
			map.put("msn", false);
		}
		
		return map;
	}
}
