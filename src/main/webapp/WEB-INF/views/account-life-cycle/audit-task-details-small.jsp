<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
    <title>台账管理--运维管理系统</title>

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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
        
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/jquery.form.js'></script>
    
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
    var table;
    var atid = ${at.id};
            $(document).ready(function () {

            	$(".header").load("${contextPath }/header?t="+pm_random());
            	$(".menu").load("${contextPath }/menu?t="+pm_random(), function() {$("#node_${moduleId}").addClass("active");});
            	$(".breadLine .buttons").load("${contextPath}/contentbuttons?t="+pm_random());
            	
              //表单验证
                $("#validation2").validationEngine({promptPosition : "topRight", scroll: true });
                
                $('#groupBtn').click(function(){
                	table.fnDraw();
                });
                
                $('#groupBackBtn').click(function(){
					if(confirm('确定发回重审？')){
						var a = $("input[name='group2']:checked").val();
						if(a==null||a==''){alert('请选择一个部门');return false;}
						else{
		            		$.post("${contextPath }/account-life-cycle/audit-task/reaudit?at="+atid+"&group="+a, function(data) {
		
								if(data.flag)
									table.fnDraw();
								else
									alert("操作失败，原因："+data.msg);
	            			},"json");
		            		return true;
						}
            		} else
            			return false;
                });
                
                
                
                $('#pass').click(function(){
                	if(confirm('确认操作？')){
	                	var arrs = [];
	            		$("input[name=ids]").each(function() {
	            			if($(this).attr("checked")=='checked') {
	            				arrs.push($(this).val());
	            			}
	            		});
	            		
	            		if(arrs.length ==0 ) {
	            			alert('请选择一项');
	            			return false;
	            		}
	            		
	            		$.post("${contextPath }/account-life-cycle/audit-task/pass?decide=1&ids="+arrs, function(data) {
	
							if(data.flag)
								table.fnDraw();
							else
								alert("操作失败，原因："+data.msg);
            			},"json");
	            		return true;
            		} else
            			return false;
                });
                
                $('#batchVerity').click(function(){
                	if(confirm('确认操作？')){
	                	var arrs = [];
	            		$("input[name=ids]").each(function() {
	            			if($(this).attr("checked")=='checked') {
	            				arrs.push($(this).val());
	            			}
	            		});
	            		
	            		if(arrs.length ==0 ) {
	            			alert('请选择一项');
	            			return false;
	            		}
	            		
	            		$.post("${contextPath }/account-life-cycle/audit-task/batch-verity?ids="+arrs, {at:atid},function(data) {
	
							if(data.flag)
								table.fnDraw();
							else
								alert("操作失败，原因："+data.msg);
            			},"json");
	            		return true;
            		} else
            			return false;
                });
                
                $('#commitBtn').click(function(){
                	if(confirm('确认提交？')){
	                	$.ajax({
	        				url : "${contextPath }/account-life-cycle/audit-task/commit/"+atid,//这个就是请求地址对应sAjaxSource
	        				type : "post",//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	        				success : function(result) {
	        					if(result.flag) {
	        						table.fnDraw();
	        						alert('提交成功');
	        					} else {
	        						alert(result.msg);
	        					}
	        				}
	        			});
	                	return true;
                	} 
                	
                });
                
                $("input[name=checkall]").click(function(){
                    
                    if(!$(this).is(':checked'))
                        $(this).parents('table').find('input[type=checkbox]').attr('checked',false);
                    else
                        $(this).parents('table').find('input[type=checkbox]').attr('checked',true);
                        
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
    		        "bServerSide": true,//这个用来指明是通过服务端来取数据
    	            "sAjaxSource": ctx + '/account-life-cycle/get-accounts-by-group?t=' + pm_random(),//这个是请求的地址
    	            "fnServerData": retrieveData, // 获取数据的处理函数
    	            "fnServerParams": function (aoData) {  //查询条件
    	                aoData.push(
    	                		{ "name": "groupId", "value": $("input[name='group1']").val() },
    	                		{ "name": "atId", "value": ${at.id} }
    	                    );
    	            },
    	            "aoColumnDefs": [ {  
    	            	sDefaultContent : '',  
    	            	aTargets: ['_all']  
    	            	}, 
    	            	{
    	                "aTargets": [ 0 ],
    	                "mRender": function ( data, type, full ) {
    	                	if(full.departmentcommitstatus==1&&full.ReviewStatus == '05')
    	                  		return '<input type="checkbox" name="ids" value="'+full.id+'"/>';
    	                }
    	              },
    	              {
      	                "aTargets": [ 2 ],
      	                "mRender": function ( data, type, full ) {
      	                	return '<a href="${contextPath}/stats/account/detail/'+full.id+'">'+data+'</a>';
      	                }
      	              },
      	            	{
        	                "aTargets": [ 10 ],
        	                "mRender": function ( data, type, full ) {
        	                	if(data=="") {
        	                		return "";
        	                	} else if(data=="1") {
        	                		return '<span class="label label-success">新建</span>';
        	                	} else if(data=="2") {
        	                		return '<span class="label label-warning">修改</span>';
        	                	} else if(data=="3") {
        	                		return '<span class="label label-default">删除</span>';
        	                	}
        	                }
        	              },
        	              {
          	                "aTargets": [ 11 ],
          	                "mRender": function ( data, type, full ) {
          	                	if(data) {
          	                		return '<span class="label label-info">已确认</span>';
          	                	} else
     	                			 return '<span class="label label-warning">未确认</span>';
          	                }
          	              },
    	              {
      	                "aTargets": [ 12 ],
      	                "mRender": function ( data, type, full ) {
      	                	var str = '<a href="javascript:void(0);" onclick="contains0('+data+')">对比</a><br/>';
      	                		if(full.ReviewStatus == '05') {
      	                			if(full.action=="")
	      	                  			return '<a href="javascript:void(0);" onclick="pass0('+data+',1)">审核通过</a><br/><a href="javascript:void(0);" onclick="pass0('+data+',0)">审核不通过</a>';
	      	                  		else
	      	                  			return str+'<a href="javascript:void(0);" onclick="pass0('+data+',1)">审核通过</a><br/><a href="javascript:void(0);" onclick="pass0('+data+',0)">审核不通过</a>';
      	                		} else if(full.ReviewStatus == '03' || full.ReviewStatus == '04') {
      	                			if(full.action=="")
	      	                  			return '';
	      	                  		else
      	                				return str;
      	                		}
      	                }
      	              }],
    	            "aoColumns" : [
    	            	 { "mData" : 'id' },
    	                           { "mData" : 'SecurityNo' }, 
    	                           { "mData" : 'Name' }, 
    	                           { "mData" : 'SecurityLevelName' }, 
    	                           { "mData" : 'UserInMaintenanceName' }, 
    	                           { "mData" : 'CategoryName'},
    	                           { "mData" : 'DepartmentInUseName' }, 
    	                           { "mData" : 'ServiceStartTime' }, 
    	                           { "mData" : 'StatusName' }, 
    	                           { "mData" : 'ReviewStatusName' }, 
    	                           { "mData" : 'action' }, 
    	                           { "mData" : 'verity' }, 
    	                           { "mData" : 'id' }
    	                             ]
    			});
                
            });
            
            function verity0(id) {
            	if(confirm("是否确认此台账？")) {
            		$.post("${contextPath }/account-life-cycle/audit-task/verity",{ciid: id, atid: atid},function(result){
            		    if(result.flag) {
            		    	table.fnDraw();
            		    }else {
            		    	alert(result.msg);
            		    }
            		  });
                	return true;
                	} else
                		return false;
            }
            
            function pass0(id,decide){
            	if(confirm('确认操作？')){
            		$.post("${contextPath }/account-life-cycle/audit-task/pass?decide="+decide+"&ids="+id, function(data) {

						if(data.flag)
							table.fnDraw();
						else
							alert("操作失败，原因："+data.msg);
        			},"json");
            		return true;
        		} else
        			return false;
            }
            
            function contains0(id) {
            	$.ajax({
    				url : "${contextPath}/account-life-cycle/audit-task/contains0?ciid="+id,//这个就是请求地址对应sAjaxSource
    				type : "get",//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
    				success : function(result) {
    					var str = '<div class="row-form clearfix"><div class="col-md-3">&nbsp;</div><div class="col-md-4"><b>原 值</b></div><div class="col-md-5"><b>修改值</b></div></div>';
    					$.each(result,function(name,value) {
    						var value1 = "";
    						if(value[1]!=null)
    							value1 = value[1];
    						str+='<div class="row-form clearfix"><div class="col-md-3"><b>'+name+'：</b></div><div class="col-md-4">'+value[0]+'</div><div class="col-md-5">'+value1+'</div></div>';
    						});
    					$('#contains0').empty();
    					$('#contains0').append(str);
    					$("#containsModal").modal('show');
    				}
    			});
            	
            }
            
            function del(id) {
            	if(confirm("是否删除？")) {
            	$.ajax({
    				url : "${contextPath }/account-life-cycle/audit-task/del/"+id,//这个就是请求地址对应sAjaxSource
    				type : "post",//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
    				success : function(result) {
    					if(result.flag) {
    						table.fnDraw();
    					} else {
    						alert(result.msg);
    					}
    				}
    			});
            	return true;
            	} else
            		return false;
            }
            
            function recover(id) {
            	if(confirm("是否恢复？")) {
	            	$.ajax({
	    				url : "${contextPath }/account-life-cycle/audit-task/recover/"+id,//这个就是请求地址对应sAjaxSource
	    				type : "post",//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	    				success : function(result) {
	    					if(result.flag) {
	    						table.fnDraw();
	    					} else {
	    						alert(result.msg);
	    					}
	    				}
	    			});
	            	return true;
            	} else
            		return false;
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


        <div class="content wide0">

            <!--workplace-->
            <div class="workplace wide0">             

                <div class="row">

                    <div class="col-md-12" >
                    	<input type="hidden" name="group1" value="${groupId }"/>
                        <input type="hidden" id="categorycode" value="0">
						<div class="head clearfix">
                            <ul class="buttons">
                                <li>
                                    <a class="isw-settings" href="#"></a>
                                    <ul class="dd-list">
                                        <li><a href="javascript:void(0);" id="pass"><span class="isw-edit"></span> 审核通过</a></li>
                                        <li><a data-toggle="modal" href="#fModal"><span class="isw-edit"></span> 发回重审</a>
                                    </ul>
                                </li>
                            </ul>                        
                        </div>
                        <input type="hidden" id="categorycode" value="0">
                        <div class="block-fluid  table-sorting clearfix">
                            <table  class="table" id="eventTable">
                                <thead>
                                    <tr>
                                       <th width="30px"><input type="checkbox" name="checkall"/></th>
                                       	<th width="6%">编号</th>
                                        <th>名称</th>
                                        <th width="5%">密级</th>
										<th width="7%">责任人</th>
										<th width="7%">类别</th>
										<th width="10%">所属部门</th>
										<th width="10%">启用时间</th>
										<th width="10%">使用情况</th>
										<th>审核状态</th>
										<th>审核操作</th>
										<th>是否确认</th>
										<th width="10%">操作</th>
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
        
         <!-- Bootrstrap modal form -->
        
        <div class="modal fade" id="containsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>                        
                        <h4>对比数据</h4>
                    </div>
                    <div class="modal-body modal-body-np">
                        <div class="row">
                            <div class="block-fluid" id="contains0">
                            </div>                
                        </div>
                    </div>   
                </div>
            </div>
        </div>
    </div>
</body>

</html>