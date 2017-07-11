package com.cngc.pm.controller;

import static com.cngc.utils.Common.getRemortIP;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.SysCode;
import com.cngc.pm.service.SysCodeService;

@Controller
@RequestMapping(value="/system/syscode")
public class SysCodeController {
	@Resource
	private SysCodeService syscodeService;
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(Model model,HttpServletRequest request){
		//首先判断权限
		boolean isSysAdmin = false;
		for(GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
			if(ga.getAuthority().equals("sys_admin")) {
				isSysAdmin = true;
				break;
			}
		}
		
		if(isSysAdmin) {
			String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
			SysCode code;
			Long id = Long.parseLong(request.getParameter("fp_id"));
			
			if(id!=0)
				code = syscodeService.getById(id);
			else
				code = new SysCode();
			code.setCode(request.getParameter("fp_code"));
			code.setCodeName(request.getParameter("fp_codename"));
			code.setType(request.getParameter("fp_type"));
			
			syscodeService.save(code, currentUsername, getRemortIP(request));
			
			//return "redirect:/system/syscode/list/" + code.getType();
			return "redirect:/system/syscode/list";
		} else {
			return "403";
		}
	}
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		return "sysmanage/code-list";
	}
	@RequestMapping(value="/list/{type}",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> list(@PathVariable("type") String type,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", syscodeService.getAllByType(type).getResult());
		return map;
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("id") Long id,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		
		syscodeService.delById(id);
		
		map.put("result", "true");
		
		return map;
	}
	@RequestMapping(value="/get/{id}")
	@ResponseBody
	public Map<String,Object> getUser(@PathVariable("id") long id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		SysCode code = syscodeService.getById(id);
		map.put("code",code);
		
		return map;
	}
	@RequestMapping(value="/getjson/{type}")
	@ResponseBody
	public Map<String,Object> getJson(@PathVariable("type") String type){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("json",syscodeService.getJSON(type));
		
		return map;
	}
}
