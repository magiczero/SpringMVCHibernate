<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="breadLine">
	<div class="arrow"></div>
	<div class="adminControl active">
		${user.name}&nbsp;[	<sec:authentication property="name" />]
	</div>
</div>

<div class="admin">
	<div class="image">
		<img src="${contextPath }/resources/img/users/user.jpg" class="img-thumbnail" />
	</div>
	<ul class="control">
		<li><span class="glyphicon glyphicon-comment"></span> <a href="${contextPath }/message/list">消息</a></li>
		<li><span class="glyphicon glyphicon-cog"></span> <a href="#pwdModal" role="button" data-toggle="modal">修改密码</a></li>
		<li><span class="glyphicon glyphicon-share-alt"></span> <a href="#exitModal" role="button" data-toggle="modal">退出</a></li>
	</ul>
	<div class="info">
		<span>欢迎回来！上次登录:<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${lastLogin }" /></span>
	</div>
	
</div>
<div id="tree"></div>
<div class="modal fade" id="pwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="pwdForm" action="${contextPath }/user/update-pwd" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4>修改密码</h4>
				</div>
				<div class="modal-body modal-body-np">
					<div class="row">
						<div class="block-fluid">
							<div class="row-form clearfix">
								<div class="col-md-3">旧密码:</div>
								<div class="col-md-9">
									<input type="password" name="oldPwd" class="validate[required]" />
								</div>
							</div>
							<div class="row-form clearfix">
								<div class="col-md-3">新密码:</div>
								<div class="col-md-9">
									<input type="password" name="newPwd" class="validate[required,minSize[6],maxSize[8],equals[repeatPwd]]" />
								</div>
							</div>
							<div class="row-form clearfix">
								<div class="col-md-3">新密码确认:</div>
								<div class="col-md-9">
									<input type="password" id="repeatPwd" name="repeatPwd" class="validate[required,minSize[6],maxSize[8]]" />
								</div>
							</div>
						</div>
						<div class="dr">
							<span></span>
						</div>
						<div class="block">
							<p>密码修改成功后，会自动转到登录页面，请使用新密码登录。</p>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<input class="btn btn-warning" type="submit" value="确认修改" />
					<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="exitModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="pwdForm" action="${contextPath }/logout" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4>退出</h4>
				</div>
				<div class="modal-body modal-body-np">
					<div class="row">
						<div class="block">
							<p>是否要立即退出系统？</p>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<input class="btn btn-warning" type="submit" value="退出" />
					<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script type='text/javascript' src='${contextPath }/resources/js/mymenu.js'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/treeview/bootstrap-treeview.min.js'></script>
<script>
	var ctx1 = "${contextPath }";
	$(document).ready(function() {
		pm_getcount();
		//表单验证
		$("#pwdForm").validationEngine({
			promptPosition : "topRight",
			scroll : true
		});
		
		 $.ajax({
             type: 'Get',
             url: ctx1+'/menu-json',
             //data: {reqUrl:"${reqestUrl}"},
             dataType: 'json',
             async: false,
             success: function (data) {
                 var defaultData = eval(data);
                 
                 $('#tree').treeviewbootstrap({
                     //color: "#428bca",
                     color: "#369",
                     enableLinks:true,
                     data: defaultData
                 });
                 
                 //$('#tree').treeviewbootstrap('expandNode',7);
                 //$('#tree').treeviewbootstrap('selectNode',9);
             },
             error: function (err) {
            	 //console.log(err);
                 alert('不好意思，读取菜单时出错了,请联系管理员^^^');
             }
		 });
		 
	});
	function pm_getcount() {
		$.getJSON(ctx + "/workflow/task/getmytaskcount?t" + pm_random(),
				function(datas) {
					if (datas.count == "0")
					{
						$(".link_navPopMessages").hide();
					}
					else
					{
						$(".link_navPopMessages").show();
						$(".link_navPopMessages").text(datas.count);
					}
				});
	}
	
</script>
