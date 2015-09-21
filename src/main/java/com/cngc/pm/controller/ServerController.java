package com.cngc.pm.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cngc.pm.model.Asset;
import com.cngc.pm.model.SecretLevel;
import com.cngc.pm.model.Servers;
import com.cngc.pm.service.ServerService;

@Controller
@RequestMapping("/Server")
public class ServerController {

	@Resource
	private ServerService serverService;
	
	@RequestMapping(value = "/servers", method = RequestMethod.GET)
	public String serverList(Model model) {
	
		model.addAttribute("listServers", serverService.getServerList());
		
		return "server/list1";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String initAdd(Model model) {
		//密级
		model.addAttribute("levels", SecretLevel.values());
		//服务器类型
		model.addAttribute("styles", serverService.getMapStyle());				
		model.addAttribute("server", new Servers());
		
		return "server/add1";
	}
	//分布提交后重写
	@RequestMapping(value = "/initAdd", method = RequestMethod.POST)
	public String initAdd1(Model model,  Asset currAsset, RedirectAttributes ra) {
		if(currAsset.getAssetNum()==null || "".equals(currAsset.getAssetNum().trim())) {
			//model.addAttribute("error", "未填写资产编号");
			
			ra.addFlashAttribute("error", "未填写资产编号");
			
			return "redirect:/Asset/init";
		}
		
		Servers server = new Servers();
		
		server.setAssetNum(currAsset.getAssetNum());					//资产编号
		server.setSecretNum(currAsset.getSecretNum());				//涉密编号
		server.setBrand(currAsset.getBrand()); 								//品牌
		server.setModel(currAsset.getModel()); 							//设备型号
		server.setSnNum(currAsset.getSnNum());
		server.setEquipType(currAsset.getEquipType());
		server.setSecretLevel(currAsset.getSecretLevel());
		server.setPurpose(currAsset.getPurpose());
		server.setProductionDate(currAsset.getProductionDate());
		server.setPurchaseTime(currAsset.getPurchaseTime());
		
		model.addAttribute("server", server);
		
		return "server/add2";
	}
	
	//分布提交后重写
	@RequestMapping(value = "/saves", method = RequestMethod.POST)
	public String save1(@Valid @ModelAttribute("server") Servers server, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("server", server);
			
			return "server/add2";
		}
		
		serverService.addServer(server);
		
		return "redirect:/Asset/list";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("server") Servers server, BindingResult result, Model model) {
		if(result.hasErrors()) {
			//密级
			model.addAttribute("levels", SecretLevel.values());
			//服务器类型
			model.addAttribute("styles", serverService.getMapStyle());	
			model.addAttribute("server", server);
			
			return "server/add1";
		}
		
		serverService.addServer(server);
		
		return "redirect:/Asset/list";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("server") Servers s, BindingResult result, Model model) {
		System.out.println("进入保存方法");
		if(result.hasErrors()) {
			//密级
			model.addAttribute("levels", SecretLevel.values());
			//服务器类型
			model.addAttribute("styles", serverService.getMapStyle());				
			model.addAttribute("server", new Servers());
			
			return "server/add1";
		}
		
		serverService.addServer(s);
		
		return "redirect:/Server/servers";
	}
	
	@RequestMapping("/remove/{id}")
	public String detail(@PathVariable("id") int id, Model model) {
		model.addAttribute("server", serverService.getServerById(id));
		
		return "server/detail";
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") int id, Model model) {
		//密级
		model.addAttribute("levels", SecretLevel.values());
		//服务器类型
		model.addAttribute("styles", serverService.getMapStyle());				
		model.addAttribute("server", this.serverService.getServerById(id));
				
		return "server/add1";
	}
}
