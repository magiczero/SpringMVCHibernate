package com.cngc.pm.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.common.web.BaseController;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.StyleService;

@Controller
@RequestMapping("/style")
public class StyleController extends BaseController {

	@Resource
	private StyleService styleService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("styleList", styleService.getAllByLevelFir());
		model.addAttribute("style", new Style());
		
		return "category/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute() Style style ) {
		styleService.save(style);
		
		return "redirect:/style/add";
	}
}
