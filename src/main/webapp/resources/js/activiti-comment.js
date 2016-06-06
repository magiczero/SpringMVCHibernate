var p_processInstanceId;

function act_commont_initdialog()
{
	$(".wrapper").append("<div class='dialog' id='b_popup_comment' style='display: none;' title='意见'></div>");
	$("#b_popup_comment").html("<div class='block dialog_block messages '><div id='commentList' class='scroll' style='height:200px;'></div>"
			+"<div class='dr'><span></span></div>"	
			+"<div class='controls' id='messagebox'>"
				+" <form id='validation' action='"+ctx+"/workflow/task/comment/save' method='post'><div class=‘control'>"
                +"<textarea name='fp_message' placeholder='填写意见...' style='height: 70px; width: 100%;' class='validate[required,maxSize[2000]]'></textarea>"
                +"</div><input type='hidden' name='redirectAddress' value='/leadertask/list' />"
                +"<input type='hidden' name='fp_taskId' value='0' />"
                +"<input type='hidden' name='fp_processInstanceId' value='' />"
                +"<label class='checkbox checkbox-inline'><input type='checkbox' name='isnotify' checked='checked' value='true'/> 提醒任务办理人 </label>&nbsp;&nbsp;"
                +"<button class='btn btn-default tar'>添加意见</button>"
                +"</div></form></div>");
    $("#b_popup_comment").dialog({
        autoOpen: false,
        width: 600,
        buttons: { "关闭": function () { $(this).dialog("close") } },
        open: function () { act_comment_getlist(p_processInstanceId,'0'); $("input[name='fp_processInstanceId']").val(p_processInstanceId); }
    });
    //表单验证
    $("#validation").validationEngine({promptPosition : "topLeft", scroll: true});
}
function act_comment_open(processInstanceId,isfinished)
{
	p_processInstanceId = processInstanceId;
	if(isfinished)
		$("#messagebox").hide();
	else
		$("#messagebox").show();
	$("#b_popup_comment").dialog('open');
	return false;
}
/**
 * 读取事件列表
 * @return {[type]} [description]
 */
function act_comment_getlist(processInstanceId,taskId) {
	
	$('#commentList').html('');
	// 读取意见
	$.getJSON(ctx + '/workflow/task/comment/list?t=' + pm_random(), {
		processInstanceId: processInstanceId,
		taskId: taskId
	}, function(datas) {
		var divs = "";
		$("#commentTitle").html( datas.events.length );
		if(datas.events==0)
			divs = "<div class='tac'>未查询到相关意见信息。</div>";
		else
		{
			$.each(datas.events, function(i, v) {
				var tdiv = "<div class='item clearfix'>";
				var user = v.userId ?datas.usernames[v.userId]:'';
				if(user) {
					user =  "<div class='image'><a href='#'><img src='"+ctx+"/resources/img/users/aqvatarius.jpg' class='img-thumbnail' /></a></div>"
					+"<div class='info'>"
					+" <a href='#' class='name'>"+user+"</a>";
				}
				var msg = v.message || v.fullMessage;
				var comment = act_comment_eventHandler[v.action](v, msg);
				var taskName = datas.taskNames ? datas.taskNames[v.taskId] : '';
	
				// 名称不为空时才显示
				if(taskName) {
					user += " [ " + taskName + " ]";
				}
				tdiv += user +"<p>"+ comment + "</p><span>"+new Date(v.time).toLocaleString()+"</span>";
				if(datas.enables[v.id] && datas.enables[v.id]=='true')
				{
					tdiv += "<div class='controls'>"                                    
						+ " <a href='#' msgid='"+v.id+"' class='lnk_comment_delete glyphicon glyphicon-trash tip' title='删除'></a>"
						+ "</div>";
				}
				tdiv += "</div></div>";
				divs += tdiv;
			});
		}
		$("#loader_comment").hide();
		$("#commentList").append(divs);
		// 绑定删除按钮事件
		$(".lnk_comment_delete").bind("click",function(){
			act_comment_delete($(this).attr("msgid"));
			return false;
		});
		$(".scroll").mCustomScrollbar();
		
	});
}
function act_comment_delete(id)
{
	if(!confirm("确定要删除该条意见?"))
   		return false;
	$.getJSON(ctx+"/workflow/task/comment/delete/"+id,function(){
		act_comment_getlist(processInstanceId,taskId);
	});
}
/*
根据英文类型翻译为中文
 */
function act_comment_translateType(event) {
	var types = {
		"1": "贡献人",
		"2": "项目经理",
		"3": "总经理",
		"4": "业务顾问",
		"5": "技术顾问",
		"6": "执行人",
		"owner": "拥有人",
		"candidate": "候选"
	};

	var type = (types[event.messageParts[1]] || '');
	if(type == '候选') {
		if(event.action.indexOf('User') != -1) {
			return '候选人';
		} else {
			return '候选组';
		}
	}
	return type;
}

/*
事件处理器
 */
var act_comment_eventHandler = {
	'DeleteAttachment': function(event,  msg) {
		return  '<span class="text-error">删除</span>了附件：' + msg;
	},
	'AddAttachment': function(event,  msg) {
		return  '添加了附件：' + msg;
	},
	'AddComment': function(event,  msg) {
		return  '发表了意见：<span class="text-danger">' + msg + '</span>';
	},
	'DeleteComment': function(event,  msg) {
		return  '<span class="text-error">删除</span>了意见：' + msg;
	},
	AddUserLink: function(event,  msg) {
		return  '邀请了<span class="text-info">' + event.messageParts[0] + '</span>作为任务的[<span class="text-info">' + act_comment_translateType(event) + '</span>]';
	},
	DeleteUserLink: function(event,  msg) {
        return  '<span class="text-error">取消了</span><span class="text-info">' + event.messageParts[0] + '</span>的[<span class="text-info">' + act_comment_translateType(event) + '</span>]角色';
	},
	AddGroupLink: function(event,  msg) {
		return  '添加了[<span class="text-info">' + act_comment_translateType(event) + ']</span>' + event.messageParts[0];
	},
	DeleteGroupLink: function(event,  msg) {
		return  '从[<span class="text-info">' + act_comment_translateType(event) + '</span>]中<span class="text-error">移除了</span><span class="text-info">' + event.messageParts[0] + '</span>';
	}
}