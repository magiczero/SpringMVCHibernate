package com.cngc.pm.controller.opr;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.opr.SoftwareWhite;
import com.cngc.pm.service.opr.SoftwareWhiteService;

@Controller
@RequestMapping(value="/operate/software")
public class SoftwareWhiteController {

	@Resource
	private SoftwareWhiteService softService;
	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("software",new SoftwareWhite());
		return "operate/software-add";
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("software") SoftwareWhite software, HttpServletRequest request){
		
		if(software.getId()==null || software.getId()==0)
			software.setCreateDate(new Timestamp(System.currentTimeMillis()));
		software.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		softService.save(software);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", softService.getAll());
		model.addAttribute("software",new SoftwareWhite());
		return "operate/software-list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			softService.delById(id);
		
		return "redirect:/operate/software/list";
	}
	@RequestMapping(value="/get/{id}")
	@ResponseBody
	public Map<String,Object> getSoftware(@PathVariable("id") long id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		SoftwareWhite software = softService.getById(id);
		
		Map<String, String> map1 = new HashMap<>();
		map1.put("id", software.getId().toString());
		map1.put("name", software.getName());
		map1.put("version", software.getVersion());
		map1.put("vendor", software.getVendor());
		map1.put("remark", software.getRemark());
		
		map.put("software",map1);
		
		return map;
	}
}
