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
               <li class="openable">
                    <a href="#">
                        <span class="isw-list"></span><span class="text">事件管理</span>
                    </a>
                    <ul>
                        <li>
                            <a href="event_new.html">
                                <span class="glyphicon glyphicon-plus"></span><span class="text">创建事件</span>
                            </a>                  
                        </li> 
                        <li>
                            <a href="event_control.html">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">事件控制台</span>
                            </a>    
                            <a href="event_control.html" class="caption yellow link_navPopMessages">9</a>              
                        </li>    
                        <li>
                            <a href="event.html">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">事件信息管理</span>
                            </a>    
                        </li>                    
                        <li>
                            <a href="event_chart.html">
                                <span class="glyphicon glyphicon-chevron-right"></span><span class="text">报表</span>
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
                            <a href="change_new.html">
                                <span class="glyphicon glyphicon-th"></span><span class="text">创建变更请求</span>
                            </a>                  
                        </li>    
                        <li>
                            <a href="change_control.html">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">变更控制台</span>
                            </a>    
                            <a href="event.html" class="caption yellow link_navPopMessages">1</a>               
                        </li>    
                        <li>
                            <a href="change.html">
                                <span class="glyphicon glyphicon-chevron-right"></span><span class="text">变更信息管理</span>
                            </a>                  
                        </li>                  
                        <li>
                            <a href="change_chart.html">
                                <span class="glyphicon glyphicon-chevron-right"></span><span class="text">报表</span>
                            </a>                  
                        </li>                           
                    </ul> 
                </li>                                                           
                <li class="openable">
                    <a href="#">
                        <span class="isw-graph"></span><span class="text">问题管理</span>
                    </a>
                    <ul>
                        <li>
                            <a href="problem_new.html">
                                <span class="glyphicon glyphicon-th"></span><span class="text">创建问题</span>
                            </a>                  
                        </li>   
                        <li>
                            <a href="problem_control.html">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">问题管理控制台</span>
                            </a>                  
                        </li>  
                        <li>
                            <a href="problem.html">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">问题信息管理</span>
                            </a>                  
                        </li>                    
                        <li>
                            <a href="problem_chart.html">
                                <span class="glyphicon glyphicon-chevron-right"></span><span class="text">报表</span>
                            </a>                  
                        </li>                           
                    </ul> 
                </li>                                    
                <li class="openable">
                    <a href="#">
                        <span class="isw-text_document"></span><span class="text">资产管理</span>
                    </a>
                    <ul>
                        <li>
                            <a href="${contextPath }/Asset/init">
                                <span class="glyphicon glyphicon-th"></span><span class="text">新资产</span>
                            </a>                  
                        </li>    
                        <li>
                            <a href="${contextPath }/Asset/list">
                                <span class="glyphicon glyphicon-th-large"></span><span class="text">资产信息管理</span>
                            </a>                  
                        </li>                    
                        <li>
                            <a href="asset_chart.html">
                                <span class="glyphicon glyphicon-chevron-right"></span><span class="text">报表</span>
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
                        <span class="isw-documents"></span><span class="text">配置管理</span>
                    </a>
                    <ul>
                        <li>
                            <a href="config.html">
                                <span class="glyphicon glyphicon-info-sign"></span><span class="text">配置信息管理</span>
                            </a>       
                        </li>
                        <li>
                            <a href="config_chart.html">
                                <span class="glyphicon glyphicon-user"></span><span class="text">报表</span>
                            </a>                  
                        </li>                                                         
                    </ul>                                
                </li>          
                <li class="openable">
                    <a href="#">
                        <span class="isw-zoom"></span><span class="text">终端管理</span>                    
                    </a>
                    <ul>
                        <li>
                            <a href="computer.html">
                                <span class="glyphicon glyphicon-picture"></span><span class="text">终端信息管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="computer_check.html">
                                <span class="glyphicon glyphicon-pencil"></span><span class="text">终端健康管理</span>
                            </a>
                        </li>             
                        <li>
                            <a href="computer_chart.html">
                                <span class="glyphicon glyphicon-share"></span><span class="text">报表</span>
                            </a>
                        </li>                                                                    
                    </ul>
                </li> 
                <li class="openable">
                    <a href="#">
                        <span class="isw-cancel"></span><span class="text">合同管理</span>                    
                    </a>
                    <ul>                    
                        <li><a href="contract_new.html"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">新合同</span></a></li>
                        <li><a href="contract.html"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">合同信息管理</span></a></li>
                    </ul>
                </li>  
                <li class="openable">
                    <a href="#">
                        <span class="isw-list"></span><span class="text">知识库管理</span>                    
                    </a>
                    <ul>                    
                        <li><a href="knowledge_new.html"><span class="glyphicon glyphicon-plus"></span><span class="text">新知识</span></a></li>
                        <li><a href="knowledge_control.html"><span class="glyphicon glyphicon-list-alt"></span><span class="text">知识管理控制台</span></a></li>
                        <li><a href="knowledge.html"><span class="glyphicon glyphicon-list-alt"></span><span class="text">知识库</span></a></li>
                        <li><a href="knowledge_chart.html"><span class="glyphicon glyphicon-list-alt"></span><span class="text">知识统计</span></a></li>
                    </ul>
                </li> 
                <li class="openable">
                    <a href="#">
                        <span class="isw-chat"></span><span class="text">系统管理</span>                    
                    </a>
                    <ul>                    
                        <li><a href="users.html"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">账户管理</span></a></li>
                        <li><a href="rules.html"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">系统规则</span></a></li>
                    </ul>
                </li>   
                <li class="openable">
                    <a href="#">
                        <span class="isw-documents"></span><span class="text">数据字典</span>                    
                    </a>
                    <ul>                    
                        <li><a href="users.html"><span class="glyphicon glyphicon-warning-sign"></span><span class="text">部门</span></a></li>
                        <li><a href="rules.html"><span class="glyphicon glyphicon-user"></span><span class="text">员工</span></a></li>
                        <li><a href="${contextPath }/manufacturer/list"><span class="glyphicon glyphicon-share"></span><span class="text">厂商</span></a></li>
                    </ul>
                </li>  
            </ul>
<script type='text/javascript' src='${contextPath }/resources/js/mymenu.js'></script> 