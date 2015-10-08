package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.NetworkCard;
import com.cngc.pm.model.Servers;
import com.cngc.pm.service.NetCardService;
import com.cngc.utils.Common;

@Controller
@RequestMapping("/networkcard")
public class NetworkCardController {

	@Resource
	private NetCardService netCardService;
	
	@RequestMapping(value = {"/add/{id}"}, method = RequestMethod.GET)
	public String add(@PathVariable("id") long id, Model model) {
		Servers server = netCardService.getServerById(id);
		
		if(server != null) {
			model.addAttribute("server", server);
			model.addAttribute("netcard", new NetworkCard());
			model.addAttribute("types", netCardService.getMapType());
		}
		
		return "networkcard/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("netcard") NetworkCard nc) {
		Servers server = netCardService.getServerById(nc.getServer().getId());
		
		if(server != null)
			netCardService.addNetworkCard(nc);
		
		return "redirect:/networkcard/list/" + server.getId();
	}
	
	@RequestMapping(value = {"/list/{id}"}, method = RequestMethod.GET)
	public String listByServer(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("listNet", netCardService.getListByServerid(Long.valueOf(id)));
		model.addAttribute("serverid", id);
		
		return "networkcard/list";
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody  
	public Map<String,Object> delete(Model model, String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id[] = ids.split(",");
			long serverid=0;
			for(int i=0; i<id.length; i++) {
				if(!Common.isEmpty(id[i])) {
					serverid = netCardService.delete(Long.valueOf(id[i]));
				}
			}
			map.put("flag", "true");
			map.put("serverid", serverid);
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}
}
