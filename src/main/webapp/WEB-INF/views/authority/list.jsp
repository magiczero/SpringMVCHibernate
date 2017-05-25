<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>系统管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/fullcalendar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/cleditor.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/elfinder.min.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/elfinder.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/multi-select.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ibutton.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stepy.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/tagsinput.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/fullcalendar.print.css' media='print' />
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-en.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
        
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    var ctx = "${contextPath}";
            $(document).ready(function () {
                $("#eventTable").dataTable({
                	"oLanguage": {
             			"sUrl": "${contextPath}/resources/json/Chinese.json"
         			}
                });

                $(".header").load("${contextPath }/header?t="+pm_random());
                $(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                
                $("#authority").validationEngine({promptPosition : "topLeft", scroll: true});
                
                if($("#resos").length > 0){
                    $("#resos").multiSelect({
                        selectableHeader: "<div class='multipleselect-header'>所有资源</div>",
                        selectedHeader: "<div class='multipleselect-header'>已选择</div>"
                    });
                    $('#multiselect-selectAll').click(function(){
                        $('#resos').multiSelect('select_all');
                        return false;
                    });
                    $('#multiselect-deselectAll').click(function(){
                        $('#resos').multiSelect('deselect_all');
                        return false;
                    });
                    $('#multiselect-selectIndia').click(function(){
                        $('#resos').multiSelect('select', 'in');
                        return false;
                    });         
                 }
            });
            
            function changeStatus(obj,id) {
            	$.ajax({
    				type : "post",
    				dataType : "json",
    				url : "${contextPath }/resource/enable/"+id,
    				success : function(data) {
    					if (data.flag == "true") {
    						if(obj.className=="btn btn-success") {
	    						obj.className="btn btn-danger";
	    		            	obj.innerHTML = " 停用 ";
    						} else {
    							obj.className="btn btn-success";
	    		            	obj.innerHTML = " 启用 ";
    						}
    		            	notify_e('Success','修改状态成功');
    					} else {
    						notify_e('Error','状态修改失败');
    					}
    				}
    			});
            	
            }
            
            function del(id){
            	if(confirm('确定删除？')) {
	            	$.ajax({
	    				type : "delete",
	    				dataType : "json",
	    				url : "${contextPath }/authority/"+id,
	    				success : function(data) {
	    					if (data.flag == "true") {
	    		            	notify_e('Success','删除成功');
	    		            	window.location = "${contextPath }/authority/list";
	    					} else {
	    						notify_e('Error','删除失败，请检查此权限与资源或角色的关联关系');
	    					}
	    				}
	    			});
	            	return true;
            	} else {
            		return false;
            	}
            }
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
                    <li><a href="#">系统管理</a> <span class="divider">></span></li>       
                    <li class="active">权限管理</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             

               


                <div class="row">
                    <div class="col-md-12">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>权限列表</h1>  

                            <ul class="buttons">    
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                        <li><a href="#fModal" data-toggle="modal"><span class="isw-list"></span> 添加</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table  class="table" id="eventTable">
                                <thead>
                                    <tr>
                                        <th width="40px"><input type="checkbox" name="checkall"/></th>
                                        <th width="20%">权限名称</th>
										<th>资源</th>
										<th width="25%">说明</th>
										<th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listAuths}" var="auth">
                                    <tr>
                                        <td><input type="checkbox" name="checkbox"/></td>
                                        <td>${auth.authorityName }</td>
										<td>
											<c:forEach items="${auth.authResos }" var="reso">
												${reso.resources.name }<br />
											</c:forEach>
										</td>
										<td>${auth.authorityDesc}</td>
										<td><a class="btn btn-default" href="${contextPath }/authority/init-update/${auth.id}">修改</a>
										<a class="btn btn-default" onclick="javascript:del(${auth.id});">删除</a>
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
        <div class="modal fade" id="fModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>权限信息</h4>
                    </div><c:url var="addAction" value="/authority/save" ></c:url>
                    <form:form action="${addAction}" commandName="authority" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="authorityName">权限名称*：</form:label></div>
                                    <div class="col-md-9"><form:input path="authorityName" class="validate[required,minSize[2],maxSize[20]]"/></div>
                                </div>        
                                <div class="row">
                                    
										<div class="block">                        
											<%-- <form:select multiple="true" path="setResources" class="validate[required] multiselect">
												<form:options items="${listResources}" itemValue="id" itemLabel="name"/>
											</form:select>--%>
											<select multiple id="resos" name="resos" class="validate[required] multiselect">
												<c:forEach items="${listResources }" var="resources">
												<option value="${resources.id }">${resources.name }</option>
												</c:forEach>
											</select>
                            
				                            <div class="btn-group">
				                                <button class="btn btn-default btn-xs" id="multiselect-selectAll" type="button">全选</button>
				                                <button class="btn btn-default btn-xs" id="multiselect-deselectAll" type="button">全不选</button>
				                            </div>                             
				                        
                                </div>       
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="authorityDesc">说明：</form:label></div>
                                    <div class="col-md-9"><form:textarea path="authorityDesc"/></div>
                                </div>                                                
                            </div>                
                            <div class="dr"><span></span></div>
                            <div class="block">                
                                <p>&nbsp;</p>
                            </div>
                        </div>
                    </div>   
                    <div class="modal-footer">
                         <input type="submit" class="btn btn-warning" value=" 保存 " />
                        <!--<button class="btn btn-warning" data-dismiss="modal" aria-hidden="true">Save updates</button>   -->
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Close</button>            
                    </div></div></form:form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>