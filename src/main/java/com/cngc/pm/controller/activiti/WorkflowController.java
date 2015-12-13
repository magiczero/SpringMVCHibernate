package com.cngc.pm.controller.activiti;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cngc.utils.activiti.WorkflowUtils;

/**工作流
 * @author andy
 *
 */
@Controller
@RequestMapping("/workflow")
public class WorkflowController {

	@Resource
	private IdentityService identityService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private ManagementService managementService;
	@Resource
	private HistoryService historyService;
	
	/**
	 * 获取所有流程信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/process/list", method = RequestMethod.GET)
	public String processlist(Model model) {
	    
		// 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署）
		List<Object[]> objects = new ArrayList<Object[]>();

	    ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
	    List<ProcessDefinition> processDefinitionList = processDefinitionQuery.list();
	    for (ProcessDefinition processDefinition : processDefinitionList) {
	            String deploymentId = processDefinition.getDeploymentId();
	            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
	            objects.add(new Object[]{processDefinition, deployment});
	    }
	    
	    model.addAttribute("list",objects);
	        
		return "workflow/process-list";
	}
	/**
	 * 获取活动的流程信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/process/active-list", method = RequestMethod.GET)
	public String activeList(Model model) {
	   
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().active().orderByDeploymentId().desc();
        List<ProcessDefinition> list = query.list();
        model.addAttribute("list",list);
		
		return "workflow/process-active-list";
	}
	
	/**
	 * 部署工作流
	 * @param exportDir 输入文件目录
	 * @param file 上传文件
	 * @return
	 */
	@RequestMapping(value = "/deploy")
    public String deploy(@Value("#{APP_PROPERTIES['export.diagram.path']}") String exportDir, @RequestParam(value = "file", required = false) MultipartFile file) {

        String fileName = file.getOriginalFilename();

        try {
            InputStream fileInputStream = file.getInputStream();
            Deployment deployment = null;

            String extension = FilenameUtils.getExtension(fileName);
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
            } else {
                deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
            }

            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

            for (ProcessDefinition processDefinition : list) {
                WorkflowUtils.exportDiagramToFile(repositoryService, processDefinition, exportDir);
            }

        } catch (Exception e) {
            
        }

        return "redirect:/workflow/process/list";
    }
    /**
     * 读取资源
     * @param processDefinitionId
     * @param resourceType
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/resource/read")
    public void loadByDeployment(@RequestParam("processDefinitionId") String processDefinitionId,
    							@RequestParam("resourceType") String resourceType,
                                HttpServletResponse response) throws Exception {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        String resourceName = "";
        if (resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
    /** 删除指定的流程
     * @param deploymentId
     * @return
     */
    @RequestMapping(value = "/process/delete")
    public String delete(@RequestParam("deploymentId") String deploymentId) {
    	
    	//Deletes the given deployment and cascade deletion to process instances, history process instances and jobs
        repositoryService.deleteDeployment(deploymentId, true);
        
        return "redirect:/workflow/process/list";
    }
    /**
     * 转换为model
     * @param processDefinitionId
     * @return
     * @throws UnsupportedEncodingException
     * @throws XMLStreamException
     */
    @RequestMapping(value = "/process/convert-to-model/{processDefinitionId}")
    public String convertToModel(@PathVariable("processDefinitionId") String processDefinitionId)
            throws UnsupportedEncodingException, XMLStreamException {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                processDefinition.getResourceName());
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
        XMLStreamReader xtr = xif.createXMLStreamReader(in);
        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

        BpmnJsonConverter converter = new BpmnJsonConverter();
        com.fasterxml.jackson.databind.node.ObjectNode modelNode = converter.convertToJson(bpmnModel);
        
        org.activiti.engine.repository.Model modelData = repositoryService.newModel();
        modelData.setKey(processDefinition.getKey());
        modelData.setName(processDefinition.getResourceName());
        modelData.setCategory(processDefinition.getDeploymentId());

        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
        modelData.setMetaInfo(modelObjectNode.toString());

        repositoryService.saveModel(modelData);

        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));

        return "redirect:/workflow/model/list";
    }
    /**激活、挂起流程
     * @param state
     * @param processDefinitionId
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/processdefinition/update/{state}/{processDefinitionId}")
    public String updateState(@PathVariable("state") String state, @PathVariable("processDefinitionId") String processDefinitionId,
                              RedirectAttributes redirectAttributes) {
        if (state.equals("active")) {
            redirectAttributes.addFlashAttribute("message", "已激活ID为[" + processDefinitionId + "]的流程定义。");
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
        } else if (state.equals("suspend")) {
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
            redirectAttributes.addFlashAttribute("message", "已挂起ID为[" + processDefinitionId + "]的流程定义。");
        }
        return "redirect:/workflow/process/list";
    }
    
    /**
     * 输出跟踪流程信息
     *
     * @param processInstanceId
     * @return
     * @throws Exception
     */
/*    @RequestMapping(value = "/process/trace")
    @ResponseBody
    public List<Map<String, Object>> traceProcess(@RequestParam("pid") String processInstanceId) throws Exception {
        List<Map<String, Object>> activityInfos = traceService.traceProcess(processInstanceId);
        return activityInfos;
    }*/
    @RequestMapping(value="/eventlog/list/{processInstanceId}")
    @ResponseBody
    public Map<String,Object> getEventLogList(@PathVariable("processInstanceId") String processInstanceId, Model model){
    	Map<String,Object> result = new LinkedHashMap<String,Object>();
    	List<EventLogEntry> list = managementService.getEventLogEntriesByProcessInstanceId(processInstanceId);
    	
    	// 提取任务任务名称
        List<HistoricTaskInstance> tasklist = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
        Map<String, String> taskNames = new HashMap<String, String>();
        for (HistoricTaskInstance historicTaskInstance : tasklist) {
            taskNames.put(historicTaskInstance.getId(), historicTaskInstance.getName());
        }
        result.put("taskNames", taskNames);
        
    	result.put("eventlogs", list );
    	
    	return result;
    }
}