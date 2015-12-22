<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
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

    <title>工程师工作台--运维管理系统</title>

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
    <style type="text/css">
    	#itemTable td {vertical-align:middle;}
    	#itemTable th {text-align:center;}
    </style>
    <script type="text/javascript">
    var ctx = "${contextPath}";
            $(document).ready(function () {

                $(".header").load("${contextPath }/header");
                $(".menu").load("${contextPath }/menu", function() {$("#node_${moduleId}").addClass("active");});
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");

                if($("#docSet").length > 0){
                    $("#docSet").multiSelect({
                        selectableHeader: "<div class='multipleselect-header'>所有文档</div>",
                        selectedHeader: "<div class='multipleselect-header'>已选择文档</div>"
                    });
                    $('#multiselect-selectAll').click(function(){
                        $('#docSet').multiSelect('select_all');
                        return false;
                    });
                    $('#multiselect-deselectAll').click(function(){
                        $('#docSet').multiSelect('deselect_all');
                        return false;
                    });
                 }
                
                $("#style").validationEngine({promptPosition : "topLeft", scroll: true});
                
                $("#checkitems").validationEngine({promptPosition : "topLeft", scroll: true});
                
                $("#itemTable").rowspan(0);
                $("#itemTable").rowspan(1);
                $("#itemTable").rowspan(2);
                
                $('[data-submenu]').submenupicker();
            });
            
            jQuery.fn.rowspan = function(colIdx) {
            	return this.each(function(){
	            	var that;
	            	$('tbody tr', this).each(function(row) {
		            	$('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
			            	if (that!=null && $(this).html() == $(that).html()) {
				            	rowspan = $(that).attr("rowSpan");
				            	if (rowspan == undefined) {
					            	$(that).attr("rowSpan",1);
					            	rowspan = $(that).attr("rowSpan");
					            } 
				            	rowspan = Number(rowspan)+1;
				            	$(that).attr("rowSpan",rowspan);
				            	$(this).hide();
			            	} else {
			            		that = this;
			            	}
		            	});
	            	});
            	});
            }
		
            function delItem(id) {
            	if(confirm('确定删除？')) {
	            	$.getJSON('${contextPath }/checkitems/delete/' + id,function(data){
	            		if(data.flag) {
	            			notify_e('Success','删除成功');
	            			location.reload();
	            		} else {
	            			notify_e('Error','删除失败');
	            		}
	            	});
            	}
            	return false;
            }
            
            function addItem(id, name) {
            	$("[id='item.id']").val(id);
            	$('#itemName').html(name);
            	$("#fModal3").modal();
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
                    <li><a href="javascript:void(0);">保密检查管理</a> <span class="divider">></span></li>
                    <li class="active">BMB20-2007</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">

                <div class="row">
                    <div class="col-md-12" id="mails">
                        <ul class="nav nav-pills">
                      <li class="active"><a tabindex="0" href="${contextPath }/checkitems/bmb-list/BMB20">${item.name }</a></li>
                      <c:forEach items="${item.child }" var="style">
                      <li class="dropdown">
						<a tabindex="0" data-toggle="dropdown" data-submenu>${style.name } <span class="caret"></span></a>
						<ul class="dropdown-menu">
						<li><a tabindex="0" href="${contextPath }/checkitems/list/items/${style.id}">${style.name }</a></li>
						<li class="divider"></li>
						<c:forEach items="${style.child }" var="children1">
						<li <c:if test="${!empty children1.child }">class="dropdown-submenu"</c:if>>
						<a href="${contextPath }/checkitems/list/items/${children1.id }">${children1.name }</a>
							<c:if test="${!empty children1.child }">
							<ul class="dropdown-menu" >
							<li><a href="${contextPath }/checkitems/list/items/${children1.id }">${children1.name }</a></li>
							<li class="divider"></li>
							<c:forEach items="${children1.child }" var="children2">
			                <li>
			                <a tabindex="0" href="${contextPath }/checkitems/list/items/${children2.id}">${children2.name }</a>
			                </li>
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
                            <h1>BMB20-2007</h1>      
                        </div>
                        <div class="block-fluid" id="inbox">
                        
                            <table id="itemTable" class="table">
                                <thead>
                                    <tr>
                                        <th width="20%" colspan="2">制度类别</th>
										<th width="10%">管理要求</th>
										<th width="20%">现有制度的名称</th>
										<th>现有制度中对应条目</th>
										<th width="5%">页数</th>
										<th width="25%">相关记录或文档</th>
										<th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${item.child }" var="oneLevel">
	                                <c:forEach items="${oneLevel.child }" var="twoLevel">
	                                <c:forEach items="${twoLevel.child }" var="threeLevel">
	                                <c:set var="itemsize" value="0" />
                                    <c:forEach items="${checkitemsList}" var="ci" varStatus="itr">
                                    <c:if test="${ci.item.id == threeLevel.id }">
                                	<c:set var="itemsize" value="${itr.index+1 }" />
                                    <tr>
                                        <td>${ci.item.style.style.name }</td>
                                        <td>${ci.item.style.name }</td>
										<td>${ci.item.name }</td>
										<td>${ci.name }</td>
										<td>${ci.demand}</td>
										<td>${ci.technique }</td>
										<td>
										<c:choose><c:when test="${empty ci.docSet}">${ci.record }</c:when>
										<c:otherwise>
										<c:forEach items="${ci.docSet }" var="doc">
											<c:forEach items="${doc.attachs}" var="attach"><a href="${contextPath }/attachment/download/${attach.id}">${attach.name }</a><br/></c:forEach>
										</c:forEach>
										</c:otherwise>
										</c:choose>
										</td>
										<td><a href="${contextPath }/checkitems/update/${ci.id}">编辑</a><br/><a href="javascript:void(0);" onclick="javascript:delItem(${ci.id});">删除</a></td>
                                    </tr>
                                    </c:if>
                                    </c:forEach>
                                    <c:if test="${itemsize == 0 }"><tr>
                                        <td>${threeLevel.style.style.name }</td>
										<td>${threeLevel.style.name }</td>
										<td>${threeLevel.name }</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td><a href="javascript:void(0);" onclick="javascript:addItem(${threeLevel.id},'${threeLevel.name }');">添加</a></td></tr>
                                	</c:if>
                                    </c:forEach>
                                    </c:forEach>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="toolbar bottom-toolbar clearfix">&nbsp;
                            </div>
                        </div>

                    </div>
                </div>
               <div class="dr"><span></span></div>
            </div>
            <!--workplace end-->
        </div>
    </div>
     <div class="modal fade" id="fModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>添加子项</h4>
                    </div>
                    <div class="modal-body modal-body-np">
                    <div class="row">&nbsp;</div>
                        <div class="row">
                        <div class="col-md-12">
						<c:url var="addAction" value="/checkitems/saveitems" ></c:url>
						<form:form action="${addAction}" commandName="style">
						<form:hidden path="code" value="NON"/>
						<form:hidden path="style.id" class="validate[required,digits]"/>
                        <div class="block-fluid">
                          <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="name">名称*</form:label></div>
                                <div class="col-md-10"><form:input path="name" class="validate[required,minSize[2],maxSize[30]] text-input" /><form:errors path="name" cssClass="error" /></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label>所属项</label>
                                </div>
                                <div id="itemParent" class="col-md-10">
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                	<label for="order">排序</label>
                                </div>
                                <div class="col-md-10">
                                	<form:input path="order" class="validate[digits]"/>
                                </div>
                            </div>
							<div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="link">说明</label>
                                </div>
                                <div class="col-md-10">
                                <form:textarea path="desc" class="validate[maxSize[30]]"/>
                                </div>
                            </div>
                            <div class="footer tar">
                               <input type="submit" class="btn btn-primary center-block" value="提 交" />
                            </div>
                        </div>
                        </form:form>
						</div>
                        </div>
                    </div>
                    </div>
                    </div>
                    </div>
       <div class="modal fade" id="fModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>添加检查项</h4>
                    </div>
                    <div class="modal-body modal-body-np">
                    <div class="row">&nbsp;</div>
                        <div class="row">
                        <div class="col-md-12">
						<c:url var="saveAction" value="/checkitems/save" ></c:url>
						<form:form action="${saveAction}" commandName="checkitems">
						<form:hidden path="item.id" class="validate[required,digits]"/>
                        <div class="block-fluid">                        
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label>制度类别</label>
                                </div>
                                <div class="col-md-10" id="itemName">
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <form:label path="name">名称</form:label>
                                </div>
                                <div class="col-md-10">
                                <form:input path="name" class="validate[required,minSize[2],maxSize[30]] text-input"/>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="demand">对应条目</label>
                                </div>
                                <div class="col-md-10">
                                <form:input path="demand" class="validate[maxSize[300]]"/>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="technique">页数</label>
                                </div>
                                <div class="col-md-10">
                                <form:input path="technique" class="validate[digits]"/>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="record">相关记录</label>
                                </div>
                                <div class="col-md-10">
                                <form:input path="record" class="validate[maxSize[50]]"/>
                                </div>
                            </div>
							<div class="row-form clearfix">
								<form:select multiple="true" path="docSet" class="validate[required] multiselect">
									<form:options items="${docList}" itemValue="id" itemLabel="name"/>
								</form:select>
	                            
	                            <div class="btn-group">
	                                <button class="btn btn-default btn-xs" id="multiselect-selectAll" type="button">全选</button>
	                                <button class="btn btn-default btn-xs" id="multiselect-deselectAll" type="button">全不选</button>
	                            </div>                             
                            </div>
                            <div class="footer tar">
                               <input type="submit" class="btn btn-primary center-block" value="提 交" />
                            </div>                            
                        </div>
                        </form:form>
						</div>
                        </div>
                    </div>
                    </div>
                    </div>
                    </div>
</body>

</html>
