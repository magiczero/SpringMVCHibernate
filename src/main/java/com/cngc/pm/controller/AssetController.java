package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.Asset;
import com.cngc.pm.model.SecretLevel;
import com.cngc.pm.service.AssetService;

@Controller
@RequestMapping("/Asset")
//@SessionAttributes("currAsset")
public class AssetController {

	@Resource
	private AssetService assetService;
	
	@RequestMapping(value = "/validateName")
	@ResponseBody  
	public Map<String,Object> validateAssetNum(@RequestParam String fieldId,@RequestParam String fieldValue ) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		String msg = "";
		boolean status = true;
		
		if("assetNum".equals(fieldId) && (fieldValue!=null || !"".equals(fieldValue))) {
			if(assetService.isExsitAssetNum(fieldValue.trim())) {
				status = false;
				msg = "资产编号已经存在，请重新填写";
			} else {
				
				msg = "通过";
			}
		}
		// field, status, message不可更改，和前台ajax紧耦合
		map.put("fieldId", fieldId);
		map.put("status", status);
		map.put("message", msg);
		return map;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("ListAsset", assetService.getList());
		
		return "asset/list";
	}
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String initAsset(Model model) {
		//密级
		model.addAttribute("levels", SecretLevel.values());
		//设备类型
		model.addAttribute("styles", assetService.getMapStyle());
		model.addAttribute("asset", new Asset());
		
		return "asset/add";
	}
	
	@RequestMapping(value = "/next", method = RequestMethod.POST)
	public String next(@Valid @ModelAttribute("asset") Asset asset, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			//密级
			model.addAttribute("levels", SecretLevel.values());
			//设备类型
			model.addAttribute("styles", assetService.getMapStyle());
			model.addAttribute("asset", new Asset());
			
			return "asset/add";
		}
		//放入session
		model.addAttribute("currAsset", asset);
		
		long type = asset.getEquipType().getId();
		if(type == 2) {//服务器
			//return "redirect:/Server/initAdd";
			return "forward:/Server/initAdd";
		} else if(type == 3) {		//机柜
			return "";
		} else if(type == 4) {		//涉密终端
			return "";
		} else {
			return "";
		}
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") int id) {
		Asset asset = this.assetService.getById(id);
		long type = asset.getEquipType().getId();
		if(type == 2) {//服务器
			return "redirect:/Server/view/"+id;
		}else if(type == 3) {		//机柜
			return "";
		} else if(type == 4) {		//涉密终端
			return "";
		} else {
			return "";
		}
	}
}
