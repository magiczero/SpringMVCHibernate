package com.cngc.pm.controller.cms;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.cms.Account;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.cms.AccountService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.pm.service.cms.PropertyService;
import com.cngc.utils.Common;

@Controller
@RequestMapping(value="/stats/account")
public class AccountController {
	@Resource
	private CiService ciService;
	@Resource
	private PropertyService propertyService;
	@Resource
	private AccountService accountService;
	@Resource
	private UserUtil userUtil;

	/**添加台账信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String account(Model model,HttpServletRequest request){

		model.addAttribute("fields", propertyService.getFields());
		
		return "stats/account-add";
	}
	/**保存台账信息
	 * @param category
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(HttpServletRequest request,Authentication authentication){
		
		String code = request.getParameter("category");
		String fields = request.getParameter("fm_fields");
		String properties = request.getParameter("fm_properties");
		String name = request.getParameter("fm_name");
		
		Account account = new Account();
		account.setName(name);
		account.setCategory(code);
		account.setFields(fields);
		account.setProperties(properties);
		account.setCreatedTime(new Date());
		account.setCreatedUser(userUtil.getUserId(authentication));
		
		accountService.save(account);
		
		return "redirect:list";
	}
	/**列表信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", accountService.getAll());
		return "stats/account-list";
	}
	/**删除台账
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,Model model){
			
		accountService.delById(id);
		
		return "redirect:/stats/account/list";
	}
	/**定义台账信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setting",method = RequestMethod.GET)
	public String setting(Model model,HttpServletRequest request){

		model.addAttribute("fields", propertyService.getFields());
		
		return "stats/account-setting";
	}
	/**生成自定义台账
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/generate",method = RequestMethod.POST)
	public String accountList(Model model,HttpServletRequest request){

		String code = request.getParameter("category");
		String fields = request.getParameter("fm_fields");
		String properties = request.getParameter("fm_properties");
		
		List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
		List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
		
		//生成column
		List<String> columns = new LinkedList<String>();
		for(Property p:fieldsSet)
			columns.add(p.getPropertyName());
		for(Property p:propertiesSet)
			columns.add(p.getPropertyName());
		model.addAttribute("columns", columns);
		//生成row
		ObjectMapper mapper = new ObjectMapper();
		List<Object> rows = new LinkedList<Object>();
		List<Ci> cilist = ciService.getByCategoryCode(code).getResult();
		for(Ci ci:cilist)
		{
			List<String> row = new LinkedList<String>();
			try {
				Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
				Object obj = null;
				for(Property p:fieldsSet)
				{
					obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
					if(obj==null)
						row.add("-");
					else
						row.add(obj.toString());
				}
				for(Property p:propertiesSet)
					row.add(propertymap.get(p.getPropertyId()));
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rows.add(row);
		}
		model.addAttribute("rows", rows);
		
		
		return "stats/account-generate";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/get/{id}",method = RequestMethod.GET)
	public String getAccount(@PathVariable("id") Long id,Model model){

		Account account = accountService.getById(id);
		String code = account.getCategory();
		String fields = account.getFields();
		String properties = account.getProperties();
		
		List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
		List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
		
		//生成column
		List<String> columns = new LinkedList<String>();
		for(Property p:fieldsSet)
			columns.add(p.getPropertyName());
		for(Property p:propertiesSet)
			columns.add(p.getPropertyName());
		model.addAttribute("columns", columns);
		//生成row
		ObjectMapper mapper = new ObjectMapper();
		List<Object> rows = new LinkedList<Object>();
		List<Ci> cilist = ciService.getByCategoryCode(code).getResult();
		for(Ci ci:cilist)
		{
			List<String> row = new LinkedList<String>();
			try {
				Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
				Object obj;
				for(Property p:fieldsSet)
				{
					obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
					if(obj==null)
						row.add("-");
					else
						row.add(obj.toString());
				}
				for(Property p:propertiesSet)
					row.add(propertymap.get(p.getPropertyId()));
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rows.add(row);
		}
		model.addAttribute("rows", rows);
		
		
		return "stats/account-get";
	}
}
