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

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/treeview/bootstrap-treeview.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.resize.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/myactions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/charts.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/faq.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
            $(document).ready(function () {
                $("#eventTable").dataTable();

                $(".header").load("${contextPath }/header");
                $(".menu").load("${contextPath }/menu", function() {$("#node_${moduleId}").addClass("active");});
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");
                
                $("#authority").validationEngine({promptPosition : "topLeft", scroll: true});
                
                if($("#setResources").length > 0){
                    $("#setResources").multiSelect({
                        selectableHeader: "<div class='multipleselect-header'>所有资源</div>",
                        selectedHeader: "<div class='multipleselect-header'>已选择</div>"
                    });
                    $('#multiselect-selectAll').click(function(){
                        $('#setResources').multiSelect('select_all');
                        return false;
                    });
                    $('#multiselect-deselectAll').click(function(){
                        $('#setResources').multiSelect('deselect_all');
                        return false;
                    });
                    $('#multiselect-selectIndia').click(function(){
                        $('#setResources').multiSelect('select', 'in');
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
											<c:forEach items="${auth.setResources }" var="reso">
												${reso.name }<br />
											</c:forEach>
										</td>
										<td>${auth.authorityDesc}</td>
										<td><a class="btn btn-default" href="${contextPath }/authority/init-update/${auth.id}">修改</a></td>
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
                    <form:form action="${addAction}" commandName="authority">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="authorityName">权限名称*：</form:label></div>
                                    <div class="col-md-9"><form:input path="authorityName" class="validate[required,minSize[2],maxSize[20]]"/></div>
                                </div>        
                                <div class="row">
                                    
										<div class="block">                        
											<form:select multiple="true" path="setResources" class="validate[required] multiselect">
												<form:options items="${listResources}" itemValue="id" itemLabel="name"/>
											</form:select>
                            
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