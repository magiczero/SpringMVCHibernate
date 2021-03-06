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
    
    <title>报表与统计管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/excanvas.min.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.resize.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/chart.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/modernizr.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
        <script type="text/javascript">
    	var ctx = "${contextPath}";
    	var mylabels = [<c:forEach items="${stat.column }" var="code" varStatus="status"><c:if test="${status.index>0}">,</c:if>'${code.value}'</c:forEach>];
    	var mydata=[<c:forEach items="${stat.column }" var="code" varStatus="status"><c:if test="${status.index>0 }">,</c:if>${stat.counts[code.key]==null?0:stat.counts[code.key]}</c:forEach>];
        $(document).ready(function () {
        	$(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $("#node_${moduleId}").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());

            if( ($.browser.msie&&$.browser.version=="8.0") )
            {
            	if($("#barChart").length > 0){       
                	$("#barChart").parent('div').resize(function(){
    		            var bctx = $("#barChart").get(0).getContext("2d");
    		            $("#barChart").attr('width',$("#barChart").parent('div').width()).attr('height',300);
    		            var barChart = new Chart(bctx).Bar({
    		            	labels : mylabels,
    		                datasets : [{fillColor : "rgba(151,187,205,0.5)",strokeColor : "rgba(151,187,205,1)",data : mydata}]
    		            }, {animation : Modernizr.canvas});
                	});
                }
            }else{
            	if($("#barChart").length > 0){       
    		    	var bctx = $("#barChart").get(0).getContext("2d");
    		        $("#barChart").attr('width',$("#barChart").parent('div').width()).attr('height',300);
    		        var barChart = new Chart(bctx).Bar({
    		          	labels : mylabels,
    		            datasets : [{fillColor : "rgba(151,187,205,0.5)",strokeColor : "rgba(151,187,205,1)",data : mydata}]
    		        });
                }
            }

            /*
			if($("#chart-2").length > 0){
				var vData = [[1, 103], [2, 28], [3, 135], [4, 130], [5, 145], [6, 155], [7, 155], [8, 155], [9, 155], [10, 155], [11, 155], [12, 155]]; 
				//var vData = [];
				//for(var i=0; i<mylabels.length; i++) {
				//	vData.push([mylabels[i], mydata[i]]);
				//}

				$.plot($("#chart-2"), [{ label: "", data: vData}],
						{
						    series: { lines: { show: true }, points: { radius: 3,show: true} },
						    xaxis: { ticks: [[1, "1月"], [3, "3月"], [5, "5月"], [7, "7月"], [9, "9月"], [11, "11月"]], min: 1, max: 12 },  //指定固定的显示内容
						    yaxis: { ticks: 5, min: 0 }  //在y轴方向显示5个刻度，此时显示内容由 flot 根据所给的数据自动判断
						}
				);
				<style type="text/css">
		    	#chart-2{
		        width:100%;
		        height:300px;
		    }
		    </style>
                
            }*/
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
                    <li><a href="#">报表与统计管理</a> <span class="divider">></span></li>       
                    <li class="active">事件总量统计</li>
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
                	<div class="col-md-3">
                        <div class="head clearfix">
                            <div class="isw-list"></div>
                            <h1>事件统计</h1>
                        </div>
                        <div class="block-fluid accordion">
                            <h3>统计报表</h3>
                            <div>
                                 <ul>
                                 	<li><a href="${contextPath }/stats/incident/count">事件总量统计</a></li>
                                 	<li><a href="${contextPath }/stats/incident/category">事件分类统计</a></li>
                                    <li><a href="${contextPath }/stats/incident/performance">工程师绩效统计</a></li>
                                    <li><a href="${contextPath }/stats/incident/support">支持类型统计</a></li>
                                    <li><a href="${contextPath }/stats/incident/satisfaction">满意度统计</a></li>
                                    <li><a href="${contextPath }/stats/incident/ci">故障率统计</a></li>
                                </ul>                                               
                            </div>

                            <h3>自定义统计</h3>
                            <div class="active">
                                <ul>
                                    <li><a href="${contextPath }/stats/incident1">自定义一维统计</a></li>
                                    <li><a href="${contextPath }/stats/incident">自定义二维统计</a></li>
                                </ul>                                                
                            </div>                     
                        </div>
                    </div> 
                    <div class="col-md-9">
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>本年度按月统计事件</h1>                            
                        </div>
                        
                        <div class="block-fluid">
                        	<div class="toolbar clearfix">统计说明：按月统计本年度已关闭的事件。</div>
                            <table class="table">
                                <thead>
                                    <tr>                                    
                                        <th class="tac">月份</th>
										<c:forEach items="${stat.column }" var="code">
											<th class="tac">${code.value }</th>
										</c:forEach>
                                    </tr>
                                </thead>
                                <tbody>
                                	<tr>
                                		<td class="tac">总数</td>
                                    	<c:forEach items="${stat.column }" var="code">
                                      	<td class="tac">
                                      		<c:if test="${ empty stat.counts[code.key] }">
                                        		<span style="color:#a0a0a0;">0</span>
                                        	</c:if>
                                        	<c:if test="${ not empty stat.counts[code.key] }">
                                        		<strong><span class="text-info">${stat.counts[code.key] }</span></strong>
                                        	</c:if>
                                      	</td>
                                    	</c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>统计图</h1>                            
                        </div>
                        <div class="block">
                        	<canvas id="barChart"></canvas>
                        </div>
                        
                    </div>
                </div>
            </div>
            <!--workplace end-->
        </div> 
    </div>
</body>

</html>
