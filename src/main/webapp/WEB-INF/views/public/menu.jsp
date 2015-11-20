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
                            <a href="${contextPath }/workflow/process/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">流程列表</span>
                            </a>                  
                        </li>
                        <li>
                            <a href="${contextPath }//workflow/process/active-list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">启动流程</span>
                            </a>                  
                        </li>
                        <li>
                            <a href="${contextPath }/workflow/processinstance/running">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">运行中的流程</span>
                            </a>                  
                        </li>
                        <li>
                            <a href="${contextPath }/workflow/task/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">待办任务</span>
                            </a>                  
                        </li>
                        <li>
                            <a href="${contextPath }/workflow/processinstance/finished">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">已结束流程</span>
                            </a>                  
                        </li>
                        <li>
                            <a href="${contextPath }/workflow/model/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">模型工作区</span>
                            </a>                  
                        </li>
                        <li>
                            <a href="board4engineer.html">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">工程师控制台</span>
                            </a>                  
                        </li>    
                        <li>
                            <a href="board4manager.html">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">运维主管控制台</span>
                            </a>                  
                        </li>                    
                        <li>
                            <a href="board4leader.html">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">IT主管控制台</span>
                            </a>                  
                        </li>      
                        <li>
                            <a href="mywork.html">
                                <span class="glyphicon glyphicon-briefcase"></span><span class="text">我的工作</span>
                            </a>
                            <a href="event.html" class="caption yellow link_navPopMessages">2</a>                  
                        </li>  
                        <li>
                            <a href="inspection.html">
                                <span class="glyphicon glyphicon-hdd"></span><span class="text">日常巡检</span>
                            </a>                  
                        </li> 
                        <li>
                            <a href="task_leader.html">
                                <span class="glyphicon glyphicon-file"></span><span class="text">领导交办</span>
                            </a>                  
                        </li>
                        <li>
                            <a href="document.html">
                                <span class="glyphicon glyphicon-list-alt"></span><span class="text">文档管理</span>
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
