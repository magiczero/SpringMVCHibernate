<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.cngc.pm.model.Style" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    
    <title>工程师工作台--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
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
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/treeview/bootstrap-treeview.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.resize.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-en.js' charset='utf-8'></script>
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
    
    function checkedBox () {
    	var arrs = [];
		$("input[name=checkbox]").each(function() {
			if($(this).attr("checked")=='checked') {
				arrs.push($(this).val());
			}
		});
	
		return arrs;
	}
    
    function transferId() {
    	var arr3 = checkedBox();
    	if(arr3=='' || arr3.length > 1) {
			notify_e('Error','请选择一项');
			return false;
		}
    	$('#id').val(arr3[0]);
    	$('#fModal').modal();
    }
	
            $(document).ready(function () {

                $(".header").load("${contextPath }/header");
                $(".menu").load("${contextPath }/menu", function () { $(".navigation > li:eq(5)").addClass("active"); });
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");
                
                //ul添加class
                $("#style-${flag}").addClass("active");
                
                $("#btnUpdateVersion").click(function() {
            		var arr1 = checkedBox ();
            		if(arr1.length == 1) {
            			window.location = "${contextPath }/document/update_version/"+arr1[0];
            		} else {
            			notify_e('Error','请选择一项');
		    			return false;
            		}
                });
                
                 if($("#checkItems").length > 0){
                    $("#checkItems").multiSelect({
                        selectableHeader: "<div class='multipleselect-header'>所有检查项</div>",
                        selectedHeader: "<div class='multipleselect-header'>已选择项</div>"
                    });
                    $('#multiselect-selectAll').click(function(){
                        $('#checkItems').multiSelect('select_all');
                        return false;
                    });
                    $('#multiselect-deselectAll').click(function(){
                        $('#checkItems').multiSelect('deselect_all');
                        return false;
                    });
                    $('#multiselect-selectIndia').click(function(){
                        $('#checkItems').multiSelect('select', 'in');
                        return false;
                    });         
                 }
                
                $("#delBtn").click(function() {
                		if(confirm("确定执行删除操作？")) {
        		    		//var arr = [];
        		    		//$("input[name=checkbox]").each(function() {
        		    		//	if($(this).attr("checked")=='checked') {
        		    		//		arr.push($(this).val());
        		    		//	}
        		    		//});
        		    		var arr2 = checkedBox();
        		    		if(arr2=='') {
        		    			//$("div.alert").removeClass('hide');
        		    			notify_e('Error','请至少选择一项');
        		    			return false;
        		    		} else {
        		    			//$("div.alert").addClass('hide');
        		    			$.ajax({
        		    				type : "post",
        		    				dataType : "json",
        		    				url : "${contextPath }/document/delete",
        		    				data : {ids : arr2.join(",")},
        		    				success : function(data) {
        		    					if (data.flag == "true") {
        		    						
        		    						window.location.href="${contextPath }/document/list";
        		    						
        		    					} else {
        		    						notify_e('Error','请联系管理员');
        		    					}
        		    				}
        		    			});
        		    		}
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
                     <div class="col-md-3 clearfix" id="mails_navigation">                    
                        <span class="btn btn-success btn-block" >文档类别</span>
                         <div id="tree"></div>    

                    </div>

                    <div class="col-md-9" id="mails">
                        <div class="headInfo">
                            <div class="input-group">
                                <input type="text" name="search" placeholder="search keyword..." id="widgetInputMessage" class="form-control"/>
                                <div class="input-group-btn">
                                    <button class="btn btn-success" type="button">Search</button>
                                </div>
                            </div>                                           
                            <div class="arrow_down"></div>
                        </div>    

                        <div class="block-fluid" id="inbox">
                            <div class="toolbar clearfix">
                                <div class="left">
                                    <div class="btn-group">
                                        <button id="btnCreate" class="btn btn-sm btn-success tip" title="新建文档">新建文档</button>
                                        <button id="btnUpdateVersion" class="btn btn-sm btn-warning tip" title="更新版本">更新版本</button>
                                        <button class="btn btn-sm btn-danger tip" title="删除文档" id="delBtn">删除文档</button>
                                        <button class="btn btn-sm btn-info tip" onclick="transferId();" data-toggle="modal">关联检查项</button>
                                    </div>
                                </div>
                                <div class="right">                                   
                                    <ul class="pagination pagination-sm">
                                        <li class="disabled"><a href="#">Prev</a></li>
                                        <li class="disabled"><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">Next</a></li>
                                    </ul>                         
                                </div>
                            </div>
                            <table  class="table">
                                <thead>
                                    <tr>
                                       <th width="40px"><input type="checkbox" name="checkall"/></th>
                                        <th width="10%">文档名称</th>
                                        <th width="5%">密级</th>
										<th width="7%">录入人</th>
										<th width="10%">类别</th>
										<th width="7%">编号</th>
										<th width="10%">录入时间</th>
										<th width="5%">版本</th>
										<th>检查项</th>
										<th width="10%">存放位置</th>
										<th>附件</th>                                                                        
                                    </tr>
                                </thead>
                                <tbody>
                                     <c:forEach items="${listDocs}" var="doc">
                                    <tr>
                                        <td><input type="checkbox" name="checkbox" value="${doc.id }"/></td>
                                        <td>${doc.name }</td>
                                        <td>${doc.secretLevel.level }</td>
										<td>${doc.user.username }</td>
										<td>${doc.style.name }</td>
										<td>${doc.docNum}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${doc.createDate }" /></td>
										<td>${doc.versions}</td>
										<td><c:forEach items="${doc.checkItems }" var="item">
											${item.name }<br />
										</c:forEach></td>
										<td>${doc.deposit}</td>
										<td><c:forEach items="${doc.attachs}" var="attach"><a href="${contextPath }/attachment/download/${attach.id}">${attach.name }</a><br/></c:forEach></td>
                                    </tr>
                                    </c:forEach>                                
                                </tbody>
                            </table>                       
                            <div class="toolbar bottom-toolbar clearfix">
                                <div class="left">
                                </div>
                                <div class="right">
                                    
                                    <ul class="pagination pagination-sm">
                                        <li class="disabled"><a href="#">Prev</a></li>
                                        <li class="disabled"><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">Next</a></li>
                                    </ul>
                                    
                                </div>
                            </div>                                                                     
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
                        <h4>关联检查项</h4>
                    </div><c:url var="actionUrl" value="/document/relation-item" ></c:url>
                    <form:form action="${actionUrl }" commandName="document">
                    <form:hidden path="id"/>
                    <div class="modal-body modal-body-np">
                        <div class="row">
                           <div class="block">                        
							<form:select multiple="true" path="checkItems" class="validate[required] multiselect">
								<form:options items="${listCheckItems}" itemValue="id" itemLabel="name"/>
							</form:select>
                            
                            <div class="btn-group">
                                <button class="btn btn-default btn-xs" id="multiselect-selectAll" type="button">全选</button>
                                <button class="btn btn-default btn-xs" id="multiselect-deselectAll" type="button">全不选</button>
                            </div>                             
                        </div>
                        </div>
                    </div>   
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-warning" value=" 保存 " />
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Close</button>            
                    </div>
                    </form:form>
                </div>
            </div>
        </div>   
    </div>
    <script type="text/javascript">
    var tree = [
				{
				    text: "全部文档",
				    selectable: true,
				    state: {
	                    checked: true,
	                    selected: true
	                  },
				    href: "${contextPath }/document/list"
				},
                {
                  text: "种类",
                  
                  nodes: [ 
                           <%
                           java.util.Map<Integer, Integer> map = new java.util.HashMap<Integer, Integer>();
                           List<Style> list = (List<Style>)request.getAttribute("styles");
                           int x=1, i = 0, size=list.size();
                           for(Style style : list) {
                        	   i++; x++;
                        	   map.put(style.getId().intValue(), x);
                           %>
                    {
                      text: "<%=style.getName()%>"
                      <%
                      if(style.getChild() != null) {
                    	  out.print(",");
                      	int j = 0, sizej=style.getChild().size();
                      %>
                      nodes: [
                        <%for(Style style1 : style.getChild()) {
                        	j++; x++;
                        	map.put(style1.getId().intValue(), x);
                        %>
                        {
                          text: "<%=style1.getName()%>",
                          icon: "glyphicon glyphicon-leaf",
                          href: "${contextPath }/document/list/style/<%=style1.getId()%>"
                        }
                        <%if(j<sizej) out.print(",");}%>
                      ]<%}%>
                    }<%if(i<size) out.print(",");
                    }%>
                  ]
                },
                
                {
                  text: "检查项",
                  nodes : [
					<%
					x++;
					List<Style> listItems = (List<Style>)request.getAttribute("listCheckItems"); 
					int k=0, sizek = listItems.size();
					for(Style style3 : listItems) {
						k++; x++;
						map.put(style3.getId().intValue(), x);
					%>
					{
						text: "<%=style3.getName()%>",
						icon: "glyphicon glyphicon-leaf",
                        href: "${contextPath }/document/list/item/<%=style3.getId()%>"
					}
					<%if(k<sizek) out.print(",");}%>
                  ]
                }
              ];     
     
    $('#tree').treeview({
		enableLinks:true,
    	data: tree
    	});      
    <% 
    Long styleid = (Long)request.getAttribute("styleid");
    if(styleid > 0) {
    %>
    $('#tree').treeview('selectNode', [ <%=map.get(styleid.intValue())%>, { silent: true } ]);
    $('#tree').treeview('expandAll', { silent: true });
    <%} else {%>
    $('#tree').treeview('collapseAll', { silent: true });
    <%}%>
    </script>
</body>

</html>