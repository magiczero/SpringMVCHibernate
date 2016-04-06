<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->

    <title>检查项操作</title>

	<link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
	 <link href='${contextPath }/resources/js/plugins/jstree/jquery.treeview.css' rel="stylesheet" type="text/css" />
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

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>

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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/tagsinput/jquery.tagsinput.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.edit.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.async.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    var ctx = "${contextPath}";
    var itemname = "";
    var itemid = 0;
            $(document).ready(function () {
            	$(".header").load("${contextPath }/header");
                $(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");

                $("#style").validationEngine({promptPosition : "topRight", scroll: true});
				
                $("#treeview").treeview({
                	unique: true,
            		url : ctx + '/checkitems/getAllJSonItems?t=' + pm_random()
            	});
            });
            
            function addTop() {
            	itemname = "";
                itemid = 0;
            	$("#id").val("");
            	$.getJSON(ctx + '/checkitems/get-top-parentid?t=' + pm_random(), function(data){
            		if(data == null) {alert('无法执行操作，请联系管理员'); return false;}
            		else {
            			$("#style\\.id").val(data);
            		}
            	});
            	$("#styleid").html("");
            }
            
            function checked(name, obj) {
            	itemname = name;
            	itemid = obj.parentNode.parentNode.getAttribute("id"); 
            }
            
            function addItem() {
            	if(itemname == "") {
            		alert('请先选中项');
            		return false;
            	} else if(itemid == 0) {
            		alert('请先选中项');
            		return false;
            	} else {
            		$("#id").val("");
            		$("#style\\.id").val(itemid);
                	$("#styleid").html(itemname);
            	}
            }
            
            function editItem() {
            	if(itemid == 0) {
            		alert('请先选中项');
            		return false;
            	} else {
            		$.getJSON(ctx + '/checkitems/get-style/'+itemid+'?t=' + pm_random(), function(data){
            			if(data == "") {
            				alert('未找到检查项，请联系管理员');
            				return false;
            			} else {
            				var data1 = data[0];
            				$("#id").val(data1.id);
            				$("#name").val(data1.name);
            				$("#style\\.id").val(data1.parentid);
            				$("#styleid").html(data1.parentname);
            				$("#order").val(data1.order);
            				$("#desc").val(data1.desc);
            			}
            		});
            	}
            }
            
            function deleteitem() {
            	if(itemid == 0) {
            		alert('请先选中项');
            		return false;
            	} else {
            		if(confirm('确定删除？')) {
            			$.getJSON(ctx + '/checkitems/delete-style/'+itemid+'?t=' + pm_random(), function(data){
            				if(data.flag) {
            					//成功后刷新页面
            					window.location.reload();
            				} else {
            					alert('删除失败，请确保此项下没有子项，且没有与文档相关联');
            					return false;
            				}
            			});
            		} else {
            			return false;
            		}
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
                    <li><a href="#">保密检查项</a> <span class="divider">></span></li>
                    <li class="active">添加检查项</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">

                <div class="row">
                    <div class="col-md-4">
                    <div class="head clearfix">
                        <div class="isw-donw_circle"></div>
                        <h1>检查项</h1>
                        <ul class="buttons">                          
                            	<li>
                                    <span class="isw-plus tipb" onclick="addTop();" title="添加顶层"></span>
                                </li>
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="javascript:void(0);" onclick="addItem();"><span class="isw-target"></span> 添加子项</a></li>
                                        <li><a href="javascript:void(0);" onclick="editItem();"><span class="isw-edit"></span> 编辑</a></li>
                                        <li><a href="javascript:void(0);" onclick="deleteitem();"><span class="isw-delete"></span> 删除</a></li>
                                    </ul>
                                </li>
                            </ul> 
                    </div>
                    	<div class="block-fluid">
                		<ul id="treeview" style="margin-left:10px;"></ul>
                		</div>
                    </div>
                    <div class="col-md-8">
                        <div class="head clearfix">
                            <div class="isw-documents"></div>
                            <h1>类别信息录入</h1>
                        </div><c:url var="addAction" value="/checkitems/saveitems" ></c:url>
						<form:form action="${addAction}" commandName="style">
						<form:hidden path="id" value="" />
						<form:hidden path="code" value="NON"/>
						<form:hidden path="style.id" value="" class="validate[required]"/>
                        <div class="block-fluid">
                          <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="name">检查项名称*</form:label></div>
                                <div class="col-md-7"><form:input path="name" class="validate[required,minSize[2],maxSize[30]] text-input" /><form:errors path="name" cssClass="error" /></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label>所属项</label>
                                </div>
                                <div class="col-md-7">
									<span id="styleid"></span>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                	<label for="order">排序</label>
                                </div>
                                <div class="col-md-7">
                                	<form:input path="order" class="validate[digits]"/>
                                </div>
                            </div>
							<div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="link">说明</label>
                                </div>
                                <div class="col-md-7">
                                <form:textarea path="desc"/>
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
            <!--workplace end-->
        </div>
    </div>

</body>

</html>
