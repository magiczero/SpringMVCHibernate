function pm_mytask_gettips()
{
	$("#popup_task_box").html('');
	$.getJSON(ctx+"/workflow/task/getmytask?t"+pm_random(),function(datas){
		var divs = "";
		var t = JSON.parse(datas.tasks);
		if(t.length==0)
		{
			$("#popup_task_count").html('');
			$("#popup_task_box").append("暂时没有待办任务");
			return;
		}
		
		$.each(t, function(i, v) {
			divs += "<div class='item clearfix'>"
				+"<a href='#' class='name'>"+v.processname+"</a>"
				+"<p><span class='text-danger'>"+v.user+"</span> 发起流程已进行至 ["+v.taskname+"]，请处理。</p>"
				+"<span class='date'>"+new Date(v.createtime).toLocaleString()+"</span>"
				+"<div class='controls'>"                                    
                +"<a href='"+ctx+v.url+"' class='glyphicon glyphicon-hand-right tip' title='处理' ></a>"
                +"</div>"
				+"</div>";
		});
		$("#popup_task_box").append(divs);
	});
}