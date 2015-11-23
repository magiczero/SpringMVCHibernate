<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
                <li>
                        <a href="#" class="link_bcPopupList"><span class="glyphicon glyphicon-user"></span><span class="text">用户1</span></a>

                        <div id="bcPopupList" class="popup">
                            <div class="head clearfix">
                                <div class="arrow"></div>
                                <span class="isw-users"></span>
                                <span class="name">用户列表</span>
                            </div>
                            <div class="body-fluid users">

                                <div class="item clearfix">
                                    <div class="image"><a href="#"><img src="${contextPath }/resources/img/users/aqvatarius_s.jpg" width="32"/></a></div>
                                    <div class="info">
                                        <a href="#" class="name">杨海鹏</a>                                    
                                        <span>在线</span>
                                    </div>
                                </div>

                                <div class="item clearfix">
                                    <div class="image"><a href="#"><img src="${contextPath }/resources/img/users/olga_s.jpg" width="32"/></a></div>
                                    <div class="info">
                                        <a href="#" class="name">刘坤</a>                                
                                        <span>在线</span>
                                    </div>
                                </div>                        

                                <div class="item clearfix">
                                    <div class="image"><a href="#"><img src="${contextPath }/resources/img/users/alexey_s.jpg" width="32"/></a></div>
                                    <div class="info">
                                        <a href="#" class="name">王鹏宇</a>  
                                        <span>在线</span>
                                    </div>
                                </div>                              

                                <div class="item clearfix">
                                    <div class="image"><a href="#"><img src="${contextPath }/resources/img/users/dmitry_s.jpg" width="32"/></a></div>
                                    <div class="info">
                                        <a href="#" class="name">刘路希</a>                                    
                                        <span>在线</span>
                                    </div>
                                </div>                         

                                <div class="item clearfix">
                                    <div class="image"><a href="#"><img src="${contextPath }/resources/img/users/helen_s.jpg" width="32"/></a></div>
                                    <div class="info">
                                        <a href="#" class="name">王小戈</a>                                                                        
                                    </div>
                                </div>                                  

                                <div class="item clearfix">
                                    <div class="image"><a href="#"><img src="${contextPath }/resources/img/users/alexander_s.jpg" width="32"/></a></div>
                                    <div class="info">
                                        <a href="#" class="name">李伟</a>                                                                        
                                    </div>
                                </div>                                  

                            </div>
                            <div class="footer">
                                <button class="btn btn-default" type="button">添加新用户</button>
                                <button class="btn btn-danger link_bcPopupList" type="button">关闭</button>
                            </div>
                        </div>                    

                    </li>                
                    <li>
                        <a href="#" class="link_bcPopupSearch"><span class="glyphicon glyphicon-search"></span><span class="text">搜索</span></a>

                        <div id="bcPopupSearch" class="popup">
                            <div class="head clearfix">
                                <div class="arrow"></div>
                                <span class="isw-zoom"></span>
                                <span class="name">搜索</span>
                            </div>
                            <div class="body search">
                                <input type="text" placeholder="关键字..." name="search"/>
                            </div>
                            <div class="footer">
                                <button class="btn btn-default" type="button">搜索</button>
                                <button class="btn btn-danger link_bcPopupSearch" type="button">关闭</button>
                            </div>
                        </div>                
                    </li>
<script type="text/javascript">
    $(document).ready(function () {
        $(".link_bcPopupList").click(function () {
            if ($("#bcPopupList").is(":visible")) {
                $("#bcPopupList").fadeOut(200);
            } else {
                $("#bcPopupList").fadeIn(300);
            }
            return false;
        });

        $(".link_bcPopupSearch").click(function () {
            if ($("#bcPopupSearch").is(":visible")) {
                $("#bcPopupSearch").fadeOut(200);
            } else {
                $("#bcPopupSearch").fadeIn(300);
            }
            return false;
        });
    });
</script>