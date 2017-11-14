package com.cngc.pm.controller.computer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.computer.InspectionTarget;
import com.cngc.pm.model.computer.InspectionItem;
import com.cngc.pm.service.computer.InspectionTargetService;
import com.cngc.pm.service.computer.InspectionItemService;

@Controller
@RequestMapping(value="/computer/inspectiontarget")
public class InspectionTargetController {

	@Resource
	private InspectionItemService itemService;
	@Resource
	private InspectionTargetService targetService;
	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("target",new InspectionTarget());
		return "computer/inspectiontarget-add";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("target") InspectionTarget target, HttpServletRequest request){
		
		targetService.save(target);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", targetService.getAll());
		model.addAttribute("target",new InspectionTarget());
		return "computer/inspectiontarget-list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			targetService.delById(id);
		
		return "redirect:/computer/inspectiontarget/list";
	}
	@RequestMapping(value="/updatetarget", method = RequestMethod.POST)
	public String updateTarget(Model model,HttpServletRequest request){
		String targetId = request.getParameter("form_id");
		String items = request.getParameter("form_items");
		if(!StringUtils.isEmpty(targetId))
		{
			InspectionTarget target = targetService.getById(Long.parseLong(targetId));
			if(!StringUtils.equals(items, "0"))
			{
				Set<InspectionItem> set = null;
				if(StringUtils.isEmpty(items))
				{
					//删除所有检查项
					target.setItems(set);
				}
				else
					set = itemService.getItemByIds(items) ;
				target.setItems(set);
				targetService.save(target);
			}
		}
		return "redirect:list";
	}
	@RequestMapping(value="/getitem/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getItem(@PathVariable("id") Long targetId,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		InspectionTarget target = targetService.getById(targetId);
		map.put("items", target.getItems());
		return map;
	}
	@RequestMapping(value="/getitemforselect/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getPropertyForSelect(@PathVariable("id") Long targetId,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Long> itemids = new LinkedList<Long>();
		InspectionTarget target = targetService.getById(targetId);

		for(InspectionItem item:target.getItems())
		{
			itemids.add(item.getItemId());
		}

		//map.put("items", itemService.getItemByNIds(itemids));
		map.put("items", itemService.getAll());
		return map;
	}
}
