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
import com.cngc.pm.model.Software;
import com.cngc.pm.service.SoftwareService;
import com.cngc.utils.Common;

@Controller
@RequestMapping("/software")
public class SoftwareController {

	@Resource
	private SoftwareService softwareService;
	
	//分布提交后重写
		@RequestMapping(value = "/initadd", method = RequestMethod.POST)
		public String initAdd(Model model,  Asset currAsset, RedirectAttributes ra) {
			if(!Common.isEmpty(currAsset.getAssetNum())) {
				Software software = new Software();
				
				software.setAssetNum(currAsset.getAssetNum());					//资产编号
				software.setSecretNum(currAsset.getSecretNum());				//涉密编号
				software.setBrand(currAsset.getBrand()); 								//品牌
				software.setModel(currAsset.getModel()); 							//设备型号
				software.setSnNum(currAsset.getSnNum());
				software.setEquipType(currAsset.getEquipType());
				software.setSecretLevel(currAsset.getSecretLevel());
				software.setPurpose(currAsset.getPurpose());
				software.setProductionDate(currAsset.getProductionDate());
				software.setPurchaseTime(currAsset.getPurchaseTime());
				
				model.addAttribute("software", software);
			}
			
			return "software/add";
		}
		
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(@Valid @ModelAttribute("software") Software software, BindingResult result, Model model) {
			if(result.hasErrors()) {
				model.addAttribute("software", software);
				
				return "software/software";
			}
			
			softwareService.add(software);
			
			return "redirect:/Asset/list";
		}
		
		@RequestMapping("/view/{id}")
		public String view(@PathVariable("id") long id, Model model) {
			//密级
			model.addAttribute("levels", SecretLevel.values());
			model.addAttribute("styles", softwareService.getMapAssetStyle());
			model.addAttribute("software", softwareService.getById(id));
			
			return "software/update";
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String update(@Valid @ModelAttribute("software") Software software, BindingResult result, Model model) {
			if(result.hasErrors()) {
				model.addAttribute("levels", SecretLevel.values());
				model.addAttribute("styles", softwareService.getMapAssetStyle());
				model.addAttribute("software", software);
				
				return "software/update";
				
			}
			
			softwareService.add(software);
			
			return "redirect:/Asset/list";
		}
}
