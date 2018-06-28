<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>资产管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/dataTables.css" rel="stylesheet" type="text/css" />
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
    	var table;
            $(document).ready(function () {
                
                $(".header").load("${contextPath}/header");
                $(".menu").load("${contextPath}/menu", function () { $("#node_${moduleId}").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath}/contentbuttons");
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                
                table = $('#eventTable').dataTable({
                	"oLanguage": {"sUrl": "${contextPath}/resources/json/Chinese.json"},
    			    "bAutoWidth": true,
    			    "sDom": '<"top"i>rt<"bottom"flp><"clear">',     //改变页面元素位置      
    			    "bPaginate": true,   
    			    "sPaginationType": "full_numbers", 
    			    "iDisplayLength": 10, 
    			    "bSort": false, 
    			    "bFilter": false, 
    			    "aaSorting": [], 
    			    "bInfo": false, 
    			    "bStateSave": false, 
    			    "iCookieDuration": 0, 
    			    "bScrollCollapse": true, 
    			    "bProcessing": true, 
    			    "bJQueryUI": false,
    			    //"sScrollX":"100%",
    			    //"sScrollXInner":"200%",
    			    //"fixedColumns":  true,
    			    //"fixedColumnsOptions":{
    		        //    LeftColumns: 2,
    		        //    RightColumns: 1
    		        //},
    		        "bServerSide": true,//这个用来指明是通过服务端来取数据
    	            "sAjaxSource": ctx + '/stats/account/search-by-table?t=' + pm_random(),//这个是请求的地址
    	            "fnServerData": retrieveData, // 获取数据的处理函数
    	            "fnServerParams": function (aoData) {  //查询条件
    	                aoData.push(
    	                		{ "name": "type", "value": $("#type").val() },
    	                		{ "name": "department", "value": $("#department").val() },
    	                		{ "name": "status", "value": $("#status").val() },
    	                		{ "name": "mainType", "value": $("#mainType").val() },
    	                		{ "name": "securityLevel", "value": $("#securityLevel").val() }
    	                    );
    	            },
    	            "aoColumnDefs": [ {
    	                "aTargets": [ 0 ],
    	                "mRender": function ( data, type, full ) {
    	                  return '<input name="ids" type="radio" value="'+full.id+'" />';
    	                }
    	              },
    	              {
      	                "aTargets": [ 1 ],
      	                "mData": "download_link",
      	                "mRender": function ( data, type, full ) {
      	                  return '<a href="${contextPath }/stats/account/detail/'+full.id+'">'+data+'</a>';
      	                }
      	              },
    	              {
        	                "aTargets": [ <c:out value="${mDatas.size()+1}"/> ],
        	                "mData": "download_link",
        	                "mRender": function ( data, type, full ) {
        	                  return '<a href="${contextPath }/account-life-cycle/addproperty/'+full.id+'">属性</a>';
        	                }
        	              }],
    	            "aoColumns" : [
    	            	{ "mData" : 'id' },
    	            	<c:forEach items="${mDatas}" var="data">
    	                           { "mData" : '${data}' }, 
    	                           </c:forEach>
    	                           { "mData" : 'ids' }
    	                             ]
    			});
                
	        	//new $.fn.dataTable.FixedColumns(table);
	        	
	        	$("button[name='btn-find']").bind("click", function () { //按钮 触发table重新请求服务器
	                table.fnDraw();
	                $("#myModal").modal('hide');
	            });
            });
            
            function in_record() {
            	var equipid = $('input:radio:checked').val();
            	if(equipid == null) {
            		alert('请选择设备...');
            		return false;
            	}
            	window.location.href=ctx+"/maintain-record/add-by-equip?equipId="+equipid;
            }
            
            function scrap_equip() {
            	var equipid = $('input:radio:checked').val();
            	if(equipid == null) {
            		alert('请选择设备...');
            		return false;
            	}
            	window.location.href=ctx+"/account-life-cycle/start-by-ciid?ciid="+equipid+"&typeid=4";
            }
            
            function start_change() {
            	var equipid = $('input:radio:checked').val();
            	if(equipid == null) {
            		alert('请选择设备...');
            		return false;
            	}
            	window.location.href=ctx+"/account-life-cycle/start-by-ciid?ciid="+equipid+"&typeid=2";
            }
            
            function reloados_equip() {
            	var equipid = $('input:radio:checked').val();
            	if(equipid == null) {
            		alert('请选择设备...');
            		return false;
            	}
            	window.location.href=ctx+"/account-life-cycle/start-by-ciid?ciid="+equipid+"&typeid=6";
            }
            
            function maintain_equip() {
            	var equipid = $('input:radio:checked').val();
            	if(equipid == null) {
            		alert('请选择设备...');
            		return false;
            	}
            	window.location.href=ctx+"/account-life-cycle/start-by-ciid?ciid="+equipid+"&typeid=7";
            }
            
            function destroy_equip() {
            	var equipid = $('input:radio:checked').val();
            	if(equipid == null) {
            		alert('请选择设备...');
            		return false;
            	}
            	window.location.href=ctx+"/account-life-cycle/start-by-ciid?ciid="+equipid+"&typeid=5";
            }
            
            function stop_equip() {
            	var equipid = $('input:radio:checked').val();
            	if(equipid == null) {
            		alert('请选择设备...');
            		return false;
            	}
            	window.location.href=ctx+"/account-life-cycle/start-by-ciid?ciid="+equipid+"&typeid=3";
            }
            
            function retrieveData( sSource111,aoData111, fnCallback111) {
    			$.ajax({
    				url : sSource111,//这个就是请求地址对应sAjaxSource
    				data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
    				type : 'get',
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
                    <li><a href="#">台账管理</a> <span class="divider">></span></li>   
                    <li class="active">${at.name }</li>
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
                            <h1>${at.name }</h1>  

                            <ul class="buttons"> 
                            <li><a href="#myModal" data-toggle="modal" class="isw-zoom tipb" title="搜索"></a></li>
                            <li><a href="#" class="isw-list tipb" onclick="in_record()" title="填写工作记录"></a></li>  
                            <li><a href="#" class="isw-delete tipb" onclick="scrap_equip()" title="报废"></a></li> 
							<li><a href="${contextPath }/account-life-cycle/add?typeid=${at.num }" class="isw-plus tipb" title="设备申请"></a></li>
							<li><a href="#" class="isw-power tipb" onclick="reloados_equip()" title="重装操作系统"></a></li>    
							<li><a href="#" class="isw-cancel tipb" onclick="stop_equip()" title="设备停用"></a></li> 
							<li><a href="#" class="isw-calc tipb" onclick="start_change()" title="设备变更"></a></li> 
							<li><a href="#" class="isw-calc tipb" onclick="maintain_equip()" title="设备维修"></a></li>
							<li><a href="#" class="isw-cancel tipb" onclick="destroy_equip()" title="设备销毁"></a></li>                      
                                <li>
                                    <a href="#" class="isw-settings tipl" title="其他操作 "></a>
                                    <ul class="dd-list">
                                    <li><a href="${contextPath }/stats/account/list"><span class="isw-refresh"></span> 台账信息列表</a></li>
                                    <li><a href="${contextPath }/stats/account/add"><span class="isw-refresh"></span> 创建台账类别</a></li>
                                    <!-- <li><a href="${contextPath }/stats/account/setting"><span class="isw-refresh"></span> 自定义台账</a></li> -->
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                            <!-- <table class="stripe row-border order-column" id="eventTable"> -->
                            <table class="table" id="eventTable">
                                <thead>
                                	<tr>   
                                	<th>&nbsp;</th>                                 
                                        <c:forEach items="${columns }" var="column">
                                        	<th>${column }</th>
                                        </c:forEach>
                                        <th>操作</th>
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
     	<!-- modal form -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>查找</h4>
                    </div>
                    <input type="hidden" id="mainType" name="mainType" value="${at.num }" />
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="type">类型：</label></div>
                                    <div class="col-md-9">
										<select id="type" name="type">
										<option value="0"></option>
                                 	<c:forEach items="${list }" var="account1">
                                 	<option value="${account1.category}">${account1.name }</option>
                                 	</c:forEach>
										</select>
									</div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="department">部门：</label></div>
                                    <div class="col-md-9">
										<select id="department" name="department">
										<option value="0"></option>
                                 	<c:forEach items="${topGroups }" var="topGroup">
                                 	<option value="${topGroup.id}">${topGroup.groupName }</option>
                                 	<c:forEach items="${topGroup.child }" var="group">
                                 	<option value="${group.id}">&nbsp;&nbsp;&nbsp;&nbsp;|--${group.groupName }</option>
                                 	<c:forEach items="${group.child }" var="child">
                                 	<option value="${child.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|--${child.groupName }</option>
                                 	</c:forEach>
                                 	</c:forEach>
                                 	</c:forEach>
										</select>
									</div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="status">状态：</label></div>
                                    <div class="col-md-9">
									<select id="status" name="status" style="width:100%">
									<option value="0"></option>
									<c:forEach items="${statusList }" var="status">
									<option value="${status.code }">${status.codeName }</option>
									</c:forEach>
									</select>
									</div>
                                </div>                                                           
                            </div>                
                        </div>
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3"><label for="securityLevel">密级：</label></div>
                                    <div class="col-md-9">
									<select id="securityLevel" name="securityLevel">
									<option value="0"></option>
									<c:forEach items="${securityLevelList }" var="securityLevel">
									<option value="${securityLevel.code }">${securityLevel.codeName }</option>
									</c:forEach>
									</select>
									</div>
                                </div>                                                           
                            </div>                
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <button class="btn btn-primary" aria-hidden="true" name="btn-find" id="btn-find">提交</button> 
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>            
                    </div>
                    <!--</form>-->
                </div>
            </div>
        </div>
    	<!-- modal form end -->
    </div>
</body>

</html>