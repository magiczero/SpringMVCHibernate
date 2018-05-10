package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.Message;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.MessageService;
import com.googlecode.genericdao.search.SearchResult;

@Controller
@RequestMapping(value="/message")
public class MessageController {
	
	@Resource
	private MessageService messageService;
	@Resource
	private UserUtil userUtil;
	
	@Resource
	private AttachService attachService;
	
	@RequestMapping(value="/init-add",method = RequestMethod.GET)
	public String initAdd(Model model) {
		
		model.addAttribute("message", new Message());
		
		return "/sysmanage/message-add";
	}
	
	@RequestMapping(value="/send",method = RequestMethod.POST)
	public String sendMessage(@Valid @ModelAttribute("message") Message message,HttpServletRequest request, Authentication authentication) throws Exception {
		
		if(!StringUtils.isEmpty(request.getParameter("fileids"))) {
			String attachIds = request.getParameter("fileids");
			
			Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
			
			message.setAttachs(attachSet);
		}
		
		message.setFromUser(userUtil.getUsernameByAuth(authentication));
		message.setFromUsername(userUtil.getNameByUsername(message.getFromUser()));
		message.setToUsername(userUtil.getNameByUsername(message.getToUser()));
		message.setCreatedTime(new java.util.Date());
		messageService.save(message);
		
		return "redirect:/message/list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model) throws Exception{
		
		//model.addAttribute("messages", messageService.getByUserId(userUtil.getUsernameByAuth(authentication)).getResult() );
		
		return "/sysmanage/message-list";
	}
	
	@RequestMapping(value="/my-list",method = RequestMethod.GET)
	public String myList(Model model) throws Exception{
		
		//model.addAttribute("messages", messageService.getByUserId(userUtil.getUsernameByAuth(authentication)).getResult() );
		
		return "/sysmanage/message-my-list";
	}
	
	
	@RequestMapping(value="/table-page-ajax-list",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String tablePageAjax(@RequestParam String aoData,Authentication authentication) throws Exception {
		JSONArray jsonarray = new JSONArray(aoData); 
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
		
		for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  
	            sEcho = obj.get("value").toString();  
	   
	        if (obj.get("name").equals("iDisplayStart"))  
	            iDisplayStart = obj.getInt("value");  
	   
	        if (obj.get("name").equals("iDisplayLength"))  
	            iDisplayLength = obj.getInt("value");  
	    } 
		
		SearchResult<Message> result = messageService.getListByUserIdAndPage(userUtil.getUsernameByAuth(authentication), iDisplayStart, iDisplayLength);
		
		int count = result.getTotalCount();
		
		JSONObject getObj = new JSONObject();
	    
	    getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", count);//实际的行数
	    getObj.put("iTotalDisplayRecords",  count);//显示的行数,这个要和上面写的一样
	    getObj.put("aaData", result.getResult());
		
		return getObj.toString();
	}
	
	@RequestMapping(value="/table-page-ajax-my-list",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String tablePageAjaxMine(@RequestParam String aoData,Authentication authentication) throws Exception {
		JSONArray jsonarray = new JSONArray(aoData); 
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
		
		for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  
	            sEcho = obj.get("value").toString();  
	   
	        if (obj.get("name").equals("iDisplayStart"))  
	            iDisplayStart = obj.getInt("value");  
	   
	        if (obj.get("name").equals("iDisplayLength"))  
	            iDisplayLength = obj.getInt("value");  
	    } 
		
		SearchResult<Message> result = messageService.getListByMineAndPage(userUtil.getUsernameByAuth(authentication), iDisplayStart, iDisplayLength);
		
		int count = result.getTotalCount();
		
		JSONObject getObj = new JSONObject();
	    
	    getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", count);//实际的行数
	    getObj.put("iTotalDisplayRecords",  count);//显示的行数,这个要和上面写的一样
	    getObj.put("aaData", result.getResult());
		
		return getObj.toString();
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			messageService.delById(id);
		
		return "redirect:/message/list";
	}
	
	@RequestMapping(value="/getnotreadcount")
	@ResponseBody
	public Map<String,Object> getNotReadMessageCount(Model model,Authentication authentication) throws Exception
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", messageService.getNotReadMessageCountByUserId(userUtil.getUsernameByAuth(authentication)));
		
		return map;
	}
	
	@RequestMapping(value="/getnotreadlist")
	@ResponseBody
	public Map<String,Object> getNotReadMessageList(Model model,Authentication authentication) throws Exception
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("messages", messageService.getNotReadMessageByUserId(userUtil.getUsernameByAuth(authentication)).getResult());
		
		return map;
	}
	@RequestMapping(value="/getlist")
	@ResponseBody
	public Map<String,Object> getMessageList(Model model,Authentication authentication) throws Exception
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("messages", messageService.getByUserId(userUtil.getUsernameByAuth(authentication)).getResult());
		
		return map;
	}
	
	@RequestMapping(value="/read/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> read(@PathVariable("id") long id,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		if(id!=0)
			messageService.readMessage(id);
		
		map.put("result","ok");
		
		return map;
	}
	
	@RequestMapping(value="/readmessage/{id}", method = RequestMethod.GET)
	public String readmessage(@PathVariable("id") long id,Model model){
		if(id!=0)
			messageService.readMessage(id);
		
		return "redirect:/message/list";
	}

}
