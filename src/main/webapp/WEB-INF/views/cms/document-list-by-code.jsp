<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link href="${contextPath }/resources/css/dataTables.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath }/resources/css/stylesheets2.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap-submenu.min.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/js/plugins/dataTables/extras/css/TableTools.css" rel="stylesheet" type="text/css" />
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/extras/js/ZeroClipboard.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/extras/js/TableTools.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
        
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
		
    	$(".header").load("${contextPath }/header?t="+pm_random());
        $(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
        $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
        
        $("#btnCreate").bind("click", function() {
        	window.location='${contextPath}/cms/ci/document/init-add';
        });
        
      //获取菜单
        $.ajax({  
        	type:"GET",  
        	dataType: 'text',
        	url:"${contextPath}/cms/ci/document/getsubmenu/${category.categoryCode }",  
        	error:function(data){  
            	alert("出错了！！:"+data.msg);  
        	},  
        	success:function(data){  
            	$("ul.nav-pills").append(data);
            	$('[data-submenu]').submenupicker();
        	}
        });
      	//获取table值
        table = $("#myTable").dataTable({
    		"bFilter" : false,
    		//关闭过滤功能替换为文件导出按钮
            "sDom" : 'T<"clear">lfrtip' ,
        	"oTableTools": {
                "sSwfPath": "${contextPath }/resources/js/plugins/dataTables/extras/swf/copy_csv_xls_pdf.swf",
                "aButtons" : [
                              {
                                 "sExtends" : "xls" ,
                                 "sButtonText" : "导出Excel文件" ,
                                 "sFileName" : "*.xls"
                                },
                              ]
            },
            "bSort": false, //排序功能
        	"oLanguage": {"sUrl": "${contextPath}/resources/json/Chinese.json"},
        	"bProcessing": false, // 是否显示取数据时的那个等待提示
		    "bServerSide": true,//这个用来指明是通过服务端来取数据
            "sAjaxSource": "${contextPath}/cms/ci/document/table-page-ajax-list",//这个是请求的地址
            "fnServerData": retrieveData, // 获取数据的处理函数
            "fnServerParams": function (aoData) {  //查询条件
                aoData.push(
                		{ "name": "categoryCode", "value":  $("input[name='categorycode']").val() }
                    );
            },
            "aoColumns" : [
                           { "mData":"id","fnRender":  function ( obj ) {
                        	                                          return obj.iDataRow+1;
                        	                                        },"bSortable": false }, 
                           { "mData" : 'name' }, 
                           { "mData" : 'securityLevelName' }, 
                           { "mData" : 'lastUpdateUser' },
                                 { "mData" : 'categoryName' },
                                 { "mData" : 'model' },
                                 { "mData" : 'lastUpdateTime' },
                                 { "mData" : null },
                                 { "mData" : 'incidence' },
                                 { "mData" : 'location' },
                                 { "mData":"attachs","fnRender":  function ( obj ) {
                                	 var str = "";
                                	 $.each(obj.aData.attachs, function(i,val){      
                                	      str+="<a href=\"${contextPath }/attachment/download/"+val.id+"\">"+val.name+"</br>";
                                	  });  
                                	 return str;                                      },"bSortable": false  }
                             ]	
        });
      	
    });
    
    function searchByCode(code) {
    	$("input[name='categorycode']").val(code);
    	table.fnDraw();
    }
    
    function retrieveData( sSource111,aoData111, fnCallback111) {
		$.ajax({
			url : sSource111,//这个就是请求地址对应sAjaxSource
			data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
			type : 'post',
			dataType : 'json',
			async : false,
			success : function(result) {
				fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
			},
			error : function(msg) {
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
                    <li><a href="${contextPath}/document">文档管理</a> <span class="divider">></span></li>       
                    <li class="active">${category.categoryName }</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             

                <div class="row">

                    <div class="col-md-12" >
                            <input type="hidden" name="categorycode" value="${category.categoryCode }" />
                      <ul class="nav nav-pills">
	                      <li class="active"><a tabindex="0" onclick="searchByCode(${category.categoryCode })">${category.categoryName }</a></li>
                      </ul>
                                    <div class="dr"></div>
						<div class="head clearfix">
                            <div class="isw-grid"></div>
                            <ul class="buttons">
                                <li>
                                    <a class="isw-settings" href="#"></a>
                                    <ul class="dd-list">
                                        <li><a id="btnCreate" href="#"><span class="isw-plus"></span> 新建文档</a></li>
                                        <li><a id="btnUpdateVersion" href="#"><span class="isw-edit"></span> 更新版本</a></li>
                                        <li><a id="btnEdit" href="#"><span class="isw-edit"></span> 修改文档</a></li>
                                        <li><a id="delBtn" href="#"><span class="isw-delete"></span> 删除文档</a></li>
                                    </ul>
                                </li>
                            </ul>                        
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="myTable">
                                <thead>
                                    <tr>
                                       	<th width="10px">No.</th>
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
                                </tbody>
                            </table>                       
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