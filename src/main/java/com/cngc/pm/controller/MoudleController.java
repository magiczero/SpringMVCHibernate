package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.BaseController;
import com.cngc.pm.model.ModuleType;
import com.cngc.pm.model.Moudle;
import com.cngc.pm.service.MoudleService;

@Controller
@RequestMapping("/moudle")
public class MoudleController extends BaseController {

	@Resource
	private MoudleService moudleService;
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(ModuleType.class, new PropertyEditorSupport(){
//			@Override
//			public void setAsText(String text) throws java.lang.IllegalArgumentException {
//				int val = Integer.parseInt(text);
//				for(ModuleType mt : ModuleType.values()) {
//					if(mt.getNum() == val) {
//						this.setValue(mt);
//						break;
//					}
//				}
//			}
//		});
//	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Moudle> list = moudleService.getAllMenu();
		//List<Moudle> list1 = new ArrayList<>();
		model.addAttribute("listmoudle", list);
		
//		for(Moudle m : list) {					//获取第一层菜单，只有他们才可以添加子菜单
//			if(m.getLevel() == 1)
//				list1.add(m);
//		}
		
		model.addAttribute("moudle", new Moudle());
//		model.addAttribute("list1", list1);
		//model.addAttribute("type", ModuleType.values());
		return "moudle/list";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("moudle") Moudle moudle, BindingResult result) throws BindException {
		//System.out.println("保存成功");
		if(result.hasErrors()) {
			throw new BindException(result);
		}
		if(moudle.getParent().getId() == null) {
			moudle.setLevel(1);
		} else {
			moudle.setLevel(2);
		}
		moudle.setType(ModuleType.menu);
		moudle.setPriority(1);
		moudle.setEnable(true);
		//保存
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		moudleService.save(moudle, username);
		
		return "redirect:/moudle/list";
	}
	
	@RequestMapping(value = "/enable/{id}")
	@ResponseBody  
	public Map<String,Object> enableStatus(@PathVariable("id") long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(moudleService.enableStatus(id, username)) {
			map.put("flag", "true");
		} else {
			map.put("flag", "false");
		}
		
		return map;
	}
}
