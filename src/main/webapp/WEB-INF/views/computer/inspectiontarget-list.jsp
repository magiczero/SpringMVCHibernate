<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    
    <title>终端合规性管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/multi-select.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    		var ctx = "${contextPath}";
            $(document).ready(function () {
                $("#eventTable").dataTable({"oLanguage": {
         			"sUrl": "${contextPath}/resources/json/Chinese.json"
     			},"aoColumns": [ { "bSortable": false }, null, null, {"bSortable":false}]});
                
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath}/menu?t="+pm_random(), function () { $(".navigation > li:eq(1)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                
              	//表单验证
              	$("#newTargetForm").validationEngine({promptPosition : "topRight", scroll: true});
              
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
            	$("#btn_item_submit").bind("click",getItem);
           	 	if ($("#selItem").length > 0) {
                    $("#selItem").multiSelect({
                    	selectableHeader: "<div class='multipleselect-header'>待选择检查项</div>",
                        selectedHeader: "<div class='multipleselect-header'>已选择检查项</div>"
                   	});
                }
            });
            function setItem(id){
            	$("#selItem").multiSelect("deselect_all");
            	$.getJSON(ctx + '/computer/inspectiontarget/getitemforselect/' + id +'?t=' + pm_random(),function(data){
            		$("#selItem option").remove();
            		for(var i=0;i<data.items.length;i++)
            		{
            			$("#selItem").append("<option value="+data.items[i].itemId+">"+data.items[i].itemName+"</option");
            		}
            		$("#selItem").multiSelect('refresh');
            		
                	$.getJSON(ctx + '/computer/inspectiontarget/getitem/' + id +'?t=' + pm_random(),function(data){
                		for(var i=0;i<data.items.length;i++)
                		{
                			$("#selItem").multiSelect("select",data.items[i].itemId);;
                		}
                		$("#form_id").attr('value',id);
                    	$("#setItemFormDialog").modal('show');
                	});	
            	});
            }
            function getItem(){
            	$("#form_items").attr('value',$("#selItem").val());
            	return true;
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
                    <li><a href="#">终端合规性管理</a> <span class="divider">></span></li>       
                    <li class="active">检查指标管理</li>
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
                            <div class="isw-tag"></div>
                            <h1>检查指标管理</h1>  

                            <ul class="buttons">  
								<li>
                                    <a href="#newForm" role="button" data-toggle="modal" class="isw-plus tipb" title="创建新指标"></a>
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
										<th width="200px">指标</th>
										<th>检查项</th>
										<th width="150px">操作</th>
									</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list}" var="target">
									<tr>
										<td class="code">
											${target.targetId }
										</td>
										<td>${target.targetName }</td>
										<td>
											<c:forEach items="${target.items }" var="item">
												${item.itemName } | 
											</c:forEach>
										</td>
										<td>
											<a href="#"  onclick="setItem('${target.targetId}')"><span class="glyphicon glyphicon-edit"></span> 设置检查项</a>
											<a class="confirm" href="${contextPath}/computer/inspectiontarget/delete/${target.targetId}"><span class="glyphicon glyphicon-remove"></span> 删除</a>
										</td>
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
                        <h4>新建检查指标</h4>
                    </div>
                    <form:form id="newTargetForm"  action="${contextPath}/computer/inspectiontarget/save" commandName="target" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><form:label path="targetName">指标名：</form:label></div>
                                    <div class="col-md-9"><form:input path="targetName" class="validate[required,maxSize[50]]"></form:input></div>
                                </div>                                                           
                            </div>                
                        </div>
                    	<div class="dr"><span></span></div>
                        <div class="block">                
                            <p>说明：可按需添加或修改指标集，指标集名称应具备较好的区别能力。</p>
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
    	<!-- modal form end -->
    	<!-- 检查项属性 modal form -->
    	<div class="modal fade" id="setItemFormDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>设置检查项</h4>
                    </div>
                    <form id="setItemForm" action="${contextPath}/computer/inspectiontarget/updatetarget" method="post">
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-12">
                                    <select multiple class="multiselect" id="selItem" name="test[]">   
                                    </select></div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" id="btn_item_submit">保存</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <input type="hidden" id="form_id"  name="form_id" value="0" /> 
                    <input type="hidden" id="form_items" name="form_items" value="0" />
                    </form>
                </div>
            </div>
        </div>
    	<!-- 设置属性 end from -->
    </div>
</body>

</html>