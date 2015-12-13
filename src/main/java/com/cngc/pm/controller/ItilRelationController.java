package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.ItilRelation;
import com.cngc.pm.service.ItilRelationService;

@Controller
@RequestMapping(value="/itilrelation")
public class ItilRelationController {

	@Resource
	private ItilRelationService itilRelationService;
	
	@RequestMapping(value="/save-incident-ci")
	@ResponseBody
	public Map<String,Object> saveRelations(HttpServletRequest request,Model model)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Long primaryId = Long.valueOf(request.getParameter("primary_id").toString());
		String secondaryId = request.getParameter("secondary_ids").toString();
		
		String ids[] = secondaryId.split(",");
		for(String id:ids)
			saveRelation(primaryId, Long.valueOf(id), "INCIDENT_CI");
		
		map.put("result","true");
		
		return map;
	}
	
	@RequestMapping(value="/remove-incident-ci")
	@ResponseBody
	public Map<String,Object> getCi(HttpServletRequest request,Model model)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		
		Long primaryId = Long.valueOf(request.getParameter("primary_id"));
		Long secondaryId = Long.valueOf(request.getParameter("secondary_id"));
		
		delete(primaryId, secondaryId, "INCIDENT_CI");
		
		map.put("result", "true");
		
		return map;
	}

	@RequestMapping(value="/get-incident-ci/{id}")
	@ResponseBody
	public Map<String,Object> getCi(@PathVariable("id") long id,HttpServletRequest request,Model model)
	{
		Map<String,Object> map = new HashMap<String,Object>();

		map.put("cis", getIds(id,"INCIDENT_CI",true));
		
		return map;
	}
	@RequestMapping(value="/get-ci-incident/{id}")
	@ResponseBody
	public Map<String,Object> getIncidentByCi(@PathVariable("id") long id,HttpServletRequest request,Model model)
	{
		Map<String,Object> map = new HashMap<String,Object>();

		map.put("incidents", getIds(id,"INCIDENT_CI",false));
		
		return map;
	}
	
	@RequestMapping(value="/save-incident-knowledge")
	@ResponseBody
	public Map<String,Object> saveIncidentKnowledge(HttpServletRequest request,Model model)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Long primaryId = Long.valueOf(request.getParameter("primary_id").toString());
		Long secondaryId = Long.valueOf(request.getParameter("secondary_id").toString());
		
		saveRelation(primaryId, secondaryId, "INCIDENT_KNOWLEDGE");
		
		map.put("result","true");
		
		return map;
	}
	@RequestMapping(value="/get-incident-knowledge/{id}")
	@ResponseBody
	public Map<String,Object> getIncidentKnowledge(@PathVariable("id") long id,HttpServletRequest request,Model model)
	{
		Map<String,Object> map = new HashMap<String,Object>();

		map.put("knowledges", getIds(id,"INCIDENT_Knowledge",true));
		
		return map;
	}
	
	private void saveRelation(Long primaryId,Long secondaryId,String type)
	{
		itilRelationService.save(primaryId, secondaryId, type);
	}
	
	private void delete(Long primaryId,Long secondaryId,String type)
	{
		itilRelationService.delById(primaryId, secondaryId, type);
	}
	private String getIds(Long id,String type,boolean isprimary)
	{
		String ids = "";
		List<ItilRelation> list = itilRelationService.getByType(id, type,isprimary).getResult();
		for(ItilRelation i:list){
			if(!ids.isEmpty())
				ids += ",";
			if(isprimary)
				ids += i.getSecondaryId();
			else
				ids += i.getPrimaryId();
		}
		return ids;
	}
}
