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
                            <a href="${contextPath }/workflow/task/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">待办任务</span>
                            </a>                  
                        </li>
                        <li>
<<<<<<< HEAD
=======
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
>>>>>>> master
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
<<<<<<< HEAD
                        <li>
                            <a href="${contextPath}/incident/add">
                                <span class="glyphicon glyphicon-plus"></span><span class="text">创建新事件</span>
                            </a>                  
                        </li> 
                        <li>
                            <a href="${contextPath }/incident/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">事件控制台</span>
                            </a>    
                            <a href="event_control.html" class="caption yellow link_navPopMessages">9</a>              
                        </li>    
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">事件信息管理</span>
                            </a>    
                        </li>                    
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-chevron-right"></span><span class="text">事件统计</span>
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
                                <span class="glyphicon glyphicon-th"></span><span class="text">创建变更请求</span>
                            </a>                  
                        </li>    
                        <li>
                            <a href="${contextPath }/change/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">变更控制台</span>
                            </a>    
                            <a href="event.html" class="caption yellow link_navPopMessages">1</a>               
                        </li>    
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-chevron-right"></span><span class="text">变更信息管理</span>
                            </a>                  
                        </li>                  
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-chevron-right"></span><span class="text">变更统计</span>
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
                        <span class="isw-cancel"></span><span class="text">运维工作管理</span>                    
                    </a>
                    <ul>
                    	<li><a href="${contextPath }/leadertask/list"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">领导交办</span></a></li>                    
                        <li><a href="${contextPath }/record/list"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">运维记录管理</span></a></li>
                        <li><a href="${contextPath }/contract/list"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">合同信息管理</span></a></li>
                    </ul>
                </li>  
                <li class="openable">
                    <a href="#">
                        <span class="isw-list"></span><span class="text">知识库管理</span>                    
                    </a>
                    <ul>                    
                        <li><a href="${contextPath}/knowledge/add"><span class="glyphicon glyphicon-plus"></span><span class="text">新知识</span></a></li>
                        <li><a href="${contextPath}/knowledge/list"><span class="glyphicon glyphicon-list-alt"></span><span class="text">知识管理控制台</span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span><span class="text">知识统计</span></a></li>
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
                        <li><a href="${contextPath }/manufacturer/list"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">数据字典</span></a></li>
                    </ul>
                </li>    
=======
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
>>>>>>> master
            </ul>
<script type='text/javascript' src='${contextPath }/resources/js/mymenu.js'></script> 
