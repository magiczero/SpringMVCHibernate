package com.cngc.pm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.HardDisk;
import com.cngc.pm.service.HardDiskService;

@Controller
@RequestMapping("/harddisk")
public class HardDiskController {

	@Resource
	private HardDiskService diskService;
	
	//@RequestMapping(value = {"/list","","/"}, method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("diskList", diskService.getList());
		
		return "harddisk/list";
	}
	
	/**
	 * @param id		服务器id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/list/{id}"}, method = RequestMethod.GET)
	public String listByServer(@PathVariable("id") int id, Model model) {
		List<HardDisk> list = diskService.getListByServerId(id);
		
		model.addAttribute("serverid", id);
		model.addAttribute("listHD", list); 
		
		return "harddisk/list1";
	}
	
	@RequestMapping(value = "/add/{serverid}", method = RequestMethod.GET)
	public String add(@PathVariable("serverid") int id,Model model) {
		
		model.addAttribute("serverid", id);
		model.addAttribute("harddisk", new HardDisk());
		
		return "harddisk/add1";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("harddisk") HardDisk hd) {
		diskService.addHardDisk(hd);
		
		return "redirect:/harddisk/list/"+hd.getServer().getId();
	}
}
