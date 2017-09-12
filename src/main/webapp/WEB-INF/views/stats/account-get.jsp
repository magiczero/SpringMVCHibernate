<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>台账--运维管理系统</title>

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
    <link href="${contextPath }/resources/js/plugins/dataTables/extras/css/TableTools.css" rel="stylesheet" type="text/css" />
    

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/extras/js/ZeroClipboard.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/extras/js/TableTools.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
    	var data = [];
    	var data_index=0;
            $(document).ready(function () {
            	$(".header").load("${contextPath }/header?t=" + pm_random());
                $(".menu").load("${contextPath }/menu?t=" + pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath }/contentbuttons?t=" + pm_random());
                
                var datatables_options = {
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
                	    "bAutoWidth": true,
                	    "bPaginate": false,   
                	    "bSort": true, 
                	    "bFilter": false, 
                	    "aaSorting": [], 
                	    "bInfo": false, 
                	    "bStateSave": false, 
                	    "iCookieDuration": 0, 
                	    "bScrollAutoCss": true, 
                	    "bProcessing": true, 
                	    "bJQueryUI": false 
                	};

                	datatables_options["sScrollY"] = "450px";
                	datatables_options["sScrollX"] = "100%";
                	datatables_options["bScrollCollapse"] = true;

                	// add this
                	datatables_options["sScrollXInner"] = '150%';
                	//
                	                   
                	var table = $('#accountTable').DataTable(datatables_options);
                	//new $.fn.dataTable.FixedColumns(table);
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
                    <li><a href="${contextPath }/incident/list">报表与统计管理</a> <span class="divider">></span></li>       
                    <li class="active">台账</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">             

				<div class="alert alert-danger hide">                
                    <h4>错误!</h4>请至少选择一项
                </div> 
                <div class="row">
                	<div class="col-md-2">
                        <div class="head clearfix">
                            <div class="isw-list"></div>
                            <h1>台账管理</h1>
                        </div>
                        <div class="block-fluid accordion">
                            <h3>台账</h3>
                            <div class="active">
                                 <ul>
                                 	<c:forEach items="${accounts }" var="account">
                                 	<li><a href="${contextPath }/stats/account/get/${account.id}">${account.name }</a></li>
                                 	</c:forEach>
                                </ul>                                               
                            </div>

                            <h3>自定义</h3>
                            <div>
                                <ul>
                                	<li><a href="${contextPath }/stats/account/list">台账信息列表</a></li>
                                    <li><a href="${contextPath }/stats/account/setting">自定义台账</a></li>
                                </ul>                                                
                            </div>                    
                        </div>
                    </div> 
                    <div class="col-md-10">
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>台账</h1> 
                            <ul class="buttons">
                            	<li>
                                    <a title="导出为Excel" class="isw-download" href="${contextPath }/stats/account/dcExcel?id=${id}"></a>                            
                                </li>
                            </ul>                           
                        </div>
                        <div class="block-fluid">
                            <table id="accountTable" class="stripe row-border order-column">
                                <thead>
                                    <tr>                                    
                                        <th width="60px">序号</th>
                                        <c:forEach items="${columns }" var="column">
                                        	<th>${column }</th>
                                        </c:forEach>
                                    </tr>
                                </thead>
                                <tbody>
									<c:forEach items="${rows }" var="row" varStatus="status">
										<tr>
											<td>${status.index+1 }</td>
											<c:forEach items="${row }" var="value">
												<td>${value }</td>
											</c:forEach>
										</tr>
									</c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!--workplace end-->
        </div> 
    </div>
</body>

</html>
