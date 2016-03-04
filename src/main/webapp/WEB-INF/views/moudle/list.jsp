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

    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/fullcalendar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/select2.css" rel="stylesheet" type="text/css" />
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
    <link href="${contextPath }/resources/css/dataTables.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath }/resources/css/stylesheets2.css" rel="stylesheet" type="text/css" />
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
    <%--
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.resize.js'></script>
     --%>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    
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
        
    
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
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
                $(".breadLine .buttons").load("${contextPath }/contentbuttons?t="+pm_random());
                
                $("#moudle").validationEngine({promptPosition : "topRight", scroll: true});
            });
            
            function changeStatus(obj,id) {
            	$.ajax({
    				type : "post",
    				dataType : "json",
    				url : "${contextPath }/moudle/enable/"+id,
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
                    <li class="active">菜单管理</li>
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
                            <h1>系统菜单列表</h1>  

                            <ul class="buttons">                          
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                        <li><a href="#fModal" data-toggle="modal"><span class="isw-list"></span> 添加</a></li>
                                        <li><a href="#"><span class="isw-ok"></span> 查看未指派</a></li>
                                        <li><a href="#"><span class="isw-minus"></span> 查看已超期事件</a></li>
                                        <li><a href="#"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table  class="table" id="eventTable">
                                <thead>
                                    <tr>
                                        <th width="40px"><input type="checkbox" name="checkall"/></th>
                                        <th width="25%">菜单名称</th>
										<th>路径</th>
										<th width="10%">层级</th>
										<th width="25%">图标</th>
										<th width="10%">是否启用</th>
										<th width="8%">优先级</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listmoudle}" var="moudle">
                                    <tr>
                                        <td><input type="checkbox" name="checkbox"/></td>
                                        <td><c:if test="${moudle.parent!=null }">${moudle.parent.name }&nbsp;----&nbsp;</c:if>${moudle.name }</td>
										<td>${moudle.url }</td>
										<td>${moudle.level}</td>
										<td>${moudle.styleClass }</td>
										<td><c:choose><c:when test="${moudle.enable }"><button class="btn btn-success" onclick="changeStatus(this,${moudle.id });" type="button"> 启用 </button></c:when><c:otherwise><button class="btn btn-danger" onclick="changeStatus(this,${moudle.id });" type="button"> 停用 </button></c:otherwise></c:choose></td>
										<td>${moudle.priority }</td>
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
                        <h4>菜单信息</h4>
                    </div><c:url var="addAction" value="/moudle/save" ></c:url>
                    <form:form action="${addAction}" commandName="moudle">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="name">菜单名称*：</form:label></div>
                                    <div class="col-md-9"><form:input path="name" class="validate[required,minSize[2],maxSize[12]]"/></div>
                                </div>            
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="parent.id">上级菜单：</form:label></div>
                                    <div class="col-md-9">
                                    	<form:select path="parent.id">
                                    		<form:option value=""></form:option>
                                    		<c:forEach items="${listmoudle}" var="moudle2">
                                    		<c:if test="${moudle2.level == 1 }">
                                    		<form:option value="${moudle2.id }">${moudle2.name }</form:option></c:if>
                                    		</c:forEach>
                                    	</form:select>
                                    </div>                    
                                </div>      
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="url">路径*：</form:label></div>
                                    <div class="col-md-9"><form:input path="url" value="#" class="validate[required,maxSize[50]]"/></div>
                                </div> 
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="styleClass">样式*：</form:label></div>
                                    <div class="col-md-9"><form:input path="styleClass" class="validate[required,minSize[2],maxSize[50]]"/></div>
                                </div>    
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="priority">优先级*：</form:label></div>
                                    <div class="col-md-9"><form:input path="priority" class="validate[required,integer]" /></div>
                                </div>                          
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="desc">说明：</form:label></div>
                                    <div class="col-md-9"><form:textarea path="desc"/></div>
                                </div>                                                
                            </div>                
                            <div class="dr"><span></span></div>
                            <div class="block">                
                                <p>注意：上级菜单可不选，这样此菜单为顶级菜单，菜单为顶级菜单是，路径请填入“#”</p>
                            </div>
                        </div>
                    </div>   
                    <div class="modal-footer">
                         <input type="submit" class="btn btn-warning" value=" 保存 " />
                        <!--<button class="btn btn-warning" data-dismiss="modal" aria-hidden="true">Save updates</button>   -->
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Close</button>            
                    </div></form:form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>