<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>终端合规性管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/jstree/jquery.treeview.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx="${contextPath}";
		var groupId="${group.id}";
		var delpath = "${contextPath}/computer/delete/";
        $(document).ready(function () {
            $("#eventTable").dataTable({
            	"oLanguage": {
         			"sUrl": "${contextPath}/resources/json/Chinese.json"
     			},"aoColumns": [ { "bSortable": false },{ "bSortable": false }, null,null,null,null,null,null]});
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $(".navigation > li:eq(1)").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            $("#mytree").treeview({collapsed:true}); 
            //alert(groupId);
            //var obj =  $("a[data='"+groupId+"']");
            //obj.parents("li").removeClass("expandable").addClass("collapsable");
            //obj.parents("li").find("div").click();
            $("#chkAll").bind("click",function(){
            	if( $("#chkAll").attr("checked")=="checked" )
            	{
            		//全选
            		$("#eventTable input:checkbox").attr("checked",true);
            	}
            });
            $("#lnkDelete").bind("click",function(){
            	//删除
            	if( $("#eventTable input.item:checked").length==0  )
            	{
            		alert("请选择需要删除的终端");
            		return false;
            	}
            	else
            	{
            		if(!confirm("确定要删除所选终端?"))
                    	return false;
            		else
            		{
            			window.location.href = delpath + getIds();
            			return false;
            			
            		}
            	}
            });
            $("#lnkSetGroup").bind("click",function(){
            	//删除
            	if( $("#eventTable input.item:checked").length==0  )
            	{
            		alert("请选择需要设置部门的终端");
            		return false;
            	}
            	$("#ids").val(getIds());
            	$("#newForm").modal();
            });
            function getIds()
            {
            	var sid = "";
    			$("#eventTable input.item:checked").each(function(){
    				if(sid!="")
    					sid = sid + ",";
    				sid = sid + $(this).val();
    			});
    			return sid;
            }
            $(".confirm").bind("click",function(){
            	if(!confirm("确定要执行该操作?"))
                	return false;
        	});
        });
    </script>
</head>
<body>
    <div class="wrapper"> 

        <!--header-->
        <div class="header"></div>
        <!--header end-->
        
        <!--menu-->
        <div class="menu"></div>
        <!--menu end-->

        <div class="content">
            <!--breadline-->
            <div class="breadLine">

                <ul class="breadcrumb">
                    <li><a href="#">运维管理系统</a> <span class="divider">></span></li>
                    <li><a href="#">终端合规性管理</a> <span class="divider">></span></li>       
                    <li class="active">用户终端管理</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             
				<div class="alert alert-danger hide">                
                    <h4>错误!</h4>请至少选择一项
                </div> 
                <div class="row">
                	<div class="col-md-3">
                		<a href="${contextPath }/computer/notregisted" role="button" class="btn btn-default btn-block">新注册用户终端</a>
                		<div class="head clearfix">
                        	<div class="isw-users"></div>
                           <h1>部门</h1>
                        </div>
                        <div class="block-fluid clearfix">
                        	<div id="mytree" class="treeview">
                        	  <c:forEach items="${groupList }" var="group">
                        	  		<li class="open">
                        	  			<c:if test="${empty group.child }">
                        	  				<span class="file"><span class="glyphicon glyphicon-tasks"></span> <a href="${contextPath }/computer/list/${group.id}" data="${group.id }" class="group">${group.groupName }</a></span>
                        	  			</c:if>
                        	  			<c:if test="${!empty group.child }">
	                        	  			<span class="folder"><span class="glyphicon glyphicon-tasks"></span> <a href="${contextPath }/computer/list/${group.id}"  data="${group.id }" class="group">${group.groupName }</a></span>
	                        	  			<ul>
		                        	  			<c:forEach items="${group.child }" var="child">
		                        	  				<c:if test="${empty child.child }">
		                        	  					<li><span class="file"><span class="glyphicon glyphicon-tasks"></span> <a href="${contextPath }/computer/list/${child.id}"  data="${child.id }" class="group">${child.groupName }</a></span></li>
		                        	  				</c:if>
		                        	  				<c:if test="${!empty child.child }">
		                        	  					<li><span class="folder"><span class="glyphicon glyphicon-tasks"></span> <a href="${contextPath }/computer/list/${child.id}"  data="${child.id }" class="group">${child.groupName }</a></span>
		                        	  					<ul>
		                        	  					<c:forEach items="${child.child }" var="child1">
		                        	  						<c:if test="${empty child1.child }">
		                        	  							<li><span class="file"><span class="glyphicon glyphicon-tasks"></span> <a href="${contextPath }/computer/list/${child1.id}"  data="${child1.id }" class="group">${child1.groupName }</a></span></li>
		                        	  						</c:if>
		                        	  						<c:if test="${!empty child1.child }">
				                        	  				<li><span class="folder open"><span class="glyphicon glyphicon-tasks"></span> <a href="${contextPath }/computer/list/${child1.id}"  data="${child1.id }" class="group">${child1.groupName }</a></span></li>
				                        	  				<ul>
				                        	  					<c:forEach items="${child1.child}" var="child2">
																	<li><span class="file"><span class="glyphicon glyphicon-tasks"></span> <a href="${contextPath }/computer/list/${child2.id}"  data="${child2.id }" class="group">${child2.groupName }</a></span></li>
																</c:forEach>
															</ul>
															</c:if>
														</c:forEach>
														</ul></li>
													</c:if>
												</c:forEach>
											</ul>
										</c:if>
                        	  		</li>
								</c:forEach>
                            </div>
                        </div>
                        
                    </div>
                    <div class="col-md-9">   
                    	<a href="#" id="lnkSetGroup" role="button" class="btn btn-default">批量设置部门</a>  
                    	<a href="#" id="lnkDelete" role="button" class="btn btn-default">批量删除</a>                
                        <div class="head clearfix">
                            <div class="isw-user"></div>
                            <h1>用户终端管理--
                            	<c:if test="${!empty group }">${group.groupName }</c:if>
                            	<c:if test="${empty group }">新注册</c:if>
                            </h1>  

                            <ul class="buttons">  
                            	<li>
                                    <a href="${contextPath }/interface/register" role="button" class="isw-plus tipb" target=_blank title="用户终端注册"></a>
                                </li>                        
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="${contextPath }/interface/register" target=_blank><span class="isw-plus"></span> 用户终端注册</a></li>
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>
                                		<th width="40px"><input type="checkbox" id="chkAll"/></th>
                                		<th width="60px">序号</th>
										<th>终端名称</th>
										<th width="140px">所在部门</th>
										<th width="140px">IP地址</th>
										<th width="60px">版本</th>
										<th width="80px">最后访问</th>
										<th width="120px">最后检查</th>
									</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list}" var="computer" varStatus="st">
									<tr>
										<td><input type="checkbox" class="item" value="${computer.id }"/></td>
										<td>${st.index+1 }</td>
										<td>${computer.userName }</td>
										<td>${computer.group.groupName }</td>
										<td><span class="tipb" title="MAC:${computer.mac }磁盘序列号:${computer.diskSn}" style="cursor:pointer;">${computer.ip }</span></td>
										<td>${computer.version }</td>
										<td><fmt:formatDate value="${computer.lastRebackDate }" pattern="yyyy/MM/dd HH:mm" /></td>
										<td>
											<c:if test="${not empty computer.lastTaskId }">
												<a href="${contextPath }/analyse/view/${computer.id }/${computer.lastTaskId}/COMPUTERINFO" class="tipb" title="查看详情">
												<c:if test="${computer.compliance }">
													<span class='label label-success'>合规</span>
												</c:if>
												<c:if test="${!computer.compliance }">
													<span class='label label-danger'>不合规</span>
												</c:if>
												</a>
											</c:if>
											<c:if test="${empty computer.lastTaskId }">
												<span class='label label-warning'>未知</span>
											</c:if><br>
											<fmt:formatDate value="${computer.lastCheckDate }" pattern="yyyy/MM/dd HH:mm" />
										</td>
									</tr>
								</c:forEach>   
                                </tbody>
                            </table>
                        </div>
                    </div>  
                  
                </div>
                <div class="dr"><span></span></div>
            </div>
            <!--workplace end-->
        </div>   
     	<!-- modal form -->
        <div class="modal fade" id="newForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>设置部门</h4>
                    </div>
                    <form id="validation" action="${contextPath}/computer/setdepartment" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">部门：</div>
                                    <div class="col-md-9">
	                                    <select name="group">
											<c:forEach items="${groupList }" var="group">
												<option value="${group.id }">&nbsp;${group.groupName }</option>
												<c:forEach items="${group.child }" var="child">
													<option value="${child.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
														- ${child.groupName }</option>
													<c:forEach items="${child.child }" var="child1">
														<option value="${child1.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
															- ${child1.groupName }</option>
													</c:forEach>
												</c:forEach>
											</c:forEach>
										</select>
									</div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>   
                        <input type="hidden" id="ids"  name="ids" value="0" />         
                    </div>
                    </form>
                </div>
            </div>
        </div>
    	<!-- modal form end -->
    </div>
</body>

</html>