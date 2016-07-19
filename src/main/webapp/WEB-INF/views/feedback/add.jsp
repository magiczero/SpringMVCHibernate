<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
    <title>事件管理--运维管理系统</title>

    <!-- <link rel="icon" type="image/ico" href="favicon.ico"/> -->
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uploadify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href='${contextPath }/resources/js/plugins/jstree/jquery.treeview.css' rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uploadify/jquery.uploadify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.edit.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.async.js'></script>
      
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <%--<script type='text/javascript' src='${contextPath }/resources/js/pm-select.js'></script> //IE8+    --%>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
        $(document).ready(function () {
          	$(".header").load("${contextPath }/header?t=" + pm_random());
            $(".menu").load("${contextPath }/menu?t=" + pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath }/contentbuttons?t=" + pm_random());
          	
            //表单验证
            $("#validation").validationEngine({promptPosition : "topRight", scroll: true});
            //输入摘要是自动补全描述字段
            $("#abs").bind("blur",function(){
            	if( $("#detail").val()=="" )
            		$("#detail").text($("#abs").val());
            });
            
			$('#file_upload').uploadify({
				'formData' : { 'type' : 7 },
		        'swf'      : '${contextPath }/resources/flash/uploadify.swf',
		      //按钮显示的文字
                'buttonText': '选择文件……',
		        'uploader' : '${contextPath}/attachment/upload',
		        'removeCompleted' : false,
		        'multi': true,
		        // Put your options here
		        'onUploadSuccess': function (file, data, response) {
		        	console.log(data);
	                $('#' + file.id).find('.data').html(' 上传完毕');
	                var fileids = document.getElementById("fileids").value + '';
	                document.getElementById("fileids").value = fileids + data;
		        }
		    });

        });
        
        function delAttach(obj, attachid) {
        	var msg = "您真的确定要删除吗？请确认！";  
            if (confirm(msg)==true){  
            	$.getJSON(ctx + '/feedback/delAttach/'+attachid+'?t=' + pm_random(), function(data){
            		//var flag = data.flag;
            		if(data) {
            			$(obj).parent().remove();
            		} else {
            			return false;
            		}
            	});
            }else{  
                return false;  
            }  
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
                    <li><a href="${contextPath }/feedback/list">反馈信息</a> <span class="divider">></span></li>   
                    <c:if test="${not empty incident.id }" >     
                    	<li class="active">修改事件信息</li>
                    </c:if>
                    <c:if test="${empty incident.id }" >     
                    	<li class="active">创建反馈意见</li>
                    </c:if>
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
	                            <div class="isw-empty_document"></div>
	                            <h1>反馈信息</h1>
	                        </div>
	                        <c:url var="addAction" value="/feedback/save" ></c:url>
	                        <form:form action="${addAction}" commandName="feedback" method="post" id="validation">
	                        <form:hidden path="id" />
	                        <form:hidden path="applyUser"/>
	                        <input id="fileids" name="fileids" type="hidden" />
	                        <div class="block-fluid">                        
	                            
	                            <div class="row-form clearfix">
	                                <div class="col-md-1"><form:label path="detail">反馈内容:</form:label></div>
	                                <div class="col-md-11"><form:textarea rows="10" path="detail" class="validate[required,maxSize[255]]"></form:textarea>
	                                </div>
	                            </div>
	                            
	                            <div class="row-form clearfix">
	                            	<div class="col-md-1"><label >附件:</label></div>
	                            	<div class="col-md-11">
										<input type="file" name="file_upload" id="file_upload" /><br/>
										<c:if test="${not empty feedback.id }"><ul>
											<c:forEach items="${feedback.attachs }" var="attach"><li><a href="javascript:void(0);" onclick="delAttach(this,${attach.id})">删除</a>&nbsp;&nbsp;<a href="${contextPath }/attachment/download/${attach.id}">${attach.name }</a></li></c:forEach></ul>
										</c:if>
									</div>
	                            </div>                                                   
	                            <div class="footer">
	                                <button class="btn btn-primary center-block"> 保 存 </button>
	                            </div>                            
	                        </div>
	                   		</form:form>
	                    </div>
	                <div class="col-md-1"></div>
                </div>

            </div>
            <!--workplace end-->
        </div>  
    </div>
    
</body>
</html>
