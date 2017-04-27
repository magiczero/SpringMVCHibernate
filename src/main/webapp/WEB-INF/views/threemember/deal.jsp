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
    
    <title>三员工作管理--运维管理系统</title>

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
          	
            var tab = document.getElementById("itemTable");
            megercell(tab,0);
        });
        
        function megercell(tab, col){
    		count = 1;
    		val = "";
    		rowlength = tab.rows.length;
    		for(var i=0; i<rowlength; i++){
    			if(val == tab.rows[i].cells[col].innerHTML){
    				if(rowlength-i==1) {
    					from = i - count;

    					tab.rows[from].cells[col].rowSpan = count+1;
    					for(var j=from+1; j<rowlength; j++){
    						tab.rows[j].cells[col].style.display = "none";
    					}
    					break;
    				} 
    				count++;
    				
    			}else{
    				if(count > 1  ){
    					from = i - count;

    					tab.rows[from].cells[col].rowSpan = count;
    					for(var j=from+1; j<i; j++){
    						tab.rows[j].cells[col].style.display = "none";
    					}
    					count = 1;
    				}
    				val = tab.rows[i].cells[col].innerHTML;
    			}
    		}
    	}
    	
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
                    <li><a href="${contextPath }/feedback/list">三员记录管理</a> <span class="divider">></span></li>   
                    	<li class="active">填写工作记录</li>
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
	                            <h1>记录信息</h1>
	                        </div>
	                        <div class="block-fluid">      
							<form action="${contextPath }/three-member/finish/${tid }" method="post">
	                        <table id="itemTable" class="table">
                                <thead>
                                    <tr>
                                        <th width="20%">项目</th>
										<th width="15%">变动（操作）</th>
										<th >详细情况</th>
										<th width="25%">依据</th>
                                    </tr>
                                </thead>
                                <tbody>                 
	                            <c:forEach items="${formMap }" var="map">
	                            	<c:forEach items="${map.value }" var="relation">
	                            <tr>
	                                <td>${map.key.name }</td>
	                                <td><label class="checkbox checkbox-inline"><input type="checkbox" name="rid" value="${relation.id }"/>${relation.action.name }</label></td>
	                                <td><input type="text" name="${relation.id }_1" style="width:100%"/></td>
	                                <td><input type="text" name="${relation.id }_2" style="width:100%"/></td>
	                            </tr>
	                            </c:forEach>
	                            </c:forEach>
	                            </tbody>
	                            </table>
	                            <div class="footer">
	                                <button class="btn btn-primary center-block"> 保 存 </button>
	                            </div>  
	                            </form>                          
	                        </div>
	                    </div>
	                <div class="col-md-1"></div>
                </div>

            </div>
            <!--workplace end-->
        </div>  
    </div>
    
</body>
</html>
