<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.cngc.pm.model.Style" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>工程师工作台--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap-submenu.min.css" rel="stylesheet" type="text/css" />
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/submenu/bootstrap-submenu.min.js'></script>
    
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
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    var ctx = "${contextPath}";
    function checkedBox () {
    	var arrs = [];
		$("input[name=checkbox]").each(function() {
			if($(this).attr("checked")=='checked') {
				arrs.push($(this).val());
			}
		});
	
		return arrs;
	}
    <%--
    function transferId() {
    	var arr3 = checkedBox();
    	if(arr3=='' || arr3.length > 1) {
			notify_e('Error','请选择一项');
			return false;
		}
    	$('#id').val(arr3[0]);
    	$('#fModal').modal();
    }
	--%>
            $(document).ready(function () {

                $(".header").load("${contextPath }/header");
                $(".menu").load("${contextPath }/menu", function() {$("#node_${moduleId}").addClass("active");});
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");
                
                $('[data-submenu]').submenupicker();
                
                $("#btnCreate").click(function() {
                	window.location = "${contextPath }/document/increases/${syscode.id }";
                });
                $("#btnUpdateVersion").click(function() {
            		var arr1 = checkedBox ();
            		if(arr1.length == 1) {
            			window.location = "${contextPath }/document/update_version/"+arr1[0];
            		} else {
            			notify_e('Error','请选择一项');
		    			return false;
            		}
                });
                
                $("#delBtn").click(function() {
                		if(confirm("确定执行删除操作？")) {
        		    		//var arr = [];
        		    		//$("input[name=checkbox]").each(function() {
        		    		//	if($(this).attr("checked")=='checked') {
        		    		//		arr.push($(this).val());
        		    		//	}
        		    		//});
        		    		var arr2 = checkedBox();
        		    		if(arr2=='') {
        		    			//$("div.alert").removeClass('hide');
        		    			notify_e('Error','请至少选择一项');
        		    			return false;
        		    		} else {
        		    			//$("div.alert").addClass('hide');
        		    			$.ajax({
        		    				type : "post",
        		    				dataType : "json",
        		    				url : "${contextPath }/document/delete",
        		    				data : {ids : arr2.join(",")},
        		    				success : function(data) {
        		    					if (data.flag == "true") {
        		    						
        		    						window.location.href="${contextPath }/document/list";
        		    						
        		    					} else {
        		    						notify_e('Error','请联系管理员');
        		    					}
        		    				}
        		    			});
        		    		}
        	    		}
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
                    <li><a href="${contextPath }/Asset/list">文档管理</a> <span class="divider">></span></li>       
                    <li class="active">${syscode.codeName }</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             

                <div class="row">

                    <div class="col-md-12" >
                            
                      <ul class="nav nav-pills">
                      <li class="active"><a tabindex="0" href="${contextPath }/document/sys-code/${syscode.id }">${syscode.codeName }</a></li>
                      <c:forEach items="${styles }" var="style">
                      <li class="dropdown">
						<a tabindex="0" data-toggle="dropdown" data-submenu>${style.name } <span class="caret"></span></a>
						<ul class="dropdown-menu">
						<c:forEach items="${style.child }" var="children1">
						<li <c:if test="${!empty children1.child }">class="dropdown-submenu"</c:if>><a href="${contextPath }/document/list/style/${children1.id }">${children1.name }</a>
							<c:if test="${!empty children1.child }">
							<ul class="dropdown-menu" >
							<c:forEach items="${children1.child }" var="children2">
			                <li><a tabindex="0" href="${contextPath }/document/list/style/${children2.id}">${children2.name }</a></li>
			                </c:forEach>
			                </ul>
							</c:if>
						</li>
						
						</c:forEach>	
						</ul>			
					</li>
                      </c:forEach>
                                    </ul>
                                    <div class="dr"></div>
						<div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h2><c:if test="${empty nav}">${syscode.codeName } &gt; 文档列表</c:if><c:if test="${!empty nav}">${nav }</c:if></h2>      
                            <ul class="buttons">
                                <li>
                                    <a class="isw-settings" href="#"></a>
                                    <ul class="dd-list">
                                        <li><a id="btnCreate" href="#"><span class="isw-plus"></span> 新建文档</a></li>
                                        <li><a id="btnUpdateVersion" href="#"><span class="isw-edit"></span> 更新版本</a></li>
                                        <li><a id="delBtn" href="#"><span class="isw-delete"></span> 删除文档</a></li>
                                    </ul>
                                </li>
                            </ul>                        
                        </div>
                        <div class="block-fluid" id="inbox">
                            <table  class="table">
                                <thead>
                                    <tr>
                                       <th width="40px"><input type="checkbox" name="checkall"/></th>
                                       	<th>No.</th>
                                        <th width="10%">文档名称</th>
                                        <th width="5%">密级</th>
										<th width="7%">录入人</th>
										<th width="10%">类别</th>
										<th width="7%">编号</th>
										<th width="10%">录入时间</th>
										<th width="5%">版本</th>
										<th>链接</th>
										<th width="10%">存放位置</th>
										<th>附件</th>                                                                        
                                    </tr>
                                </thead>
                                <tbody>
                                     <c:forEach items="${listDocs}" var="doc" varStatus="itr">
                                    <tr>
                                        <td><input type="checkbox" name="checkbox" value="${doc.id }"/></td>
                                        <td>${offset + itr.index +1 }</td>
                                        <td>${doc.name }</td>
                                        <td>${doc.secretLevel.level }</td>
										<td>${doc.user.username }</td>
										<td>${doc.style.name }</td>
										<td>${doc.docNum}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${doc.createDate }" /></td>
										<td>${doc.versions}</td>
										<td><c:choose><c:when test="${empty doc.link}">-</c:when><c:otherwise><a href="${doc.link }" target="_blank">文件</a></c:otherwise></c:choose></td>
										<td>${doc.deposit}</td>
										<td><c:choose><c:when test="${empty doc.link}"><c:forEach items="${doc.attachs}" var="attach"><a href="${contextPath }/attachment/download/${attach.id}">${attach.name }</a><br/></c:forEach></c:when><c:otherwise>-</c:otherwise></c:choose></td>
                                    </tr>
                                    </c:forEach>                                
                                </tbody>
                            </table>                       
                            <div class="toolbar bottom-toolbar clearfix">
                                <tag:paginate max="5" offset="${offset}" count="${count}" uri="${url }" />
                            </div>                                                                     
                        </div>

                    </div> 
                  
                </div>
               <div class="dr"><span></span></div>
            </div>
            <!--workplace end-->
        </div>
    </div>
</body>

</html>