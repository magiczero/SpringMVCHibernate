<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>知识库管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/tagsinput.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/cleditor.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/tree-ie8.css" rel="stylesheet" type="text/css" />
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/bootstrap-treeview.css' media='print' />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
   	<script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <%--<script type='text/javascript' src='${contextPath }/resources/js/plugins/treeview/bootstrap-treeview.min.js'></script> --%>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jtree/jtree.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/tagsinput/jquery.tagsinput.min.js'></script>
    <!-- umeditor -->
    <link type="text/css"  href="${contextPath }/resources/js/plugins/umeditor/themes/default/css/umeditor.css" rel="stylesheet">
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/umeditor/umeditor.config.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/umeditor/umeditor.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/umeditor/lang/zh-cn/zh-cn.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uploadify/jquery.uploadify.min.js'></script>
    
    <!-- <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script> -->
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-select.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
        $(document).ready(function () {
          	$(".header").load("${contextPath }/header?t="+pm_random());
            $(".menu").load("${contextPath }/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath }/contentbuttons?t="+pm_random());
          	
            //表单验证
            $("#validation").validationEngine({promptPosition : "topRight", scroll: true});
          
            //分类
            /*
            act_dialog_category_init();
            $("input[name='category']").bind("click",function(){
            	act_dialog_category_select('INCIDENT_CATEGORY','category');
          	});
            */
            
            $(".wrapper").append("<div class='dialog' id='b_popup_select' style='display: none;' title='事件分类'></div>");
			$("#b_popup_select").html("<div class='block dialog_block messages '>"
					+"<div>"
					+"<ul id='treeview'></ul>"
					+"</div></div>");
			
		    $("#b_popup_select").dialog({
		        autoOpen: false,
		        width: 400,
		        height:500,
		        buttons: { "确定": function () { $(this).dialog("close") } }
		    });
            
		    $("input[name='category']").bind("click",function(){
		    	$("#treeview").html('');
		    	$.getJSON(ctx + '/system/syscode/getjson/INCIDENT_CATEGORY?t=' + pm_random(), function(data){
		    		obj= $.parseJSON(data.json);
		    		$.each(obj, function (index, element) {
		    			var liStr = "";
                    	if(element.nodes) {
                    		liStr = "<li><a href=\"#\">"+element.text+"</a><ul>";
                    		$.each(element.nodes, function (j, element1) {
                    			if(element1.nodes) {
                    				liStr += "<li><a href=\"#\">"+element1.text+"</a><ul>";
                    				$.each(element1.nodes, function (k, element2) {
                    					var code2 = element2.text.substring(0,element2.text.indexOf(" "));
                        				liStr += "<li><a href=\"#\" onclick=\"inputAttr('category','"+code2+"');\">"+element2.text+"</a></li>";
                    				});
                    				liStr += "</ul>";
                    			} else {
                    				var code1 = element1.text.substring(0,element1.text.indexOf(" "));
                    				liStr += "<li><a href=\"#\" onclick=\"inputAttr('category','"+code1+"');\">"+element1.text+"</a></li>";
                    			}
                    		})
                    		liStr += "</ul>";
                    	} else {
                    		var code = element.text.substring(0,element.text.indexOf(" "));
                    		liStr = "<li><a href=\"#\" onclick=\"inputAttr('category','"+code+"');\">"+element.text+"</a>";
                    	}
                    	
                    	liStr += "</li>";
                    	$("#treeview").append(liStr);
		    		});
		    		$("#treeview").treed();
		    		$("#b_popup_select").dialog('open');
		    	});
            });
		    //----------------------IE8 end--------------------
            
            var um = UM.getEditor('solution');
            //$("#solution").cleditor();
            $('#file_upload').uploadify({
				'formData' : { 'type' : 4 },
		        'swf'      : '${contextPath }/resources/flash/uploadify.swf',
		      //按钮显示的文字
                'buttonText': '选择文件……',
		        'uploader' : '${contextPath}/attachment/upload',
		        'removeCompleted' : false,
		        // Put your options here
		        'onUploadSuccess': function (file, data, response) {
		        	console.log(data);
                    $('#' + file.id).find('.data').html(' 上传完毕');
                    var fileids = document.getElementById("fileids").value + '';
                    document.getElementById("fileids").value = fileids + data;
		        }
		    });
        });
        
        function inputAttr(name,value) {
        	$("input[name='"+name+"']").attr("value",value);
        }
    </script>
    <style type="text/css">
    	.uploadify-button-text {color:#fff !important;}
    </style>
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
                    <li><a href="${contextPath }/knowledge/search">知识库管理</a> <span class="divider">></span></li>         
                    <li class="active">新建知识</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">

                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="head clearfix">
                            <div class="isw-documents"></div>
                            <h1>新建知识</h1>
                        </div>
                        <c:url var="addAction" value="/knowledge/save" ></c:url>
						<form:form id="validation" action="${addAction}" commandName="knowledge" method="post">
						<form:hidden path="id"/>
						<input id="fileids" name="fileids" type="hidden" />
                        <div class="block-fluid">                        
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="title">知识标题:</form:label></div>
                                <div class="col-md-10"><form:input path="title" class="validate[required,maxSize[50]]"></form:input></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="keyword">关键字:</form:label></div>
                                <div class="col-md-4"><form:input path="keyword" type="text" class="tags"></form:input></div>
                                <div class="col-md-2"><form:label path="category">分类：</form:label></div>
                                <div class="col-md-4"><form:input path="category" class="validate[required,maxSize[50]]"></form:input></div>
                            </div>  
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="desc">故障描述:</form:label></div>
                                <div class="col-md-10"><form:textarea path="desc" class="validate[required,maxSize[255]]"></form:textarea></div>
                            </div> 
                            <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="solution">解决办法</form:label></div>
                                <div class="col-md-10"><form:textarea path="solution" id="solution"  cssStyle="height:240px;" ></form:textarea></div>   
                            </div>  
                            <div class="row-form clearfix">
	                            	<div class="col-md-2"><label >文件:</label></div>
	                            	<div class="col-md-10">
										<input type="file" name="file_upload" id="file_upload" />
									</div>
	                            </div>         
                            <div class="footer tar">                        	
                                <button class="btn btn-primary center-block"> 保 存 </button>
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
