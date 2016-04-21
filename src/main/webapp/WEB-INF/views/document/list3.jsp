<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/fnReloadAjax.js'></script>   
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
        
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.gears.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.silverlight.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.flash.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.browserplus.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html5.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/jquery.plupload.queue/jquery.plupload.queue.js'></script>    
    
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
    function del(docid, rowid, obj) {
    	if(confirm("确定执行删除操作？")) {
    		//alert("行id " + rowid + "docid " + docid);
    		$.ajax({
        		    				type : "post",
        		    				dataType : "json",
        		    				url : "${contextPath }/document/remove/"+docid,
        		    				success : function(data) {
        		    					if (data.flag) {
        		    						//删除行
        		    						$('#datalist').DataTable().fnDeleteRow(rowid);
        		    					} else {
        		    						notify_e('删除失败','请联系管理员');
        		    					}
        		    				}
        		    			});
    		return true;
    	}
    	return false;
    }
    
            $(document).ready(function () {

                $(".header").load("${contextPath }/header");
                $(".menu").load("${contextPath }/menu", function () { $(".navigation > li:eq(5)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");
                
                //table加载数据
                var t = $("#datalist").dataTable({
		    		"bProcessing": true,
		    		"bServerside": true,
		    		"oLanguage": { //国际化配置  
		    			"sProcessing" : "正在获取数据，请稍后...",    
		    			"sLengthMenu" : "显示 _MENU_ 条",    
		    			"sZeroRecords" : "没有您要搜索的内容",    
		    			"sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",    
		    			"sInfoEmpty" : "记录数为0",    
		    			"sInfoFiltered" : "(全部记录数 _MAX_ 条)",    
		    			"sInfoPostFix" : "",    
		    			"sSearch" : "搜索",    
		    			"sUrl" : "",    
		    			"oPaginate": {    
		    			  "sFirst" : "第一页",    
		    			  "sPrevious" : "上一页",    
		    			  "sNext" : "下一页",    
		    			  "sLast" : "最后一页"    
		    			  }  
		    		}, 
		    		"sAjaxSource": "${contextPath }/document/list3",
		    		"iDisplayLength" : 10, //默认显示的记录数  
		    		"bRetrieve": true,
		    		"bDestroy" : true,
		    		"bPaginate": true, //翻页功能
		    		"bLengthChange": true, //改变每页显示数据数量
		    		"bFilter": false, //过滤功能
		    		"bSort": false, //排序功能
		    		"bInfo": true,//页脚信息
		    		"bAutoWidth": true,//自动宽度
		    		//服务器端，数据回调处理  
		    		"fnServerData" : function(sSource, aDataSet, fnCallback) {  
			    		$.ajax({  
				    		"dataType" : 'json',  
				    		"type" : "POST",  
				    		"url" : sSource,  
				    		"data" : aDataSet,  
				    		"success" : fnCallback  
			    		});  
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
                    <li class="active">文档信息列表</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             

                <div class="row">
                     <div class="col-md-2 clearfix" id="mails_navigation">                    
                        <a href="#" role="button" class="btn btn-success btn-block" data-toggle="modal">新建文档</a>
                        <div class="block-fluid sNavigation">
                            <ul>
                                <li class="active"><a href="${contextPath }/document/list"><span class="glyphicon glyphicon-inbox"></span> 全部文档</a><span class="arrow"></span></li>
                                <c:forEach items="${styles}" var="style">
                               	<li><a href="${contextPath }/document/list/style/${style.id}"><span class="glyphicon glyphicon-envelope"></span> ${style.name }</a><span class="arrow"></span></li>
                                </c:forEach>
                                <li><a href="${contextPath }/document/list/private"><span class="glyphicon glyphicon-remove"></span> 我的文档</a><span class="arrow"></span></li>
                            </ul>
                        </div>

                    </div>

                    <div class="col-md-10" id="mails">
                        <div class="headInfo">
                            <div class="input-group">
                                <input type="text" name="search" placeholder="search keyword..." id="widgetInputMessage" class="form-control"/>
                                <div class="input-group-btn">
                                    <button class="btn btn-success" type="button">Search</button>
                                </div>
                            </div>                                           
                            <div class="arrow_down"></div>
                        </div>    

                            <div class="block-fluid table-sorting clearfix">
                            <table id="datalist" class="table">
                                <thead>
                                    <tr>
                                    <th width="5%"></th>
                                        <th width="10%">文档名称</th>
										<th width="15%">关键字</th>
										<th width="10%">录入人</th>
										<th width="10%">类别</th>
										<th width="7%">&nbsp;</th>
										<th width="10%">录入时间</th>
										<th width="5%">版 本</th>
										<th>附 件</th> 
										<th>操 作</th>                                                                       
                                    </tr>
                                </thead>
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