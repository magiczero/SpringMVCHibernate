package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.HardDisk;
import com.cngc.pm.service.HardDiskService;
import com.cngc.utils.Common;

@Controller
@RequestMapping("/harddisk")
public class HardDiskController {

	@Resource
	private HardDiskService diskService;
	
	//@RequestMapping(value = {"/list","","/"}, method = RequestMethod.GET)
//	public String list(Model model) {
//		model.addAttribute("diskList", diskService.getList());
//		
//		return "harddisk/list";
//	}
	
	/**
	 * @param id		服务器id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/list/{id}"}, method = RequestMethod.GET)
	public String listByServer(@PathVariable("id") int id, Model model) {
		//List<HardDisk> list = diskService.getListByServerId(id);
		
		model.addAttribute("serverid", id);
		//model.addAttribute("listHD", list); 
		
		return "harddisk/list1";
	}
	
	@RequestMapping(value = "/listhd/{serverid}")
	@ResponseBody  
	public Map<String,Object> validateAssetNum(@PathVariable("serverid") int id ) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<HardDisk> list = diskService.getListByServerId(id);
		
		int rows = list.size();
		
		String[][] str = new String[rows][5];
		
		for(int i=0;  i<rows;  i++) {
			HardDisk hd = list.get(i);
			str[i][0] = "<div class=\"checker\"><span><input name=\"ids\" type=\"checkbox\" value=\""+hd.getId()+"\" /></span></div>";
			str[i][1] = hd.getSerialNum();
			str[i][2] = hd.getInterf();
			str[i][3] = hd.getType();
			str[i][4] = hd.getCapacity();
		}
		
//		StringBuffer data = new StringBuffer("[");
//		
//		for(HardDisk hd : list) {
//			data.append("[\""+1+"\",\""+hd.getSerialNum()+"\",\""+hd.getInterf()+"\",\""+hd.getType()+"\",\""+hd.getCapacity()+"\"],");
//		}
//		
//		data.replace(data.length()-1, data.length(), "]");
		
		map.put("aaData", str);
		
		return map;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody  
	public Map<String,Object> delete(Model model, String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id[] = ids.split(",");
			for(int i=0; i<id.length; i++) {
				if(!Common.isEmpty(id[i])) {
					diskService.delete(Long.valueOf(id[i]));
				}
			}
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
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
