var p_processInstanceId;

$(document).ready(function(){
    // 操作历史信息

});  
function act_history_initdialog()
{
	$(".wrapper").append("<div class='dialog' id='b_popup_history' style='display: none;' title='操作历史'></div>");
	$("#b_popup_history").html("<div class='block dialog_block history'>	<div id='scroll_history' class='scroll' style='height:260px;'></div></div>");
    $("#b_popup_history").dialog({
        autoOpen: false,
        width: 600,
        buttons: { "关闭": function () { $(this).dialog("close") } },
        open: function () { act_history_init();  }
    });
}
function act_history_open(processInstanceId)
{
	p_processInstanceId = processInstanceId;
	$("#b_popup_history").dialog('open');
	return false;
}
var act_history_eventHandler = {
		'PROCESSINSTANCE_START':function(v,taskNames){
			return ' 启动流程';
		},
		'TASK_CREATED':function(v,taskNames){
			return ' 创建任务：' +(v.taskId?'['+taskNames[v.taskId]+']':'');
		},
		'TASK_ASSIGNED':function(v,taskNames){
			return ' 签收任务：' +(v.taskId?'['+taskNames[v.taskId]+']':'');
		},
		'TASK_COMPLETED':function(v,taskNames){
			return ' 提交任务：' +(v.taskId?'['+taskNames[v.taskId]+']':'');
		},
		'PROCESSINSTANCE_END':function(v,taskNames){
			return ' 结束流程';
		}
}
function act_history_init()
{
	$('#scroll_history').html('');
	$.getJSON(ctx + '/workflow/eventlog/list/' + p_processInstanceId + '?t=' + pm_random(),  function(datas) {
		//alert('a');
		//alert(JSON.stringify(datas.eventlogs[1]));
		var divs = "";
		if(datas.eventlogs.length==0)
			divs = "未查询到操作历史信息。";
		else
		{
			$.each(datas.eventlogs, function(i, v){
				if(v.type=='PROCESSINSTANCE_START' || v.type=='PROCESSINSTANCE_END' 
					|| v.type=='TASK_CREATED' || v.type=='TASK_ASSIGNED' || v.type=='TASK_COMPLETED')
				{
			        divs = "<div class='item'>"
		            		+"<div class='text'>"
		                +"<div class='info clearfix'>"
		                +"<span class='name'>"+(v.userId==null?'系统': datas.usernames[v.userId] )+"</span>"
		                +"<span class='date'>"+new Date(v.timeStamp).toLocaleString()+"</span>"
		                +"</div>"
		                + act_history_eventHandler[v.type](v,datas.taskNames)
		                +"</div>"
		                +"</div>"
		                + divs;  
				}
			});
		}
		$("#scroll_history").append(divs);
		$("#scroll_history").mCustomScrollbar();
	});
}