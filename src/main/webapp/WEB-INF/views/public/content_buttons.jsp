<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<script type='text/javascript' src='${contextPath }/resources/js/pm-message.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/pm-task.js'></script>
<li><a href="#" class="link_bcPopupTask"><span class="glyphicon glyphicon-tasks"></span><span class="text">待办任务<span class="task_count"></span></span></a>
	<div id="bcPopupTask" class="popup">
		<div class="head clearfix">
			<div class="arrow"></div>
			<span class="isw-list"></span> <span class="name">待办任务 <span class="task_count"></span></span> 
		</div>
		<div class="body tips" id="popup_task_box"></div>
		<div class="footer">
			<span style="color: #666666;">仅显示最新 5 件任务 &nbsp;&nbsp;</span> <a href="${contextPath }/workflow/task/mytask" role="button" class="btn btn-default" data-toggle="modal">查看全部</a>
			<button class="btn btn-danger link_bcPopupTask" type="button">关闭</button>
		</div>
	</div></li>
<li><a href="#" class="link_bcPopupMessage"><span class="glyphicon glyphicon-comment"></span><span class="text">消息<span class="popup_messages_count"></span></span></a>
	<div id="bcPopupMessage" class="popup">
		<div class="head clearfix">
			<div class="arrow"></div>
			<span class="isw-chat"></span> <span class="name">消息 <span class="popup_messages_count"></span></span>
		</div>
		<div class="body tips" id="popup_messages_box"></div>
		<div class="footer">
			<span style="color: #666666;">仅显示最新 5 条消息 &nbsp;&nbsp;</span> <a href="${contextPath }/message/list" role="button" class="btn btn-default" data-toggle="modal">查看全部</a>
			<button class="btn btn-danger link_bcPopupMessage" type="button">关闭</button>
		</div>
	</div></li>
<li><a href="#" class="link_bcPopupList"><span class="glyphicon glyphicon-user"></span><span class="text">用户</span></a>
	<div id="bcPopupList" class="popup">
		<div class="head clearfix">
			<div class="arrow"></div>
			<span class="isw-users"></span> <span class="name">用户列表</span>
		</div>
		<div class="body-fluid users" id="popup_users_box"></div>
		<div class="footer">
			<button class="btn btn-danger link_bcPopupList" type="button">关闭</button>
		</div>
	</div></li>
<li><a href="#" class="link_bcPopupSearch"><span class="glyphicon glyphicon-search"></span><span class="text">搜索</span></a>

	<div id="bcPopupSearch" class="popup">
		<div class="head clearfix">
			<div class="arrow"></div>
			<span class="isw-zoom"></span> <span class="name">搜索知识库</span>
		</div>
		<form action="${contextPath }/knowledge/search">
			<div class="body search">
				<input type="text" placeholder="关键字..." name="keyword" />
			</div>
			<div class="footer">
				<button class="btn btn-primary">搜索知识</button>
				<button class="btn btn-danger link_bcPopupSearch" type="button">关闭</button>
			</div>
		</form>
	</div></li>
<script type="text/javascript">
	$(document).ready(function() {
		$(".link_bcPopupTask").click(function() {
			if ($("#bcPopupTask").is(":visible")) {
				$("#bcPopupTask").fadeOut(200);
			} else {
				pm_mytask_gettips();
				$("#bcPopupTask").fadeIn(300);
			}
			return false;
		});
		$(".link_bcPopupMessage").click(function() {
			if ($("#bcPopupMessage").is(":visible")) {
				$("#bcPopupMessage").fadeOut(200);
			} else {
				pm_message_gettips();
				$("#bcPopupMessage").fadeIn(300);
			}
			return false;
		});
		$(".link_bcPopupList").click(function() {
			if ($("#bcPopupList").is(":visible")) {
				$("#bcPopupList").fadeOut(200);
			} else {
				pm_user_getcurrent();
				$("#bcPopupList").fadeIn(300);
			}
			return false;
		});

		$(".link_bcPopupSearch").click(function() {
			if ($("#bcPopupSearch").is(":visible")) {
				$("#bcPopupSearch").fadeOut(200);
			} else {
				$("#bcPopupSearch").fadeIn(300);
			}
			return false;
		});
		pm_message_gettipscount();
		pm_task_getmytaskcount();

		// 定时刷新
		//setInterval(pm_message_gettipscount, 10000);
		//setInterval(pm_task_getmytaskcount, 10000);
	});
	function pm_task_getmytaskcount() {
		$.getJSON(ctx + "/workflow/task/getmytaskcount?t" + pm_random(),
				function(datas) {
					if (datas.count == "0")
						return;
					$(".task_count").text(
							"(" + datas.claim + "/" + datas.count + ")");
				});
	}
	function pm_user_getcurrent(){
		$("#popup_users_box").html('');
		$.getJSON(ctx + "/getcurrentusers?t" + pm_random(),
				function(datas) {
					if (datas.users.length == 0)
						return;
					var divs = "";
					for(var i=0;i<datas.users.length;i++)
					{
						divs += "<div class='item clearfix'>"
								+"<div class='image'>"
								+"<a href='#''><img src='"+ctx+"/resources/img/users/aqvatarius_s.jpg' width='32' /></a>"
								+"</div>"
								+"<div class='info'>"
								+"<a href='#' class='name'>"+datas.users[i]+"</a> <span>在线</span>"
								+"</div>"
								+"</div>";
					}
					$("#popup_users_box").append(divs);
				});
	}
</script>