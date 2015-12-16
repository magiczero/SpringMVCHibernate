<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.cngc.pm.model.Style" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/treeview/bootstrap-treeview.min.js'></script>
    
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
                
                
                $("#itemSearch").click(function() {
            		window.location = "${contextPath }/checkitems/list/items/"+$("#itemsid").val();
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
                    <li><a href="${contextPath }/checkitems/list">检查项</a> <span class="divider">></span></li>       
                    <li class="active">检查项列表</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             

                <div class="row">
                     <!-- <div class="col-md-2 clearfix" id="mails_navigation">                    
                        <span class="btn btn-success btn-block" >文档类别</span>
                         <div id="tree"></div>    

                    </div> -->

                    <div class="col-md-12" id="mails">
                        <div class="headInfo">
                            <div class="input-group row-form clearfix">
                                <!-- <input type="text" name="search" placeholder="search keyword..." id="widgetInputMessage" class="form-control"/> -->
                                <select name="search" id="itemsid" class="form-control">
                                	<option></option>
                                	<c:forEach items="${itemsList }" var="oneLevel">
                                		<option value="1-${oneLevel.id }">${oneLevel.name }</option>
                                		<c:forEach items="${oneLevel.child }" var="twoLevel">
                                		<option value="2-${twoLevel.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${twoLevel.name }</option>
                                		<c:forEach items="${twoLevel.child }" var="threeLevel">
                                		<option value="3-${threeLevel.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${threeLevel.name }</option>
                                		<c:forEach items="${threeLevel.child }" var="fourLevel">
                                		<option value="4-${fourLevel.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${fourLevel.name }</option>
                                		<c:forEach items="${fourLevel.child }" var="fiveLevel">
                                		<option value="5-${fiveLevel.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${fiveLevel.name }</option>
                                		</c:forEach>
                                		</c:forEach>
                                		</c:forEach>
                                		</c:forEach>
                                	</c:forEach>
                                </select>
                                <div class="input-group-btn">
                                    <button class="btn btn-success" id="itemSearch" type="button">Search</button>
                                </div>
                            </div>                                           
                            <div class="arrow_down"></div>
                        </div>    

                        <div class="block-fluid" id="inbox">
                        <div class="toolbar clearfix">
                                <div class="left">
                                    <div class="btn-group">&nbsp;&nbsp;标 * 为基本测评项</div>
                                </div>
                            </div>
                            <table  class="table">
                                <thead>
                                    <tr>
                                       <th width="40px"><input type="checkbox" name="checkall"/></th>
                                       	<th >No.</th>
                                        <th width="10%">测评内容</th>
                                        <th width="10%">测评大项及分值</th>
										<th width="10%">测评项及分值</th>
										<th width="10%">测评小项</th>
										<th width="20%">测评要求</th>
										<th width="20%">测评方法</th>
										<th width="20%">文档</th>
                                    </tr>
                                </thead>
                                <tbody>
                                     <c:forEach items="${checkitemsList}" var="ci" varStatus="itr">
                                    <tr>
                                        <td><input type="checkbox" name="checkbox" value="${ci.id }"/></td>
                                        <td>${offset + itr.index +1 }</td>
                                        <td>${ci.item.style.style.style.name }</td>
                                        <td>${ci.item.style.style.name }</td>
										<td><c:if test="${ci.base }">*&nbsp;</c:if>${ci.item.style.name }</td>
										<td>${ci.item.name }</td>
										<td>${ci.demand}</td>
										<td>${ci.technique }</td>
										<td>
										<c:forEach items="${ci.docSet }" var="doc" varStatus="status1">
											${status1.index+1 }.${doc.name }：<br />
											<c:forEach items="${doc.attachs}" var="attach" varStatus="status"><a href="${contextPath }/attachment/download/${attach.id}">${attach.name }</a><br/></c:forEach><br/>
										</c:forEach>
										</td>
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