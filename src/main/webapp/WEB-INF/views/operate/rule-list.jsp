<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    
    <title>用户终端合规性管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
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
    	var ctx="${contextPath}";
        $(document).ready(function () {
            $("#eventTable").dataTable({
            	"oLanguage": {
         			"sUrl": "${contextPath}/resources/json/Chinese.json"
     			},"aoColumns": [ { "bSortable": false }, null, null, null,null,null,null,null,{"bSortable":false},{"bSortable":false}]});
            $(".header").load("${contextPath}/header?t="+pm_random());
            $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $(".navigation > li:eq(1)").addClass("active"); });
            $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            
          	//表单验证
            $("#validation").validationEngine({promptPosition : "topRight", scroll: true});
          
            $(".confirm").bind("click",function(){
            	if(!confirm("确定要执行该操作?"))
                	return false;
        	});
            $(".lnk_modify").bind("click", modify);
            $(".addnew").bind("click",add);
        });
        function add(){
        	$("#id").attr('value','0');
    		$("#name").attr('value','');
    		$("#version").attr('value','');
    		$("#vendor").attr('value','');
    		$("#remak").attr('value','');
    		$("#newForm").modal('show');
        }
        function modify(){
        	var strid = $(this).attr("data");
        	$.getJSON(ctx + '/operate/rule/get/' + strid,function(data){
        		$("#id").attr('value',data.rule.id);
        		$("#name").attr('value',data.rule.name);
        		$("#item").attr('value',data.rule.item);
        		$("#subitem").attr('value',data.rule.subitem);
        		$("#indexOfData").attr('value',data.rule.indexOfData);
        		$("#value").attr('value',data.rule.value);
        		$("#compliance option[value='"+data.rule.compliance+"']").attr("selected", true);
        		$("#mark").attr('value',data.rule.mark);
        		$("#newForm").modal('show');
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
                    <li><a href="#">用户终端合规性管理</a> <span class="divider">></span></li>       
                    <li class="active">合规性规则管理</li>
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
                    <div class="col-md-12">                    
                        <div class="head clearfix">
                            <div class="isw-grid"></div>
                            <h1>合规性规则管理</h1>  

                            <ul class="buttons"> 
                           		<li>
                                    <a href="#"  class="isw-plus tipb addnew" title="创建新规则"></a>
                                </li>                         
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>
                                		<th width="60px">序号</th>
										<th>规则名称</th>
										<th width="100px">项</th>
										<th width="100px">子项</th>
										<th width="70px">项索引</th>
										<th width="100px">项值</th>
										<th width="90px">合规性判断</th>
										<th width="90px">匹配方式</th>
										<th width="100px">标识</th>
										<th width="120px">操作</th>
									</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list}" var="rule" varStatus="st">
									<tr>
										<td>${st.index + 1 }</td>
										<td>${rule.name }</td>
										<td>${rule.item }</td>
										<td>${rule.subitem }</td>
										<td>${rule.indexOfData }</td>
										<td>${rule.value }</td>
										<td>
											<c:if test="${rule.compliance == 'true' }">合规</c:if>
											<c:if test="${rule.compliance == 'false' }">不合规</c:if>
										</td>
										<td>
											<c:if test="${rule.equal == 'true' }">精确匹配</c:if>
											<c:if test="${rule.equal == 'false' }">模糊匹配</c:if>
										</td>
										<td>${rule.mark }</td>
										<td>
										<a class="lnk_modify" href="#" data="${rule.id }"><span class="glyphicon glyphicon-edit"></span> 编辑</a>
										<a class="confirm" href="${contextPath}/operate/rule/delete/${rule.id}"><span class="glyphicon glyphicon-remove"></span> 删除</a></td>
									</tr>
								</c:forEach>   
                                </tbody>
                            </table>
                        </div>
                    </div>  
                  
                </div>
                <div class="dr"><span></span></div>
            </div>
            <!--workplace end-->
        </div>   
     	<!-- modal form -->
        <div class="modal fade" id="newForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>合规性规则</h4>
                    </div>
                    <form:form id="validation" action="${contextPath}/operate/rule/save" commandName="rule" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-2"><form:label path="name">规则名称：</form:label></div>
                                    <div class="col-md-10"><form:input path="name" class="validate[required,maxSize[250]]"></form:input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-2"><form:label path="item">项：</form:label></div>
                                    <div class="col-md-4"><form:input path="item" class="validate[required,maxSize[250]]"></form:input></div>
                                    <div class="col-md-2"><form:label path="subitem">子项：</form:label></div>
                                    <div class="col-md-4"><form:input path="subitem"></form:input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-2"><form:label path="compliance">合规性：</form:label></div>
                                    <div class="col-md-4">
	                                   <form:select path="compliance" items="${complianceitems }"></form:select>
                                    </div>
                                    <div class="col-md-2"><form:label path="equal">匹配方式：</form:label></div>
                                    <div class="col-md-4">
	                                   <form:select path="equal" items="${equalitems}"></form:select>
                                    </div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                	<div class="col-md-2"><form:label path="indexOfData">索引：</form:label></div>
                                    <div class="col-md-4"><form:input path="indexOfData"></form:input></div>
                                    <div class="col-md-2"><form:label path="value">项值：</form:label></div>
                                    <div class="col-md-4"><form:input path="value" class="validate[required,maxSize[250]]"></form:input></div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-2"><form:label path="mark">标识：</form:label></div>
                                    <div class="col-md-10"><form:input path="mark"></form:input></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>
                    <div class="dr"><span></span></div>
                    <div class="block">                
                    	<p>说明：需要检测的数据索引等信息可查阅数据字典中的相关定义。</p>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="id"  name="id" value="0" /> 
                    </form:form>
                </div>
            </div>
        </div>
    	<!-- modal form end -->
    </div>
</body>

</html>