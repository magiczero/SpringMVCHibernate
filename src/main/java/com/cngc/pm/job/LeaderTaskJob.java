package com.cngc.pm.job;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Comment;
import org.apache.commons.lang3.time.FastDateFormat;

import com.cngc.pm.model.LeaderTask;
import com.cngc.pm.service.LeaderTaskService;
import com.cngc.pm.service.MessageService;

public class LeaderTaskJob {

	@Resource
	private LeaderTaskService leaderTaskService;
	@Resource
	private MessageService messageServcie;
	@Resource
	private TaskService taskService;
	
	public void taskNotify(){
		System.out.println("领导交办任务提醒...");
		
		List<LeaderTask> list = leaderTaskService.getNotFinishedTask().getResult();
		
		FastDateFormat fmt = FastDateFormat.getInstance("yyyy-MM-dd");   
		Date current = new Date();
		String sdate = fmt.format(current);
		try {
			current = fmt.parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String msg = "";
		
		for(LeaderTask task:list)
		{
			if(task.getDueTime().compareTo(current)<=0)
			{
				//今天是最后时间
				msg = "领导交办任务："+task.getTaskTitle()+" 交办时间为:"
						+fmt.format(task.getDueTime())+"，请及时处理。" ;
				messageServcie.sendMessage("系统", task.getToUser(), msg, "#");
			}
			else
			{
				//提醒填报
				boolean isadd = false;
				List<Comment> comments = taskService.getProcessInstanceComments(task.getProcessInstanceId());
				for(Comment cmt:comments)
				{
					if(cmt.getUserId().equals(task.getToUser())&&fmt.format(cmt.getTime()).equals(sdate))
						isadd = true;
				}
				if(!isadd)
				{
					// 当天没有添加意见
					msg = "领导交办任务："+task.getTaskTitle()+" 今日未添加处理进度，请及时填写。" ;
					messageServcie.sendMessage("系统", task.getToUser(), msg, "#");
				}
			}
		}
	}
}
