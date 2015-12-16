<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->

    <title>添加类别</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>

    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <!-- <link type="text/css" rel="stylesheet" href="${contextPath }/resources/plupload/css/jquery-ui.min.css" /> -->
	<!-- <link type="text/css" rel="stylesheet" href="${contextPath }/resources/js/plugins/plupload/jquery.plupload.queue/css/jquery.plupload.queue.css" /> -->
	<link type="text/css" rel="stylesheet" href="${contextPath }/resources/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css" />

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

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.stack.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.resize.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-zh-CN.js' charset='utf-8'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>

    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/tagsinput/jquery.tagsinput.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>

    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/actions.js'></script>
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
            $(document).ready(function () {
            	$(".header").load("${contextPath }/header");
                $(".menu").load("${contextPath }/menu", function() {$("#node_${moduleId}").addClass("active");});
                $(".breadLine .buttons").load("${contextPath }/contentbuttons");

                $("#style").validationEngine({promptPosition : "topLeft", scroll: true});

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
                    <li><a href="#">保密检查项</a> <span class="divider">></span></li>
                    <li class="active">添加检查项</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">

                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="head clearfix">
                            <div class="isw-documents"></div>
                            <h1>类别信息录入</h1>
                        </div><c:url var="addAction" value="/checkitems/saveitems" ></c:url>
						<form:form action="${addAction}" commandName="style">
						<form:hidden path="code" value="NON"/>
                        <div class="block-fluid">
                          <div class="row-form clearfix">
                                <div class="col-md-2"><form:label path="name">检查项名称*</form:label></div>
                                <div class="col-md-7"><form:input path="name" class="validate[required,minSize[2],maxSize[30]] text-input" /><form:errors path="name" cssClass="error" /></div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="style.id">所属项</label>
                                </div>
                                <div class="col-md-7">
									<form:select path="style.id">
									<form:option value="${itemsAll.id }">-</form:option>
									<c:forEach items="${itemsAll.child}" var="styleOne">
										<form:option value="${styleOne.id }">${styleOne.name }</form:option>
										<c:forEach items="${styleOne.child}" var="styleTwo">
											<form:option value="${styleTwo.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${styleTwo.name }</form:option>
											<c:forEach items="${styleTwo.child}" var="styleThree">
												<form:option value="${styleThree.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${styleThree.name }</form:option>
												<c:forEach items="${styleThree.child}" var="styleFour">
													<form:option value="${styleFour.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${styleFour.name }</form:option>
													<c:forEach items="${styleFour.child}" var="styleFive">
														<form:option value="${styleFive.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| - ${styleFive.name }</form:option>
													</c:forEach>
												</c:forEach>
											</c:forEach>
										</c:forEach>
									</c:forEach>
									</form:select>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">
                                	<label for="order">排序</label>
                                </div>
                                <div class="col-md-7">
                                	<form:input path="order" class="validate[digits]"/>
                                </div>
                            </div>
							<div class="row-form clearfix">
                                <div class="col-md-2">
                                <label for="link">说明</label>
                                </div>
                                <div class="col-md-7">
                                <form:textarea path="desc"/>
                                </div>
                            </div>
                            <div class="footer tar">
                               <input type="submit" class="btn btn-primary center-block" value="提 交" />
                            </div>
                        </div>
                        </form:form>
                    </div>

                </div>

            </div>
            <!--workplace end-->
        </div>
    </div>

</body>

</html>
