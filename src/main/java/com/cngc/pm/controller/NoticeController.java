package com.cngc.pm.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Notice;
import com.cngc.pm.service.NoticeService;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

	@Resource
	private NoticeService noticeService;
	@Resource
	private UserUtil userUtil;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model){
		model.addAttribute("list",noticeService.getAll());
		return "notice/list";
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model){
		
		model.addAttribute("notice", new Notice());
		
		return "notice/add";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("notice") Notice notice,HttpServletRequest request,Authentication authentication){
		
		if(notice.getId()==null)
		{
			notice.setCreatedTime(new Date());
			notice.setCreatedUser(userUtil.getUserId(authentication));
			notice.setStatus("01");
		}
		else
		{
			Notice oldnotice = noticeService.getById(notice.getId());
			oldnotice.setTitle(notice.getTitle());
			oldnotice.setContent(notice.getContent());
			notice = oldnotice;
		}
		noticeService.save(notice);
		
		return "redirect:/notice/list";
	}
	@RequestMapping(value = "/savedialog", method = RequestMethod.POST)
	public String saveDialog(@RequestParam("fp_notice_title") String title,
			@RequestParam("fp_notice_content") String content, HttpServletRequest request, Model model, Authentication authentication) {


		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setCreatedTime(new Date());
		notice.setCreatedUser(userUtil.getUserId(authentication));
		notice.setStatus("01");
		
		noticeService.save(notice);
		
		if (StringUtils.isEmpty(request.getParameter("redirectAddress")))
			return "redirect:/notice/list";
		else
			return "redirect:" + request.getParameter("redirectAddress");
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id, Model model){
		if(id!=0)
		{
			noticeService.delById(id);
		}
		return "redirect:/notice/list";
	}
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model){
		
		model.addAttribute("notice", noticeService.getById(id));
		return "notice/add";
	}
}
