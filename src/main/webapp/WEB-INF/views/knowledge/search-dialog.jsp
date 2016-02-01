<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>知识库管理--运维管理系统</title>

    <link href="${contextPath }/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/fullcalendar.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/ui.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath }/resources/css/select2.css" rel="stylesheet" type="text/css" />
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
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>  
    <script type='text/javascript' src='${contextPath }/resources/js/pm-common.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/pm-knowledge.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var ctx = "${contextPath}";
    	var type = "";
    	var id = "";
            $(document).ready(function () {
                var keyword = pm_getQueryString("keyword");
                type = pm_getQueryString("type");
                id = pm_getQueryString("id");
                if(keyword!=null)
                {
                	$("input[name='keyword']").attr("value",keyword);
                	pm_knowledge_highlight(keyword);
                }
                if(type==null||type=="")
                	$("#btn_knowledge_relation").hide();
                else
                	$("#btn_knowledge_relation").show();
                
                $("#btn_knowledge_relation").bind("click",pm_knowledge_relation);
            });
            function pm_knowledge_relation(){
            	var knowledgeid = $("#knowledge_id").text();
            	//保存
            	switch(type)
            	{
            	case 'incident':
            		//关联事件
            		$.getJSON(ctx+'/itilrelation/save-incident-knowledge?t='+pm_random(),{primary_id:id,secondary_id:knowledgeid},function(data){
            			
            		});
            		break;
            	}
            }
            function pm_knowledge_getdetail(id)
            {
            	$.getJSON(ctx+"/knowledge/detail/getjson/" + id,function(data){
            		$("#scroll_knowledge").show();
            		$("#knowledge_title").html(data.knowledge.title);
            		$("#knowledge_desc").html(data.knowledge.desc);
            		$("#knowledge_solution").html(data.knowledge.solution);
            		$("#knowledge_hits").html(data.knowledge.hits);
            		$("#knowledge_id").html(data.knowledge.id);
            		$("#scroll_knowledge").mCustomScrollbar();
            	});
            	return false;
            }
    </script>
</head>
<body>
    <div class="wrapper"> 
            <!--workplace-->
            <div class="workplace">             
                <div class="row">
                    <div class="col-md-4">   
                    <form action="${contextPath }/knowledge/searchdialog">  
                        <div class="headInfo">
                            <div class="input-group">
                            	
                                <input type="text" name="keyword" placeholder="关键字.." class="form-control"/>
                                <div class="input-group-btn">
                                    <!--  button class="btn btn-success" type="button">搜索知识</button-->
                                    <button class="btn btn-primary">搜索知识 </button>
                                </div>
                                
                            </div>                                           
                            <div class="arrow_down"></div>
                        </div>
                        </form>
                        <div class="block-fluid">
                            <div class="toolbar clearfix">
                                <div class="left">
                                    <div id="faqSearchResult" class="note"></div>
                                </div>
                                <div class="right">
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-sm tip" id="faqOpenAll" title="显示摘要"><span class="glyphicon glyphicon-chevron-down glyphicon glyphicon-white"></span></button>
                                        <button class="btn btn-default btn-sm tip" id="faqCloseAll" title="关闭摘要"><span class="glyphicon glyphicon-chevron-up glyphicon glyphicon-white"></span></button>
                                        <button class="btn btn-default btn-sm tip" id="faqRemoveHighlights" title="去除高亮显示"><span class="glyphicon glyphicon-remove glyphicon glyphicon-white"></span></button>
                                    </div>
                                </div>
                            </div>                                                        
                            <div class="faq">
                            	<c:if test="${count ==0}">
                            	未查询到相应知识项。
                            	</c:if>
                            	<c:forEach items="${list}" var="knowledge">
                                <div class="item" id="faq-1">
                                    <div class="title"><a href="#" onclick="pm_knowledge_getdetail(${knowledge.id})">${knowledge.title }</a></div>
                                    <div class="text"><p>${knowledge.desc }</p></div>
                                </div>
                                </c:forEach>
                            </div>
                            <div class="toolbar bottom-toolbar clearfix">
                                <tag:paginate max="5" offset="${offset}" count="${count}" uri="${url }" />
                            </div>
                        </div>
                    </div>
	                <div class="col-md-8">
					    <div class="block without-head news scrollBox">
					        <div id="scroll_knowledge" class="scroll" style="height:420px;display:none">
					            <h4 class="text-info" id="knowledge_title"></h4>
					            <h4 style="display:none" id="knowledge_id"></h4>
					            <hr />
					            <div class="actions text-right" style="margin-right:10px;">
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-sm" ><span class="glyphicon glyphicon-plus glyphicon glyphicon-white"></span> 阅读<span id="knowledge_hits"></span>次</button>
                                        <!-- 
                                        <button class="btn btn-default btn-sm"  id="btn_knowledge_relation"><span class="glyphicon glyphicon-envelope glyphicon glyphicon-white"></span> 引用</button>
                                        <button class="btn btn-default btn-sm" ><span class="glyphicon glyphicon-share-alt glyphicon glyphicon-white"></span> 附件</button>
                                        -->
                                    </div>
                            	</div>
					           <div><h4><span class="label label-info">故障描述</span></h4></div>
	                           <div id="knowledge_desc"></div>
	                           <div><h4><span class="label label-success">解决方案</span></h4></div>
	                           <div id="knowledge_solution"></div>
					        </div>
					    </div>
					</div>
                </div>
            </div>
            <!--workplace end-->
        </div> 
</body>

</html>