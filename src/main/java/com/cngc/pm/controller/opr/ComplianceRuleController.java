package com.cngc.pm.controller.opr;

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

import com.cngc.pm.model.opr.ComplianceRule;
import com.cngc.pm.service.opr.ComplianceRuleService;

@Controller
@RequestMapping(value="/operate/rule")
public class ComplianceRuleController {

	@Resource
	private ComplianceRuleService ruleService;
	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("rule",new ComplianceRule());
		return "operate/rule-add";
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("rule") ComplianceRule rule, HttpServletRequest request){
		
		ruleService.save(rule);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", ruleService.getAll());
		model.addAttribute("rule",new ComplianceRule());
		Map<String, String> map =new HashMap<String,String>();  
		map.put("false", "不合规");  
		map.put("true", "合规");
		model.addAttribute("complianceitems", map);
		Map<String, String> equalmap =new HashMap<String,String>();  
		equalmap.put("false", "模糊匹配");  
		equalmap.put("true", "精确匹配");
		model.addAttribute("equalitems", equalmap);
		return "operate/rule-list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			ruleService.delById(id);
		
		return "redirect:/operate/rule/list";
	}
	@RequestMapping(value="/get/{id}")
	@ResponseBody
	public Map<String,Object> getSoftware(@PathVariable("id") long id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		ComplianceRule rule = ruleService.getById(id);
		
		Map<String, String> map1 = new HashMap<>();
		map1.put("id", rule.getId().toString());
		map1.put("name", rule.getName());
		map1.put("item", rule.getItem());
		map1.put("subitem", rule.getSubitem());
		map1.put("indexOfData", Integer.toString(rule.getIndexOfData()));
		map1.put("value", rule.getValue());
		map1.put("compliance", (rule.isCompliance()?"true":"false") );
		map1.put("mark",rule.getMark());
		
		map.put("rule",map1);
		
		return map;
	}
}
