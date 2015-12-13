<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <div class="breadLine">            
                <div class="arrow"></div>
                <div class="adminControl active">
                    ${user.username }
                </div>
            </div>

            <div class="admin">
                <div class="image">
                    <img src="${contextPath }/resources/img/users/aqvatarius.jpg" class="img-thumbnail"/>                
                </div>
                <ul class="control">                
                    <li><span class="glyphicon glyphicon-comment"></span> <a href="messages.html">短消息</a> <a href="messages.html" class="caption red">12</a></li>
                    <li><span class="glyphicon glyphicon-cog"></span> <a href="forms.html">设置</a></li>
                    <li><span class="glyphicon glyphicon-share-alt"></span> <a href="${contextPath }/loginout">退出</a></li>
                </ul>
                <div class="info">
                    <span>欢迎回来！上次登录:<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${lastLogin }" /></span>
                </div>
            </div>

            <ul class="navigation">            
                <li class="openable">
                    <a href="#">
                        <span class="isw-attachment"></span><span class="text">快速链接</span>
                    </a>
                    <ul>
                    	<li>
                            <a href="${contextPath }/incident/addbyuser">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">我要报修</span>
                            </a>                  
                        </li> 
                        <li>
                            <a href="${contextPath }/incident/mylist">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">处理中的报修</span>
                            </a>                  
                        </li> 
                        <li>
                            <a href="${contextPath }/incident/search">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">历史报修信息</span>
                            </a>                  
                        </li> 
                        <li>
                            <a href="${contextPath }/workflow/task/mytask">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">待办任务</span>
                            </a> 
                            <a href="#" class="caption yellow link_navPopMessages">0</a>                   
                        </li>
                        <li>
                            <a href="${contextPath }/incident/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">事件控制台</span>
                            </a>                  
                        </li>    
                        <li>
                            <a href="${contextPath }/change/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">变更控制台</span>
                            </a>                  
                        </li> 
                       <li>
                            <a href="${contextPath }/leadertask/list">
                                <span class="glyphicon glyphicon-star"></span><span class="text">领导交办</span>
                            </a>                  
                        </li>                       
                        <li>
                            <a href="${contextPath }/record/inspection">
                                <span class="glyphicon glyphicon-search"></span><span class="text">日常巡检</span>
                            </a>                  
                        </li> 

                        <li>
                            <a href="${contextPath }/knowledge/search">
                                <span class="glyphicon glyphicon-search"></span><span class="text">知识库</span>
                            </a>                  
                        </li>   
                         <li>
                            <a href="${contextPath }/message/list">
                                <span class="glyphicon glyphicon-envelope"></span><span class="text">我的消息</span>
                            </a>                  
                        </li>                     
                    </ul>                
                </li> 
                <li class="openable">
                    <a href="#">
                        <span class="isw-list"></span><span class="text">事件管理</span>
                    </a>
                    <ul>
                        <li>
                            <a href="${contextPath}/incident/add">
                                <span class="glyphicon glyphicon-plus"></span><span class="text">创建新事件</span>
                            </a>                  
                        </li> 
                        <li>
                            <a href="${contextPath }/incident/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">事件控制台</span>
                            </a>    
                        </li>                       
                        <li>
                            <a href="${contextPath }/incident/search">
                                <span class="glyphicon glyphicon-search"></span><span class="text">历史事件查询</span>
                            </a>                  
                        </li>                           
                    </ul>                
                </li>         
                <li class="openable">
                    <a href="#">
                        <span class="isw-archive"></span><span class="text">变更管理</span>                 
                    </a>
                    <ul>
                        <li>
                            <a href="${contextPath }/change/add">
                                <span class="glyphicon glyphicon-plus"></span><span class="text">创建变更请求</span>
                            </a>                  
                        </li>    
                        <li>
                            <a href="${contextPath }/change/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">变更控制台</span>
                            </a>                  
                        </li>                    
                        <li>
                            <a href="${contextPath }/change/search">
                                <span class="glyphicon glyphicon-search"></span><span class="text">历史变更查询</span>
                            </a>                  
                        </li>                           
                    </ul> 
                </li>    
                <li class="openable">
                    <a href="#">
                        <span class="isw-text_document"></span><span class="text">配置管理</span>
                    </a>
                    <ul>
                    	<li>
                            <a href="${contextPath }/cms/property/list">
                                <span class="glyphicon glyphicon-th"></span><span class="text">属性池管理</span>
                            </a>                  
                        </li>
                        <li>
                            <a href="${contextPath }/cms/category/list">
                                <span class="glyphicon glyphicon-th"></span><span class="text">CI分类管理</span>
                            </a>                  
                        </li>
                        <li>
                            <a href="${contextPath }/cms/relation/list">
                                <span class="glyphicon glyphicon-th"></span><span class="text">CI关系管理</span>
                            </a>                  
                        </li>    
                        <li>
                            <a href="${contextPath }/cms/categoryrelation/list">
                                <span class="glyphicon glyphicon-th"></span><span class="text">CI模型管理</span>
                            </a>                  
                        </li>
                        <li>
                            <a href="${contextPath }/cms/ci/list">
                                <span class="glyphicon glyphicon-th"></span><span class="text">CI管理</span>
                            </a>                  
                        </li>  
                    </ul> 
                </li>   
                <li class="openable">
                    <a href="#">
                        <span class="isw-text_document"></span><span class="text">文档管理</span>
                    </a>
                    <ul>
                        <li>
                            <a href="${contextPath }/document/add">
                                <span class="glyphicon glyphicon-th"></span><span class="text">创建文档</span>
                            </a>                  
                        </li>    
                        <li>
                            <a href="${contextPath }/document/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">文档管理</span>
                            </a>                  
                        </li>                    
                    </ul> 
                </li>         
                <li class="openable">
                    <a href="#">
                        <span class="isw-documents"></span><span class="text">运维工作管理</span>                    
                    </a>
                    <ul>
                    	<li><a href="${contextPath }/leadertask/list"><span class="glyphicon glyphicon-star"></span><span class="text">领导交办</span></a></li>
                    	<li><a href="${contextPath }/record/inspection"><span class="glyphicon glyphicon-off"></span><span class="text">日常巡检</span></a></li>  
                    	<li><a href="${contextPath }/record/update"><span class="glyphicon glyphicon-download-alt"></span><span class="text">升级管理</span></a></li>
                    	<li><a href="${contextPath }/record/secjob"><span class="glyphicon glyphicon-file"></span><span class="text">三员工作管理</span></a></li>                  
                        <li><a href="${contextPath }/record/income"><span class="glyphicon glyphicon-tasks"></span><span class="text">记录管理</span></a></li>
                    </ul>
                </li>  
                <li class="openable">
                    <a href="#">
                        <span class="isw-list"></span><span class="text">知识库管理</span>                    
                    </a>
                    <ul>                    
                        <li><a href="${contextPath}/knowledge/search"><span class="glyphicon glyphicon-search"></span><span class="text">知识库</span></a></li>
                        <li><a href="${contextPath}/knowledge/add"><span class="glyphicon glyphicon-plus"></span><span class="text">新知识</span></a></li>
                        <li><a href="${contextPath}/knowledge/list"><span class="glyphicon glyphicon-th-large"></span><span class="text">知识管理控制台</span></a></li>
                    </ul>
                </li> 
                <li class="openable">
                    <a href="#">
                        <span class="isw-list"></span><span class="text">报表与统计管理</span>                    
                    </a>
                    <ul>                    
                        <li><a href="${contextPath}/stats/incident/count"><span class="glyphicon glyphicon-th"></span><span class="text">事件统计</span></a></li>
                        <li><a href="${contextPath}/stats/change/count"><span class="glyphicon glyphicon-th"></span><span class="text">变更统计</span></a></li>
                        <li><a href="${contextPath}/stats/cms/count"><span class="glyphicon glyphicon-th"></span><span class="text">资产配置统计</span></a></li>
                        <li><a href="${contextPath}/stats/knowledge/count"><span class="glyphicon glyphicon-th"></span><span class="text">知识统计</span></a></li>
                        <li><a href="${contextPath}/stats/leadertask"><span class="glyphicon glyphicon-th"></span><span class="text">运维工作统计</span></a></li>
                        <li><a href="${contextPath}/stats/account/list"><span class="glyphicon glyphicon-th"></span><span class="text">台账</span></a></li>
                    </ul>
                </li>
                <li class="openable">
                    <a href="#">
                        <span class="isw-chat"></span><span class="text">工作流管理</span>                    
                    </a>
                    <ul>                    
                        <li><a href="${contextPath }/workflow/process/list"><span class="glyphicon glyphicon-th-large"></span><span class="text">流程列表</span></a></li>
                        <li><a href="${contextPath }//workflow/process/active-list"><span class="glyphicon glyphicon-th-large"></span><span class="text">启动流程</span></a></li>
                        <li><a href="${contextPath }/workflow/processinstance/running"><span class="glyphicon glyphicon-th-large"></span><span class="text">运行中的流程</span></a></li>
                        <li><a href="${contextPath }/workflow/task/list"><span class="glyphicon glyphicon-th-large"></span><span class="text">运行中的任务</span></a></li>
					    <li><a href="${contextPath }//workflow/processinstance/finished"><span class="glyphicon glyphicon-th-large"></span><span class="text">已结束流程</span></a></li>
                        <li><a href="${contextPath }/workflow/model/list"><span class="glyphicon glyphicon-th-large"></span><span class="text">模型工作区</span></a></li>
					</ul>
                </li>
                <li class="openable">
                    <a href="#">
                        <span class="isw-chat"></span><span class="text">系统管理</span>                    
                    </a>
                    <ul>                    
                        <li><a href="${contextPath}/user/list"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">用户管理</span></a></li>
                        <li><a href="${contextPath}/role/list"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">角色管理</span></a></li>
                        <li><a href="${contextPath }/system/syscode/list"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">数据字典</span></a></li>
                    </ul>
                </li> 
                <!-- 
                <c:forEach items="${menu1 }" var="mod1">
                <li id="node_${mod1.id }" class="openable">
                	<a href="${mod1.url }">
                        <span class="${mod1.styleClass }"></span><span class="text">${mod1.name }</span>
                    </a>
                    <ul>
                    <c:forEach items="${menu2 }" var="mod2">
                    <c:if test="${mod2.parent.id == mod1.id }">
                    	<li>
                    	 	<a href="${contextPath }${mod2.url }">
                                <span class="${mod2.styleClass }"></span><span class="text">${mod2.name }</span>
                            </a>                  
                        </li> 
                    </c:if>
                    </c:forEach>
                    </ul>
                </li>
                </c:forEach>
                -->
            </ul>
<script type='text/javascript' src='${contextPath }/resources/js/mymenu.js'></script> 
<script>
$(document).ready(function(){
	pm_getcount();
});
function pm_getcount()
{
	$.getJSON(ctx+"/workflow/task/getmytaskcount?t"+pm_random(),function(datas){
		if(datas.count=="0")
			return;
		$(".link_navPopMessages").text( datas.count );
	});
}
</script>
