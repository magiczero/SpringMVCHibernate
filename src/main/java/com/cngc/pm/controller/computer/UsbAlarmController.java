package com.cngc.pm.controller.computer;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.computer.UsbAlarmService;

@Controller
@RequestMapping(value="/computer/usbalarm")
public class UsbAlarmController {
	@Resource
	private UsbAlarmService usbService;
	@Resource
	private SysCodeService syscodeService;
	
	@RequestMapping(value="/list/{itemname}", method = RequestMethod.GET)
	public String list(@PathVariable("itemname") String itemName,Model model){
		
		model.addAttribute("datas",usbService.getAll());
		model.addAttribute("fields", syscodeService.getAllByType(itemName).getResult());
		
		return "/computer/usb-alarm";
	}
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,Model model){
		
		usbService.delById(id);
		
		return "redirect:/computer/usbalarm/list/USBALARM";
	}
}
