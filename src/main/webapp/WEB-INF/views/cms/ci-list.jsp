<%@page import="com.cngc.utils.activiti.ProcessDefinitionCache,org.activiti.engine.RepositoryService,org.activiti.engine.RuntimeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    
    <title>配置管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/pnotify.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/styling.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/validation.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/mycss.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/dataTables.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/dataTables.fixedColumns.css" rel="stylesheet" type="text/css" />
    <link href='${contextPath }/resources/js/plugins/jstree/jquery.treeview.css' rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/uploadify.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/dataTables.fixedColumns.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>
	<script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
	<script type='text/javascript' src='${contextPath }/resources/js/plugins/uploadify/jquery.uploadify.min.js'></script>   
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.edit.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jstree/jquery.treeview.async.js'></script>
    
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
            	
                $(".header").load("${contextPath}/header?t="+pm_random());
                $(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
                $(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
                $(".confirm").bind("click",function(){
                	if(!confirm("确定要执行该操作?"))
                		return false;
                });
                
                $("#b_popup_type").dialog({
    		        autoOpen: false,
    		        width: 400,
    		        height:500,
    		        buttons: { "确定": function () { $(this).dialog("close") } }
    		    });
                
                $('#file_upload').uploadify({
    				'formData' : { 'type' : 1 },
    		        'swf'      : '${contextPath }/resources/flash/uploadify.swf',
    		        'fileTypeExts': '*.xls;*.xlsx',//允许上传的文件类型，限制弹出文件选择框里能选择的文件
    		      //按钮显示的文字
                    'buttonText': '选择文件……',
    		        'uploader' : '${contextPath}/attachment/upload',
    		        'removeCompleted' : false,
    		        'multi': true,
    		        // Put your options here
    		        'onUploadSuccess': function (file, data, response) {
    		        	
    	                $('#' + file.id).find('.data').html(' 上传完毕');
    	                var fileids = document.getElementById("fileids").value + '';
    	                document.getElementById("fileids").value = fileids + data;
    		        }
    		    });
    		    
    		    $("#type_sel").bind("click",function(){
    		    	$("#treeview").empty();
    		    	$("#treeview").treeview({
    		    		url:ctx + '/cms/category/getjson?t=' + pm_random(),
                		collapsed: true,
                		unique: true
                	});
    	    		$("#b_popup_type").dialog('open');
                });
    		    
            	table = $('#ciTable').dataTable({
        				"oLanguage": {"sUrl": "${contextPath}/resources/json/Chinese.json"},
        			    "bAutoWidth": true,
        			    "sDom": '<"top"i>rt<"bottom"flp><"clear">',     //改变页面元素位置      
        			    "bPaginate": true,   
        			    "sPaginationType": "full_numbers", 
        			    "iDisplayLength": 10, 
        			    "bSort": true, 
        			    "bFilter": false, 
        			    "aaSorting": [], 
        			    "bInfo": false, 
        			    "bStateSave": false, 
        			    "iCookieDuration": 0, 
        			    "bScrollCollapse": true, 
        			    "bProcessing": true, 
        			    "bJQueryUI": false,
        			    "sScrollX":"100%",
        			    "sScrollXInner":"200%",
        			    //"fixedColumns":  true,
        			    //"fixedColumnsOptions":{
        		        //    iLeftColumns: 1,
        		        //    iRightColumns: 1
        		        //},
        		        "bServerSide": true,//这个用来指明是通过服务端来取数据
        	            "sAjaxSource": ctx + '/cms/ci/list-by-category?t=' + pm_random(),//这个是请求的地址
        	            "fnServerData": retrieveData, // 获取数据的处理函数
        	            "fnServerParams": function (aoData) {  //查询条件
        	                aoData.push(
        	                		{ "name": "code", "value": $("#search_code").val() }
        	                    );
        	            },
        	            "aoColumnDefs": [ {
        	                "aTargets": [ 0 ],
        	                "mData": "download_link",
        	                "mRender": function ( data, type, full ) {
        	                  return '<a href="${contextPath }/cms/ci/detail/'+full.id+'">'+data+'</a>';
        	                }
        	              },
        	              //{
          	              //  "aTargets": [ 8 ],
          	              //  "mRender": function ( data, type, full ) {
          	              //  	if(data==null)
          	              //  		return "";
          	              //  	else
          	              //    		return (data.split(" "))[0];
          	              //  }
          	              //},
          	           
          	            	{
            	                "aTargets": [ 13 ],
            	                "mData": "download_link",
            	                "mRender": function ( data, type, full ) {
            	                	
            	                	if(data.length>0){
            	                		for (var k = 0, length = data.length; k < length; k++) {
            	                			return '<a href="${contextPath }/attachment/download/'+data[k].id+'">'+data[k].name+'</a><br/>';
            	                		}
            	                	}else
            	                		return "";
            	                }
            	              },
        	              {
          	                "aTargets": [ 14 ],
          	                "mData": "download_link",
          	                "mRender": function ( data, type, full ) {
          	                  return '<a href="${contextPath }/cms/ci/addproperty/'+full.id+'">属性</a>&nbsp;&nbsp;<a href="${contextPath }/cms/ci/edit?ciid='+full.id+'">修改</a>&nbsp;&nbsp;<a href="#" onclick="del('+full.id+');">删除</a>';
          	                }
          	              }],
        	            "aoColumns" : [
        	                           { "mData" : 'name' }, 
        	                           { "mData" : 'num' }, 
        	                           { "mData" : 'categoryName' }, 
        	                           { "mData" : 'producer' }, 
        	                           { "mData" : 'model' }, 
        	                           { "mData" : 'departmentName' }, 
        	                           { "mData" : 'userInMaintenanceName' }, 
        	                           { "mData" : 'serial' }, 
        	                           { "mData" : 'createdTime' }, 
        	                           { "mData" : 'expirationTime' }, 
        	                           { "mData" : 'serviceStartTime' }, 
        	                           { "mData" : 'purpose' }, 
        	                           { "mData" : 'statusName' }, 
        	                           { "mData" : 'attachs' },
        	                           { "mData" : 'id' }
        	                             ]
        			});
            	new $.fn.dataTable.FixedColumns(table);
                
            });
            
            function initTable(code)
            {
            	//datatables_options["sAjaxSource"] = ctx + '/cms/ci/list-by-category?t=' + pm_random();
            		$("#search_code").val(code);

            		 table.fnDraw();
            		
                    //$(".confirm").bind("click",function(){
                    //	if(!confirm("确定要执行该操作?"))
                    //		return false;
                    //}); 
            		 $("#b_popup_type").dialog('close');    
            }
            
            //function transDate(time_) {
            //	if(time_==null) return "";
            //	var dt = new Date(time_);
            	
            //	return dt.toLocaleDateString();
            //}
            
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
            
            function del(id) {
            	if(confirm("确认删除")) {
            		window.location='${contextPath}/cms/ci/delete/'+id;
            	} else {
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
                    <li><a href="${contextPath }/cms/ci/list">配置管理</a> <span class="divider">></span></li>       
                    <li class="active">配置项管理</li>
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
                            <h1>配置项管理</h1>  

                            <ul class="buttons">                          
                            	<li>
                                    <a href="${contextPath }/cms/ci/add" role="button" data-toggle="modal" class="isw-plus tipb" title="创建配置项"></a>
                                </li>
                                <li>
                                    <a href="#" class="isw-settings tipl" title="操作 "></a>
                                    <ul class="dd-list">
                                    	<li><a href="#" id="type_sel" title="按照配置项的类别显示"><span class="isw-list"></span> 类别选择</a></li>
                                    	<li><a href="#fModal" data-toggle="modal"><span class="isw-up"></span> 导入数据</a></li>
                                        <li><a href="#" onclick="pm_refresh()"><span class="isw-refresh"></span> 刷新</a></li>
                                    </ul>
                                </li>
                            </ul>                             
                        </div>
                        <div class="block-fluid table-sorting clearfix">
                        	<input type="hidden" id="search_code" name="search_code" value="0" />
                            <table class="stripe row-border order-column" id="ciTable">
                                <thead>
                                	<tr>
										<th>设备名称</th>
										<th>设备编号</th>
										<th>分类</th>
										<th>生产厂商/品牌</th>
										<th>型号/版本号</th>
										<th>所属部门</th>
										<th>责任人</th>
										<th>出厂编号/序列号</th>
										<th>购置时间</th>
										<th>维保截止时间</th>
										<th>启用时间</th>
										<th>设备用途</th>
										<th>使用情况</th>
										<th>文件</th>
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
        <div class="modal fade" id="fModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4>导入Excel文件</h4>
                    </div>
                    <form method="post" action="${contextPath}/cms/ci/importData">
                    <input id="fileids" name="fileids" type="hidden" />
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid">
                                <div class="row-form clearfix">
                                    <div class="col-md-3">文件:</div>
                                    <div class="col-md-9"><input type="file" name="file_upload" id="file_upload" /></div>
                                </div>
                                
                            </div>
                            
                        </div>
                    </div>
                    <div class="modal-footer">
                    <input type="submit" value="导入" class="btn btn-warning" />
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>  
    </div>
    
    <div class='dialog' id='b_popup_type' style='display: none;' title='配置项类别'>
    	<div class='block dialog_block messages '><div><ul id='treeview'></ul></div></div>
    </div>
</body>

</html>
