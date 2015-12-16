<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <div class="breadLine">            
                <div class="arrow"></div>
                <div class="adminControl active">
                    <sec:authentication property="name"/>
                </div>
            </div>

            <div class="admin">
                <div class="image">
                    <img src="${contextPath }/resources/img/users/user.jpg" class="img-thumbnail"/>                
                </div>
                <ul class="control">                
                    <li><span class="glyphicon glyphicon-comment"></span> <a href="${contextPath }/message/list">消息</a></li>
                    <li><span class="glyphicon glyphicon-cog"></span> <a href="#">设置</a></li>
                    <li><span class="glyphicon glyphicon-share-alt"></span> <a href="${contextPath }/logout">退出</a></li>
                </ul>
                <div class="info">
                    <span>欢迎回来！上次登录:<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${lastLogin }" /></span>
                </div>
            </div>

            <ul class="navigation">            
                <li class="openable">
                    <a href="#">
                        <span class="isw-attachment"></span><span class="text">快速链接</span>
                    </a>
                    <ul>
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
