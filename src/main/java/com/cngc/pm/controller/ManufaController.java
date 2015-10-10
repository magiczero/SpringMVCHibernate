package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.Manufacturer;
import com.cngc.pm.service.ManufaService;

import static com.cngc.utils.Common.isEmpty;

@Controller
@RequestMapping("/manufacturer")
public class ManufaController {
	
	@Resource
	private ManufaService manufaService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		
		model.addAttribute("listManufa", manufaService.getList());
		
		return "manufacturer/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		
		model.addAttribute("manufacturer", new Manufacturer());
		
		return "manufacturer/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("manufacturer") Manufacturer manufa, BindingResult result) {
		
		if(result.hasErrors()) {
			return "manufacturer/add";
		}
		
		manufaService.save(manufa);
		
		return "redirect:list";
	}
	
	/**
	 * 验证num是否重复
	 * @param fieldId
	 * @param fieldValue
	 * @return
	 */
	@RequestMapping(value = "/repeatnum")
	@ResponseBody  
	public Map<String,Object> validateRepeatNum(@RequestParam String fieldId,@RequestParam String fieldValue ) {
		Map<String,Object> map = new HashMap<String,Object>();
		boolean status = true;
		String msg = "";
		
		if("num".equals(fieldId) && !isEmpty(fieldValue)) {
			if(manufaService.isExistsNum(fieldValue)) {
				status = false;
				msg = "厂商编号已经存在，请重新填写";
			} else {
				msg = "此编号可以使用";
			}
		}
		map.put("fieldId", fieldId);
		map.put("status", status);
		map.put("message", msg);
		
		return map;
	}
}
